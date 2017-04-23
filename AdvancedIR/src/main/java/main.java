import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.IOException;

/**
 * Created by angelos on 17.04.17.
 */
public class main {
    public static void main(String args[]) throws IOException {
        List<Document> documentList = new ArrayList<>();    //create a list of documents to be indexed

        BufferedReader in = new BufferedReader(new FileReader("/home/angelos/projects/TREC8all/list.txt"));     //create a list of the files in the collection
        String str;

        List<String> filenames = new ArrayList<>();
        while((str = in.readLine()) != null){
            filenames.add(str);
        }

        TextUtils textUtils = new TextUtils();  //create an instance of TextUtils to process documents
        String content;
        int i = 0;  //text and docID for the documents to be indexed
        for (String filename : filenames) {     //Iterate through all the files in the collections and add them to the document list with a unique ID
            content = textUtils.readFile(filename);
            documentList.add(new Document(content, i+1));
            i++;
        }

        InvertedIndex index = TextUtils.buildIndex(documentList);

    }
}

