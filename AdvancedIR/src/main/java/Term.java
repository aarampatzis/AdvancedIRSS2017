/**
 * Created by angelos on 17.04.17.
 */
public class Term {

    private String word;
    private int position;

    public Term(String word, int position) {
        this.word = word;
        this.position = position;
    }

    public String getWord() {
        return word;
    }

    public int getPosition() {
        return position;
    }
}
