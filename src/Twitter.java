import java.util.*;

public class Twitter {
    private static final int LIMIT = 10;
    // easy to find if user exists
    // <key=userId, value=User>
    private final Map<Integer, User> userIndices;

    // tweet link-list to next tweet so that we can save a lot of time
    // when we execute getNewsFeed(userId)
    // the reason is, when we get all the lists of tweets from the people, we just
    // need to offer the tweet_head of each list into the minHeap for operations
    private static class Tweet {
        private static long timeStamp = 0;
        public int id;
        public long creationTime;
        public Tweet next;

        public Tweet(int id) {
            this.id = id;
//            this.creationTime = System.currentTimeMillis();
            creationTime = timeStamp++;
        }
    }

    public static class User {
        public int id;
        public Set<Integer> following;
        public Tweet mostRecentTweet;

        public User(int id) {
            this.id = id;
            following = new HashSet<>();
            follow(id); // follow himself, this is smart
        }

        public void follow(int id) {
            following.add(id);
        }

        public void unfollow(int id) {
            following.remove(id);
        }

        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = mostRecentTweet;
            mostRecentTweet = t;
        }

        boolean hasNoPost() {
            return mostRecentTweet == null;
        }
    }

    public Twitter() {
        userIndices = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        User u = userIndices.computeIfAbsent(userId, k -> new User(userId));
        u.post(tweetId);
    }

    // firstly get all tweets lists from one user including itself and all people it followed.
    // Second add all heads into a max heap. Every time we poll a tweet with
    // the largest timeStamp from the heap, then we add its next tweet into the heap.
    // So after adding all heads we only need to add 9 tweets at most into this
    // heap before we get the 10 most recent tweets.
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        User u = userIndices.get(userId);
        if (u == null) {
            return res;
        }
        Set<Integer> followeeIds = u.following;
        PriorityQueue<Tweet> pq = new PriorityQueue<>(followeeIds.size(), (a, b) -> Long.compare(b.creationTime, a.creationTime));
        for (int followeeId : followeeIds) {
            User followee = userIndices.get(followeeId);
            if (followee.hasNoPost()) {
                continue;
            }
            Tweet mostRecentTweet = followee.mostRecentTweet;
            pq.offer(mostRecentTweet);
        }

        int num = 0;
        while (!pq.isEmpty() && num++ < LIMIT) {
            Tweet latestTweet = pq.poll();
            res.add(latestTweet.id);
            if (latestTweet.next != null) {
                pq.offer(latestTweet.next);
            }
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        userIndices.computeIfAbsent(followeeId, k -> new User(followeeId));
        userIndices.computeIfAbsent(followerId, k -> new User(followerId)).follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        // in our design, a user follows itself by default
        // therefore, pay attention to this corner case
        if (followerId == followeeId) {
            return;
        }
        User u = userIndices.get(followerId);
        if (u != null) {
            u.unfollow(followeeId);
        }
    }
}
