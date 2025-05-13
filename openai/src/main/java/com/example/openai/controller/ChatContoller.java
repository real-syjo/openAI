package com.example.openai.controller;

import com.example.openai.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatContoller {

    private final ChatService chatService;

    public ChatContoller(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message")String message){
        return chatService.chat(message);
    }

    @GetMapping("/chatt")
    public String chatt(@RequestParam("message")String message){
        return chatService.chat(message);
    }



}
