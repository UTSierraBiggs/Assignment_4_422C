package assignment4;

import java.lang.reflect.Array;
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
        Map<String,Integer> followerMap = new HashMap<>();
        List<String> allUsersRanked = new ArrayList<>();

        for (int i = 0 ; i < tweets.size() ; i ++ ){
            String[] splitTweetText= tweets.get(i).getText().split(" ");
            for(int j = 0 ; j < splitTweetText.length; j ++){
                String filteredString = splitTweetText[j].replaceAll("[^A-Za-z0-9@]", "");
                if (!filteredString.isEmpty()) {
                    if (filteredString.charAt(0) == '@') {
                        if (followerMap.get(filteredString) == null) {
                            followerMap.put(filteredString, 1);
                        } else {
                            followerMap.put((filteredString),followerMap.get(filteredString) +1);
                        }
                    }
                }
            }
        }

        while(allUsersRanked.size() < k) {
            int currentMostFollowed = 0;
            for (Map.Entry<String, Integer> entry : followerMap.entrySet()) {
                if (entry.getValue() > currentMostFollowed) {
                    currentMostFollowed = entry.getValue();
                }
            }

            for (Map.Entry<String, Integer> entry : followerMap.entrySet()) {
                if (entry.getValue() == currentMostFollowed) {
                    allUsersRanked.add(entry.getKey());
                    followerMap.put(entry.getKey(), 0);
                }
            }
        }


        List<String> mostFollowers = new ArrayList<String>();
        mostFollowers = allUsersRanked;
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
                graph.put("@" + tweets.get(j).getName(), new Node("@" + tweets.get(j).getName(), new ArrayList<Node>()));
            }
        }

        //For each tweet, get corresponding node from graph depending on tweet's username, find tags in
        //tweet, and then set the people tagged as edges in the graph node
        for (int i = 0 ; i < tweets.size() ; i ++ ){
            Node username = graph.get("@" + tweets.get(i).getName());
            String[] splitTweetText= tweets.get(i).getText().split(" ");
            for(int k = 0 ; k < splitTweetText.length; k ++){
                String filteredString = splitTweetText[k].replaceAll("[^A-Za-z0-9@]", "");
                if (!filteredString.isEmpty()) {
                    if (filteredString.charAt(0) == '@') {
                        if (graph.get(filteredString) == null) {
                            List<Node> temp = username.getEdges();
                            temp.add(new Node(filteredString, new ArrayList<Node>()));
                            username.setEdges(temp);
                        } else {
                            List<Node> temp = username.getEdges();
                            temp.add(graph.get(filteredString));
                            username.setEdges(temp);
                        }
                    }
                }
            }
        }
        List<String> pathArray = new ArrayList<>();
        HashSet<String> noDuplicatePaths = new HashSet<>();
        // Find one-way loops between nodes
        // DFS will end when you return to the starting node

        List<Node> nodes = new ArrayList<>(graph.values());
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getEdges().size(); j++) {
                nodes.get(i).setVisited(true);
                dfs(nodes.get(i).getName(), nodes.get(i), nodes.get(i).getEdges().get(j), pathArray);
                nodes.get(i).setVisited(false);
            }
        }

        HashSet<String> removeList = new HashSet<>();
        HashSet<String> duplicateSet = new HashSet<>();
        // Do subset
        for (int i = 0; i < pathArray.size(); i++) {
            for (int j = 0; j < pathArray.size(); j++) {
                if (!pathArray.get(i).equals(pathArray.get(j))) {
                    String[] splitPath= pathArray.get(i).split(",");
                    int counter = 0;
                    for(int k = 0 ; k < splitPath.length; k ++) {
                        if (pathArray.get(j).contains(splitPath[k])){
                            counter ++;
                        }
                    }
                    if(counter == splitPath.length){
                        // add to temp array
                        char[] temp = pathArray.get(i).toCharArray();
                        Arrays.sort(temp);
                        if (!duplicateSet.contains(new String(temp))) {
                            removeList.add(pathArray.get(i));
                            duplicateSet.add(new String(temp));
                        }
                    }
                }
            }
        }

        List<String> pathArrayNoSubsets = new ArrayList<String>();
        // for loop to remove subsets from patharray
        Iterator iterator = removeList.iterator();
        while (iterator.hasNext()) {
            pathArrayNoSubsets.add((String) iterator.next());
        }

        HashSet<String> noSubsets = new HashSet<>();
        for (int i = 0; i < pathArrayNoSubsets.size(); i++) {
            for (int j = 0; j < pathArrayNoSubsets.size(); j++) {
                if (!(pathArrayNoSubsets.get(i).equals(pathArrayNoSubsets.get(j)))) {
                    String[] split = pathArrayNoSubsets.get(i).split(",");
                    int count = 0;
                    for (int k = 0; k < split.length; k++) {
                        if (pathArrayNoSubsets.get(j).contains(split[k])) {
                            count++;
                        }
                    }
                    if (count == split.length) {
                        noSubsets.add(pathArrayNoSubsets.get(i));
                    }
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < pathArrayNoSubsets.size(); i++) {
            if (!noSubsets.contains(pathArrayNoSubsets.get(i))) {
                res.add(pathArrayNoSubsets.get(i));
            }
        }
            return null;
    }

    public static void dfs(String pathStr, Node sourceNode, Node currentNode, List<String> pathArray) {
        if (currentNode.getVisited() && !currentNode.getName().equals(sourceNode.getName())) {
            return;
        }
        if (sourceNode.getName().equals(currentNode.getName())) {
            pathArray.add(pathStr);
            return;
        }
        for (int i = 0; i < currentNode.getEdges().size(); i++) {
            Node nextNode = currentNode.getEdges().get(i);
            String name = nextNode.getName();
            currentNode.setVisited(true);
            dfs(pathStr + "," + currentNode.getName(), sourceNode, nextNode, pathArray);
            currentNode.setVisited(false);
        }
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


