package NSU.computerScience.Substring;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Search {

    public static int[] zFunction(String template, String source) {
        int sourceLen = source.length();
        int templateLen = template.length();

        int stringLen = sourceLen + templateLen;
        String string = template + source;

        int l = 0;
        int r = 0;
        int[] z = new int[stringLen];
        for (int i = 1; i < stringLen; i++) {
            z[i] = (r > i) ? Math.min(z[i - l], r - i) : 0;

            while (z[i] < templateLen && i + z[i] < stringLen && string.charAt(z[i]) == string.charAt(i + z[i])) {
                z[i]++;
            }

            if (i + z[i] > r) {
                l = i;
                r = i + z[i];
            }
        }

        return z;
    }

    public static ArrayList<Integer> SearchSubstring(String template, Reader textFile) throws IOException {
        int templateLen = template.length();
        if (templateLen == 0) return null;

        final int bufferSize = 8 * templateLen;
        char[] buffer = new char[bufferSize];

        BufferedReader text = new BufferedReader(textFile);
        int buffCount = text.read(buffer, 0, bufferSize);
        int numberOfElements = buffCount;
        if (buffCount == -1) return null;

        int move = 0;
        int[] z;
        ArrayList<Integer> result = new ArrayList<>();

        textProcessing:
        while (buffCount != -1 && numberOfElements != 0) {
            z = zFunction(template, new String(buffer, 0, numberOfElements));

            for (int i = templateLen; i < z.length; i++) {
                int BufferIndex = i - templateLen;

                if (z[i] == templateLen) {
                    result.add(BufferIndex + move);
                } else if (z[i] > 0 && bufferSize - BufferIndex < templateLen) {
                    int moveIndex = numberOfElements - BufferIndex;
                    buffer = Arrays.copyOfRange(buffer, BufferIndex, bufferSize + BufferIndex);
                    move += BufferIndex;
                    buffCount = text.read(buffer, moveIndex, BufferIndex);
                    numberOfElements = moveIndex;
                    if (buffCount != -1) {
                        numberOfElements += buffCount;
                    }
                    continue textProcessing;
                }
            }

            move += numberOfElements;
            buffCount = text.read(buffer, 0, bufferSize);
            numberOfElements = buffCount;

        }

        text.close();
        return result;

    }

}


