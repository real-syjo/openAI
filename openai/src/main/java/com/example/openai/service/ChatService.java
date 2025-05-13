package com.example.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public String chat(String message){

        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .call()             //호출
                .content();         //요청 정보를 받는 부분
    }
}
