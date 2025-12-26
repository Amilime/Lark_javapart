package org.example.consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Note;
import org.example.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
// 这里是把卡夫卡中消费者收到的add消息传入实际数据库的工厂。
@Component // 被spring管理组件
public class NoteConsumer {

    @Autowired
    private NoteMapper noteMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 监听note-topic
    @KafkaListener(topics = "note-topic")
    public void handleNote(String message) {
        System.out.println("消费者收到kafka"+message);

        try{
            Note note = objectMapper.readValue(message,Note.class);
            noteMapper.insert(note);// 这里来写入数据库
            System.out.println("消费者已入库,ID:"+note.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
