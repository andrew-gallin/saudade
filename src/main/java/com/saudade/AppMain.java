package com.saudade;

public class AppMain {


    public static void main(String[] args){
        AppComponent component = DaggerAppComponent.create();
        ChatGptRepository chatGPT = component.getChatGPT();
        String response = chatGPT.sendMessage("hey");
        System.out.println(response);
    }
}
