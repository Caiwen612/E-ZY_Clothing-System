package user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class People implements Cloneable{
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
    public static boolean vldEmail(String email) {
        Pattern emailPtn = Pattern.compile("^(.+)@(.+)$");
        Matcher emailMatch = emailPtn.matcher(email);

        if (emailMatch.matches()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean vldPassword(String password) {
        boolean hasAlpha = false;
        boolean hasNum = false;
        boolean enoughLength = false;

        if (password.length() >= 6) {
            enoughLength = true;
        }

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasNum = true;
                break;
            }
        }

        for (int j = 0; j < password.length(); j++) {
            if (Character.isLetter(password.charAt(j))) {
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

    public static boolean vldPhoneNo(String phoneNo) {
        Pattern phonePtn = Pattern.compile("[0-9]{3}-[0-9]{7,8}");
        Matcher phoneNoMatch = phonePtn.matcher(phoneNo);

        if (phoneNoMatch.matches()) {
            return true;
        }
        else {
            return false;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        People people = (People) o;

        if (name != null ? !name.equals(people.name) : people.name != null) return false;
        if (email != null ? !email.equals(people.email) : people.email != null) return false;
        if (password != null ? !password.equals(people.password) : people.password != null) return false;
        return phoneNo != null ? phoneNo.equals(people.phoneNo) : people.phoneNo == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        return result;
    }

    @Override
    public People clone() {
        try {
            People clone = (People) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
