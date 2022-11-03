
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        Node root = new Node("https://skillbox.ru/");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<String> references = forkJoinPool.invoke(new CreateSitemap(root));
        references.forEach(System.out::println);





    }

}
