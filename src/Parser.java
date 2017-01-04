import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by kreska on 07.12.16.
 */
public class Parser {
    private String path;
    private LinkedList<String> lines = new LinkedList<>();
    public FileReader file;
    private WordsMatcher wordsMatcher = new WordsMatcher();

    public Parser(String path) throws FileNotFoundException {
        this.path = path;
        this.file = new FileReader(path);
    }

    private void moveContentToList() {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
    }

    private void removeTrash() {
        ListIterator<String> iterator = lines.listIterator();
        while (iterator.hasNext()) {
            if (wordsMatcher.detectType(iterator.next()).equals(TypesOfLine.Trash))
                iterator.remove();
        }
    }

    private void mergeSeparatedSentences() {
        for (int i = 0; i < lines.size(); i++) {
            if (wordsMatcher.detectType(lines.get(i)).equals(TypesOfLine.Separated)) {
                String[] upperLine = lines.get(i).split("\\s+");
                String[] lowerLine = lines.get(i + 1).split("\\s+");
                String mergedWord = mergeTwoWords(upperLine[upperLine.length - 1], lowerLine[0]);
                upperLine[upperLine.length - 1] = mergedWord;
                String result = "";
                for (int j = 0; j < upperLine.length - 1; j++)
                    result += upperLine[j] + " ";
                result += upperLine[upperLine.length - 1];
                lines.set(i, result);
                result = "";
                if (lowerLine.length > 1) {
                    for (int j = 1; j < lowerLine.length - 1; j++)
                        result += lowerLine[j] + " ";
                    result += lowerLine[lowerLine.length - 1];
                    lines.set(i + 1, result);
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
        while (!wordsMatcher.detectType(iterator.next()).equals(TypesOfLine.Chapter))
            iterator.remove();
    }

    public void parse() {
        moveContentToList();
        removeTrash();
        mergeSeparatedSentences();
        removePreamble();
    }

    public LinkedList<String> returnList () {
        return this.lines;
    }
}
