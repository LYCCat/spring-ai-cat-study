package com.zzcat.springaicatstudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class GameController {
    private ChatMemory chatMemory;
    private  final ChatClient chatClient;
    private final ChatClient gameChatClient;

    @RequestMapping(value = "/game",produces="text/html;charset=UTF-8")
    public Flux<String> chat(String prompt,String chatId){
        return gameChatClient.prompt()
                .user(prompt)
                .advisors(a->a.param(chatMemory.CONVERSATION_ID,chatId))
                .stream()
                .content();
    }
}
