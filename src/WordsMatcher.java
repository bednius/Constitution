import java.util.regex.Pattern;

/**
 * Created by kreska on 02.01.17.
 */
public class WordsMatcher {
    public static final Pattern chapter = Pattern.compile("Rozdział [IVXL]+");
    public static final Pattern date = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    public static final Pattern article = Pattern.compile("Art. [0-9]+.");
    public static final Pattern separated = Pattern.compile(".+-$");
    public static final Pattern title = Pattern.compile("[A-Z ŻĆŹÓĘĄŁ]{4,}.+");
    public static final Pattern trash = Pattern.compile("[A-Za-z0-9]");
    public static final Pattern trash2 = Pattern.compile("[©].+");
    //static final Pattern title = Pattern.compile("KONSTYTUCJA");
    //static final Pattern exception = Pattern.compile("Tracą moc:");

    //static Matcher matcher;

    //WordsMatcher() {}

    public TypesOfLine detectType(String line) {

        if(chapter.matcher(line).matches())
            return TypesOfLine.Chapter;

        if(article.matcher(line).matches())
            return TypesOfLine.Article;

        if(separated.matcher(line).matches())
            return TypesOfLine.Separated;

        if(title.matcher(line).matches())
            return TypesOfLine.CapitalLetters;

        if(trash.matcher(line).matches() || trash2.matcher(line).matches() || date.matcher(line).matches())
            return TypesOfLine.Trash;

        return TypesOfLine.Default;
    }

    public static boolean isContentArticle (String line) {
        return (!chapter.matcher(line).matches() && !article.matcher(line).matches() && !title.matcher(line).matches());
    }
}
