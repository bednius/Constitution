import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by kreska on 07.12.16.
 */
public class Parser {
    private String path;
    private LinkedList<String> lines = new LinkedList<>();
    private FileReader file;
    private WordsMatcher wordsMatcher = new WordsMatcher();

    public Parser(String path) throws FileNotFoundException {
        this.path = path;
        this.file = new FileReader(path);
    }

    private void moveContentToList() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(file);
        String textLine = bufferedReader.readLine();
        try {
            do {
                lines.add(textLine);
                textLine = bufferedReader.readLine();
            } while (textLine != null);
        } finally {
            bufferedReader.close();
        }
    }

    private void removeTrash() {
        ListIterator<String> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            if (wordsMatcher.detectType(iterator.next()).equals(LineType.Trash))
                iterator.remove();
        }
    }

    private void mergeSeparatedSentences() {
        for (int i = 0; i < lines.size(); i++) {
            if (wordsMatcher.detectType(lines.get(i)).equals(LineType.Separated)) {
                String[] upperLine = lines.get(i).split("\\s+");
                String[] lowerLine = lines.get(i + 1).split("\\s+");
                String mergedWord = mergeTwoWords(upperLine[upperLine.length - 1], lowerLine[0]);
                upperLine[upperLine.length - 1] = mergedWord;
                StringBuilder result = new StringBuilder();
                for (int j = 0; j < upperLine.length - 1; j++)
                    result.append(upperLine[j]).append(" ");
                result.append(upperLine[upperLine.length - 1]);
                lines.set(i, result.toString());
                result = new StringBuilder();
                if (lowerLine.length > 1) {
                    for (int j = 1; j < lowerLine.length - 1; j++)
                        result.append(lowerLine[j]).append(" ");
                    result.append(lowerLine[lowerLine.length - 1]);
                    lines.set(i + 1, result.toString());
                } else
                    lines.remove(i + 1);
            }
        }
    }

    private String mergeTwoWords(String s1, String s2) {
        return s1.substring(0, s1.length() - 1) + s2;
    }

    private void removePreamble() {
        ListIterator<String> iterator = lines.listIterator();
        while (!wordsMatcher.detectType(iterator.next()).equals(LineType.Chapter))
            iterator.remove();
    }

    public void parse() {
        try {
            moveContentToList();
        } catch (IOException e) {
            System.err.println("Cannot read file. Unknown error");
        }
        removeTrash();
        mergeSeparatedSentences();
        removePreamble();
    }

    public LinkedList<String> returnList() {
        return this.lines;
    }
}
