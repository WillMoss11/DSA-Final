package DSA_Final;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Arrays;

@Controller
public class BinarySearchTreeController {

    @GetMapping("/view-tree")  // Change this to a different path like "/view-tree"
    public String showBinaryTree(Model model) {
        // Your logic to display the tree (JSON, etc.)
        return "bst-result";  // Or whatever view you want to display for the binary tree
    }

    @PostMapping("/process-numbers-basic")
    public String processNumbersBasic(@RequestParam("number") String numbers, Model model) {
        // Parse the numbers from the form (comma-separated)
        String[] numberStrings = numbers.split(",");
        int[] nums = Arrays.stream(numberStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        // Create the BST using BinarySearchTree class
        BinarySearchTree bst = new BinarySearchTree();
        for (int num : nums) {
            bst.insert(num);
        }

        // Get the in-order traversal result as a string (comma-separated)
        String bstResult = bst.inorder();

        // Get the tree's JSON-like representation
        String jsonTree = bst.toJson();

        // Add the results to the model to pass it to the view
        model.addAttribute("bstResult", bstResult);
        model.addAttribute("jsonTreeResult", jsonTree);

        return "bst-result";  // Return to the result page with both the BST result and JSON tree
    }
}




