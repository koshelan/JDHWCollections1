import java.util.Queue;

public class ATS extends Thread {

    private static final int NUMBER_OF_CALLS = 10;
    private static final long TIME_BETWEEN_CALLS = 1_000;
    Queue<Call> queue;

    public ATS(Queue<Call> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUMBER_OF_CALLS; i++) {
            try {
                Thread.sleep(TIME_BETWEEN_CALLS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Поступил звонок " + i);
            queue.add(new Call(i));
        }

    }
}
