import java.util.*;
import java.io.*;
public class Sort{
	public static void reverse(int[] t){
		int n=t.length;
		int[] clone=Arrays.copyOf(t, n);
		Arrays.sort(clone);
		for(int i=0;i<n-1;i++)
			t[i]=clone[n-i-1];
		t[n-1]=clone[0];
	}
	public static void arrayPrint(int t[]){
		for(int i=0;i<t.length;i++)
			System.out.print(t[i]+ "   ");
		System.out.println();
	}
	public static void main(String[] args) throws IOException{
		Random rm=new Random();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int temp;
		
		System.out.print("Jak d³ugi ma byæ ci¹g? ");
		int n=Integer.parseInt(br.readLine());
		int[] ciag=new int[n];
		int[][] log=new int[n][2];
		
		for(int i=0;i<n;i++){
			temp=rm.nextInt(101); 
			ciag[i]=temp;
		}
		for(int i=0;i<n;i++){
			log[i][0]=i;
		    log[i][1]=0;
		}	
		System.out.print("Sortowanie ci¹gu malej¹cego?\n 1-TAK\nInny klawisz - NIE");
		int c1=Integer.parseInt(br.readLine());
		boolean c=false;
		if(c1==1)
			c=true;
		if(c==true)
			Sort.reverse(ciag);
		System.out.println("Tablica przed sortowaniem: \n");
		Sort.arrayPrint(ciag);
		
		System.out.print("\nJakim algorytmem posortowaæ ci¹g? ");
		System.out.println("\n1 - Insertion Sort");
		System.out.println("2 - Merge Sort");
		System.out.println("3 - Heap Sort");
		System.out.println("4 - Quick Sort");
		c1=Integer.parseInt(br.readLine());
		switch(c1){
			case 1:{
				Insertion insertion=new Insertion(ciag,n);
				insertion.sort();
				System.out.println("Tablica po posortowaniu:");
				insertion.print();
				insertion.conclusion();
				break;
			}
			case 2:{
				Merge merge=new Merge(ciag,n);
				merge.sort(0, n-1);
				System.out.println("Tablica po posortowaniu:");
				merge.print();
				merge.conclusion();
				break;
			}
			case 3:{
				Heap heap=new Heap(ciag,n);
				heap.sort();
				System.out.println("Tablica po posortowaniu:");
				heap.print();
				heap.conclusion();
				break;
			}
			case 4:{
				Quick quick=new Quick(ciag,n);
				quick.sort(0,n-1);
				System.out.println("Tablica po posortowaniu:");
				quick.print();
				quick.conclusion();
				break;
			}
		}
	}

}
