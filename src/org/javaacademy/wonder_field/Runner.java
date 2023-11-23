package org.javaacademy.wonder_field;

import java.util.Scanner;

public class Runner {

    public static Scanner scanner = new Scanner(System.in);
    public static void runGame() {

        Game newGame = Game.init(scanner);

        System.out.println("Иницализация закончена, игра начнется через");

        try {
            int i = 5;
            while(i>0) {
                System.out.println(i);
                Thread.sleep(1000);
                i--;
            }

        } catch (InterruptedException e){
            System.out.println("Игра была прервана");
            return;
        }

        System.out.println("\n".repeat(50));
    }
}
