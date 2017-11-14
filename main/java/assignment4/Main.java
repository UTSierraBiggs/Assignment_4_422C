package assignment4;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Main {
    final static String URLEndpoint = "http://kevinstwitterclient2.azurewebsites.net/api/products";

    /**
     * We will not use your Main class to test your code
     * Feel free to modify this as much as you want
     * Here is an example of how you might test the code from main
     * for Problem 1 and Problem 2
     */
    public static void main(String[] args) throws Exception {

        // Problem 1: Returning Tweets from Server
        assignment4.TweetReader reader = new assignment4.TweetReader();
        List<assignment4.Tweets> tweetsList = reader.readTweetsFromWeb(URLEndpoint);
        System.out.println(tweetsList);

        // Problem 2:
        // Filter Tweets by Username
        assignment4.Filter filter = new assignment4.Filter();
        List<assignment4.Tweets> filteredUser = filter.writtenBy(tweetsList,"kevinyee");
        System.out.println(filteredUser);

        // Filter by Timespan
        Instant testStart = Instant.parse("2017-11-11T00:00:00Z");
        Instant testEnd = Instant.parse("2017-11-12T12:00:00Z");
        assignment4.Timespan timespan = new assignment4.Timespan(testStart,testEnd);
        List<assignment4.Tweets> filteredTimeSpan = filter.inTimespan(tweetsList,timespan);
        System.out.println(filteredTimeSpan);

        //Filter by words containinng
        List<assignment4.Tweets> filteredWords = filter.containing(tweetsList,Arrays.asList("good","luck"));
        System.out.println(filteredWords);
    }
}

