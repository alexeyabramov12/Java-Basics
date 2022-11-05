import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        String url = "https://lenta.ru/";
        Node root = new Node(url);
        String pathFile = "data/file.txt";

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Set<String> links = forkJoinPool.invoke(new CreateSitemap(root));
        List<String> linksList = new ArrayList<>(links);
        Collections.sort(linksList, Collections.reverseOrder());
        StringBuilder builder = new StringBuilder();
        linksList.forEach(l -> builder.append(l + "\n"));
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
