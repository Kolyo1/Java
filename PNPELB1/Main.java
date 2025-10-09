import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        // Car car1=new Car("BMW");
        // Car car2=new Car("Audi");
        // Car car3=new Car("Mercedes");
        // System.out.println(Car.getCount());

        // Scanner scanner = new Scanner(System.in);
        // BankAccount account1=new BankAccount();
        // account1.OwnerName="John Doe";
        // account1.Balance=1000.0;
        // double amount = scanner.nextDouble();
        // System.out.println("Owner: "+account1.OwnerName);
        // System.out.println("Balance: "+account1.Balance);
        // System.out.println(String.format("Interest on %.2f: %.2f",amount ,BankAccount.calculateInterest(amount)));
        // double newBalance = BankAccount.calculateInterest(amount) + account1.Balance;
        // System.out.println("New Balance: "+newBalance);
        // scanner.close();

        Student student1=new Student("Alice", 123456);
        Student student2=new Student("Bob", 234567);
        Student student3=new Student("Charlie", 345678);
        System.out.println("Total Students: %d" + Student.getTotalStudents());
    }
}

