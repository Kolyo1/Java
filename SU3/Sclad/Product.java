package Sclad;

public class Product {
    String name;
    int quantity;
    int hazardLevel;

    public Product(String name, int quantity, int hazardLevel) {
        if (hazardLevel < 0 || hazardLevel > 10) {
            throw new IllegalArgumentException("Hazard level must be between 0 and 10.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.name = name;
        this.quantity = quantity;
        this.hazardLevel = hazardLevel;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public int getHazardLevel() { return hazardLevel; }

    @Override
    public String toString() {
        return "Product Name: " + name + ", Quantity: " + quantity + ", Hazard Level: " + hazardLevel;
    }
    public void storeProduct(Product p) {
        if (p == null) return;

        if (p.hazardLevel > 7) {
            throw new HazardousMaterialException("The product has a hazard level greater than 7: " + p.hazardLevel);
        }
        int newQuantity = this.quantity + p.quantity;
        if (newQuantity > 1000) {
            throw new OverStockException("Exceeding maximum stock limit of 1000 units. Current: " + this.quantity + ", Adding: " + p.quantity);
        }

        this.quantity = newQuantity;
    }
}



