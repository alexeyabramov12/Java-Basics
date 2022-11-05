import java.util.HashSet;
import java.util.Set;

public class TraverseLinks {


    private static Set<String> setLinks = new HashSet<>();



    public static Set<String> getSetLinks() {
        return setLinks;
    }

    public static void addLink(String reference) {
        setLinks.add(reference);
    }

}
