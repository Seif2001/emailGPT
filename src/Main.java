import GUI.Email;
import Helpers.GPT;
import Models.Mood;
import Services.PromptService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Email emailgui = new Email();
        PromptService ps = new PromptService();
        ps.setText("Dear mike, I am sorry to inform you that ");
        ps.setSubject("Lay off");
        ps.setTotalNumOfWords(80);
        ps.setNumOfWordsToBeGenerated(10);
        ps.setMood(Mood.HATE);
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print(ps.getText());
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                break;
            }
            ps.addText(line);

            try{
                ps.makePrompt();
                System.out.println(ps.getOutput());
                System.out.println("Do you accept this email? (y/n)");
                String accept = input.nextLine();
                while (!"y".equalsIgnoreCase(accept)) {
                    ps.rejectAndRePrompt();
                    ps.makePrompt();
                    System.out.println(ps.getOutput());
                    System.out.println("Do you accept this email? (y/n)");
                    accept = input.nextLine();
                }
                ps.acceptPrompt();

            }catch (Exception e){
                System.out.println(e.getStackTrace());
            }

        }

    }


}
