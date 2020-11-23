import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class BTTest {

	public static void main(String[] args) throws IOException {
		Random rm = new Random();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int Il_Por_Kluczy = 0;
		int Max_Por_Kluczy = 0;
		int Suma_Por_Kluczy = 0;

		System.out.print("Ilosc kluczy: ");
		int n = Integer.parseInt(br.readLine());

		int[] klucze = new int[n];

		for (int i = 0; i < n; i++)
			klucze[i] = i + 10;

		System.out.println();
		System.out
				.println("Posortowany ciag kluczy\n 1-TAK\nInny klawisz -NIE(Losowe ciagi kluczy)");
		int c1 = Integer.parseInt(br.readLine());
		boolean c = false;
		if (c1 == 1)
			c = true;
		if (c == false)
			for (int i = 0; i < n; i++) { // permutacja kluczy
				int l = rm.nextInt(n - i);
				int temp = klucze[n - i - 1];
				klucze[n - i - 1] = klucze[l];
				klucze[l] = temp;
			}

		System.out.print("Parametr B-Drzewa t>=2: ");
		int t = Integer.parseInt(br.readLine());

		System.out.println();
		System.out.println("Wybor krokowego trybu pracy:\n");
		System.out
				.println("Tryb po kazdym wyswietleniu\n 1-TAK\nInny klawisz -NIE(po kazdej operacji)");
		int tryb1 = Integer.parseInt(br.readLine());
		boolean tryb = false;
		if (tryb1 == 1)
			tryb = true;
		System.out.println("klawisz ENTER - dalej...");
		BTree btree = new BTree(t);

		for (int i = 0; i < n; i++) {
			System.out.println("\nWstawianie klucza " + klucze[i]);
			btree.BTinsert(klucze[i], tryb);

			Il_Por_Kluczy = btree.il_por_kluczy;
			Max_Por_Kluczy = max(Max_Por_Kluczy, Il_Por_Kluczy);
			Suma_Por_Kluczy += btree.il_por_kluczy;

			btree.printPreOrder();
			btree.print();

			System.out.println("\n--- BTree po wstawieniu klucza ---");

			br.readLine(); // zatrzymanie po operacji wstawiania-tryb
							// "po operacji"
		}

		System.out.println("Statystyki:");
		System.out.println();
		System.out
				.println("Maksimum po liczbie porownan kluczy w trakcie pojedynczej operacji insert: "
						+ Max_Por_Kluczy);
		System.out.println("Srednia ilosc porównan kluczy dla insert: "
				+ Suma_Por_Kluczy / n + "\n");

		System.out.println("Koniec");

	}

	private static int max(int x, int y) {
		return x > y ? x : y;
	}
}
