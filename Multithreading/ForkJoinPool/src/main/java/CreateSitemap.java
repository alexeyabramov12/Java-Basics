import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class CreateSitemap extends RecursiveTask<List<String>> {

    private Node node;
    private boolean stop;


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
            Set<String> traverseLinks = TraverseLinks.getSetLinks();
            String validUrl = ".*\\.(js|css|jpg|pdf)($|\\?.*)";
            String[] arrayTab = {"", "\t", "\t\t", "\t\t\t"};
            for (Element element : elements) {
                String url = element.attr("abs:href");
                int countTub = calculateAndAddNumberOfIndents(url);
                stop = countTub > 3;
                if (url.isEmpty() || traverseLinks.contains(url) || !url.contains(node.getLink()) || url.matches(validUrl) || stop) {
                    continue;
                }
                Node child = new Node(url);
                CreateSitemap task = new CreateSitemap(child);
                task.fork();
                subTasks.add(task);
                node.addChild(child);
                TraverseLinks.addLink(url);
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
