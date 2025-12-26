package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Note;
import org.example.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@RestController  //Response data interface
@RequestMapping("/notes")  // 通过反射调用方法
public class NoteController {

    @Autowired // 自动装配（依赖注入)
    private NoteMapper noteMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();// 转字符串
    @GetMapping   //query all note
    public List<Note> getAll(){
        // 泛型，是输出return的值
        // 每次有人访问则访问量+1  key里的是redis命令
        redisTemplate.opsForValue().increment("note:view:total");
        String views = redisTemplate.opsForValue().get("note:view:total");
        System.out.println("当前访问量:"+views);
        return noteMapper.selectList(null);
        // 解释一下，这里opsforvalue就是操作，对着键值模式，key是自己取名的，按规范是： increment对应INCR自增操作
        // get就是打印到控制台上看
        // 最后返回的是没有条件约束的参数（所有参数）noteMapper是接口
    }
    @PostMapping// add new note
    public String add(@RequestBody Note note){
        try {
            String noteJson = objectMapper.writeValueAsString(note);
            // 转JSON字符串
            kafkaTemplate.send("note-topic",noteJson);
            System.out.println("已发送kafka:"+noteJson);
            return "等待后台处理";
        } catch (Exception e){
            return "发送失败";
        }
    }
}
