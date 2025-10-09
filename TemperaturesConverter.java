import java.util.Scanner;

public class TemperaturesConverter {

static final double KELVIN_OFFSET = 273.15;

    static double toCelsius(double degree) {
        return degree - KELVIN_OFFSET;
    }

    static double toFahrenheit(double degree) {
        return (degree - KELVIN_OFFSET) * 9/5 + 32;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double degrees = scanner.nextDouble();

        System.out.println(String.format("To Celsius: %.2f", toCelsius(degrees)));
        System.out.println(String.format("To Fahrenheit: %.2f", toFahrenheit(degrees)));

        scanner.close();
    }
}
