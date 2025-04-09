package DSA_Final;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInputController {

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage(Model model) {
        model.addAttribute("message", "Enter your numbers to create a binary search tree");
        return "enter-numbers"; // This comes from the resources/templates
    }
}
