package ro.netvoip.voteapp;

import java.util.ArrayList;
import java.util.List;

public class Voter {
    private String name;
    private int id;
    private List<Integer> votes;
    private Ballot ballot;

    public Voter(String name, int id) {

        this.name = name;
        this.id = id;

       votes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Ballot getBallot() {
        return ballot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getVotes() {
        return votes;
    }
    public int getVote(int index) {

        return votes.get(index);
    }


    public void castVote(Ballot ballot, int choice) {
        // code to cast a vote for a candidate on the ballot
        this.ballot = ballot;
        choice-=1;
        if (choice >= 0 && choice < ballot.getCandidates().length) {
            votes.add(choice);
        } else {
            System.out.println("Invalid choice: " + choice);
        }
    }
}
