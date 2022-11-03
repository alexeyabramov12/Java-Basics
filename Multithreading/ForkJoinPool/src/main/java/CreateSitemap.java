import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CreateSitemap extends RecursiveTask<List<String>> {

    private Node node;


    public CreateSitemap(Node node) {
        this.node = node;
    }

    @Override
    protected List<String> compute() {
        List<String> references = new ArrayList<>();
        try {
            Thread.sleep(150);
            Document doc = Jsoup.connect(node.getReference()).get();
            Elements elements = doc.select("a[href]");
            List<CreateSitemap> subTasks = new LinkedList<>();
            for (Element element : elements) {
                String validUrl = "https:\\/\\/skillbox\\.ru\\/[^#pdf]+";
                String url = element.attr("abs:href");
                Node child = new Node(url);
                if (url == null || url.isEmpty() || node.getReference().equals(url) || !url.matches(validUrl)) {
                    continue;
                }
                CreateSitemap task = new CreateSitemap(child);
                task.fork();
                subTasks.add(task);
                node.addChild(child);
                System.out.println(node.getReferences().size());
            }
            for (CreateSitemap task : subTasks) {
                references.addAll(task.join());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        node.addListReferences(references);
        return references;
    }
}
