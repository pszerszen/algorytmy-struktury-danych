import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BTree {
	BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
	int order;
	BTNode root;
	int wysokosc;
	public int il_por_kluczy;

	public BTree(int order) {
		this.order = order;
		root = new BTNode(order, null);
	}

	public void BTinsert(int k, boolean tryb) throws IOException {
		il_por_kluczy = 0;
		BTNode btNode = root;
		// jezeli root jest pelny i jest lisciem
		if ((root.nKey == order * 2 - 1) && (root.isLeaf == true)) {
			split(root, tryb);
													if (tryb == true) {
														print();
														System.out.println("\nRozbito wezel ");
														b.readLine();
													}
		}
		while (!btNode.isLeaf) { // szukanie wezla do którego bedzie wstawiony klucz
			int i = 0;
			if (k < btNode.kArray[i]) {
				il_por_kluczy++; 
			}
			while (k > btNode.kArray[i]) {
				il_por_kluczy++;
				i++;
				if (i == btNode.nKey)
					break;
			}
			btNode = btNode.btnArray[i];
		}
		insert(btNode, k, tryb);

	}

	private void insert(BTNode x, int k, boolean tryb) throws IOException {
		if (x.nKey == 0) {
			x.nKey++;
			x.kArray[0] = k;
														if (tryb == true) {
															print();
															System.out.println("\nWstawiono klucz ");
															b.readLine();
														}
		} else {
			int pos = 0;

			if (k < x.kArray[pos]) {
				il_por_kluczy++;
			}
			while (k > x.kArray[pos]) {
				il_por_kluczy++;
				pos++;
				if (pos == x.nKey) {
					break;
				}
			}
			if (x.nKey == order * 2 - 1) {
				BTNode right = split(x, tryb);
				if (tryb == true) {
					print();
					System.out.println("\nRozbito wezel ");
					b.readLine();
				}
				if (pos > order - 1) {
					insert(right, k, tryb);
				} else {
					if (pos != x.nKey) {
						shift(x, pos);
					} else {
						x.nKey++;
					}
					x.kArray[pos] = k;

														if (tryb == true) {
															print();
															System.out.println("\nWstawiono klucz ");
															b.readLine();
														}

				}
			} else {
				if (pos != x.nKey) {
					shift(x, pos);

				} else {
					x.nKey++;
				}
				x.kArray[pos] = k;
														if (tryb == true) {
															print();
															System.out.println("\nWstawiono klucz ");
															b.readLine();
														}
			}
		}
	}

	private void shift(BTNode x, int startPos) {
		for (int i = x.nKey; i > startPos; i--) {
			x.kArray[i] = x.kArray[i - 1];
			if (!x.isLeaf) {
				x.btnArray[i + 1] = x.btnArray[i];
			}
		}
		x.nKey++;
	}

	private BTNode split(BTNode x, boolean tryb) {
		if (x.nKey == order * 2 - 1) {
			BTNode right = null;
			if (x.parent == null) {
				BTNode left = new BTNode(order, x);
				right = new BTNode(order, x);
				for (int i = 0; i < order - 1; i++) {
					left.kArray[i] = x.kArray[i];
					right.kArray[i] = x.kArray[order + i];
				}
				if (!x.isLeaf) {
					for (int i = 0; i < order; i++) {
						left.btnArray[i] = x.btnArray[i];
						left.btnArray[i].parent = left;
						right.btnArray[i] = x.btnArray[order + i];
						right.btnArray[i].parent = right;
					}
					left.isLeaf = false;
					right.isLeaf = false;
				} else
					x.isLeaf = false;
				x.kArray[0] = x.kArray[order - 1];
				x.nKey = 1;
				left.nKey = order - 1;
				right.nKey = order - 1;
				for (int i = 1; i < order * 2 - 1; i++) {
					x.kArray[i] = 0;
					x.btnArray[i + 1] = null;
				}
				x.btnArray[0] = left;
				x.btnArray[1] = right;
			} else {
				if (x.parent.nKey == order * 2 - 1)
					split(x.parent, tryb);
				int pos = 0;
				if (x.kArray[order - 1] < x.parent.kArray[pos]) {
					il_por_kluczy++;
				}
				while (x.kArray[order - 1] > x.parent.kArray[pos]) {
					il_por_kluczy++;
					pos++;
					if (pos == x.parent.nKey) {
						break;
					}
				}
				shift(x.parent, pos);
				x.parent.kArray[pos] = x.kArray[order - 1];
				right = new BTNode(order, x.parent);
				for (int i = 0; i < order - 1; i++) {
					right.kArray[i] = x.kArray[order + i];
				}
				if (!x.isLeaf) {
					for (int i = 0; i < order; i++) {
						right.btnArray[i] = x.btnArray[order + i];
						right.btnArray[i].parent = right;
					}
					right.isLeaf = false;
				}
				right.nKey = order - 1;
				x.nKey = order - 1;

				for (int u = 0; u < order - 1; u++) {
					x.kArray[order - 1 + u] = 0;
					x.btnArray[order + u] = null;
				}
				x.parent.btnArray[pos] = x;
				x.parent.btnArray[pos + 1] = right;
			}
			return right;
		} else
			return null;
	}

	private void wysokoscBTree() {
		int i = 0;
		BTNode btNode = root;
		while (!btNode.isLeaf) {
			btNode = btNode.btnArray[0];
			i++;
		}
		wysokosc = i;
	}

	public void Level(int lev) {
		if (root != null)
			root = Level(root, lev);
	}

	private BTNode Level(BTNode x, int lev){
		x.level = lev;
		if (x.isLeaf == false) {
			for (int i = 0; i <= x.nKey; i++)
				if (x.btnArray[i].isLeaf == false)
					Level(x.btnArray[i], lev + 1);
				else
					x.btnArray[i].level = lev + 1;
		}
		return x;
	}

	private void print(BTNode x, int lev) {
		if (x.level == lev) {
			for (int i = 0; i < x.nKey; i++)
				System.out.print(" " + x.kArray[i]);
			System.out.print("   ");
		}
		if (x.isLeaf == false)
			for (int i = 0; i <= x.nKey; i++)
				print(x.btnArray[i], lev);
	}

	private void printKreski(BTNode x, int lev) {
		if (x.level == lev && x.isLeaf == false) {

			for (int i = 0; i < x.nKey; i++)
				System.out.print("|  ");
			System.out.print("|  ");
		}
		if (x.isLeaf == false)
			for (int i = 0; i <= x.nKey; i++)
				printKreski(x.btnArray[i], lev);
	}

	public void print() {
		wysokoscBTree();
		Level(0);

		for (int i = 0; i <= wysokosc; i++) {
			print(root, i);
			System.out.println();
			printKreski(root, i);
			System.out.println();
		}
	}

	private void printPreOrder(BTNode x) {
		if (x != null) {
			for (int i = 0; i < x.nKey; i++)
				System.out.print(x.kArray[i] + " ");

			System.out.print("  ");
			for (int j = 0; j <= x.nKey; j++)
				printPreOrder(x.btnArray[j]);
		}
	}

	public void printPreOrder() {
		System.out.print("Pre-order: ");
		printPreOrder(root);
		System.out.println();
	}
}
