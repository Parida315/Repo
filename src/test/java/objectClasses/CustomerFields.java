package objectClasses;

public class CustomerFields {
    private String lastName, title, firsName,email, phone, cellPhone, password, confirmPassword;

    public CustomerFields(String lastName, String firsName, String email) {
        this.lastName = lastName;
        this.firsName = firsName;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getFirsName() {
        return firsName;
    }

    public String getEmail() {
        return email;
    }
}
