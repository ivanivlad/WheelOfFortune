package org.javaacademy.wonder_field;

public class Tableau {
    private static final String UNKNOWN_SYMBOL = "_";

    private String correctAnswer;
    private String[] letters;

    public void init(String answer) {
        correctAnswer = answer;
        letters = UNKNOWN_SYMBOL.repeat(answer.length()).split("");
    }

    public void printTableau() {
        checkAttributes();

        for (String letter : letters) {
            System.out.print(letter);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public void openLetter(String letter) {
        checkAttributes();

        int indexOf = -1;
        while (correctAnswer.indexOf(letter, indexOf + 1) != -1) {
            indexOf = correctAnswer.indexOf(letter, indexOf + 1);
            letters[indexOf] = correctAnswer.substring(indexOf, indexOf + 1);
        }
    }

    public void openWord() {
        letters = correctAnswer.split("");
    }

    public boolean hasUnknownLetters() {
        for (String letter : letters) {
            if (letter.equals(UNKNOWN_SYMBOL)) {
                return true;
            }
        }
        return false;
    }

    public void checkAttributes() {
       try {
           if (letters.length == 0) {
               throw new EmptyAttribute("Не заполнены буквы табло");
           } else if (correctAnswer.isEmpty()) {
               throw new EmptyAttribute("Не задан ответ");
           }
       }catch (EmptyAttribute e) {
           System.out.println(e.getMessage());
       }
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
