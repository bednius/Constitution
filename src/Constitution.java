/**
 * Created by kreska on 07.12.16.
 */
public class Constitution {
    public String prelude;
    public Chapter[] chapters;

    public Constitution(String prelude, Chapter[] chapters) {
        this.prelude = prelude;
        this.chapters = chapters;
    }

    @Override
    public String toString() {

    }

    public String toStringArticle(int numOfChapter, int numOfArticle) {
        return this.chapters[numOfChapter].articles[numOfArticle].toString();
    }

    public String toStringArticles(int numOfChapter, int begin, int end) {

    }

    public String toStringChapter(int numOfChapter) {

    }

    public String toStringChapters(int begin, int end) {

    }
}
