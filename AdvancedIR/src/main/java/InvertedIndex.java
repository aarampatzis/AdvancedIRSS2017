import java.util.*;
import java.util.concurrent.*;

/**
 * Created by angelos on 17.04.17.
 */
public class InvertedIndex {



    private ArrayList<ParsedDocument> corpus;
    private Map<String, PostingCollection> termToPostings;

    public InvertedIndex(ArrayList<ParsedDocument> corpus) {
        this.corpus = corpus;
        init();
    }

    private void init() {
        Map<String, PostingCollection> termToPostingsMap = new HashMap<>();
        for (ParsedDocument document : corpus) {
            for (Term documentTerm : document.getDocumentTerms()) {
                final String word = documentTerm.getWord();
                if (!termToPostingsMap.containsKey(word)) {
                    termToPostingsMap.put(word, new PostingCollection(word));
                }
                termToPostingsMap.get(word).addPosting(documentTerm, document);
            }
        }

        termToPostings = termToPostingsMap;

    }

    public int numDocuments() {
        return corpus.size();
    }

    public int termCount() {
        return termToPostings.keySet().size();
    }


    public Map<String, PostingCollection> getTermToPostings() {
        return termToPostings;
    }
}
