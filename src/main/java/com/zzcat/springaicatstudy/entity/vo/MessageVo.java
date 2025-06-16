package com.zzcat.springaicatstudy.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;

import java.io.Serializable;
@NoArgsConstructor
@Data
public class MessageVo implements Serializable {
    private String role;
    private  String content;
    public  MessageVo(Message message){
        this.role=switch (message.getMessageType()){
            case USER -> "user";
            case ASSISTANT-> "assistant";
            case SYSTEM -> "system";
            default -> "";
        };
        this.content=message.getText();
    }
}
