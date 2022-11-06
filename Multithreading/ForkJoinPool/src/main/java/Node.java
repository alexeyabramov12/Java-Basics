import java.util.ArrayList;
import java.util.List;

public class Node {

    private String link;
    private List<Node> children;
    private List<String> links;

    public Node (String reference) {
        children = new ArrayList<>();
        links = new ArrayList<>();
        this.link = reference;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public String getLink() {
        return link;
    }

    public void addLink(String link) {
        links.add(link);
    }

    public void addListLinks(List<String> links) {
        this.links.addAll(links);
    }

}
