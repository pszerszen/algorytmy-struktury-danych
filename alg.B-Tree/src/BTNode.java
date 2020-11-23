public class BTNode {
	int order = 0;
	int nKey = 0; // ilosc kluczy w wezle
	int kArray[]; // tablica kluczy
	BTNode btnArray[]; // tablica synów
	boolean isLeaf;
	BTNode parent;
	int level;

	BTNode(int order, BTNode parent) {
		this.order = order;
		this.parent = parent;
		kArray = new int[2 * order - 1];
		btnArray = new BTNode[2 * order];
		isLeaf = true;
		level = 0;
	}
}