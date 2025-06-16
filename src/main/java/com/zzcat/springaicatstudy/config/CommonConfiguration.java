package com.zzcat.springaicatstudy.config;

import com.zzcat.springaicatstudy.constants.SystemConstants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonConfiguration {

    @Bean
    public InMemoryChatMemoryRepository inMemoryChatMemoryRepository() {
        return new InMemoryChatMemoryRepository();
    }
    @Bean
    public ChatMemory chatMemory(InMemoryChatMemoryRepository inMemoryChatMemoryRepository) {
        return  MessageWindowChatMemory.builder().chatMemoryRepository(inMemoryChatMemoryRepository).maxMessages(Integer.MAX_VALUE).build();
    }

    //new chatmemory
    @Bean
    public ChatClient chatClient(OllamaChatModel model,ChatMemory chatMemory){
        return ChatClient
                .builder(model) //创建chatclien工厂实例
                .defaultSystem("你是一个热心，可爱的智能助手，你的名字叫小咪，请以小咪的身份回答问题")
                .defaultAdvisors(new SimpleLoggerAdvisor())//配置日志记录器
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()) //配置聊天内存
                .build(); //创建chatclient实例
    }
    @Bean
    public ChatClient gameChatClient(OpenAiChatModel openAiChatModel,ChatMemory chatMemory){
        return ChatClient.builder(openAiChatModel)
                .defaultSystem(SystemConstants.GAME_SYSTEM_PROMPT)
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }


}
