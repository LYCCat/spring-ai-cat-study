package com.zzcat.springaicatstudy.controller;

import com.zzcat.springaicatstudy.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("ai")
public class ChatController {

    private final ChatClient chatClient;
    private  final ChatMemory chatMemory;
    private  final ChatHistoryRepository chatHistoryRepository;
//
//    @RequestMapping("/chat")
//    public  String chat(@RequestParam("prompt") String prompt) {
//        return chatClient.prompt()
//                .user(prompt)
//                .call()
//                .content();
//    }
    //声明响应的字符编码，否则出现乱码 流式对话
    @RequestMapping(value = "/chat",produces = "text/html;charset=UTF-8")
    public Flux<String> chatStream(String prompt,String chatId) {
        chatHistoryRepository.save("chat", chatId);
        return chatClient.prompt()
                .user(prompt)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream()
                .content();
    }
   
}
