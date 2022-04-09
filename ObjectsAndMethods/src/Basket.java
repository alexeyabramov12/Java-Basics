public class Basket {

    private static int countBasket;
    private static int totalCost;
    private static int totalCount;
    private static int averagePrice;
    private static int averagePriceBasket;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight = 0;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static void totalCost(int prise) {
        totalCost += prise;
    }

    public static void totalCount(int count) {
        totalCount += count;
    }

    public static int getAveragePrice() {
        System.out.print("Cредняя цена товаров во всех корзинах: ");
        averagePrice = totalCost / totalCount;
        return averagePrice;
    }

    public static int getAveragePriceBasket() {
        System.out.print("Средняя стоимость корзины: ");
        averagePriceBasket = totalCost / countBasket;
        return averagePriceBasket;
    }

    public static int getCountBasket() {
        return countBasket;
    }

    public static void increaseCount(int count) {
        Basket.countBasket = Basket.countBasket + count;
    }

    public void add(String name, int price) {
        add(name, price, 1,0);
    }

    public void add(String name, int price, int count) {
        add(name, price, count,0);
    }

    public void add (String name, int price, int count, double weight){
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - " + weight + " гр. - " + price + " руб.";
        totalPrice += count * price;
        totalWeight += weight;
        totalCost(price);
        totalCount(count);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
