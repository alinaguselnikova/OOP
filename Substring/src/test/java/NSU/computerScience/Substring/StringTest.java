package NSU.computerScience.Substring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class StringTest {
    private ArrayList<Integer> referenceSearch(String template, String text) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        while (i != -1) {
            i = text.indexOf(template, i);
            if (i != -1) {
                result.add(i);
                i++;
            }
        }
        return result;
    }

    @Test
    public void EmptyTemplate() throws IOException {
        String template = "";
        String text = "God please it'll work";
        ArrayList<Integer> actual = Search.SearchSubstring(template, new StringReader(text));
        assertNull(actual);
    }

    @Test
    public void EmptyString() throws IOException {
        String template = "God please it'll work";
        String text= "";
        ArrayList<Integer> actual = Search.SearchSubstring(template, new StringReader(text));
        assertNull(actual);
    }
    @Test
    public void testSearch_smallFile() throws IOException {
        String pattern = "sps";
        FileReader textFile = new FileReader("src/test/resources/test1.txt", StandardCharsets.UTF_8);
        ArrayList<Integer> actual = Search.SearchSubstring(pattern, textFile);
        String textString = Files.readString(Paths.get("src/test/resources/test1.txt"));
        ArrayList<Integer> expected = referenceSearch(pattern, textString);
        Assertions.assertEquals(expected, actual);
    }
}
