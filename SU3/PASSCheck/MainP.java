package PASSCheck;

import java.util.Scanner;

public class MainP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("admin", "password123");
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3 && !loggedIn) {
            System.out.print("Въведете потребителско име: ");
            String username = scanner.nextLine();
            System.out.print("Въведете парола: ");
            String password = scanner.nextLine();

            if (user.login(username, password)) {
                loggedIn = true;
                System.out.println("Успешен вход! Добре дошли, " + user.getUsername() + ".");
            } else {
                attempts++;
                System.out.println("Грешно потребителско име или парола. Опит " + attempts + " от 3.");
            }
        }
        if(!loggedIn) {
            try{
                throw new UserBlockedException("Потребителят е блокиран след 3 неуспешни опита за вход.");
            } catch (UserBlockedException e){
                System.out.println(e.getMessage());
            }
        }


    scanner.close();
}
}
