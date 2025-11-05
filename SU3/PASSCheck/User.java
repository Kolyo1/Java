package PASSCheck;

public class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }


    @Override
    public String toString() {
        return "Username: " + username + ", Password: " + password;     
    }

    public void PassCheck(String password) throws WrongPasswordException {
        if (!this.password.equals(password)) {
            throw new WrongPasswordException("Invalid password for user: " + username);
        }
    }

    

}
