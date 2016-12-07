/**
 * Created by kreska on 07.12.16.
 */
public class Main {
    public static void main(String[] args) {
        String path = "konstytucja.txt";
        Constitution constitution = new Parser().parse(path);
    }
}
