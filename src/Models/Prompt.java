package Models;

public class Prompt {
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public int getTotalNumOfWords() {
        return totalNumOfWords;
    }

    public void setTotalNumOfWords(int totalNumOfWords) {
        this.totalNumOfWords = totalNumOfWords;
    }

    public int getWordsToBeGenerated() {
        return wordsToBeGenerated;
    }

    public void setWordsToBeGenerated(int wordsToBeGenerated) {
        this.wordsToBeGenerated = wordsToBeGenerated;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInputWords() {
        return inputWords;
    }

    public void setInputWords(String inputWords) {
        this.inputWords = inputWords;
    }

    private String inputWords;
    private String subject;
    private Mood mood;
    private int totalNumOfWords;
    private int wordsToBeGenerated;
    private String output;
    public Prompt(){

    }
    public Prompt(String subject,String inputWords, Mood mood, int totalNumOfWords, int wordsToBeGenerated) {
        this.inputWords = inputWords;
        this.subject = subject;
        this.mood = mood;
        this.totalNumOfWords = totalNumOfWords;
        this.wordsToBeGenerated = wordsToBeGenerated;
    }

    @Override
    public String toString() {
        return "Help me draft an email about " + subject + " I've started with the following i want my tone to be " + mood.toString()+": ' "+inputWords+" ' "+ "Can you please continue the email, adding " + wordsToBeGenerated + " words each time? Thanks!";
    }
}
