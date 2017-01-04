import java.io.FileNotFoundException;

/**
 * Created by kreska on 07.12.16.
 */
public class Main {
    public static void main(String[] args) {
        try {
            ArgumentsParser argumentsParser = new ArgumentsParser(args);
            argumentsParser.parse();
        } catch (NumberFormatException e) {
            System.err.println("Wrong format of Arguments");
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (FileNotFoundException e) {
            System.err.println("Wrong path");
        }
    }
}
