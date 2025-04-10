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

}
