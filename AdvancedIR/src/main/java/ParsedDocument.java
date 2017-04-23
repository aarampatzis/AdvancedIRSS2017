import java.util.*;

/**
 * Created by angelos on 17.04.17.
 */
public class ParsedDocument {
    private List<Term> documentTerms;
    private Map<String, Integer> wordFrequencyMap;
    private Set<String> uniqueWords;
    private int uniqueId;

    public ParsedDocument(List<Term> documentTerms, int uniqueId) {
        this.documentTerms = documentTerms;
        this.uniqueId = uniqueId;
        HashMap<String, Integer> wordFrequency = new HashMap<>();
        uniqueWords = null;

        for (Term t : documentTerms) {
            String word = t.getWord();
            if (!wordFrequency.containsKey(word)) {
                wordFrequency.put(word, 0);

            }

            int count = wordFrequency.get(word);
            wordFrequency.put(word, count + 1);
        }

        wordFrequencyMap = wordFrequency;
        uniqueWords = getUniqueWordsHashSet();
    }


    public int getWordFrequency(String word) {
        if (!wordFrequencyMap.containsKey(word)) {
            return 0;
        }

        return wordFrequencyMap.get(word);
    }

    public boolean isEmpty() {
        return documentTerms == null || documentTerms.isEmpty();
    }

    public List<Term> getDocumentTerms() {
        return documentTerms;
    }

    public Set<String> getUniqueWords() {
        return uniqueWords;
    }

    private HashSet<String> getUniqueWordsHashSet() {

        HashSet<String> w = new HashSet<>();
        for (Term t : documentTerms) {
            w.add(t.getWord());
        }
        return w;
    }

    public Object getUniqueId() {
        return uniqueId;
    }
}
