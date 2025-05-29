package com.example.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {


    @Value("classpath:/prompt.txt")
    private Resource resource;


    //chatClientBuilder :: key가 자동으로 들어옴 
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){
                                                    //system Massage(LLM역할부여)
       // return chatClientBuilder.defaultSystem(resource).build();
        return chatClientBuilder.build();
    }
}
