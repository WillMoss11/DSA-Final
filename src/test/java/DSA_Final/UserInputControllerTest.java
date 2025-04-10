package DSA_Final;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserInputController.class)
public class UserInputControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreeRecordRepository treeRecordRepository;

    @BeforeEach
    public void setUp() {
        // any setup needed for the repository or other dependencies
    }

    @Test
    public void testProcessNumbers() throws Exception {
        String numbers = "10,5,15,3,7";

        mockMvc.perform(post("/process-numbers")
                        .param("number", numbers))
                .andExpect(status().isOk())
                .andExpect(view().name("bst-result"))
                .andExpect(model().attribute("bstResult", "3,5,7,10,15"));
    }

    @Test
    public void testShowPreviousTrees() throws Exception {
        TreeRecord record1 = new TreeRecord("10,5,15,3,7", "3,5,7,10,15");
        TreeRecord record2 = new TreeRecord("20,15,25,10,5", "5,10,15,20,25");

        when(treeRecordRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        mockMvc.perform(get("/previous-trees"))
                .andExpect(status().isOk())
                .andExpect(view().name("previous-trees"))
                .andExpect(model().attribute("allTrees", Arrays.asList(record1, record2)));
    }

    @Test
    public void testShowEnterNumbersPage() throws Exception {
        mockMvc.perform(get("/enter-numbers"))
                .andExpect(status().isOk())
                .andExpect(view().name("enter-numbers"))
                .andExpect(model().attribute("message", "Enter your numbers to create a binary search tree"));
    }
}


