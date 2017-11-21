package assignment4;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String name;
    private List<Node> edges;

    public Node(){
        this.edges = new ArrayList<Node>();
    }

    public Node(String a, List<Node> b){
        this.name = a;
        this.edges = b;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String inputName){
        this.name = inputName;
    }

    public List<Node> getEdges(){
        return this.edges;
    }

    public void setEdges(List<Node> inputList){
        this.edges = inputList;
    }
}