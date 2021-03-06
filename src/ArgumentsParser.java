import java.io.FileNotFoundException;

public class ArgumentsParser {

    private String[] args;

    public ArgumentsParser(String[] args) {
        this.args = args;
    }

    private void checkNumOfArgs() {
        if (!(this.args.length == 3 || this.args.length == 4))
            throw new IllegalArgumentException("Wrong number of arguments!");
    }

    public void parse() throws FileNotFoundException {
        checkNumOfArgs();

        Parser parser = new Parser(args[0]);
        parser.parse();
        Constitution constitution = new Builder(parser.returnList()).build();

        switch (args[1]) {
            case "-a":
                printArticles(constitution);
                break;
            case "-c":
                printChapter(constitution);
                break;
            default:
                throw new IllegalArgumentException("Incorrect argument: Use -c to get chapter or -a to get article or articles.");
        }
    }

    private void printArticles(Constitution constitution) {
        int begin = Integer.parseInt(args[2]);
        checkCorrectOfArticle(begin);
        if (args.length == 4) {
            int end = Integer.parseInt(args[3]);
            checkCorrectOfArticle(end);
            if (begin > end)
                throw new IllegalArgumentException("Wrong interval of Articles");
            System.out.println(constitution.toStringArticles(begin, end));
        } else {
            System.out.println(constitution.toStringArticles(begin));
        }
    }

    private void printChapter(Constitution constitution) {
        if (args.length == 4)
            throw new IllegalArgumentException("Too many arguments in -c option");
        int num = Integer.parseInt(args[2]);
        checkCorrectOfChapter(num);
        System.out.println(constitution.toStringChapter(num));
    }

    private void checkCorrectOfArticle(int num) {
        if (num < 1 || num > 243)
            throw new IllegalArgumentException("Wrong number of Article");
    }

    private void checkCorrectOfChapter(int num) {
        if (num < 1 || num > 13)
            throw new IllegalArgumentException("Wrong number of Chapter");
    }

}
