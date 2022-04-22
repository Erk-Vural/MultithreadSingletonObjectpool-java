import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        final DataTimeFormatResourcePool pool = new DataTimeFormatResourcePool(2, true);

        Callable<Date> task = () -> pool.convert("20130224");

        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            results.add(exec.submit(task));
        }
        exec.shutdown();
        try {
            for (Future<Date> result : results) {
                System.out.println(result.get());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}