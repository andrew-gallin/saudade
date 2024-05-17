package com.saudade.dto;


import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
public interface TranslationPair {
    String original();

    String translation();
    boolean successfulTranslation();

    int translationAttempts();

}
