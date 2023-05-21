package ro.netvoip.voteapp;

public class Ballot {
    private String[] candidates;
    public static int candidateCount = 0;

    public String[] getCandidates() {
        return candidates;
    }

    public Ballot() {
        this.candidates = new String[1];
        candidates[0] = "Unknown";
        System.out.println("Ballot created with default candidate: " + candidates[0]);
    }
    public Ballot(String[] candidates) {
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] == null || candidates[i].isEmpty() || candidates[i].isBlank()) {
                candidates[i] = "Unknown";
            }
                this.candidates = candidates;
        }
    }

    public void addCandidate(String candidate) {
        // code to add a candidate to the ballot
        this.candidates[candidateCount] = candidate;
        candidateCount++;

    }

    public void removeCandidate(String candidate) {
        // code to remove a candidate from the ballot
        for (int i = 0; i < candidateCount; i++) {
            if (this.candidates[i].equals(candidate)) {
                this.candidates[i] = null;
            }
        }
    }

    public void displayCandidates() {
        // code to display the list of candidates
        for (int i = 0; i < candidateCount; i++) {
            System.out.println(this.candidates[i]);
        }
    }
}
