import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {

    private String link;
    private List<Node> children;
    private Set<String> links;

    public Node (String reference) {
        children = new ArrayList<>();
        links = new HashSet<>();
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

    public void addListLinks(Set<String> links) {
        this.links.addAll(links);
    }

}
