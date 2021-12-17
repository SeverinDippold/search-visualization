package ai_algorithm;

import java.util.LinkedList;
import java.util.List;

import ai_algorithm.problems.Problem;
import ecs.GameObject;

public class Path extends GameObject {
	SearchNode searchNode;

	public Path(SearchNode searchNode) {
		this.searchNode = searchNode;
	}
	
	public Problem getProblem() {
		return this.searchNode.getState().getProblem();
	}
	
	
	public List<String> getPathActions() {
		List<String> actionList = new LinkedList<String>();
		SearchNode next = this.searchNode;
		while (next != null) {
			actionList.add(actionList.size(), next.getAction());
			next = next.getParent();
		}
		return actionList;
	}

	public List<State> getVisitedStates() {
		List<State> stateList = new LinkedList<State>();
		SearchNode next = this.searchNode;
		while (next != null) {
			stateList.add(stateList.size(), next.getState());
			next = next.getParent();
		}
		return stateList;
	}
	
}