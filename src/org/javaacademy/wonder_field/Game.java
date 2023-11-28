package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.Player;
import org.javaacademy.wonder_field.player.PlayerAnswer;

import java.util.Scanner;

public class Game {
    private static final int PLAYERS_TOTAL = 3;
    private static final int ROUNDS_TOTAL = 4;
    private static final int ROUNDS_OF_GROUP = 3;
    private static final int INDEX_OF_FINAL_ROUND = 3;
    public Scanner scanner;
    private final String[] questionList;
    private final String[] answerList;
    private final Tableau tableau;
    private final Yakubovich yakubovich;
    private final Player[] winners;

    Game(){
        questionList = new String[ROUNDS_TOTAL];
        answerList = new String[ROUNDS_TOTAL];
        yakubovich = new Yakubovich();
        tableau = new Tableau();
        winners = new Player[ROUNDS_OF_GROUP];
        scanner = new Scanner(System.in);
    }

    public static boolean isFinalRound(int round){
        return round == INDEX_OF_FINAL_ROUND;
    }

    public void setNewQuestion(int numOfQuestion, String question, String answer){
        questionList[numOfQuestion - 1] = question;
        answerList[numOfQuestion - 1] = answer.toUpperCase();
    }

    public void printAllQuestionForMock(){
        for (int i = 0; i < Game.ROUNDS_TOTAL; i++) {
            System.out.println(questionList[i]);
            System.out.println(answerList[i]);
        }
    }

    public void init() throws InterruptedException {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");

        for (int i = 1; i <= Game.ROUNDS_TOTAL; i++) {
            System.out.printf("Введите вопрос #%d \n", i);
            String newQuestion = scanner.nextLine();
            System.out.print("Введите ответ на этот вопрос \n");
            String newAnswer = scanner.nextLine();
            setNewQuestion(i, newQuestion, newAnswer);
        }

        System.out.println("Иницализация закончена, игра начнется через ...");

        int i = 5;
        while( i > 0) {
            System.out.println(i);
            Thread.sleep(1000);
            i--;
        }
     }

     //Мок-объект. Заполняет список вопросов и ответов
    public void initMock() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        setNewQuestion(1, "Что не губит людей, в отличии от воды?", "Пиво");
        setNewQuestion(2, "Что мешает бетон, пока бригадир жрёт самогон?", "Бетономешалка");
        setNewQuestion(3, "Птица семейства Ястребиные?", "Орел");
        setNewQuestion(4, "Имя первого косманавта?", "Юра");

        printAllQuestionForMock();
    }

    public Player[] joinPlayers(){
        Player[] players = new Player[PLAYERS_TOTAL];
        for (int i = 0; i < PLAYERS_TOTAL; i++) {
            System.out.printf("Игрок №%d представьтесь: имя,город. Например: Иван,Москва\n", i+1);
            String newAnswer = scanner.nextLine();
            String[] newAnswerSplit = newAnswer.split(",");
            if (newAnswerSplit.length != 2){
                players[i] = new Player("NONAME", "DEFAULT_CITY");
                continue;
            }
            players[i] = new Player(newAnswerSplit[0].strip(), newAnswerSplit[1].strip());
        }
        return players;
    }

    public boolean isRoundFinished(){
        return !tableau.hasUnknownLetters();
    }

    public boolean playerMove(Player player){
        while (true) {
            PlayerAnswer newAnswer = player.nextAttempt(scanner);
            if (newAnswer.typeAnswer == PlayerAnswer.TypeAnswer.WORD
                    && yakubovich.isCorrectWord(newAnswer.answer, tableau.correctAnswer)) {
                tableau.openWord();
                tableau.printTableau();
                return true;
            } else if (newAnswer.typeAnswer == PlayerAnswer.TypeAnswer.LETTER
                    && yakubovich.isCorrectLetter(newAnswer.answer, tableau.correctAnswer)) {
                tableau.openLetter(newAnswer.answer);
                tableau.printTableau();
                if (isRoundFinished()){
                    return true;
                }
            }else {
                return false;
            }
        }
    }

    public void playRound(Player[] players, int roundIndex){
        boolean isFinalRound = isFinalRound(roundIndex);
        String question = questionList[roundIndex];
        tableau.init(answerList[roundIndex]);
        yakubovich.invitePlayers(players, isFinalRound);
        yakubovich.askQuestion(question);
        tableau.printTableau();
        while (true){
            for (Player player: players) {
                boolean playerWin = playerMove(player);
                if (playerWin) {
                    yakubovich.sayWin(player.name, player.city, isFinalRound);
                    if (!isFinalRound){
                        winners[roundIndex] = player;
                    }
                    return;
                }
            }
        }
    }

    public void playAllGroupRounds(){
        for (int i = 0; i < ROUNDS_OF_GROUP; i++) {
            Player[] players = joinPlayers();
            playRound(players, i);
        }
    }

    public void playFinalRound(){
        playRound(winners, INDEX_OF_FINAL_ROUND);
    }
    public void start(){
        yakubovich.beforeStartGame();
        playAllGroupRounds();
        playFinalRound();
        yakubovich.beforeEndGame();
    }

}
