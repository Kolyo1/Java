package KPK;

import java.util.*;

public class MainC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ConfigUnit> configUnits = new ArrayList<>();

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добави конфигурационен елемент\n2. Редактирай конфигурация\n3. Показване на всички конфигурации\n0. Изход");
            System.out.print("Изберете опция: ");
            String n = scanner.nextLine();

            switch (n) {
                case "1. Добави конфигурационен елемент":
                    System.out.print("Въведете брой елементи за добавяне: ");
                    int a = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < a; i++) {
                        System.out.print("Въведете ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Въведете ключ: ");
                        String key = scanner.nextLine();
                        System.out.print("Въведете стойност: ");
                        String value = scanner.nextLine();
                        ConfigUnit configUnit = new ConfigUnit(id, key, value);
                        configUnits.add(configUnit);
                        System.out.println("Добавен елемент: " + configUnit);
                    }
                    break;

                case "2. Редактирай конфигурация":
                    System.out.print("Въведете ID на конфигурационен елемент за редакция: ");
                    int targetId = Integer.parseInt(scanner.nextLine());

                    ConfigUnit found = null;
                    for (ConfigUnit cu : configUnits) {
                        if (cu.id == targetId) {
                            found = cu;
                            break;
                        }
                    }

                    if (found == null) {
                        System.out.println("Конфигурационен елемент с това ID не е намерен.");
                    } else {
                        System.out.print("Въведете нов ключ: ");
                        String newKey = scanner.nextLine();
                        System.out.print("Въведете нова стойност: ");
                        String newValue = scanner.nextLine();

                        found.key = newKey;
                        found.value = newValue;
                        System.out.println("Елементът е обновен: " + found);
                    }
                    break;

                case "3. Показване на всички конфигурации":
                    for (ConfigUnit cu : configUnits) {
                        System.out.println(cu);
                    }
                    break;

                case "0. Изход":
                    System.out.println("Изход от програмата.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невалидна опция.");
                    break;
            }
        }
    }
}