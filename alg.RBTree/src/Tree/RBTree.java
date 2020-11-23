package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RBTree {
	BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
	static final boolean RED = true;
	static final boolean BLACK = false;

	BufferedReader bb = new BufferedReader(new InputStreamReader(System.in));
	private Node root;
	private Node Nil = new Node(0); // wartownik

	public int il_por_insert; // do statystyk
	public int il_rot_insert;
	public int il_por_delete;
	public int il_rot_delete;

	public RBTree() {
		Nil.color = BLACK;
		root = Nil;
	}

	public void printInOrder(Node node) {
		if (node != Nil) {
			printInOrder(node.left);
			System.out.print(node.value + (node.color ? "/R" : "/B") + "/ ");
			printInOrder(node.right);
		}
	}

	public void printTreeStr() {
		Level(0);
		if (maxLevel() == -1)
			System.out.println("Drzewo puste");
		else {
			int max = maxLevel() + 1;

			for (int i = 0; i <= maxLevel(); i++) {
				String result = "";
				String spaces = "                                                                                                                                      ";

				int z = (int) Math.pow(2, (max - i));
				if (z > 128)
					z = 2;
				result = spaces.substring(0, z);
				System.out.print("\n" + result);
				printTreeStr(root, i, max);

				System.out.print("\n" + result);
				printColor(root, i, max);

				result = spaces.substring(0, z - 1);
				System.out.print("\n" + result);

				printKreski(root, i, max);
			}
		}
	}

	private void printTreeStr(Node p, int lev, int max) {
		String result = "";
		String spaces = "                                                                                                                                           ";
		if (p.left != Nil)
			printTreeStr(p.left, lev, max);

		int z = (int) Math.pow(2, (max - lev + 1)) - 2;
		if (z > 128)
			z = 2;
		result = spaces.substring(0, z);
		if ((p.level == lev - 3) && (p.left == Nil))
			System.out.print("  " + result + "  " + result + "  " + result
					+ "  " + result);
		if ((p.level == lev - 3) && (p.right == Nil))
			System.out.print("  " + result + "  " + result + "  " + result
					+ "  " + result);
		if ((p.level == lev - 2) && (p.left == Nil))
			System.out.print("  " + result + "  " + result);
		if ((p.level == lev - 2) && (p.right == Nil))
			System.out.print("  " + result + "  " + result);
		if ((p.level == lev - 1) && (p.left == Nil))
			System.out.print("  " + result);
		if ((p.level == lev - 1) && (p.right == Nil))
			System.out.print("  " + result);

		if (p.level == lev)
			System.out.print(p.value + result);

		if (p.right != Nil)
			printTreeStr(p.right, lev, max);
	}

	private void printColor(Node p, int lev, int max) {
		String result = "";
		String spaces = "                                                                                                                                           ";
		if (p.left != Nil)
			printColor(p.left, lev, max);

		int z = (int) Math.pow(2, (max - lev + 1)) - 2;
		if (z > 1280)
			z = 2;
		result = spaces.substring(0, z);
		if ((p.level == lev - 3) && (p.left == Nil))
			System.out.print("  " + result + "  " + result + "  " + result
					+ "  " + result);
		if ((p.level == lev - 3) && (p.right == Nil))
			System.out.print("  " + result + "  " + result + "  " + result
					+ "  " + result);

		if ((p.level == lev - 2) && (p.left == Nil))
			System.out.print("  " + result + "  " + result);
		if ((p.level == lev - 2) && (p.right == Nil))
			System.out.print("  " + result + "  " + result);

		if ((p.level == lev - 1) && (p.left == Nil))
			System.out.print("  " + result);
		if ((p.level == lev - 1) && (p.right == Nil))
			System.out.print("  " + result);
		if (p.level == lev) {
			System.out.print((p.color ? "R" : "B") + " " + result);
		}
		if (p.right != Nil)
			printColor(p.right, lev, max);
	}

	private void printKreski(Node p, int lev, int max) {
		String result2 = "";
		String spaces = "                                                                                                                                           ";

		if (p.left != Nil)
			printKreski(p.left, lev, max);

		int z2 = (int) Math.pow(2, (max - lev + 1)) - 4;
		result2 = spaces.substring(0, z2);
		if ((p.level == lev - 3) && (p.left == Nil))
			System.out.print("    " + result2 + "    " + result2 + "    "
					+ result2 + "    " + result2);
		if ((p.level == lev - 3) && (p.right == Nil))
			System.out.print("    " + result2 + "    " + result2 + "    "
					+ result2 + "    " + result2);
		if ((p.level == lev - 2) && (p.left == Nil))
			System.out.print("    " + result2 + "    " + result2);
		if ((p.level == lev - 2) && (p.right == Nil))
			System.out.print("    " + result2 + "    " + result2);

		if ((p.level == lev - 1) && (p.left == Nil))
			System.out.print("    " + result2);
		if ((p.level == lev - 1) && (p.right == Nil))
			System.out.print("    " + result2);

		if (p.level == lev)
			if (p.left != Nil)
				System.out.print("-  ");
			else
				System.out.print("   ");
		if (p.level == lev)
			if (p.right != Nil)
				System.out.print("-" + result2);
			else
				System.out.print(" " + result2);
		if (p.right != Nil)
			printKreski(p.right, lev, max);
	}

	public void Level(int lev) {
		if (root != Nil)
			root = Level(root, lev);
	}

	private Node Level(Node t, int lev) {
		t.level = lev;
		if (t.left != Nil)
			Level(t.left, lev + 1);
		if (t.right != Nil)
			Level(t.right, lev + 1);
		return t;
	}

	private int max(int x, int y) {
		return x > y ? x : y;
	}

	public int maxLevel() {
		return maxLevel(root);
	}

	private int maxLevel(Node t) {
		if (t == Nil)
			return -1;
		int m;
		m = t.level;
		if (t.left != Nil)
			m = max(m, maxLevel(t.left));
		if (t.right != Nil)
			m = max(m, maxLevel(t.right));
		return m;
	}

	public void RBInsert(int w, boolean tryb) throws IOException {
		il_por_insert = 0;
		Node par = Nil;
		Node _root = root;
		while (_root != Nil) {
			il_por_insert++;
			par = _root;
			if (_root.value > w) {
				_root = _root.left;
				il_por_insert++;
			} else
				_root = _root.right;
		}

		Node node = new Node(w);
		node.parent = par;
		node.left = Nil;
		node.right = Nil;

		if (par == Nil) {
			root = node;
			il_por_insert++;
		} else if (par.value > w) {
			par.left = node;
			il_por_insert++;
		} else
			par.right = node;

		if (tryb == true) {
			druk();
			System.out.println("\nWstawiono wezel (czerwony)");
			b.readLine();
		}

		fixUp(node, tryb);

	}

	private void fixUp(Node x, boolean tryb) throws IOException {
		Node y;
		il_rot_insert = 0;

		while ((x != root) && (x.parent.color == RED)) {

			il_por_insert++;
			if (x.parent == x.parent.parent.left) {
				il_por_insert++;
				y = x.parent.parent.right;
				if ((y != Nil) && (y.color == RED)) {
					il_por_insert++;
					x.parent.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					y.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					x.parent.parent.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					x = x.parent.parent;

				} else {
					if (x == x.parent.right) {
						il_por_insert++;
						x = x.parent;
						Lrotate(x);
						il_rot_insert++;
						if (tryb == true) {
							druk();
							System.out.println("\nLewa rotacja");
							b.readLine();
						}
					}
					x.parent.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					x.parent.parent.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					Rrotate(x.parent.parent);
					il_rot_insert++;
					if (tryb == true) {
						druk();
						System.out.println("\nPrawa rotacja");
						b.readLine();
					}
				}
			} else {
				y = x.parent.parent.left;
				if ((y != Nil) && (y.color == RED)) {
					il_por_insert++;
					x.parent.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					if (y != Nil)
						y.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}

					x.parent.parent.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					x = x.parent.parent;
				} else {
					if (x == x.parent.left) {
						il_por_insert++;
						x = x.parent;
						Rrotate(x);
						il_rot_insert++;
						if (tryb == true) {
							druk();
							System.out.println("\nPrawa rotacja");
							b.readLine();
						}
					}
					x.parent.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					x.parent.parent.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezla");
						b.readLine();
					}
					Lrotate(x.parent.parent);
					il_rot_insert++;
					if (tryb == true) {
						druk();
						System.out.println("\nLewa rotacja");
						b.readLine();
					}
				}
			}
		}

		boolean col = root.color;
		root.color = BLACK;
		if ((col == RED) && (tryb == true)) {
			druk();
			System.out.println("\nZmieniono kolor root-a na czarny");
			b.readLine();
		}

	}

	private void Lrotate(Node x) {
		Node y = x.right;

		x.right = y.left;
		if (y.left != Nil)
			y.left.parent = x;
		y.parent = x.parent;
		if (x.parent == Nil)
			root = y;
		else if (x == x.parent.left)
			x.parent.left = y;
		else
			x.parent.right = y;

		y.left = x;
		x.parent = y;
	}

	private void Rrotate(Node y) {
		Node x = y.left;

		y.left = x.right;
		if (x.right != Nil)
			x.right.parent = y;
		x.parent = y.parent;
		if (y.parent == Nil)
			root = x;
		else if (y == y.parent.left)
			y.parent.left = x;
		else
			y.parent.right = x;

		x.right = y;
		y.parent = x;
	}

	private Node TSearch(Node x, int k) {
		if ((x == Nil) || (k == x.value))
			return x;
		if (k < x.value)
			return TSearch(x.left, k);
		else
			return TSearch(x.right, k);
	}

	private Node TMinimum(Node x) {
		while (x.left != Nil)
			x = x.left;
		return x;
	}

	private Node TSuccesor(Node x) {
		if (x.right != Nil)
			return TMinimum(x.right);
		Node y = x.parent;
		while ((y != Nil) && (x != y.right)) {
			x = y;
			y = y.parent;
		}
		return y;
	}

	public void RBDelete(int w, boolean tryb) throws IOException {
		Node x = TSearch(root, w);
		RBDelete(x, tryb);
	}

	private void RBDelete(Node z, boolean tryb) throws IOException {
		il_por_delete = 0;
		Node x, y;
		if ((z.left == Nil) || (z.right == Nil)) {
			y = z;
			il_por_delete++;
		} else
			y = TSuccesor(z);
		if (y.left != Nil) {
			x = y.left;
			il_por_delete++;
		} else
			x = y.right;
		x.parent = y.parent;
		if (y.parent == Nil) {
			root = x;
			il_por_delete++;
		} else if (y == y.parent.left) {
			y.parent.left = x;
			il_por_delete++;
		} else
			y.parent.right = x;
		if (y != z) {
			z.value = y.value;
			il_por_delete++;
		}
		if (tryb == true) {
			druk();
			System.out.println("\nUsunieto klucz");
			b.readLine();
		}

		if (y.color == BLACK) {
			RBDeleteFixUp(x, tryb);
			il_por_delete++;
		}
	}

	private void RBDeleteFixUp(Node x, boolean tryb) throws IOException {
		il_rot_delete = 0;
		Node w;
		boolean col;
		while ((x != root) && (x.color == BLACK)) {
			il_por_delete++;
			if (x == x.parent.left) {
				il_por_delete++;
				w = x.parent.right;

				if (w.color == RED) {
					il_por_delete++;
					w.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP1");
						b.readLine();
					}
					x.parent.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP1");
						b.readLine();
					}
					Lrotate(x.parent);
					il_rot_delete++;
					if (tryb == true) {
						druk();
						System.out.println("\nLewa rotacjaP1");
						b.readLine();
					}
					w = x.parent.right;

				}
				if ((w.left.color == BLACK) && (w.right.color == BLACK)) {
					il_por_delete++;
					w.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP2");
						b.readLine();
					}
					x = x.parent;
				} else {
					if (w.right.color == BLACK) {
						il_por_delete++;
						w.left.color = BLACK;
						if (tryb == true) {
							druk();
							System.out.println("\nZmieniono kolor wezlaP3");
							b.readLine();
						}
						w.color = RED;
						if (tryb == true) {
							druk();
							System.out.println("\nZmieniono kolor wezlaP3");
							b.readLine();
						}
						Rrotate(w);
						il_rot_delete++;
						if (tryb == true) {
							druk();
							System.out.println("\nPrawa rotacjaP3");
							b.readLine();
						}
						w = x.parent.right;
					}

					col = w.color != x.parent.color;
					w.color = x.parent.color;
					if ((tryb == true) && col) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP4");
						b.readLine();
					}
					col = (x.parent.color == RED);
					x.parent.color = BLACK;
					if ((tryb == true) && col) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP4");
						b.readLine();
					}
					col = (w.right.color == RED);
					w.right.color = BLACK;
					if ((tryb == true) && col) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP4");
						b.readLine();
					}
					Lrotate(x.parent);
					il_rot_delete++;
					if (tryb == true) {
						druk();
						System.out.println("\nLewa rotacja");
						b.readLine();
					}
					x = root;
				}
			} else {
				w = x.parent.left;
				if (w.color == RED) {
					il_por_delete++;
					w.color = BLACK;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP1-2");
						b.readLine();
					}
					x.parent.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP1-2");
						b.readLine();
					}
					Rrotate(x.parent);
					if (tryb == true) {
						druk();
						System.out.println("\nPrawa rotacjaP1-2");
						b.readLine();
					}
					il_rot_delete++;
					w = x.parent.left;
				}
				if ((w.right.color == BLACK) && (w.left.color == BLACK)) {
					il_por_delete++;
					w.color = RED;
					if (tryb == true) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP2-2");
						b.readLine();
					}
					x = x.parent;
				} else {
					if (w.left.color == BLACK) {
						il_por_delete++;
						w.right.color = BLACK;
						if (tryb == true) {
							druk();
							System.out.println("\nZmieniono kolor wezlaP3-2");
							b.readLine();
						}
						w.color = RED;
						if (tryb == true) {
							druk();
							System.out.println("\nZmieniono kolor wezlaP3-2");
							b.readLine();
						}
						Lrotate(w);
						il_rot_delete++;
						if (tryb == true) {
							druk();
							System.out.println("\nLewa rotacjaP3-2");
							b.readLine();
						}
						w = x.parent.left;
					}
					col = w.color != x.parent.color;
					w.color = x.parent.color;
					if ((tryb == true) && col) {

						druk();
						System.out.println("\nZmieniono kolor wezlaP4-2");
						b.readLine();
					}
					col = (x.parent.color == RED);
					x.parent.color = BLACK;
					if ((tryb == true) && col) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP4-2");
						b.readLine();
					}
					col = (w.left.color == RED);
					w.left.color = BLACK;
					if ((tryb == true) && col) {
						druk();
						System.out.println("\nZmieniono kolor wezlaP4-2");
						b.readLine();
					}
					Rrotate(x.parent);
					il_rot_delete++;
					if (tryb == true) {
						druk();
						System.out.println("\nPrawa rotacjaP4-2");
						b.readLine();
					}
					x = root;
				}
			}

		}
		col = root.color;
		x.color = BLACK;
		if ((col == RED) && (tryb == true)) {
			druk();
			System.out.println("\nZmieniono kolor root-a na czarny");
			b.readLine();
		}
	}

	public void druk() {
		printInOrder(root);
		System.out.println();
		printTreeStr();
	}
}
