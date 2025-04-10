package DSA_Final;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserInputController {

    private BinarySearchTree bst;

    // Setting up the binary search tree
    public UserInputController() {
        this.bst = new BinarySearchTree();
    }

    // Show the input page to the user
    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage(Model model) {
        model.addAttribute("message", "Enter your numbers to create a binary search tree");
        return "enter-numbers";
    }

    // Handle the form submission and build the BST
    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numberInput, Model model) {
        String[] numberStrings = numberInput.split(",");
        List<Integer> numbers = new ArrayList<>();

        try {
            // to parse every number
            for (String numStr : numberStrings) {
                numbers.add(Integer.parseInt(numStr.trim()));
            }

            // Insert the numbers into our precious BST
            for (int num : numbers) {
                bst.insert(num);
            }

            // just a little confirmation message
            model.addAttribute("message", "BST created successfully! Here's the in-order traversal:");
            model.addAttribute("bstResult", bst.inorder());

        } catch (NumberFormatException e) {
            model.addAttribute("message", "Oops! Make sure you're only entering numbers separated by commas.");
        }


        return "enter-numbers";
    }
}

