package DSA_Final;

import jakarta.persistence.*;

@Entity
public class TreeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String inputNumbers;

    private String bstResult;

    // default
    public TreeRecord() {}

    public TreeRecord(String inputNumbers, String bstResult) {
        this.inputNumbers = inputNumbers;
        this.bstResult = bstResult;
    }

    public Long getId() {
        return id;
    }

    public String getInputNumbers() {
        return inputNumbers;
    }

    public String getBstResult() {
        return bstResult;
    }

    public void setInputNumbers(String inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public void setBstResult(String bstResult) {
        this.bstResult = bstResult;
    }
}
