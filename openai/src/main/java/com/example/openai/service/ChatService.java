package com.example.openai.service;

import com.example.openai.entity.Answer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {

        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .call()             //호출
                .content();         //요청 정보를 받는 부분
    }

    public String chatMessage(String message) {
        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .call()             //호출
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    public String chatPlace(String subject,String tone,String message) {
        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .system(sp->sp.param("subject",subject)
                                              .param("tone",tone)
                )
                .call()             //호출
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    public ChatResponse chatJson( String message) {
        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .call()             //호출
                .chatResponse();
    }

    public Answer chatObject(String message) {
        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .call()             //호출
                .entity(Answer.class);
    }

    private final String recipeTemplate = """
           Answer for {foodName} for {question}? """;

    public Answer recipe(String foodName, String question) {
        return chatClient.prompt()  //프롬프트 생성
                .user(userSpec -> userSpec.text(recipeTemplate)
                        .param("foodName", foodName)
                        .param("question", question))      //사용자메시지
                .call()             //호출
                .entity(Answer.class);
    }

    public List<String> chatList(String message) {
        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .call()             //호출
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    public Map<String, String> chatMap(String message) {
        return chatClient.prompt()  //프롬프트 생성
                .user(message)      //사용자메시지
                .call()             //호출
                .entity(new ParameterizedTypeReference<Map<String, String>>() {});
    }
}