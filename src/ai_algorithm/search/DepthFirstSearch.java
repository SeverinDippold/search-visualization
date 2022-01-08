package ai_algorithm.search;

import ai_algorithm.ExploredSet;
import ai_algorithm.Frontier;
import ai_algorithm.SearchNode;
import ai_algorithm.State;
import ai_algorithm.problems.Problem;
import application.debugger.Debugger;

public class DepthFirstSearch extends SearchAlgorithm {
//default construchtor required

	public DepthFirstSearch() {
		super();
	}

	public DepthFirstSearch(Problem problem) {
		super(problem);
	}

	@Override
	public SearchNode search() {

		Frontier frontier = new Frontier();

		SearchNode start = new SearchNode(null, problem.getInitialState(), 0, null);

		Debugger.pause();

		if (this.problem.isGoalState(start.getState())) {
			return start;
		}

		frontier.add(start);
		Debugger.pause();

		while (!frontier.isEmpty()) {

			SearchNode node = frontier.removeLast();
			Debugger.pause();
			System.out.println(node);
			if (problem.isGoalState(node.getState())) {
				return node;
			}

			for (SearchNode child : node.expand()) {
				State state = child.getState();
				if (problem.isGoalState(state)) {
					Debugger.pause("Finished");
					return child;
				}
				if (!node.contains(state)) {
					frontier.add(child);
				}

			}

		}
		Debugger.pause("No Sulution found");
		return null;
	}

}
