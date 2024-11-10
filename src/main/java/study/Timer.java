package study;

public class Timer {
    public static long countTimeMillis(Runnable runnable) {
        long currentTime = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - currentTime;
    }

    public static long countTimeNanos(Runnable runnable) {
        long currentTime = System.nanoTime();
        runnable.run();
        return System.nanoTime() - currentTime;
    }
}
