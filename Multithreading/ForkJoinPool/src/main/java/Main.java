import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String url = "https://lenta.ru/";
        String pathFile = "data/file.txt";
        Node root = new Node(url);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<String> links = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        links.add(url);
        links.addAll(forkJoinPool.invoke(new CreateSitemap(root)));

        Collections.sort(links, Collections.reverseOrder());
        System.out.println(links.size());
        links.forEach(l -> builder.append(l + "\n"));
        try {
            FileWriter fileWriter = new FileWriter(pathFile);
            fileWriter.write(builder.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}
