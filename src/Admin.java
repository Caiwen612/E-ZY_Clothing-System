import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Admin extends People {









    // methods
    public boolean vldDoubleAuth(String doubleAuthCode) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("authCode.txt"));
            doubleAuthCode = reader.readLine();
            reader.close();
        } catch (IOException e) {
            // pinpoint the exact line in which the method raised the exception
            e.printStackTrace();
        }
        return false;
    }
}
