package org.javaacademy.wonder_field;

import java.util.Scanner;

public class Game {
    private static final int players = 3;
    private static final int rounds = 4;
    private static final int roundsOfGroup = 3;
    private static final int indexOfFinalRound = 3;

    private final String[] questionList;
    private final String[] answerList;
    Game(){
        questionList = new String[rounds];
        answerList = new String[rounds];
    }
    public void setNewQuestion(int numOfQuestion, String question, String answer){
        questionList[numOfQuestion-1] = question;
        answerList[numOfQuestion-1] = answer.toUpperCase();
    }

    public void printAllQuestionForMock(){
        for (int i = 0; i < Game.rounds-1; i++) {
            System.out.println(questionList[i]);
            System.out.println(answerList[i]);
        }
    }

    public static Game init(Scanner scanner) {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        Game newGame = new Game();
        for (int i = 1; i <= Game.rounds; i++) {
            System.out.printf("Введите вопрос #%d \n", i);
            String newQuestion = scanner.nextLine();
            System.out.print("Введите ответ на этот вопрос \n");
            String newAnswer = scanner.nextLine();
            newGame.setNewQuestion(i, newQuestion, newAnswer);
        }

        return newGame;
     }

    //Мок-объект. Заполняет список вопросов и ответов
     public static Game initMock() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        Game newGame = new Game();
         newGame.setNewQuestion(1, "Что не губит людей, в отличии от воды?", "Пиво");
         newGame.setNewQuestion(2, "Что мешает бетон, пока бригадир жрёт самогон?", "Бетономешалка");
         newGame.setNewQuestion(3, "Птица семейства Ястребиные?", "Орел");
         newGame.setNewQuestion(4, "Имя первого косманавта?", "Юра");

         newGame.printAllQuestionForMock();
        return newGame;
    }

}
