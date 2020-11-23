public class Bucket {
	Element[] tab;
	Lista[] L;
	int n, k;
	int nowy; // indeks nowego elementu w tablicy L[], ktory dodajemy do kubelka
	int poprzedni;// indeks poprzedniego elementuna liscie
	int biezacy; // indeks biezacego elementu na liscie
	int ilosc_przedzialow = 10; // te wartosc mozna zmienic na 5
	double szer_kubelka = 1.0 / ilosc_przedzialow;
	int[] kubelek;
	Element element_dodawany; // element aktualnie dodawany do listy

	Bucket(double[] t, int a) {
		this.n = a;
		tab = new Element[n];
		for (int i = 0; i < n; i++)
			tab[i] = new Element(t[i], i);

		kubelek = new int[ilosc_przedzialow];
		for (int i = 0; i < ilosc_przedzialow; i++)
			kubelek[i] = 0; // pusta lista elementow

		L = new Lista[n + 1];
		for (int i = 0; i < n + 1; i++) {
			L[i] = new Lista();

		}

	}

	public void sort() {
		int i, j;
		nowy = 1;
		System.out.println("Wylosowane liczby sa z przedzialu [0;1)");
		System.out.println("Szerokosc przedzialu: " + szer_kubelka);
		// umieszczenie elementow na listach z sorowaniem przez wstawianie
		// tablica kubelek zawiera indeksy pierwszych elementow
		// z poszczegolnych przedzialow
		// tablica L zawiera w polu nastepnik indeks nastepnego elementu
		// w okreslonym przedziale lub 0 gdy juz nie ma liczb w tym kubelku
		for (i = 0; i < n; i++) {
			element_dodawany = tab[i];
			k = (int) ((element_dodawany.el) / szer_kubelka);

			L[nowy].nastepnik = 0;
			L[nowy].dane = element_dodawany;
			poprzedni = 0;
			biezacy = kubelek[k];
			while ((biezacy > 0) && (L[biezacy].dane.el < element_dodawany.el)) {
				poprzedni = biezacy;
				biezacy = L[biezacy].nastepnik;
			}
			if (poprzedni == 0) {
				L[nowy].nastepnik = biezacy;
				kubelek[k] = nowy;
			} else if (biezacy == 0)
				L[poprzedni].nastepnik = nowy;
			else {
				L[poprzedni].nastepnik = nowy;
				L[nowy].nastepnik = biezacy;
			}
			nowy++;
		}
		// Dane z kubelkow sa umieszczane w tablicy tab[]
		System.out.println();
		j = 0;
		for (k = 0; k < ilosc_przedzialow; k++) {
			i = kubelek[k];
			while (i != 0) {
				L[i].dane.end = j; // pozycja koncowa
				tab[j++] = L[i].dane;
				i = L[i].nastepnik;
			}
		}
		System.out
				.println("Tablica kubelek[] zawiera indeksy pierwszych elementow w poszczegolnych ");
		System.out
				.println("kubelkach zaczynajac numeracje od 1; 0 - oznacza ze kubelek jest pusty");
		for (i = 0; i < ilosc_przedzialow; i++)
			System.out.print(kubelek[i] + " ");
		System.out.println();
		System.out.println("Liczby w poszczególnych kubelkach:");
		for (k = 0; k < ilosc_przedzialow; k++) {
			System.out.print("kubelek " + (k + 1) + ": ");
			i = kubelek[k];
			while (i != 0) {
				System.out.print(L[i].dane.el + " ");
				i = L[i].nastepnik;
			}
			System.out.println();
		}
		System.out.println("\nPo sortowaniu:");
		print();
		for (i = 0; i < tab.length; i++) {
			System.out.print("klucz: " + tab[i].el + " poz.pocz.: "
					+ tab[i].start);
			System.out.println("  poz.konc: " + tab[i].end);
		}
		System.out.println();
	}

	private void print() {
		for (int i = 0; i < tab.length - 1; i++)
			System.out.print(tab[i].el + "   ");
		System.out.println("\n");
	}
}
