package Application;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger counter = new AtomicInteger(100);

    public static String generateStudentId() {
        LocalDateTime now = LocalDateTime.now();

        String year = String.format("%02d", now.getYear() % 100);

        int uniqueNum = counter.getAndIncrement();

        return year + "BD" + uniqueNum;
    }

}
