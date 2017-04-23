import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by angelos on 17.04.17.
 */
public class PostingCollection {
    private String word;
    private List<Posting> postings;
    private HashSet<ParsedDocument> uniqueDocuments;

    public PostingCollection(String word) {
        this.word = word;
        this.postings = new ArrayList<>();
        this.uniqueDocuments = new HashSet<>();
    }

    public void addPosting(Term documentTerm, ParsedDocument doc) {
        postings.add(new Posting(documentTerm, doc));
        uniqueDocuments.add(doc);
    }

    public String getWord() {
        return word;
    }

    public List<Posting> getPostings() {
        return postings;
    }

    public HashSet<ParsedDocument> getUniqueDocuments() {
        return uniqueDocuments;
    }
}
