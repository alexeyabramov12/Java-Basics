public class Printer {

    private String queue = "";
    private int pandingPageCount = 0;
    private int totalPageCount =0;

    public void append(String text) {
        append(text,"", 1);
    }

    public void append(String text, String name) {
        append(text, name, 1);
    }

    public void append(String text, String name, int nomberOfPages) {

        queue = queue +"\n" +  name + "\n"  + nomberOfPages + "шт. " + "\n" + text;
        pandingPageCount = pandingPageCount + nomberOfPages;
        totalPageCount = totalPageCount + nomberOfPages;
    }

    public void clear() {
        queue = "";
        pandingPageCount = 0;
    }

    public void print(){
        System.out.println(queue);
        clear();
    }

    public int getPandingPagesCount() {
        System.out.println("количество листов ожидающих печати: ");
        return pandingPageCount;
    }

    public int getTotalPageCount() {
        System.out.println("Общее количество листов: ");
        return totalPageCount;
    }
}
