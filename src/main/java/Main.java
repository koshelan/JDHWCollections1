import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    static volatile Boolean endOfWork = false;

    public static void main(String[] args) {

        Queue<Call> queue = new ConcurrentLinkedQueue<>();/* не блокирующая быстарая работа подходит к нашей задачи т.к.
         не очень много входящих звонков и много одновременных попыток записи  */

        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        es.submit(new Speсialist(queue, "Оператор 1"));
        es.submit(new Speсialist(queue, "Оператор 2"));
        es.submit(new Speсialist(queue, "Оператор 3"));
        ATS ats = new ATS(queue);
        ats.start();

        try {
            ats.join();
            System.out.println("АТС запончила работу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("очередь звонков пуста");
        endOfWork = true;
        es.shutdown();

    }

}
