package org.example.controller;

import org.example.common.Result;
import org.example.entity.Document;
import org.example.mapper.DocumentMapper;
import org.example.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    private DocumentMapper documentMapper;

    // 这是一个【受保护】的接口，必须带 Token 才能访问
    @PostMapping("/create")
    public Result<Map<String, Object>> createDoc(@RequestBody Document doc) {

        // 【验证成果】这里直接从 UserContext 拿 ID！
        // 如果拦截器工作正常，这里一定能拿到值。
        // 如果拦截器没工作，或者 Token 没传，这里会报错或者拿到 null。
        Long currentUserId = UserContext.getUserId();

        // 自动填入 ownerId，不再需要前端传了
        doc.setOwnerId(currentUserId);

        // 补全其他信息
        doc.setVersion(1);
        doc.setCreateTime(LocalDateTime.now());
        doc.setUpdateTime(LocalDateTime.now());

        documentMapper.insert(doc);

        Map<String, Object> map = new HashMap<>();
        map.put("docId", doc.getId());
        return Result.success(map);
    }

    // 获取列表接口
    @GetMapping("/list")
    public Result<List<Document>> listDocs() {
        return Result.success(documentMapper.selectList(null));
    }
}