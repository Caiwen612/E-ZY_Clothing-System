import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class AuthCodeMultithreading extends Thread{

    public static int authCode;

    @Override
    public void run() {
        int min = 1000;
        int max = 9999;
        double secondsDelay = 20;
        double timeDif;

        // start timer
        Instant initialTime = Instant.now();

        authCode = min + (int) (Math.random() * ((max - min) + 1));
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("authCode.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert writer != null;
            writer.write(Integer.toString(authCode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {
            try {
                // finding the duration between new time and initial time
                Instant newTime = Instant.now();
                Duration timeElapsed = Duration.between(initialTime, newTime);
                timeDif = timeElapsed.toSeconds();


                // compare to see if the duration has exceeded 20 secs
                if (timeDif >= secondsDelay) {
                    authCode = min + (int) (Math.random() * ((max - min) + 1));
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter("authCode.txt"));
                    writer1.write(Integer.toString(authCode));
                    writer1.close();
                    // reset initial time
                    initialTime = Instant.now();
                }

            } catch (IOException e) {
                // pinpoint the exact line in which the method raised the exception
                e.printStackTrace();
            }
        }
    }
}
