import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.tartarus.snowball.*;
import org.tartarus.snowball.ext.EnglishStemmer;

/**
 * Created by angelos on 17.04.17.
 */
public class TextUtils {
    public TextUtils() {

    }

    public String readFile(String filename) throws IOException {
        String content = null;
        File file = new File(filename);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader !=null){reader.close();}
        }
        return content;
    }

    public static InvertedIndex buildIndex(List<Document> documents) throws IOException {

        Collection<ParsedDocument> parsedDocuments = buildParsedDocuments(documents);

        InvertedIndex invertedIndex = new InvertedIndex(new ArrayList<>(parsedDocuments));

        return invertedIndex;
    }

    private static Collection<ParsedDocument> buildParsedDocuments(List<Document> docs) throws IOException {
        List<ParsedDocument> parsedDocuments = new ArrayList<>();

        for (Document doc : docs) {
            parsedDocuments.add(parseDocument(doc));
        }

        return parsedDocuments;
    }

    private static ParsedDocument parseDocument(Document doc) throws IOException {
        List<Term> documentTerms = textToTermList(doc.getText(), true, true, true);

        ParsedDocument document = new ParsedDocument(documentTerms, doc.getDocID());
        return document;
    }

    private static List<Term> textToTermList(String txt, boolean CASE_FOLDING_FLAG, boolean STEMMING_FLAG, boolean STOP_WORD_FLAG) throws IOException {
        String text = txt;

        if (txt == null) {
            return new ArrayList<>();
        }

        if (CASE_FOLDING_FLAG) {
            text = text.toLowerCase();
        }

        List<String> terms = tokenize(text);

        if (STOP_WORD_FLAG) {
            HashSet<String> stopwords = getStopWords();
            terms.removeAll(stopwords);

        }

        List<Term> termList = new ArrayList<>();
        int pos = 0;

        for (String str : terms) {
            String normalizedTerm = str;
            if (STEMMING_FLAG) {
                stemWord(str);
            }

            String stemmed = normalizedTerm;
            termList.add(new Term(stemmed, pos));
            pos++;
        }

        return termList;
    }

    private static List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();

        if (text == null) {
            return tokens;
        }

        Scanner in = new Scanner(text);

        while (in.hasNext()) {
            String str = in.next();
            if (str == null) {
                continue;
            }

            str = str.replaceAll("[^a-zA-Z ]", "");
            tokens.add(str);
        }
        return tokens;
    }

    private static String stemWord(String word) {
        EnglishStemmer stemmer = new EnglishStemmer();
        stemmer.setCurrent(word);
        return stemmer.getCurrent();
    }

    private static HashSet<String> getStopWords() throws IOException {
        HashSet<String> stopwords = new HashSet<>();
        File file = new File("stop.txt");
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line == null) {
                    continue;
                }
                line = line.trim();
                line = line.toLowerCase();
                if (line.isEmpty()) {
                    continue;
                }
                stopwords.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopwords;
    }
}
