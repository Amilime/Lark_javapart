package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data // get/set
@TableName("note") // note table connect with this class
public class Note {
    @TableId(type =  IdType.AUTO) //self add primary key
    private Long id;
    private String title;
    private String content;
    private String fileUrl;
    private LocalDateTime createTime;
}