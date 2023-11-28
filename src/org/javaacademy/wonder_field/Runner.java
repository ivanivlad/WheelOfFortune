package org.javaacademy.wonder_field;

public class Runner {

    public static void runGame() {
        Game newGame = new Game();
        try {
            newGame.init();
        } catch (InterruptedException e){
            System.out.println("Игра была прервана");
            return;
        }
        System.out.println("\n".repeat(50));
        newGame.start();
    }
}
