import java.io.*;
import java.util.*;

//import java.awt.*;
public class Randomized {
		
	public static void arrayPrint(int t[]){
		for(int i=0;i<t.length;i++)
			System.out.print(t[i]+ " ");
		System.out.println();
	}
	public static void arrayPrintStat(int t[],int stat){
		for(int i=0;i<stat-1;i++)
			System.out.print(t[i]+ " ");
		System.out.print("|"+t[stat-1]+"| ");
		for(int i=stat;i<t.length;i++)
			System.out.print(t[i]+ " ");
		System.out.println();
	}
	public static void QuickSort(int t[],int x, int y){
		int pom; 
		int i=x;
		int j=y;
		pom=t[(x+y)/2];
		do {
			while (t[i]<pom){   i++; }
			while (pom<t[j]){  	j--; }
			if (i<=j) {
				int temp; temp=t[i]; t[i]=t[j]; t[j]=temp;
					i++; j--;
			}
		}
		while (i<=j);
		if (x<j) QuickSort(t,x,j);
		if (i<y) QuickSort(t,i,y);
	}
	public static void insertion(int t[],int x,int y) {
        //sortowanie przez wstawianie
		//sortowanie elementow od indeksu x do indeksu y
        for (int i = x+1; i <= y; i++) {
            int j = i ;
            int v = t[i];
            while (j > x && v < t[j-1]) {
                t[j] = t[j-1];
                j--;
            }
            t[j] = v;
        }
	}
	public static int RandomizedPartition(int t[],int p,int r){
		Random rm=new Random();
		int k=rm.nextInt(r-p+1)+p;
		int temp=t[k]; t[k]=t[p]; t[p]=temp;
		
	int x=t[p];
	int i=p-1;
	int j=r+1;
	while (true) {
	    do {    j=j-1;
	    }while (t[j]>x);
	    do {    i=i+1;
	    }while (t[i]<x);
	    if (i<j){  temp=t[i]; t[i]=t[j];  t[j]=temp;}   
	    else  return j;
	}
	}
	public static int RandomizedSelect(int t[],int p,int r,int i){
		  int q,k;  
		 if (p==r){ return t[p];}
		 arrayPrint(t);
		  q=RandomizedPartition(t,p,r);
		  k=q-p+1;
		   if (i<=k) { return RandomizedSelect(t,p,q,i);}   
		  else { return RandomizedSelect(t,q+1,r,i-k);}  
		
		}
	
	
	public static void main(String[] args)throws IOException {
		int p,temp;
		Random rm=new Random();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Jak d³ugi ma byæ ci¹g? ");
		int n=Integer.parseInt(br.readLine());
		int[] ciag=new int[n];
		
		System.out.println("Losowe ciagi kluczy\n KLAWISZ - 1");
		System.out.println("Losowe ciagi roznowartosciowe\n INNA CYFRA");
		int c1=Integer.parseInt(br.readLine());
	
		if(c1==1){
			for(int i=0;i<ciag.length;i++){
			  temp=rm.nextInt(100); 
			  ciag[i]=temp;
		    }
		}
			else{
				int[] liczby=new int[n];
		        for (int i=0; i<liczby.length;i++)
		    	  liczby[i]=i+1;
		       
		       p=n-1;
		    for (int i=0; i<liczby.length-1;i++){
		    	temp=rm.nextInt(p);
		    	ciag[i]=liczby[temp];
		    	liczby[temp]=liczby[p];
		    	p--;
		    }
		    ciag[n-1]=liczby[0];	
		}
		
		System.out.println("Numer szukanej ststystyki: ");
		int statystyka=Integer.parseInt(br.readLine());	
		
		System.out.println("Tablica przed sortowaniem: ");		
				
		for (int i=0; i<ciag.length; i++)
		   System.out.print(ciag[i]+" ");
        System.out.println();
		RandomizedSelect(ciag,0,n-1,statystyka);
		arrayPrintStat(ciag,statystyka);
		
		System.out.println("Nacisnij cyfre aby dosortowac lewe klucze: ");
		@SuppressWarnings("unused")
		int cyfra=Integer.parseInt(br.readLine());	
		
		//QuickSort(ciag,0,statystyka-2);
		insertion(ciag,0,statystyka-2);
		arrayPrintStat(ciag,statystyka);
		
		System.out.println("Nacisnij cyfre aby dosortowac prawe klucze: ");
		cyfra=Integer.parseInt(br.readLine());	
		
		//QuickSort(ciag,statystyka,n-1);
		insertion(ciag,statystyka,n-1);
		arrayPrintStat(ciag,statystyka);
		}	

}
