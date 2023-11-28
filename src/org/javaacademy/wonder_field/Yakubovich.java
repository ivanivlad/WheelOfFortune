package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.Player;

import java.util.StringJoiner;

public class Yakubovich {
    public void beforeStartGame(){
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    public void beforeEndGame(){
        System.out.println("Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    public void invitePlayers(Player[] players, boolean isFinalRound){
        String names = concatNames(players);
        if (isFinalRound) {
            System.out.printf("Якубович: приглашаю победителей групповых этапов: %s\n", names);
        } else{
            System.out.printf("Якубович: приглашаю (номер тройки) тройку игроков: %s\n", names);
        }
    }

    private String concatNames(Player[] players){
        StringJoiner joiner = new StringJoiner(", ");
        for (Player player: players) {
            joiner.add(player.name);
        }
        return  joiner.toString();
    }

    public void askQuestion(String question){
        System.out.printf("Якубович: Внимание вопрос!\n %s\n", question);
    }

    public void sayWin(String name, String city, boolean isFinalRound){
        if (isFinalRound) {
            System.out.printf("Якубович: И перед нами победитель Капитал шоу поле чудес! Это %s из %s\n", name, city);
        } else{
            System.out.printf("Якубович: Молодец! %s из %s проходит в финал!\n", name, city);
        }
    }

    public boolean isCorrectLetter(String letter, String answer){
        boolean isCorrect = answer.contains(letter);
        if (isCorrect) {
            System.out.println("Якубович: Есть такая буква, откройте ее!");
        } else{
            System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!");
        }
        System.out.println("__________________________________");
        return isCorrect;
    }

    public boolean isCorrectWord(String playerAnswer, String answer){
        boolean isCorrect = answer.equals(playerAnswer);
        if (isCorrect) {
            System.out.printf("Якубович: %s! Абсолютно верно!\n", answer);
        } else{
            System.out.println("Якубович: Неверно! Следующий игрок!");
        }
        System.out.println("__________________________________");
        return isCorrect;
    }
}
