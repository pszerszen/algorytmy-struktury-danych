import java.util.Arrays;
//import java.util.Random;
import java.util.*;
import java.io.*;

public class Sort {
	public static void reverse(double[] t) {
		int n = t.length;
		double[] clone = Arrays.copyOf(t, n);
		Arrays.sort(clone);
		for (int i = 0; i < n - 1; i++)
			t[i] = clone[n - i - 1];
		t[n - 1] = clone[0];
	}

	public static void arrayPrint(int t[]) {
		for (int i = 0; i < t.length; i++)
			System.out.print(t[i] + "   ");
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		Random rm = new Random();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double temp;
		System.out.print("Jak d³ugi ma byæ ci¹g? ");
		int n = Integer.parseInt(br.readLine());
		double[] ciag = new double[n];
		int[][] log = new int[n][2];
		for (int i = 0; i < ciag.length; i++) {
			temp = rm.nextInt(999) / 1000.0;
			ciag[i] = temp;
		}

		for (int i = 0; i < n; i++) {
			log[i][0] = i;
			log[i][1] = 0;
		}
		System.out
				.print("Sortowanie ci¹gu malej¹cego?\n 1-TAK\nInny klawisz - NIE");
		int c1 = Integer.parseInt(br.readLine());
		boolean c = false;
		if (c1 == 1)
			c = true;
		if (c == true)
			Sort.reverse(ciag);
		System.out.println("Tablica przed sortowaniem: \n");
		Bucket bucket = new Bucket(ciag, ciag.length);
		for (int i = 0; i < ciag.length; i++)
			System.out.print(ciag[i] + "  ");

		System.out.println();

		bucket.sort();

	}

}
