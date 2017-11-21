package assignment4;

import java.util.*;

/**
 * Social Network consists of methods that filter users matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Get K most followed Users.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @param k
     *            integer of most popular followers to return
     * @return the set of usernames who are most mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getName()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like ethomaz@utexas.edu does NOT
     *         contain a mention of the username.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static List<String> findKMostFollower(List<assignment4.Tweets> tweets, int k) {
        List<String> mostFollowers = new ArrayList<String>();
        return mostFollowers;
    }

    /**
     * Find all cliques in the social network.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     *
     * @return list of set of all cliques in the graph
     */
    public static List<Set<String>> findCliques(List<assignment4.Tweets> tweets) {

        //Create a Map of Nodes and initialize them by taking the usernames from tweets
        Map<String,Node> graph = new HashMap<>();
        for(int j = 0 ; j < tweets.size(); j ++){
            if(graph.get(tweets.get(j).getName()) == null){
                graph.put(tweets.get(j).getName(), new Node());
            }
        }



        //For each tweet, get corresponding node from graph depending on tweet's username, find tags in
        //tweet, and then set the people tagged as edges in the graph node
        for (int i = 0 ; i < tweets.size() ; i ++ ){
            Node username = graph.get(tweets.get(i).getName());
            String[] splitTweetText= tweets.get(i).getText().split(" ");
            for(int k = 0 ; k < splitTweetText.length; k ++){
                String filteredString = splitTweetText[k].replaceAll("[^A-Za-z0-9@]", "");
                if(graph.get(filteredString) ==null) {
                    List<Node> temp = username.getEdges();
                    temp.add(new Node(filteredString, new ArrayList<Node>()));
                    username.setEdges(temp);
                }else{
                    List<Node> temp = username.getEdges();
                    temp.add(graph.get(filteredString));
                    username.setEdges(temp);
                }
            }
        }

        List<Set<String>> result = new ArrayList<Set<String>>();
        return result;
    }

    public boolean Contains(String key, ArrayList<String> valueList){
        for (int i = 0 ; i < valueList.size(); i ++){
            if (valueList.get(i).equals(key)){
                return true;
            }
        }
        return false;
    }
}


