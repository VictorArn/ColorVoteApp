package ro.netvoip.voteapp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Ballot ballot = new Ballot(new String[3] );
        ballot.addCandidate("John");
        ballot.addCandidate("Jane");
        ballot.addCandidate("Jack");

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        Voter voter1 = new Voter("John", 1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        Voter voter2 = new Voter("Jane", 2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(3);
        Voter voter3 = new Voter("Jack", 3);

        VotingSystem votingSystem = new VotingSystem(ballot);

        votingSystem.addVoter(voter1, list1);


        votingSystem.displayResults();
    }
}
