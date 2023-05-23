package ro.netvoip.voteapp;

import java.util.ArrayList;
import java.util.List;

public class VotingSystem {
    private List<Voter> listVoters ;
    private Ballot ballot;

    public List<Voter> getListVoters() {
        return listVoters;
    }


    public Ballot getBallot() {
        return ballot;
    }

    public VotingSystem(Ballot ballot) {
        this.ballot = ballot;
        listVoters = new ArrayList<>();
    }

    public void addVoter(Voter voter, List <Integer> choices){

        for (int choice : choices) {
            voter.castVote(ballot, choice);
        }
        listVoters.add(voter);
    }

    public void addExistingVoter(Voter voter){
        listVoters.add(voter);
    }
    public void removeVoter(Voter voter){
        listVoters.remove(voter);
    }

    public void displayResults(){
        int[] results = new int[ballot.getCandidates().length];
        for (Voter voter : listVoters) {
            for(int choice : voter.getVotes()){
                results[choice]++;
            }

        }
        for (int i = 0; i < results.length; i++) {
            System.out.println(ballot.getCandidates()[i] + ": " + results[i]);
        }
    }
    public void displayIndividualVotes() {
        for (Voter voter : listVoters) {
            System.out.println(voter.getName() + " voted for " + ballot.getCandidates()[voter.getVote(listVoters.indexOf(voter))]);
        }
    }


}
