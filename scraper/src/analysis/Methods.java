package analysis;

import java.util.HashMap;
import java.util.Set;

import objects.Contribution;
import objects.User;

public class Methods {
	
	public static HashMap<User, Integer> listNumberOfContributionsPerUser(Set<Contribution> contributions) {
		HashMap<User, Integer> rankings = new HashMap<>();
		for (Contribution contribution : contributions) {
			User user = contribution.getUser();
			if (rankings.containsKey(user)) {
				Integer currentTotal = rankings.get(user);
				rankings.put(user, currentTotal + 1); 
			} else {
				rankings.put(user, 0);
			}
		}
		return rankings;
	}
	
	public static Integer totalContributions(Set<Contribution> contributions) {
		return contributions.size();
	}
	
	public static Integer totalMessages(Set<Contribution> contributions) {
		int runningTotal = 0;
		for (Contribution contribution : contributions) {
			runningTotal = runningTotal + contribution.getMessages().size();
		}
		return runningTotal;
	}
	
	
	
}
