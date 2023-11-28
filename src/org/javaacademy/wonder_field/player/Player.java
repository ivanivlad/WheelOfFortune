package org.javaacademy.wonder_field.player;

import java.util.Scanner;

public class Player {
    public String name;
    public String city;

    public Player(String name, String city){
        this.name = name;
        this.city = city;
    }

    public PlayerAnswer sayLetter(Scanner scanner){
        String newLetter = scanner.nextLine();
        System.out.printf("Игрок %s: буква %s \n", name, newLetter);
        return new PlayerAnswer(PlayerAnswer.TypeAnswer.LETTER, newLetter.toUpperCase());
    }

    public PlayerAnswer sayWord(Scanner scanner){
        String newWord = scanner.nextLine();
        System.out.printf("Игрок %s: слово %s \n", name, newWord);
        return new PlayerAnswer(PlayerAnswer.TypeAnswer.WORD, newWord.toUpperCase());
    }

    public PlayerAnswer nextAttempt(Scanner scanner){

        System.out.printf("Ход игрока %s, %s\n", name, city);

        while (true) {

            System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");

            String newAnswer = scanner.nextLine();

            if (newAnswer.equals("б")) {
                return sayLetter(scanner);
            } else if (newAnswer.equals("с")) {
                return sayWord(scanner);
            } else {
                System.out.println("Некорректное значение, введите 'б' или 'с'");
            }
        }
    }

}
