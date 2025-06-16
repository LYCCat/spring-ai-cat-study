package com.zzcat.springaicatstudy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//会话记忆：是指让大模型记住每一轮对话的内容，不至于前一句刚问完，下一句就忘了。
//会话历史：是指要记录总共有多少不同的对话
@Repository
@RequiredArgsConstructor
public class InmemoryChatHistoryRepository implements ChatHistoryRepository{
    private  final Map<String,List<String>> chatHistory=new ConcurrentHashMap<>();

    @Override
    public void save(String type, String chatId) {
//        if(!chatHistory.containsKey(type)) {
//            chatHistory.put(type,new ArrayList<>());
//        }
//        List<String> chatIds = chatHistory.get(type);
        List<String> chatIds = chatHistory.computeIfAbsent(type, k -> new ArrayList<>());
        if(chatIds.contains(chatId)){
            return; //如果已经存在，则不添加
        }
        chatIds.add(chatId);

    }

    @Override
    public List<String> getChatIds(String type) {
//        List<String> chatIds = chatHistory.get(type);
//        return chatIds==null?List.of():chatIds;
        return  chatHistory.getOrDefault(type, List.of());

    }
}
