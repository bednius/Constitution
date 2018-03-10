

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by kreska on 07.12.16.
 */
public class Constitution {
    private ArrayList<Chapter> chapters = new ArrayList<>();
    private ArrayList<Article> articles = new ArrayList<>();

    public void addArticle(String content) {
        this.articles.add(new Article(content));
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    @Override
    public String toString() {
        return articles.toString();
    }

    public String toStringArticles(int numOfArticle) {
        return articles.get(numOfArticle - 1).toString();
    }

    public String toStringArticles(int begin, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = begin - 1; i < end; i++) {
            result.append(articles.get(i).toString());
        }
        return result.toString();
    }

    public String toStringChapter(int chapterNo) {
        chapterNo--;
        String result = chapters.get(chapterNo).getTitle() + "\n";
        result += toStringArticles(chapters.get(chapterNo).getBegin() + 1, chapters.get(chapterNo).getEnd());
        return result;
    }
}
