//TAREK KASSAB - 900213491
//Seifeldin Elshabshiri - 900202310
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

    public void setSubject(String subject){
        prompt.setSubject(subject);
    }

    public void setMood(Mood mood){
        prompt.setMood(mood);
    }

    public void setLength(Length length){
        prompt.setLength(length);
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

    public String[] getLengths(){
        return Length.names();
    }

    public void acceptPrompt(){
        this.setText(prompt.getOutput());
        System.out.println(prompt.getInputWords());
    }
    public String getOutput(){
        return prompt.getOutput().replace("\\n","\n");
    }
}
