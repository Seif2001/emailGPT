package Services;
import Helpers.GPT;
import Models.*;
public class PromptService {
    private Prompt prompt;
    public PromptService(){
        prompt = new Prompt();
    }
    public void setText(String words){
        prompt.setInputWords(words);
    }
    public String getText(){
        return prompt.getInputWords();
    }
    public void addText(String words){
        prompt.setInputWords(prompt.getInputWords() + words);
    }

    public void setNumOfWordsToBeGenerated(int num){
        prompt.setWordsToBeGenerated(num);
    }

    public void setSubject(String subject){
        prompt.setSubject(subject);
    }

    public void setMood(Mood mood){
        prompt.setMood(mood);
    }

    public void setTotalNumOfWords(int total){
        prompt.setTotalNumOfWords(total);
    }

    public void makePrompt() throws Exception {
        prompt.setOutput(GPT.getOpenAIResponse(prompt.toString()));
    }
    public String[] getMoods(){
        return Mood.names();
    }

    public void acceptPrompt(){
        this.setText(prompt.getOutput());
        System.out.println(prompt.getInputWords());
    }

    public void rejectAndRePrompt() throws Exception{
        prompt.setOutput(GPT.getOpenAIResponse("I did not like this response please phrase it differently."));
    }
    public String getOutput(){
        return prompt.getOutput();
    }
}
