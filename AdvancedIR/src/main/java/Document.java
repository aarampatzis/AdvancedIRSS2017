/**
 * Created by angelos on 17.04.17.
 */
public class Document {

    private String text;
    private int docID;

    /**
     *
     * @param text - the raw text for this document.
     * @param docID - a unique ID for this document. Used in search results to refer back
     *                         to original data
     */
    public Document(String text, int docID) {
        this.text = text;
        this.docID = docID;
    }

    public String getText() {
        return text;
    }

    public int getDocID() {
        return docID;
    }
}
