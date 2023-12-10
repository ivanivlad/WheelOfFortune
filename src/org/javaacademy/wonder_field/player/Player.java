package org.javaacademy.wonder_field.player;

import org.javaacademy.wonder_field.Game;

public class Player {
    private final String name;

    private final String city;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public PlayerAnswer sayLetter() {
        String newLetter = Game.scanner.nextLine();
        System.out.printf("Игрок %s: буква %s \n", name, newLetter);
        if (!newLetter.matches("[а-я][A-Z]")){
            System.out.println("Разрешены только русские буквы");
            return new PlayerAnswer(TypeAnswer.LETTER, "");
        }
        return new PlayerAnswer(TypeAnswer.LETTER, newLetter.toUpperCase());
    }

    public PlayerAnswer sayWord() {
        String newWord = Game.scanner.nextLine();
        System.out.printf("Игрок %s: слово %s \n", name, newWord);
        if (!newWord.matches("[а-я][A-Z]")){
            System.out.println("Слово должно содержать только русские буквы");
            return new PlayerAnswer(TypeAnswer.WORD, "");
        }
        return new PlayerAnswer(TypeAnswer.WORD, newWord.toUpperCase());
    }

    public PlayerAnswer nextAttempt() {
        System.out.printf("Ход игрока %s, %s\n", name, city);
        while (true) {
            System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");
            String newAnswer = Game.scanner.nextLine();
            if (newAnswer.equals("б")) {
                return sayLetter();
            } else if (newAnswer.equals("с")) {
                return sayWord();
            } else {
                System.out.println("Некорректное значение, введите 'б' или 'с'");
            }
        }
    }

}
