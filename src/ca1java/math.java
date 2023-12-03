/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca1java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author ataturkoglu
 */
public class math {

    private int level;
    private int currentScore;
    private String playerName;
    private int correctAnswer;
    private String question;
    private int userAnswer;
    private ArrayList<String> playerScores = new ArrayList<>();

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getPlayerScores() {
        return playerScores;
    }

    public void setPlayerScores(ArrayList<String> playerScores) {
        this.playerScores = playerScores;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public math() {
        level = 1; // Default level
        currentScore = 0;
        playerName = " ";
        correctAnswer = 0;
        question = " ";
        userAnswer = 0;
    }

    public void questionGenerate() {
        int num1, num2, num3, num4;
        switch (level) {
            case 1:
                num1 = (int) (Math.random() * 10) + 1;
                num2 = (int) (Math.random() * 10) + 1;
                num3 = (int) (Math.random() * 10) + 1;
                question = num1 + " + " + num2 + " - " + num3;
                correctAnswer = num1 + num2 - num3;
                break;
            case 2:
                num1 = (int) (Math.random() * 20) + 1;
                num2 = (int) (Math.random() * 20) + 1;
                num3 = (int) (Math.random() * 20) + 1;
                question = num1 + " * " + num2 + " - " + num3;
                correctAnswer = num1 * num2 - num3;
                break;
            case 3:
                num1 = (int) (Math.random() * 30) + 1;
                num2 = (int) (Math.random() * 30) + 1;
                num3 = (int) (Math.random() * 30) + 1;
                num4 = (int) (Math.random() * 30) + 1;
                question = num1 + " - " + num2 + " * " + num3 + " + " + num4;
                correctAnswer = num1 - num2 * num3 + num4;
                break;
        }

    }

    public boolean check(int userAnswer) {
        if (userAnswer == correctAnswer) {
            currentScore += level * 10; // Update score when correct answer is given
            updatePlayerScore(playerName, currentScore); // Update score
            questionGenerate(); // Generate and return new questions
            return true;
        } else {
            currentScore = 0; // Reset score for the next player or game
            return false;
        }
    }

    private void updatePlayerScore(String playerName, int newScore) {
        String playerScoreEntry = null;
        int maxScore = 0;

        // Find the current highest score
        for (String scoreEntry : playerScores) {
            if (scoreEntry.startsWith(playerName + " : ")) {
                int score = Integer.parseInt(scoreEntry.split(" : ")[1]);
                if (score > maxScore) {
                    maxScore = score;
                    playerScoreEntry = scoreEntry;
                }
            }
        }

        // If the new score is higher, update
        if (newScore > maxScore) {
            if (playerScoreEntry != null) {
                playerScores.remove(playerScoreEntry);
            }
            playerScores.add(playerName + " : " + newScore);
        }
    }

    public ArrayList<String> playerScores() {
        Collections.sort(playerScores, new Comparator<String>() {
            public int compare(String o1, String o2) {
                int score1 = Integer.parseInt(o1.split(" : ")[1]);
                int score2 = Integer.parseInt(o2.split(" : ")[1]);
                return Integer.compare(score2, score1); // Sort in descending order
            }
        });

        // Limit to top 10 scores
        int size = playerScores.size();
        return new ArrayList<>(playerScores.subList(0, Math.min(size, 10)));
    }

}
