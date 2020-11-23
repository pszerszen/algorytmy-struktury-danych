public class Counting {
	Element[] tab;
	int n;

	static final int k = 10; // zakres losowanych liczb [0; 9]
	Element[] out;

	Counting(int[] t, int a) {
		this.n = a;
		tab = new Element[n];
		out = new Element[n];
		for (int i = 0; i < n; i++) {
			tab[i] = new Element(t[i], i);
			out[i] = new Element(0, i);
		}

	}

	public void sort() {
		int[] C = new int[k];
		// Zerowanie tablicy
		for (int i = 0; i < k; i++)
			C[i] = 0;
		System.out.println("Wylosowano liczby z przedzialu [0; 9]");
		for (int i = 0; i < tab.length; i++)
			C[tab[i].el] = C[tab[i].el] + 1;

		System.out.println("Liczba wystapien kluczy o wartosci i");
		for (int i = 0; i < 10; i++)
			System.out.print(String.format("%3d", C[i]));
		System.out.println();

		for (int i = 1; i < k; i++)
			C[i] = C[i] + C[i - 1];

		System.out
				.println("ilosc kluczy mniejszych lub rownych od danego czyli\npozycje w posortowanej tablicy ostatniego elementu o kluczu i");
		// System.out.println("pozycje w posortowanej tablicy ostatniego elementu o kluczu i");
		for (int i = 0; i < 10; i++)
			System.out.print(String.format("%3d", C[i]));
		System.out.println();
		// utworzenie posortowanej tablicy out
		for (int i = n - 1; i >= 0; i--) {
			C[tab[i].el]--;
			tab[i].end = C[tab[i].el];
			out[C[tab[i].el]].el = tab[i].el;
		}

		System.out.println();
		System.out.println("\nPo sortowaniu:");
		print();
		for (int i = 0; i < n; i++) {
			System.out.print("Klucz: " + tab[i].el + " Pozycja pocz¹tkowa: "
					+ tab[i].start);
			System.out.println(" Pozycja koncowa: " + tab[i].end);
		}
	}

	private void print() {
		System.out.println("Posortowana tablica");
		for (int i = 0; i < out.length; i++)
			System.out.print(String.format("%3d", out[i].el));
		System.out.println();
	}
}
