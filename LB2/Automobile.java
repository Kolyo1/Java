package LB2;

import java.util.*;

abstract class Car implements Extras{
    String make;
    String model;
    int year;
    double engineCapacity; // куб. см
    double price;

    ACType acType;
    Interior interior;
    Rims rims;

    Car(String make,String model, int year, double engineCapacity, double price, ACType acType, Interior interior, Rims rims){
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.price = price;
        this.acType = acType;
        this.interior = interior;
        this.rims = rims;
    }

    abstract double ecoTax();

    @Override
    public
    double extrasPrice() {
        double total = 0;
        switch (acType) {
            case MANUAL -> total += 500;
            case AUTOMATIC -> total += 900;
        }
        switch (interior) {
            case FABRIC -> total += 0;
            case LEATHER -> total += 1200;
        }
        switch (rims) {
            case STEEL -> total += 0;
            case ALLOY -> total += 600;
        }
        return total;
    }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getEngineCapacity() { return engineCapacity; }
    public void setEngineCapacity(double engineCapacity) { this.engineCapacity = engineCapacity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override public ACType getACType() { return acType; }
    @Override public void setACType(ACType acType) { this.acType = acType; }

    @Override public Interior getInterior() { return interior; }
    @Override public void setInterior(Interior interior) { this.interior = interior; }

    @Override public Rims getRims() { return rims; }
    @Override public void setRims(Rims rims) { this.rims = rims; }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [AC: " + acType +
                ", Interior: " + interior +
                ", Rims: " + rims +
                ", Extras price: " + extrasPrice() + "]";
    }
}


enum ACType{
    MANUAL,
    AUTOMATIC
}
enum Interior{
    LEATHER,
    FABRIC
}
enum Rims{
    STEEL,
    ALLOY
}

interface Extras{
    ACType getACType();
    void setACType(ACType acType); // Вид климатик
    Interior getInterior();
    void setInterior(Interior interior);// Вид интериор
    Rims getRims();
    void setRims(Rims rims); // Вид джанти

