package DSA_Final;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserInputController {

    private final BinarySearchTree bst;
    private final TreeRecordRepository treeRecordRepository;

    // Inject the repository into this controller
    @Autowired
    public UserInputController(TreeRecordRepository treeRecordRepository) {
        this.bst = new BinarySearchTree();
        this.treeRecordRepository = treeRecordRepository;
    }

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage(Model model) {
        model.addAttribute("message", "Enter your numbers to create a binary search tree");
        return "enter-numbers"; // loads templates/enter-numbers.html
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String number, Model model) {
        // Reset the BST each time new numbers are entered
        BinarySearchTree freshTree = new BinarySearchTree();

        // Split input by commas and throw the numbers into the tree
        String[] numberStrings = number.split(",");
        for (String numStr : numberStrings) {
            int num = Integer.parseInt(numStr.trim());
            freshTree.insert(num);
        }

        // Get the in-order traversal to display
        String bstResult = freshTree.inorder();
        model.addAttribute("bstResult", bstResult);

        // Save this data to the DB like a good citizen
        TreeRecord record = new TreeRecord(number, bstResult);
        treeRecordRepository.save(record);

        return "bst-result"; // this will be the next HTML page we build
    }
}


