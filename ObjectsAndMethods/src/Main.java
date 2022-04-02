public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk",40,1,312);
        basket.add("bread", 20,1,123);
        basket.print("Milk");

       Arithmetic arithmetic = new Arithmetic(12,6);
       arithmetic.print();

        Printer printer = new Printer();
        printer.append("привет, как дела?", "беседа", 12);
        printer.print();
        printer.append("кек");
        printer.print();
        System.out.println(printer.getTotalPageCount());
    }
}
