import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class CreateSitemap extends RecursiveTask<List<String>> {

    private final Node node;
    private static HashSet <String> traversedLinks = new HashSet<>();
    private static final String[] arrayTab = {"", "\t", "\t\t", "\t\t\t"};


    public CreateSitemap(Node node) {
        this.node = node;
    }

    @Override
    protected List<String> compute() {
        List<String> links = new ArrayList<>();
        try {
            Thread.sleep(150);
            Document doc = Jsoup.connect(node.getLink()).get();
            Elements elements = doc.select("a[href]");
            List<CreateSitemap> subTasks = new LinkedList<>();
            String validUrl = ".*\\.(js|css|jpg|pdf)($|\\?.*)";

            traversedLinks.add(node.getLink());
            for (Element element : elements) {
                String url = element.attr("abs:href");
                int countTub = calculateAndAddNumberOfIndents(url);
                boolean stop = countTub > 3;
                if (url.isEmpty() || traversedLinks.contains(url) || !url.contains(node.getLink()) || url.matches(validUrl) || stop) {
                    continue;
                }
                Node child = new Node(url);
                CreateSitemap task = new CreateSitemap(child);
                task.fork();
                subTasks.add(task);
                node.addChild(child);
                traversedLinks.add(url);
                links.add(arrayTab[countTub] + url);
            }
            for (CreateSitemap task : subTasks) {
                links.addAll(task.join());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        node.addListLinks(links);
        return links;
    }

    private int calculateAndAddNumberOfIndents(String url) {
        if (url.isEmpty()) {
            return 0;
        }
        int countTab = -3;
        if (url.charAt(url.length() - 1) != '/') {
            countTab = -2;
        }
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '/'){
                countTab++;
            }
        }
        if (countTab < 0) {
            countTab = 0;
        }
        return countTab;
    }
}
