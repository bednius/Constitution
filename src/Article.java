import java.util.ArrayList;

/**
 * Created by kreska on 07.12.16.
 */
public class Article {
    private String content;

    public Article(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
