/**
 * Created by angelos on 17.04.17.
 */
public class Posting {
    private Term documentTerm;
    private ParsedDocument parsedDocument;
    public Posting(Term documentTerm, ParsedDocument document) {
        this.documentTerm = documentTerm;
        this.parsedDocument = document;
    }

    public Term getTerm() {
        return documentTerm;
    }

    public ParsedDocument getParsedDocument() {
        return parsedDocument;
    }

}
