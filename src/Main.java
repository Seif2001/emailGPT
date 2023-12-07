import Helpers.GPT;


public class Main {
    public static void main(String[] args) {
        try {
            String prompt = "how many medals did micheal phelps win in the olimics?";
            String response = GPT.getOpenAIResponse(prompt);

            System.out.println("OpenAI Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
