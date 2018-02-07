package searchclient;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.*;

import static java.lang.Math.abs;

import static searchclient.Node.MAX_ROW;
import static searchclient.Node.MAX_COL;

import searchclient.NotImplementedException;

public abstract class Heuristic implements Comparator<Node> {
	public Heuristic(Node initialState) {

		// Consists of all goals on a given level
		ArrayList<Goal> goals = new ArrayList<Goal>();

		// Consists of all boxes on a given level
		ArrayList<Box> boxes = new ArrayList<Box>();
		
		for (int row = 1; row < MAX_ROW - 1; row++) {
			for (int col = 1; col < MAX_COL - 1; col++) {
				char g = initialState.goals[row][col];
				char b = initialState.boxes[row][col];
				
				if ('a' <= g && g <= 'z') {
					goals.add(new Goal(initialState.goals[row][col], row, col));
				}
				if ('A' <= b && b <= 'Z') {
					boxes.add(new Box(initialState.goals[row][col], row, col));
				}
			}
		}
	}

	public int h(Node n) {
		
		// GoalAbs which should be calcualted for every boxes and goals on the level
		int goalAbs_x = 0;
		int goalAbs_y = 0;
		
		for (int i = 0; i < goals.size(); i++) {
			
		}

		for (int i = 0; i < boxes.size(); i++) {

		}

		int dx = abs(n.agentRow - goalAbs_x);
		int dy = abs(n.agentCol - goalAbs_y);
		return 1 * (dx + dy);
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

	public class Goal {
		char goal;
		int row;
		int col;

		public Goal(char goal, int row, int col) {
			this.goal = goal;
			this.row = row;
			this.col = col;
		}
	}

	public class Box {
		char box;
		int row;
		int col;

		public Box(char box, int row, int col) {
			this.box = box;
			this.row = row;
			this.col = col;
		}
	}
}
