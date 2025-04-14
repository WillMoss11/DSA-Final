package DSA_Final;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Arrays;

@Controller
public class BinarySearchTreeController {

    @GetMapping("/view-tree")
    public String showBinaryTree(Model model) {
        return "bst-result";
    }

    @PostMapping("/process-numbers-basic")
    public String processNumbersBasic(@RequestParam("number") String numbers, Model model) {
        // numbers from the form (comma-separated)
        String[] numberStrings = numbers.split(",");
        int[] nums = Arrays.stream(numberStrings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        // create the BST using BinarySearchTree class
        BinarySearchTree bst = new BinarySearchTree();
        for (int num : nums) {
            bst.insert(num);
        }

        // get the in-order result as a string (comma-separated)
        String bstResult = bst.inorder();

        // get the trees JSON-like representation
        String jsonTree = bst.toJson();

        // add the results to the model to pass it to the view
        model.addAttribute("bstResult", bstResult);
        model.addAttribute("jsonTreeResult", jsonTree);

        return "bst-result";
    }
}