    double extrasPrice(); // Цена на екстрите
}


class DieselCar extends Car{
    DieselCar(String make,String model, int year, double engineCapacity, double price, ACType acType, Interior interior, Rims rims){
        super(make ,model, year, engineCapacity, price, acType, interior, rims);
    }
    @Override
    double ecoTax() {
        return engineCapacity * 0.1 + 300;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class PetrolCar extends Car {
    PetrolCar(String make,String model, int year, double engineCapacity, double price, ACType acType, Interior interior, Rims rims) {
        super(make, model, year, engineCapacity, price, acType, interior, rims);
    }

    @Override
    double ecoTax() {
        return engineCapacity * 0.08 + 200;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}

class ElectricCar extends Car {
    double batteryCapacityKWh;
    ElectricCar(String make,String model, int year, double engineCapacity, double price, double batteryCapacityKWh,ACType acType, Interior interior, Rims rims) {
        super(make, model, year, 0, price, acType, interior, rims);
        this.batteryCapacityKWh = batteryCapacityKWh;
    }

    @Override
    double ecoTax() {
        return (batteryCapacityKWh <= 50) ? 0 : 50 ;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}

class Dealership {
    private List<Car> cars;

    public Dealership() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public double totalEcoTax() {
        return cars.stream()
                .mapToDouble(Car::ecoTax)
                .sum();
    }
}

public class Automobile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dealership dealership = new Dealership();
        int dieselCount = 0;
        int petrolCount = 0;
        int electricCount = 0;

        while (dieselCount < 5 || petrolCount < 5 || electricCount < 5) {
            System.out.println("Изберете тип кола за добавяне (Diesel, Petrol, Electric)");
            String typeInput = scanner.nextLine().trim().toUpperCase();

            ACType acType = null;
            Interior interior = null;
            Rims rims = null;

            while(acType == null) {
                try{
                    System.out.println("Изберете тип климатик (MANUAL, AUTOMATIC):");
                    acType = ACType.valueOf(scanner.nextLine().trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Невалиден избор. Опитайте отново.");
                }
            }

            while(interior == null) {
                try{
                    System.out.println("Изберете тип интериор (FABRIC, LEATHER):");
                    interior = Interior.valueOf(scanner.nextLine().trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Невалиден избор. Опитайте отново.");
                }
            }

            while(rims == null) {
                try{
                    System.out.println("Изберете тип джанти (STEEL, ALLOY):");
                    rims = Rims.valueOf(scanner.nextLine().trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Невалиден избор. Опитайте отново.");
                }
            }

            String make = "";
            String model = "";
            int year = 0;
            double engineCapacity = 0; // куб. см
            double price = 0;
            boolean validYear = false;
            boolean validEngineCapacity = false;
            boolean validPrice = false;
            boolean validBattery = false;


            switch(typeInput){
                case "DIESEL":
                    while(make.isBlank() || model.isBlank() || !validYear || !validEngineCapacity || !validPrice) {
                        System.out.println("Въведете марка, модел, година, обем на двигателя (куб. см) и цена (разделени с интервал):");
                        String line = scanner.nextLine();
                        String[] parts = line.split(" ");
                        if(parts.length != 5) {
                            System.out.println("Невалиден брой параметри. Опитайте отново.");
                            continue;
                        }
                        make = parts[0];
                        model = parts[1];
                        try{
                            year = Integer.parseInt(parts[2]);
                            if(year >= 1886 && year <= 2050){
                                validYear = true;
                            } else {
                                System.out.println("Невалидна година. Опитайте отново.");
                                validYear = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалидна година. Опитайте отново.");
                        }

                        try{
                            engineCapacity = Double.parseDouble(parts[3]);
                            if(engineCapacity > 0){
                                validEngineCapacity = true;
                            } else {
                                System.out.println("Невалиден обем на двигателя. Опитайте отново.");
                                validEngineCapacity = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалиден обем на двигателя. Опитайте отново.");
                        }

                        try{
                            price = Double.parseDouble(parts[4]);
                            if(price > 0){
                                validPrice = true;
                            } else {
                                System.out.println("Невалидна цена. Опитайте отново.");
                                validPrice = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалидна цена. Опитайте отново.");
                    }

                    if(make.isBlank() || model.isBlank()){
                        System.out.println("Марка и модел не могат да бъдат празни. Опитайте отново.");
                    }

                    DieselCar dieselCar = new DieselCar(make, model, year, engineCapacity, price, acType, interior, rims);
                    dealership.addCar(dieselCar);
                    dieselCount++;
                    System.out.println("Добавена кола: " + dieselCar);
                    break;

                case "PETROL":
                    while(make.isBlank() || model.isBlank() || !validYear || !validEngineCapacity || !validPrice) {
                        System.out.println("Въведете марка, модел, година, обем на двигателя (куб. см) и цена (разделени с интервал):");
                        String line = scanner.nextLine();
                        String[] parts = line.split(" ");
                        if(parts.length != 5) {
                            System.out.println("Невалиден брой параметри. Опитайте отново.");
                            continue;
                        }
                        make = parts[0];
                        model = parts[1];
                        try{
                            year = Integer.parseInt(parts[2]);
                            if(year >= 1886 && year <= 2050){
                                validYear = true;
                            } else {
                                System.out.println("Невалидна година. Опитайте отново.");
                                validYear = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалидна година. Опитайте отново.");
                        }

                        try{
                            engineCapacity = Double.parseDouble(parts[3]);
                            if(engineCapacity > 0){
                                validEngineCapacity = true;
                            } else {
                                System.out.println("Невалиден обем на двигателя. Опитайте отново.");
                                validEngineCapacity = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалиден обем на двигателя. Опитайте отново.");
                        }

                        try{
                            price = Double.parseDouble(parts[4]);
                            if(price > 0){
                                validPrice = true;
                            } else {
                                System.out.println("Невалидна цена. Опитайте отново.");
                                validPrice = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалидна цена. Опитайте отново.");
                        }

                    if(make.isBlank() || model.isBlank()){
                        System.out.println("Марка и модел не могат да бъдат празни. Опитайте отново.");
                    }

                    PetrolCar petrolCar = new PetrolCar(make, model, year, engineCapacity, price, acType, interior, rims);
                    dealership.addCar(petrolCar);
                    petrolCount++;
                    System.out.println("Добавена кола: " + petrolCar);
                break;

                case "ELECTRIC":
                    double batteryCapacityKWh = 0;
                    while(make.isBlank() || model.isBlank() || !validYear || !validBattery || !validPrice) {
                        System.out.println("Въведете марка, модел, година, обем на двигателя (куб. см) и цена (разделени с интервал):");
                        String line = scanner.nextLine();
                        String[] parts = line.split(" ");
                        if(parts.length != 5) {
                            System.out.println("Невалиден брой параметри. Опитайте отново.");
                            continue;
                        }
                        make = parts[0];
                        model = parts[1];
                        try{
                            year = Integer.parseInt(parts[2]);
                            if(year >= 1886 && year <= 2050){
                                validYear = true;
                            } else {
                                System.out.println("Невалидна година. Опитайте отново.");
                                validYear = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалидна година. Опитайте отново.");
                        }

                         try{
                            batteryCapacityKWh = Double.parseDouble(parts[3]);
                            validBattery = batteryCapacityKWh > 0;
                            if(!validBattery) System.out.println("Капацитетът на батерията трябва да е положително число!");
                            } catch(NumberFormatException e){
                                System.out.println("Капацитетът на батерията трябва да е число!");
                                validBattery = false;
                            }

                        try{
                            price = Double.parseDouble(parts[4]);
                            if(price > 0){
                                validPrice = true;
                            } else {
                                System.out.println("Невалидна цена. Опитайте отново.");
                                validPrice = false;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Невалидна цена. Опитайте отново.");
                    }

                    if(make.isBlank() || model.isBlank()){
                        System.out.println("Марка и модел не могат да бъдат празни. Опитайте отново.");
                    }

                    ElectricCar electricCar = new ElectricCar(make, model, year, 0, price, batteryCapacityKWh, acType, interior, rims);
                    dealership.addCar(electricCar);
                    electricCount++;
                    System.out.println("Добавена кола: " + electricCar);
                    break;
            }
        }
    }

        System.out.println("Общо екотакса за всички коли: " + dealership.totalEcoTax());
    }
}

}
}