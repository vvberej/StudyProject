package ThreadPool;

import java.util.*;

public class ScalableThreadPool implements ThreadPool {
    private final Object lock = new Object();
    private final int minThreadCount;
    private final int maxThreadCount;
    private final TaskQueue tasks = new TaskQueue();
    private volatile int currentThreadNumber;
    private final List<Worker> workers = new ArrayList<>();

    public ScalableThreadPool(int minThreadCount, int maxThreadCount) {
        this.minThreadCount = minThreadCount;
        this.maxThreadCount = maxThreadCount;
    }

    @Override
    public void start() {
        for (int i = 0; i < minThreadCount; i++) {
            createAndStartWorker();
        }
    }

    private void createAndStartWorker() {
        Worker w = new Worker();
        workers.add(w);
        w.start();
        currentThreadNumber++;
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (lock) {
            tasks.add(runnable);
            lock.notify();
        }
    }

    private class TaskQueue {
        private final Queue<Runnable> tasks = new ArrayDeque<>();

        public void add(Runnable task) {
            tasks.add(task);

            if (tasks.size() > 1 && currentThreadNumber < maxThreadCount) {
                createAndStartWorker();
            }
        }

        public Runnable poll() {
            Runnable task = tasks.poll();

            return task;
        }

        private void deleteWorker() {
            for (ListIterator<Worker> iterator = workers.listIterator(); iterator.hasNext();) {
                Worker worker = iterator.next();
                if (worker.isWaitNewTask()) {
                    worker.interrupt();
                    iterator.remove();
                    currentThreadNumber--;
                    return;
                }
            }
            return;
        }

        public boolean isEmpty() {
            boolean empty = tasks.isEmpty();
            if (empty && currentThreadNumber > minThreadCount) {
                deleteWorker();
            }
            return empty;
        }
    }

    public void interrupt() {
        workers.forEach(Thread::interrupt);
    }

    private class Worker extends Thread {
        private volatile boolean waitNewTask;

        public boolean isWaitNewTask() {
            return waitNewTask;
        }

        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) return;
                Runnable task;
                synchronized (lock) {
                    while (tasks.isEmpty()) {
                        try {
                            waitNewTask = true;
                            lock.wait();
                        } catch (InterruptedException e) {
                            // значит поток прервали, с  целью удаления из пула
                            System.out.println("завершаю работу");
                            return;
                        }
                    }
                    waitNewTask = false;
                    task = tasks.poll();
                }

                execute(task);
            }
        }

        private void execute(Runnable task) {
            if (task != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    writeInLog(e);
                }
            }
        }

        private void writeInLog(Exception e) {
            System.out.println(e);
        }
    }

}
