package DSA_Final;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserInputController {

    private BinarySearchTree bst;

    // initialize the BST
    public UserInputController() {
        this.bst = new BinarySearchTree();
    }

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage(Model model) {
        model.addAttribute("message", "Enter your numbers to create a binary search tree");
        return "enter-numbers"; // This comes from the resources/templates
    }

    // handle for processing numbers
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String number, Model model) {
        // split the numbers input by a comma, convert to integers n insert into the BST
        String[] numberStrings = number.split(",");
        for (String numStr : numberStrings) {
            int num = Integer.parseInt(numStr.trim());
            bst.insert(num);
        }

        // get the in order of the tree to display
        String bstResult = bst.inorder();
        model.addAttribute("bstResult", bstResult);

        // return the result
        return "bst-result";
    }
}
