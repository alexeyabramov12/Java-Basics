public class Container {
    // значение по умолчанию null, нужно задать значение было
    private Integer count = 12;

    public void addCount(int value) {
        count = count + value;
    }

    public int getCount() {
        return count;
    }

}
