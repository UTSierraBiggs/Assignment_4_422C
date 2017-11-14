package assignment4;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * TweetReader contains method used to return tweets from method
 * Do not change the method header
 */
public class TweetReader {

    /**
     * Find tweets written by a particular user.
     *
     * @param url
     *            url used to query a GET Request from the server
     * @return return list of tweets from the server
     *
     */
    //set URL, open connection at URL, create "response" string, "newResponse" = "response" - all square brackets,
    //create "grabbedTweet" from individual tweets from newResponse, make all field names lowercase,
    //use Jackson's ObjectMapper to map field names in JSON from website to members of Tweets,
    //put Tweet into list: "tweetList", and validate tweet
    public static List<assignment4.Tweets> readTweetsFromWeb(String url) throws Exception {
        List<Tweets> tweetList = new ArrayList<assignment4.Tweets>();
        URL website = new URL(url);
        URLConnection connection = website.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String newResponse = response.toString().replace("[","");
        newResponse = newResponse.toString().replace("]", "");

        String[] grabbedTweet = newResponse.split("}");
        for (int i = 0; i < grabbedTweet.length; i++) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = grabbedTweet[i] += "}";
            if (jsonString.charAt(0) == ',') {
                jsonString = jsonString.substring(1);
            }
            jsonString = jsonString.replace("Id", "id");
            jsonString = jsonString.replace("Name", "name");
            jsonString = jsonString.replace("Date", "date");
            jsonString = jsonString.replace("Text", "text");
            Tweets tweets = mapper.readValue(jsonString, Tweets.class);
            if (tweets != null){
                if(canParseDate(tweets.getDate()) && (canParseName(tweets.getName())) && (canParseText(tweets.getText()))){

                }
            }
        }


        return tweetList;
    }

    public static boolean canParseDate(String date){
        String startDateString = date;
        DateFormat df = new SimpleDateFormat("YYYY-MM-DDT00:00:00Z");
        Date startDate;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean canParseName(String name){
        for(int i = 0; i < name.length(); i ++){
            if(!Character.isLetterOrDigit(name.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean canParseText(String text){
        if(text.length() <= 140) {
            return true;
        }
        return false;
    }
}
