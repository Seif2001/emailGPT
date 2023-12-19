//TAREK KASSAB - 900213491
//Seifeldin Elshabshiri - 900202310
package Controllers;

import GUI.Email;
import Services.PromptService;

public class PromptController {
    private PromptService ps;
    private Email emailGui;
    public PromptController(){
        ps = new PromptService();
        emailGui = new Email(ps);
    }
}
