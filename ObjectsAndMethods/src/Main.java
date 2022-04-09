public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk",40,1,312);
        basket.add("bread", 20,1,123);
        basket.print("Milk");
        Basket basket1 = new Basket();
        basket.add("butter",40,1,312);
        System.out.println(Basket.getAveragePrice());
        System.out.println(Basket.getAveragePriceBasket());
    }
}
