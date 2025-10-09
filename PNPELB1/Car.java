public class Car {
    public String model;

    public static int count = 0;

    public Car(String model) {
        this.model = model;
        count++;
    }

    public static int getCount() {
        return count;
    }
}