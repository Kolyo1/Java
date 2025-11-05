package Sclad;

import java.util.*;

public class MainS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добави продукт\n2. Показване на всички продукти\n0. Изход");
            System.out.print("Изберете опция: ");
            String n = scanner.nextLine();

            switch (n) {
            case "1. Добави Продукт":
                System.out.print("Въведете брой продукти за добавяне: ");
                int a = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < a; i++) {
                    System.out.print("Въведете име на продукт: ");
                    String name = scanner.nextLine();
                    System.out.print("Въведете количество: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    System.out.print("Въведете ниво на опасност (0-10): ");
                    int hazardLevel = Integer.parseInt(scanner.nextLine());
                        Product product;
                        try {
                            product = new Product(name, quantity, hazardLevel);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Внимание: Неуспешно добавяне на продукт — " + e.getMessage());
                            continue;
                        }
                        Product existing = null;
                        for (Product p : products) {
                            if (p.getName().equals(product.getName())) {
                                existing = p;
                                break;
                            }
                        }

                        if (existing != null) {
                            try {
                                existing.storeProduct(product);
                                System.out.println("Продуктът е успешно обновен: " + existing.getName());
                            } catch (OverStockException | HazardousMaterialException e) {
                                System.out.println("Внимание: Неуспешно добавяне на продукт — " + e.getMessage());
                            }
                        } else {
                            if (product.getHazardLevel() > 7) {
                                System.out.println("Внимание: Неуспешно добавяне на продукт — опасно ниво > 7: " + product.getHazardLevel());
                                continue;
                            }
                            if (product.getQuantity() > 1000) {
                                System.out.println("Внимание: Неуспешно добавяне на продукт — количество над 1000: " + product.getQuantity());
                                continue;
                            }
                            products.add(product);
                            System.out.println("Продуктът е успешно добавен: " + product.getName());
                        }
                    }
                break;

            case "2. Показване на всички продукти":
                for (Product p : products) {
                    System.out.println(p);
                }
                break;
        
            case "0. Изход":
                System.out.println("Изход от програмата.");
                scanner.close();
                return;
            default:
                break;
            }
    }
    }
}

