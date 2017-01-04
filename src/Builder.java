import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by kreska on 07.12.16.
 */
public class Builder {

    private LinkedList<String> lines = new LinkedList<>();

    public Builder(LinkedList<String> lines) {
        this.lines = lines;
    }

    public Constitution build() {
        Constitution constitution = new Constitution();
        int numberOfArticles = 0;
        int i = 0; // actualPosition;
        Chapter chapter = new Chapter();
        chapter.setBegin(0);
        chapter.setTitle(lines.get(i) + "\n" + lines.get(i + 1));
        i += 2;

        while (i < lines.size()) {
            if (WordsMatcher.chapter.matcher(lines.get(i)).matches()) {
                chapter.setEnd(numberOfArticles);
                constitution.addChapter(chapter);
                chapter = new Chapter();
                chapter.setBegin(numberOfArticles);
                chapter.setTitle(lines.get(i) + "\n" + lines.get(i + 1));
                i++;
            } else if (WordsMatcher.article.matcher(lines.get(i)).matches() || WordsMatcher.title.matcher(lines.get(i)).matches()) {
                String article = lines.get(i) + "\n";
                if (WordsMatcher.title.matcher(lines.get(i)).matches()) {
                    i++;
                    article += lines.get(i) + "\n";
                }
                i++;
                while (i < lines.size() && WordsMatcher.isContentArticle(lines.get(i))) {
                    article += lines.get(i) + "\n";
                    i++;
                }
                i--;
                constitution.addArticle(article);
                numberOfArticles++;
            }
            i++;
        }
        chapter.setEnd(numberOfArticles);
        constitution.addChapter(chapter);
        return constitution;
    }
}
