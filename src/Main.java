import Helpers.GPT;
import Models.Mood;
import Services.PromptService;

public class Main {
    public static void main(String[] args) {
        PromptService ps = new PromptService();
        ps.setText("Dear mike, I am sorry to inform you that ");
        ps.setSubject("Lay off");
        ps.setTotalNumOfWords(80);
        ps.setNumOfWordsToBeGenerated(10);
        ps.setMood(Mood.HATE);
        try{
            ps.makePrompt();
            System.out.println("OUTPUT: " + ps.getOutput());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }


}
