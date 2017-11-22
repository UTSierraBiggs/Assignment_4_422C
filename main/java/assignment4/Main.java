package assignment4;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {
    final static String URLEndpoint = "http://kevinstwitterclient2.azurewebsites.net/api/products";

    /**
     * We will not use your Main class to test your code
     * Feel free to modify this as much as you want
     * Here is an example of how you might test the code from main
     * for Problem 1 and Problem 2
     */
    public static void main(String[] args) throws Exception {
//
//        // Problem 1: Returning Tweets from Server
//        assignment4.TweetReader reader = new assignment4.TweetReader();
//        List<assignment4.Tweets> tweetsList = reader.readTweetsFromWeb(URLEndpoint);
//        System.out.println(tweetsList);
//
//        // Problem 2:
//        // Filter Tweets by Username
//        assignment4.Filter filter = new assignment4.Filter();
//        List<assignment4.Tweets> filteredUser = filter.writtenBy(tweetsList,"kevinyee");
//        System.out.println(filteredUser);
//
//        // Filter by Timespan
//        Instant testStart = Instant.parse("2017-11-08T00:00:00Z");
//        Instant testEnd = Instant.parse("2017-11-09T12:00:00Z");
//        assignment4.Timespan timespan = new assignment4.Timespan(testStart,testEnd);
//        List<assignment4.Tweets> filteredTimeSpan = filter.inTimespan(tweetsList,timespan);
//        System.out.println(filteredTimeSpan);
//
//        //Filter by words containinng
//        List<assignment4.Tweets> filteredWords = filter.containing(tweetsList,Arrays.asList("good","luck"));
//        System.out.println(filteredWords);

        //Problem 3 Clique
        List<Tweets> customTweetList= new ArrayList<>();
        Tweets t1 = new Tweets();
        t1.setId(1);
        t1.setDate("2017-11-08T00:00:00Z");
        t1.setName("a");
        t1.setText("@b @c");

        Tweets t2 = new Tweets();
        t2.setId(2);
        t2.setDate("2017-11-08T00:00:00Z");
        t2.setName("b");
        t2.setText("@a @c");

        Tweets t3 = new Tweets();
        t3.setId(3);
        t3.setDate("2017-11-08T00:00:00Z");
        t3.setName("c");
        t3.setText("@a @b");

        customTweetList.add(t1);
        customTweetList.add(t2);
        customTweetList.add(t3);

        SocialNetwork test = new SocialNetwork();
        List<Set<String>> testlist = test.findCliques(customTweetList);
    }
}

