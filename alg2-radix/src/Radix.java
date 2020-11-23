public class Radix {
	Element[] tab;
	Element[] out;
	int n;
	int porow = 0;

	public Radix(int[] t, int a) {
		this.n = a;
		tab = new Element[n];
		out = new Element[n];
		for (int i = 0; i < n; i++) {
			tab[i] = new Element(t[i], i);
			out[i] = new Element(0, i);
		}
	}

	public void binary(int val) { // wyœwietla liczbê binarnie
		for (int i = 11; i >= 0; i--)
			System.out.print((val >> i) % 2);
		System.out.print(" ");
	}

	public void sort(int bits) {
		System.out.println("Wylosowane liczby binarnie:");
		for (int i = 0; i < n; i++)
			// wyœwietlenie wylosowanych liczb binarnie
			binary(tab[i].el);
		System.out.println();

		Element[] out_orig = out;
		System.out.println("maska sk³ada siê z " + bits + " bitów");
		int rshift = 0;
		for (int mask = ~(-1 << bits); mask < 3600; mask <<= bits, rshift += bits) {
			System.out.print("maska:" + mask + "  binarnie:");
			binary(mask);
			System.out.println();
			int[] cntarray = new int[1 << bits];

			for (int p = 0; p < tab.length; ++p) {
				int key = (tab[p].el & mask) >> rshift;
				++cntarray[key];
			}

			for (int i = 1; i < cntarray.length; ++i)
				cntarray[i] += cntarray[i - 1];

			for (int p = tab.length - 1; p >= 0; --p) {
				int key = (tab[p].el & mask) >> rshift;
				--cntarray[key];
				tab[p].end = cntarray[key]; // pozycja koñcowa klucza
				out[cntarray[key]] = tab[p];

			}

			Element[] temp = out;
			out = tab;
			tab = temp;
			for (int i = 0; i < tab.length; i++)
				System.out.print(tab[i].el + " ");
			System.out.println();

		}

		if (tab == out_orig)
			System.arraycopy(tab, 0, out, 0, tab.length);
		System.out.println("\nPo sortowaniu:");
		print();
		for (int i = 0; i < n; i++) {
			System.out.print("Klucz: " + tab[i].el + " Pozycja pocz¹tkowa: "
					+ tab[i].start);
			System.out.println(" Pozycja koncowa: " + tab[i].end);
		}
	}

	private void print() {
		for (int i = 0; i < tab.length; i++)
			System.out.print(tab[i].el + "   ");
		System.out.println();
	}
}
