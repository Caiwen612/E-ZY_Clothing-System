import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class People {
    private String name;
    private String email;
    private String password;
    private String phoneNo;

    public People() {
        this("", "", "", "");
    }

    public People(String name, String email, String password, String phoneNo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    // methods
    public boolean vldEmail() {
        Pattern emailPtn = Pattern.compile("^(.+)@(.+)$");
        Matcher emailMatch = emailPtn.matcher(this.email);

        if (emailMatch.matches()) {
            return true;
        }
        else {
            System.out.println("The email is not valid.");
            return false;
        }
    }

    public boolean vldPassword() {
        boolean hasAlpha = false;
        boolean hasNum = false;
        boolean enoughLength = false;

        if (this.password.length() >= 6) {
            enoughLength = true;
        }

        for (int i = 0; i < this.password.length(); i++) {
            if (Character.isDigit(this.password.charAt(i))) {
                hasNum = true;
                break;
            }
        }

        for (int j = 0; j < this.password.length(); j++) {
            if (Character.isLetter(this.password.charAt(j))) {
                hasAlpha = true;
                break;
            }
        }

        if (hasAlpha == true && hasNum == true && enoughLength == true) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean vldPhoneNo() {
        Pattern phonePtn = Pattern.compile("[0-9]{3}-[0-9]{7,8}");
        Matcher phoneNoMatch = phonePtn.matcher(this.phoneNo);

        if (phoneNoMatch.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(name, people.name) && Objects.equals(email, people.email) && Objects.equals(password, people.password) && Objects.equals(phoneNo, people.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, phoneNo);
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
