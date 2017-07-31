package analysis;

import java.util.*;

import objects.Contribution;
import objects.Conversation;
import objects.User;

public class Methods {
	
    public static ArrayList<String> rankByUseContribution(Conversation conversation) {
        ArrayList<String> output = new ArrayList<>();
        //TODO write this method
        return output;
    }
	
	public static Integer totalMessages(Set<Contribution> contributions) {
		int runningTotal = 0;
		for (Contribution contribution : contributions) {
			runningTotal = runningTotal + contribution.getMessages().size();
		}
		return runningTotal;
	}
	
	
	
}
