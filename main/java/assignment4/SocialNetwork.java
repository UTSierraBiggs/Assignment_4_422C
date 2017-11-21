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
        //Create a hashmap and load all the users that have made tweets as keys
        HashMap<String, ArrayList<String>> whoTagsWho = new HashMap();
        boolean keyListComparison = false;
        for(int j = 0 ; j < tweets.size(); j ++){
            if(whoTagsWho.get(tweets.get(j).getName()) == null){
                whoTagsWho.put(tweets.get(j).getName(), new ArrayList<String>());
            }
        }

        // for each user, a, (as keys) in the hashmap, compare it to every user tagged in each
        // tweet and if they ARE tagged in that tweet, made by user b, add b
        // to the String arraylist of user a in the hashmap
        for (Map.Entry<String, ArrayList<String>> entry : whoTagsWho.entrySet()) {
            for (int i = 0 ; i < tweets.size() ; i ++ ){
                String[] splitTweetText= tweets.get(i).getText().split(" ");
                for(int k = 0 ; k < splitTweetText.length; k ++){
                    String filteredString = splitTweetText[k].replaceAll("[^A-Za-z0-9@]", "");
                    if(entry.getKey().equals(filteredString)){
                        ArrayList<String> temp = whoTagsWho.get(entry.getKey());
                        temp.add(filteredString);
                        whoTagsWho.put(entry.getKey(), temp);
                    }
                }
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : whoTagsWho.entrySet()) {
            for (int i = 0 ; i < entry.getValue().size(); i ++){
                if (whoTagsWho.get(entry.getValue().get(i)) != null) {
                    for (int j = 0; j < whoTagsWho.get(entry.getValue().get(i)).size(); j++){
                        keyListComparison = true;
                    }
                }
            }
            for(int k = 0 ; k < entry.getValue().size(); k ++){
                for(int l = k+1; l < entry.getValue().size(); l++ ){
                    if (whoTagsWho.get(entry.getValue().get(l)) != null) {

                    }
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


