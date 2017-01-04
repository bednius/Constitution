import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by kreska on 07.12.16.
 */
public class Chapter {
    private String title;
    private int begin;
    private int end;
    //private LinkedList<Article> articles = new LinkedList<>();

    public void setTitle(String title) {
        this.title = title;
    }
    public void setBegin(int begin) {
        this.begin = begin;
    }
    public void setEnd(int end) {
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }
}
