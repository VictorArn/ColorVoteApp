package ro.netvoip.voteapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of candidates: ");
        int numCandidates;
        while (true) {
            numCandidates = scanner.nextInt();
            if (numCandidates > 0) {
                break;
            } else {
                System.out.println("Invalid number of candidates. Please enter a positive integer.");
            }
        }
        scanner.nextLine(); // consume the newline character
        String[] candidatesNames = new String[numCandidates];
        for (int i = 0; i < numCandidates; i++) {
            System.out.print("Enter the name of candidate #" + (i+1) + ": ");
            candidatesNames[i] = scanner.nextLine();
        }

        // Create a ballot object
        Ballot ballot = new Ballot(candidatesNames);

        // Create a voting system object

        VotingSystem votingSystem = new VotingSystem(ballot);

        // Prompt the user for the number of voters
        System.out.print("Enter the number of voters: ");
        int numVoters = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        // Add voters to the voting system
        for (int i = 1; i <= numVoters; i++) {
            System.out.print("Enter the name of voter #" + i + ": ");
            String voterName = scanner.nextLine();

            System.out.print("Enter the ID of voter #" + i + ": ");
            int id;
            while(true) {
                id = scanner.nextInt();
                if (id > 0) {
                    break;
                } else {
                    System.out.println("Invalid ID. Please enter a positive integer.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine(); // consume the newline character

            System.out.print("Enter the number of candidates voted by voter #" + i + ": ");
            int numVotes ;
            while(true) {
                numVotes = scanner.nextInt();
                if(numVotes > 0 && numVotes <= numCandidates) {
                    break;
                }
                else {
                    System.out.println("Invalid number of votes. Please enter a positive integer less than or equal to " + numCandidates + ".");

                    scanner.nextLine(); // consume the newline character
                }
            }
            scanner.nextLine(); // consume the newline character

            List<Integer> votes = new ArrayList<>();
            for (int j = 1; j <= numVotes; j++) {
                System.out.print("Enter the choice #" + j + " of voter #" + i + ": ");

                int choice;
                while(true) {
                    choice = scanner.nextInt();
                    if (choice > 0 && choice <= numCandidates) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter a positive integer less than or equal to " + numCandidates + ".");
                        scanner.nextLine(); // consume the newline character
                    }
                }
                scanner.nextLine(); // consume the newline character
                votes.add(choice);
            }

            Voter voter = new Voter(voterName, id);
            votingSystem.addVoter(voter, votes);
        }

        // Display the voting results
        System.out.println("Results:");
        votingSystem.displayResults();
        System.out.println("Press Enter to view individual votes, or Backspace to quit.");
        int input;
        while ((input = System.in.read()) != '\n') {
            if (input == '\b') { // Backspace key
                return;
            }
        }

// display individual votes
        System.out.println("Individual votes:");
        System.out.println();
        votingSystem.displayIndividualVotes();
    }
}
