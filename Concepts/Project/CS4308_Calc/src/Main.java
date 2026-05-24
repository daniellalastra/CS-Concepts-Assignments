import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("input.txt")));


        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(input);
        Parser parser = new Parser(lexicalAnalyzer);
        ArrayList<Object> parser1 = parser.program();
        ParseTree ParseTree = new ParseTree(parser1);
        ParseTree.execute();



    }
}