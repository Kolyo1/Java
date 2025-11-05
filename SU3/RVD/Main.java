package RVD;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Flight> flights = new ArrayList<>();
        Queue<Flight> departureQueue = new LinkedList<>();

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добави полет\n2. Изтегли полет за навигиране\n3. Смени статус на полет\n4. Показване на всички полети \n0. Изход");
            System.out.print("Изберете опция: ");
            String n = scanner.nextLine();

            switch (n) {
                case "1. Добави полет":
                    System.out.print("Въведете брой полети за добавяне: ");
                    int a = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < a; i++) {
                        System.out.print("Номер на полет: ");
                        String flightNumber = scanner.nextLine();
                        System.out.print("Час на заминаване: ");
                        String departureTime = scanner.nextLine();
                        System.out.print("Дестинация: ");
                        String destination = scanner.nextLine();
                        System.out.print("Статус (PARKED, TAXING, RUNWAY, DEPARTED): ");
                        String statuss = scanner.nextLine().toUpperCase();
                        try {
                            status flightStatus = status.valueOf(statuss);
                            Flight flight = new Flight(flightNumber, departureTime, destination, flightStatus);
                            flights.add(flight);
                            departureQueue.add(flight);
                            System.out.println("Полетът е добавен: " + flightNumber);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Невалиден статус. Полетът не е добавен.");
                        }
                    }

                    break;
    
        case "2. Изтегли полет за навигиране":
                Flight nextFlight = departureQueue.poll();
                if (nextFlight == null) {
                    System.out.println("Няма полети в опашката за навигиране.");
                } else {
                    System.out.println("Следващ полет за навигиране:");
                    System.out.println("Flight Number: " + nextFlight.flightNumber);
                    System.out.println("Departure Time: " + nextFlight.departureTime);
                    System.out.println("Destination: " + nextFlight.destination);
                    System.out.println("Status: " + nextFlight.flightStatus);
                    System.out.println();
                }
            break;
            
        case "3. Смени статус на полет":
                System.out.println("Въведете номер на полет:");
                String targetFlightNumber = scanner.nextLine();

                Flight found = null;
                for (Flight f : flights) {
                    if (f.flightNumber.equals(targetFlightNumber)) {
                        found = f;
                        break;
                    }
                }

                if (found == null) {
                    System.out.println("Полет с този номер не е намерен.");
                } else {
                    System.out.println("Текущ статус: " + found.flightStatus);
                    System.out.println("Въведете нов статус (PARKED, TAXING, RUNWAY, DEPARTED):");
                    String newStatusStr = scanner.nextLine().toUpperCase();
                    try {
                        status newStatus = status.valueOf(newStatusStr);
                        found.flightStatus = newStatus;
                        System.out.println("Статусът е обновен: " + found.flightStatus);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Невалиден статус. Статусът не е променен.");
                    }
                }

            break;

        case "4. Показване на всички полети ":
                for (Flight f : flights) {
                    System.out.println("Flight Number: " + f.flightNumber);
                    System.out.println("Departure Time: " + f.departureTime);
                    System.out.println("Destination: " + f.destination);
                    System.out.println("Status: " + f.flightStatus);
                    System.out.println();
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
