

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by kreska on 07.12.16.
 */
public class Constitution {
    //public String tittle;
    private ArrayList<Chapter> chapters = new ArrayList<>();
    private ArrayList<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        this.articles.add(article);
    }

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
        //return articles.get(numOfArticle - 1).toString();
        String result = "";
        for (int i = begin - 1; i < end; i++) {
            result += articles.get(i).toString();
        }
        return result;
    }

    public String toStringChapter(int chapterNo) {
        chapterNo--;
        String result = chapters.get(chapterNo).getTitle() + "\n";
        result += toStringArticles(chapters.get(chapterNo).getBegin() + 1, chapters.get(chapterNo).getEnd());
        return result;
    }
}
