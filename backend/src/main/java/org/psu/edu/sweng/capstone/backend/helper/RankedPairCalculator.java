package org.psu.edu.sweng.capstone.backend.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class RankedPairCalculator {
	
	public static void main(String[]args) {
		ArrayList<String> options = establishBallotOptions();
		
		ArrayList<ArrayList<String>> votes = establishVotes();		
		
		ArrayList<UniquePair> uniquePairs = createUniquePairs(options);
		
		HashMap<UniquePair, PairWinner> winners = determinePairWinners(uniquePairs, votes);
	}

	private static ArrayList<String> establishBallotOptions() {
		ArrayList<String> options = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
		
		System.out.println("Ballot Options: \n");
		options.forEach(option -> System.out.println(option));
		
		return options;
	}

	private static ArrayList<ArrayList<String>> establishVotes() {
		ArrayList<ArrayList<String>> votes = new ArrayList<>();
		
		votes.add(new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie")));
		votes.add(new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie")));
		votes.add(new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie")));
		votes.add(new ArrayList<>(Arrays.asList("Bob", "Charlie", "Alice")));
		votes.add(new ArrayList<>(Arrays.asList("Bob", "Charlie", "Alice")));
		votes.add(new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob")));
		votes.add(new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob")));
		votes.add(new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob")));
		votes.add(new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob")));
		
		System.out.println("\nNumber of Votes: " + votes.size() + "\n");
		votes.forEach(vote -> System.out.println(vote));
		
		return votes;
	}

	/** Creates unique pairs based upon the a list of Ballot Options.
	 * @param options The list of Ballot options (sorted alphabetically).
	 */
	private static ArrayList<UniquePair> createUniquePairs(ArrayList<String> options) {
		ArrayList<UniquePair> uniquePairs = new ArrayList<>();
		
		for (int x = 0; x < options.size(); x++) {
			if (x < options.size() - 1) {
				int y = 1;
				
				while (y < options.size()) {
					int nextPotentialOption = x + y;
					if (nextPotentialOption < options.size()) {
						UniquePair up = new UniquePair(options.get(x), options.get(nextPotentialOption));						
						uniquePairs.add(up);
					}
					
					y++;
				}
			}
		}
		
		System.out.println("\nUnique Pairs: \n");
		uniquePairs.forEach(pair -> System.out.println(pair));
		System.out.println();
		
		return uniquePairs;
	}
	
	/** Calculates the winner between each unique pair.
	 * @param uniquePairs A list of all unique pairs.
	 * @param votes A list of all the votes casted.
	 * @return A HashMap containing the winning difference between one unique pair.
	 */
	private static HashMap<UniquePair, PairWinner> determinePairWinners(ArrayList<UniquePair> uniquePairs, 
			ArrayList<ArrayList<String>> votes) {
		
		HashMap<UniquePair, PairWinner> winners = new HashMap<>();
		
		uniquePairs.forEach(pair -> {			
			int firstOptionTallies = 0;
			int secondOptionTallies = 0;
			
			String firstOption = pair.getOptionOne();
			String secondOption = pair.getOptionTwo();
			
			for (ArrayList<String> vote : votes) {
				int firstOptionIndex = 0;
				int secondOptionIndex = 0;
				
				for (int x = 0; x < vote.size(); x++) {
					if (vote.get(x).equals(firstOption)) {
						firstOptionIndex = x;
					}
					
					if (vote.get(x).equals(secondOption)) {
						secondOptionIndex = x;
					}
				}
				
				if (firstOptionIndex < secondOptionIndex) {
					firstOptionTallies++;
				}
				else {
					secondOptionTallies++;
				}
			}
			
			int difference;
			PairWinner winner = new PairWinner();
			if (firstOptionTallies > secondOptionTallies) {
				difference = firstOptionTallies - secondOptionTallies;
				winner.setOption(firstOption);
			}
			else {
				difference = secondOptionTallies - firstOptionTallies;
				winner.setOption(secondOption);
			}
			
			winner.setVoteDifference(difference);
			
			winners.put(pair, winner);
		});

		winners.entrySet().forEach(winner -> {
			System.out.println("Winner between " + winner.getKey() + " is " + winner.getValue().getOption() + 
					" with a difference of " + winner.getValue().getVoteDifference());
		});
		
		return winners;
	}
}