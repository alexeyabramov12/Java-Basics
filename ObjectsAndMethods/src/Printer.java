public class Printer {

    private String queue = "";
    private int pendingPageCount = 0;
    private int totalPageCount =0;

    public void append(String text) {
        append(text,"", 1);
    }

    public void append(String text, String name) {
        append(text, name, 1);
    }

    public void append(String text, String name, int numberOfPages) {

        queue = queue +"\n" +  name + "\n"  + numberOfPages + "шт. " + "\n" + text;
        pendingPageCount = pendingPageCount + numberOfPages;
    }

    public void clear() {
        queue = "";
        pendingPageCount = 0;
    }

    public void print(){
        System.out.println(queue);
        totalPageCount = totalPageCount +pendingPageCount;
        clear();
    }

    public int getPendingPagesCount() {
        System.out.println("количество листов ожидающих печати: ");
        return pendingPageCount;
    }

    public int getTotalPageCount() {
        System.out.println("Общее количество листов: ");
        return totalPageCount;
    }
}
