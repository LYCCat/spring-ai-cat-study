package com.zzcat.springaicatstudy.controller;

import com.zzcat.springaicatstudy.repository.ChatHistoryRepository;
import com.zzcat.springaicatstudy.tools.CourseTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class CustomerServiceController {
    private  final ChatClient chatClient;
    private  final ChatHistoryRepository chatHistoryRepository;
    private final ChatClient serviceChatClient;
    private final ChatMemory chatMemory;
    private  final  CourseTools courseTools;


    @RequestMapping("/service")
    public String service(String prompt,String chatId){
        //1保存会话id
        chatHistoryRepository.save("service",chatId);
        //2调用chatClient进行对话
        return  serviceChatClient.prompt()
                .user(prompt)
                .advisors(a->a.param("conversationId", chatId))
                .tools(courseTools)
                .call()
                .content();
    }
}
