package searchclient;

import java.util.Comparator;
import java.util.Map;

import static searchclient.Node.MAX_ROW;
import static searchclient.Node.MAX_COL;

import searchclient.NotImplementedException;

public abstract class Heuristic implements Comparator<Node> {
	public Heuristic(Node initialState) {
		// Here's a chance to pre-process the static parts of the level.
		
		// Map<String, String> goals = new HashMap<String, String>();

		// for (int row = 1; row < MAX_ROW - 1; row++) {
		// 	for (int col = 1; col < MAX_COL - 1; col++) {
		// 		char g = initialState.goals[row][col];
		// 		if ('a' <= g && g <= 'z') {
		// 			goals.put(new Integer(row).toString(), new Integer(col).toString());
		// 		}
		// 	}
		// }

		// System.out.println("length of goals" + goals.size());
	}

	public int h(Node n) {

		// if (numberGoals == 1) {
		// 	dx = abs(n.agentCol - n.goals[0][1]);
		// 	dy = abs(node.y - goal.y);
		// 	return D * (dx + dy);
		// } else {
			return 0;
		// }
	}

	public abstract int f(Node n);

	@Override
	public int compare(Node n1, Node n2) {
		return this.f(n1) - this.f(n2);
	}

	public static class AStar extends Heuristic {
		public AStar(Node initialState) {
			super(initialState);
		}

		@Override
		public int f(Node n) {
			return n.g() + this.h(n);
		}

		@Override
		public String toString() {
			return "A* evaluation";
		}
	}

	public static class WeightedAStar extends Heuristic {
		private int W;

		public WeightedAStar(Node initialState, int W) {
			super(initialState);
			this.W = W;
		}

		@Override
		public int f(Node n) {
			return n.g() + this.W * this.h(n);
		}

		@Override
		public String toString() {
			return String.format("WA*(%d) evaluation", this.W);
		}
	}

	public static class Greedy extends Heuristic {
		public Greedy(Node initialState) {
			super(initialState);
		}

		@Override
		public int f(Node n) {
			return this.h(n);
		}

		@Override
		public String toString() {
			return "Greedy evaluation";
		}
	}
}
