package org.javaacademy.wonder_field;

import java.util.Arrays;

public class Tableau {
    private static final Character UNKNOWN_SYMBOL = '_';
    String correctAnswer;
    char[] letters;

    public void init(String Answer){
        correctAnswer = Answer;
        letters = UNKNOWN_SYMBOL.toString().repeat(Answer.length()).toCharArray();
    }

    public void printTableau(){
        if (attributesIsEmpty()){
            return;
        }
        for (char letter: letters) {
            System.out.print(letter);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public void openLetter(String letter){
        if (attributesIsEmpty()){
            return;
        }
        int indexOf = -1;
        while (correctAnswer.indexOf(letter, indexOf + 1) != -1)
        {
            indexOf = correctAnswer.indexOf(letter, indexOf + 1);
            letters[indexOf] = correctAnswer.charAt(indexOf);
        }
    }

    public void openWord(){
        letters = correctAnswer.toCharArray();
    }

    public boolean hasUnknownLetters(){
        return Arrays.binarySearch(letters, UNKNOWN_SYMBOL) != -1;
    }

    public boolean attributesIsEmpty(){
        return letters.length == 0 || correctAnswer.isEmpty();
    }
}
