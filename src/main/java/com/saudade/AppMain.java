package com.saudade;

import com.saudade.dto.ModifiableTranslationPair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AppMain {


        public static void main(String[] args){
        AppComponent component = DaggerAppComponent.create();

        ChatGptRepository chatGPT = component.getChatGPT();
//        String response = chatGPT.sendMessage("give me 3 example Brazilian Portuguese " +
//                "sentences with the word 'apenas' in them.");

//        String[] lines =  response.split("\\r?\\n");

        String[] lines = {
                        "1. Eu posso ir à festa apenas se terminar todos os meus deveres de casa.",
                        "2. Ela quer apenas um pedaço de bolo, não está com fome.",
                        "3. O filme é apenas para maiores de 18 anos, não é adequado para crianças."
                };

        List<ModifiableTranslationPair> translationPairs = Arrays.stream(lines)
                .map(line -> ModifiableTranslationPair.create().setOriginal(line))
                .collect(Collectors.toList());

        translationPairs.forEach(pair -> {
            System.out.println(pair.original());
        });

    }
}
