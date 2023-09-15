import models.ProcessQueue;

public class Main {
    public static void main(String[] args) {
        ProcessQueue processQueue = new ProcessQueue(50);
        processQueue.run();
    }
}
