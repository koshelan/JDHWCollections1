import java.util.Queue;

public class Speсialist extends Thread {

    private static final long TIME_OF_CALL = 4_000;
    Queue<Call> queue;


    public Speсialist(Queue<Call> queue, String name) {
        this.queue = queue;
        super.setName(name);
    }

    @Override
    public void run() {
        System.out.printf("%s начинает работу\n", getName());
        Call call;

        while (!Main.endOfWork) {
            if (!queue.isEmpty()) {
                call = queue.poll();
                if (call != null) {
                    System.out.printf("%s взял в работу звонок %s \n", getName(), call.getNumber());
                    try {
                        Thread.sleep(TIME_OF_CALL);
                    } catch (InterruptedException e) {
                    }
                    System.out.printf("%s закончил работу со звонком %s \n", getName(), call.getNumber());
                }
            }
        }
        System.out.printf("%s закончил работу\n", getName());
    }

}
