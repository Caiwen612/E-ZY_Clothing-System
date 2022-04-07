import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class AuthCodeMultithreading extends Thread{

    @Override
    public void run() {
        int authCode;
        int min = 1000;
        int max = 9999;
        double secondsDelay = 20;
        double timeDif;

        // start timer
        Instant initialTime = Instant.now();

        while (true) {
            try {
                // finding the duration between new time and initial time
                Instant newTime = Instant.now();
                Duration timeElapsed = Duration.between(initialTime, newTime);
                timeDif = timeElapsed.toSeconds();

                // compare to see if the duration has exceeded 20 secs
                if (timeDif >= secondsDelay) {
                    authCode = min + (int) (Math.random() * ((max - min) + 1));
                    BufferedWriter writer = new BufferedWriter(new FileWriter("authCode.txt"));
                    writer.write(authCode);
                    writer.close();
                }

                // reset initial time
                initialTime = Instant.now();
            } catch (IOException e) {
                // pinpoint the exact line in which the method raised the exception
                e.printStackTrace();
            }
        }
    }
}
