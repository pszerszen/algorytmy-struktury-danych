package Tree;

public class Node {
	public static final boolean RED = true;
	public final boolean BLACK = false;

	Node parent;
	Node left;
	Node right;
	int value;
	int level; // do wyswietlenia drzewa poziomami
	boolean color;

	public Node(int w) {
		value = w;
		parent = left = right = null;
		level = 0;
		color = RED; // true
	}

}