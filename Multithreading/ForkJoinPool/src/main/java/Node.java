import java.util.ArrayList;
import java.util.List;

public class Node {

    private String reference;
    private List<Node> children;
    private List<String> references;

    public Node (String reference) {
        children = new ArrayList<>();
        references = new ArrayList<>();
        this.reference = reference;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public String getReference() {
        return reference;
    }

    public List<String> getReferences() {
        return references;
    }

    public void addReference(String reference) {
        references.add(reference);
    }

    public void addListReferences(List<String> references) {
        this.references.addAll(references);
    }

}
