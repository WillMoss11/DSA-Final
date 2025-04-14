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

    // put the repository into this controller
    @Autowired
    public UserInputController(TreeRecordRepository treeRecordRepository) {
        this.bst = new BinarySearchTree();
        this.treeRecordRepository = treeRecordRepository;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home"; // loads templates/home.html
    }

    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage(Model model) {
        model.addAttribute("message", "Enter your numbers to create a binary search tree");
        return "enter-numbers"; // loads templates/enter-numbers.html
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String number, Model model) {
        System.out.println("Received numbers: " + number); // did this for debugging, had problems
        BinarySearchTree freshTree = new BinarySearchTree();

        // input by commas and throw the numbers into the tree
        String[] numberStrings = number.split(",");
        for (String numStr : numberStrings) {
            try {
                // check to see if the input is a valid number
                int num = Integer.parseInt(numStr.trim());
                freshTree.insert(num);
            } catch (NumberFormatException e) {
                // handle invalid input
                model.addAttribute("errorMessage", "Invalid input detected. Please enter only numbers.");
                return "enter-numbers";
            }
        }

        // get the in-order numbers to display
        String bstResult = freshTree.inorder();
        model.addAttribute("bstResult", bstResult);

        // get the tree's JSON-like representation
        String jsonTree = freshTree.toJson();
        model.addAttribute("jsonTreeResult", jsonTree);

        // save this data to the DB
        TreeRecord record = new TreeRecord(number, bstResult);
        treeRecordRepository.save(record);

        return "bst-result";
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        // get all tree records from the database
        Iterable<TreeRecord> allTrees = treeRecordRepository.findAll();

        // add them to the model to display
        model.addAttribute("allTrees", allTrees);

        return "previous-trees";
    }
}




