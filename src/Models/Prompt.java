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

    public void setMood(Mood mood) {this.mood = mood;}
    public Length getLength() {
        return length;
    }

    public void setLength(Length length) {
        this.length = length;
    }

    public int getTotalNumOfWords() {
        return totalNumOfWords;
    }

    public void setTotalNumOfWords(int totalNumOfWords) {
        this.totalNumOfWords = totalNumOfWords;
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
    private Length length;
    private int totalNumOfWords;
    private String output;
    public Prompt(){

    }
    public Prompt(String subject,String inputWords, Mood mood, Length length, int totalNumOfWords) {
        this.inputWords = inputWords;
        this.subject = subject;
        this.mood = mood;
        this.length = length;
        this.totalNumOfWords = totalNumOfWords;
    }

    @Override
    public String toString() {
        return "Help me draft an email about " + subject + " I want my tone to be " + mood.toString() + ", and I want it to be of length of about " + length.getLength() + " words. Please provide the full email body without the subject, here is what I wrote till now: " + inputWords;
    }
}
