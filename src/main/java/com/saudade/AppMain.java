package com.saudade;

public class AppMain {


        public static void main(String[] args){
        AppComponent component = DaggerAppComponent.create();
        ChatGptRepository chatGPT = component.getChatGPT();
        String response = chatGPT.sendMessage("give me 3 example Brazilian Portuguese " +
                "sentences with the word 'apenas' in them.");
        System.out.println(response);
    }
}
