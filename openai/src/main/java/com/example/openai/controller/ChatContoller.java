package com.example.openai.controller;

import com.example.openai.entity.Answer;
import com.example.openai.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/chatMessage")
    public String chatMessage(@RequestParam("message")String message){
        return chatService.chatMessage(message);
    }

    @GetMapping("/chatPlace")
    public String chatPlace(@RequestParam("subject")String subject,
                            @RequestParam("tone")String tone,
                            @RequestParam("message")String message){
        return chatService.chatPlace(subject, tone, message);
    }

    @GetMapping("/chatJson")
    public ChatResponse chatPlace(@RequestParam("message")String message){
        return chatService.chatJson(message);
    }

    @GetMapping("/chatObject")
    public Answer chatObject(@RequestParam("message")String message){
        return chatService.chatObject(message);
    }

    @GetMapping("/recipe")
    public Answer recipe(String foodName, String question){
        return chatService.recipe(foodName, question);
    }

    @GetMapping("/chatList")
    public List<String> chatList(String message){
        return chatService.chatList(message);
    }

    @GetMapping("/chatMap")
    public Map<String, String> chatMap(@RequestParam(value="message", defaultValue ="국가와 그 수도를 5개만 만들어줘 ?") String message){
        return chatService.chatMap(message);
    }
}