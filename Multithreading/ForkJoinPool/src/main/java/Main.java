import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        String url = "https://lenta.ru/";
        String pathFile = "data/file.txt";
        Node root = new Node(url);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<String> links = new ArrayList<>();
        links.add(url);
        TraverseLinks.addLink(url);
        links.addAll(forkJoinPool.invoke(new CreateSitemap(root)));
        Collections.sort(links, Collections.reverseOrder());
        System.out.println(links.size());
        StringBuilder builder = new StringBuilder();
        links.forEach(l -> builder.append(l + "\n"));
        try {
            FileWriter fileWriter = new FileWriter(pathFile);
            fileWriter.write(builder.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
