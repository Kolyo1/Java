import java.util.*;

class Employee{
    String name;
    String emoloyeeId; // EMP - ####
    int experience; // in years
    String email;

    Employee(String name, String emoloyeeId, int experience, String email){
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Employee name cannot be null or empty");
        }
        if (!emoloyeeId.matches("^EMP-\\d{5}$")) {
            throw new InvalidEmployeeDataException("Employee ID must be in format EMP-#####");
        }
        if (experience < 0) {
            throw new InvalidEmployeeDataException("Experience cannot be negative");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException("Email cannot be null or empty");
        }
        
        this.name = name.trim();
        this.emoloyeeId = emoloyeeId;
        this.experience = experience;
        this.email = email;
    }

    public String getName(){return name;}
    public String getEmployeeId(){return emoloyeeId;}
    public int getExperience(){return experience;}
    public String getEmail(){return email;}

}

class Company{
    String companyName;
    String domain; // email : ex. @company.com
    List<Employee> employees;

    Company(String companyName, String domain){
        this.companyName = companyName;
        this.domain = domain;
        this.employees = new ArrayList<>();
    }

    public String getCompanyName(){return companyName;}
    public String getDomain(){return domain;}


    void addEmployee(Employee e){
        if (!validateEmail(e)) {
            System.out.println("Invalid email format. Email must end with " + domain);
            System.out.println("Please enter a new email for " + e.name + ": ");
            try (Scanner sc = new Scanner(System.in)) {
                String newEmail = sc.nextLine();
                if (!validateEmail(new Employee(e.name, e.emoloyeeId, e.experience, newEmail))) {
                    throw new InvalidEmailException("Invalid email format. Must end with " + domain);
                }
                e.email = newEmail;
            }
        }
        employees.add(e);
        System.out.println("Employee added successfully.");
    }

    void listEmployees(){
        for(Employee e : employees){
            System.out.println("Name: " + e.name + ", ID: " + e.emoloyeeId + ", Experience: " + e.experience + " years, Email: " + e.email);
        }
    }

    Employee findById(String id){
        for(Employee e : employees){
            if(e.emoloyeeId.equals(id)){
                return e;
            }
        }
        return null;
    }

    boolean validateEmail(Employee e){
        return e.email.matches("^[\\w.-]+"+domain.replace(".", "\\.") + "$");
    }

    void checkInsuranceEligibility(Employee e) {
        if (e == null) {
            throw new InvalidEmployeeDataException("Employee cannot be null");
        }
        if (e.experience > 1) {
            System.out.println(e.name + " is eligible for insurance.");
        } else {
            throw new NotEligibalException(e.name + " is not eligible for insurance (requires > 1 year experience).");
        }
    }

}


class NotEligibalException extends RuntimeException{
    NotEligibalException(String message){
        super(message);
    }
}

class InvalidEmployeeDataException extends RuntimeException {
    InvalidEmployeeDataException(String message) {
        super(message);
    }
}

class InvalidEmailException extends RuntimeException {
    InvalidEmailException(String message) {
        super(message);
    }
}


public class Main {
    public static void main(String[] args) {
        Company company = null;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Enter Company\n" +
                               "2. Add Employee\n" +
                               "3. List Employees\n" +
                               "4. Find Employee by ID\n" +
                               "5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Enter company name: ");
                    String companyName = scanner.nextLine();
                    System.out.print("Enter email domain (e.g., @company.com): ");
                    String domain = scanner.nextLine();
                    company = new Company(companyName, domain);
                    System.out.println("Company created successfully.");
                    break;
                case 2:
                    if (company == null){
                        System.out.println("Please enter company details first.");
                        break;
                    }
                    try {
                        System.out.print("Enter employee name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter employee ID (EMP-#####): ");
                        String empId = scanner.nextLine();
                        System.out.print("Enter years of experience: ");
                        int experience = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter employee email: ");
                        String email = scanner.nextLine();

                        Employee employee = new Employee(name, empId, experience, email);
                        company.addEmployee(employee);
                    } catch (InvalidEmployeeDataException | InvalidEmailException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    if (company == null){
                        System.out.println("Please enter company details first.");
                        break;
                    }
                    company.listEmployees();
                    break;
                case 4:
                    if(company == null){
                        System.out.println("Please enter company details first.");
                        break;
                    }
                    System.out.print("Enter employee ID to search: ");
                    String searchId = scanner.nextLine();
                    Employee foundEmployee = company.findById(searchId);
                    if(foundEmployee != null){
                        System.out.println("Employee found: Name: " + foundEmployee.name + ", ID: " + foundEmployee.emoloyeeId + ", Experience: " + foundEmployee.experience + " years, Email: " + foundEmployee.email);
                    } else {
                        System.out.println("Employee with ID " + searchId + " not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
             }
        }
    }
}
