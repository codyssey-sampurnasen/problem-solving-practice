import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class RateLimiter {
    private HashMap<String, Queue<Long>> userRequests = new HashMap<>();
    private int maxRequests;
    private long timeWindow; // in milliseconds

    public RateLimiter(int maxRequests, long timeWindow) {
        this.maxRequests = maxRequests;
        this.timeWindow = timeWindow;
    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();

        userRequests.putIfAbsent(userId, new LinkedList<>());
        Queue<Long> timestamps = userRequests.get(userId);

        // Remove old requests outside the time window
        while (!timestamps.isEmpty() && currentTime - timestamps.peek() > timeWindow) {
            timestamps.poll();
        }

        if (timestamps.size() < maxRequests) {
            timestamps.add(currentTime);
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter(3, 5000); // 3 requests per 5 sec

        String user = "user1";
        for (int i = 0; i < 5; i++) {
            System.out.println(limiter.allowRequest(user));
        }
    }
}
