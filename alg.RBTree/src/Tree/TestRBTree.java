package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class TestRBTree {

	public static void main(String[] args) throws IOException {
		Random rm = new Random();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Il_Por_Insert = 0;
		int Max_Por_Insert = 0;
		int Suma_Por_Insert = 0;

		int Il_Rot_Insert = 0;
		int Max_Rot_Insert = 0;
		int Suma_Rot_Insert = 0;

		int Il_Por_Delete = 0;
		int Max_Por_Delete = 0;
		int Suma_Por_Delete = 0;

		int Il_Rot_Delete = 0;
		int Max_Rot_Delete = 0;
		int Suma_Rot_Delete = 0;

		System.out.print("Ilosc kluczy: ");
		int n = Integer.parseInt(br.readLine());

		int[] klucze = new int[n];

		for (int i = 0; i < n; i++)
			klucze[i] = 10 + i;
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

		System.out.println();
		System.out.println("Wybor krokowego trybu pracy:\n");
		System.out
				.println("Tryb po kazdym wyswietleniu\n 1-TAK\nInny klawisz -NIE(po kazdej operacji)");
		int tryb1 = Integer.parseInt(br.readLine());
		boolean tryb = false;
		if (tryb1 == 1)
			tryb = true;
		System.out.println("klawisz ENTER - dalej...");

		RBTree t = new RBTree();

		for (int i = 0; i < n; i++) {
			System.out.println("\nWstawianie klucza " + klucze[i]);
			t.RBInsert(klucze[i], tryb);

			Il_Por_Insert = t.il_por_insert;
			Max_Por_Insert = max(Max_Por_Insert, Il_Por_Insert);
			Suma_Por_Insert += t.il_por_insert;

			Il_Rot_Insert = t.il_rot_insert;
			Max_Rot_Insert = max(Max_Rot_Insert, Il_Rot_Insert);
			Suma_Rot_Insert += t.il_rot_insert;

			t.druk();
			System.out.println("\n---Klucz wstawiony - drzewo RB---");

			br.readLine();
		}
		System.out.println("\nUsuwanie kluczy ");
		br.readLine();
		for (int i = 0; i < n; i++) {
			int l = rm.nextInt(n - i);
			int temp = klucze[n - i - 1];
			klucze[n - i - 1] = klucze[l];
			klucze[l] = temp;
		}

		for (int i = 0; i < n; i++) {
			System.out.println("\nUsuwanie klucza " + klucze[i]);
			t.RBDelete(klucze[i], tryb);

			Il_Por_Delete = t.il_por_delete;
			Max_Por_Delete = max(Max_Por_Delete, Il_Por_Delete);
			Suma_Por_Delete += t.il_por_delete;

			Il_Rot_Delete = t.il_rot_delete;
			Max_Rot_Delete = max(Max_Rot_Delete, Il_Rot_Delete);
			Suma_Rot_Delete += t.il_rot_delete;

			System.out.println();
			t.druk();
			System.out.println("\n---Klucz usuniety - drzewo RB---");

			br.readLine();
		}
		System.out.println("Statystyki:");
		System.out
				.println("Maksimum po liczbie porownan w trakcie pojedynczej operacji RBInsert: "
						+ Max_Por_Insert);
		System.out
				.println("Maksimum po liczbie porównañ w trakcie pojedynczej operacji RBDelete: "
						+ Max_Por_Delete);
		System.out
				.println("Maksimum po liczbie rotacji w trakcie pojedynczej operacji RBInsert: "
						+ Max_Rot_Insert);
		System.out
				.println("Maksimum po liczbie rotacji w trakcie pojedynczej operacji RBDelete: "
						+ Max_Rot_Delete);

		System.out.println("Srednia ilosc porównan dla RBInsert: "
				+ Suma_Por_Insert / n);
		System.out.println("Srednia ilosc porównan dla RBDelete: "
				+ Suma_Por_Delete / n);

		System.out.println("Srednia ilosc rotacji dla RBInsert: "
				+ (double) Suma_Rot_Insert / n);
		System.out.println("Srednia ilosc rotacji dla RBDelete: "
				+ (double) Suma_Rot_Delete / n);

		System.out.println("\nKONIEC ");

	}

	private static int max(int x, int y) {
		return x > y ? x : y;
	}

}
