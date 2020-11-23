import java.util.Arrays;

public class Merge {
	Element[] tab;
	int n;
	int porow=0;  
	Element[] t;
	
	public Merge(int[] t,int a) {
		this.n = a;
		tab=new Element[n];
		for(int i=0;i<n;i++){
			tab[i]=new Element(t[i],i);		
	    }
	}
	
	private void klucz(){	
		int najw=tab[0].mov;
		for(int i=1;i<tab.length;i++){
			if(najw<tab[i].mov)
				najw=tab[i].mov;
	    }  
		for (int i=0; i<tab.length; i++){
			if(najw==tab[i].mov){
				System.out.println("Klucz najczêœciej przestawiany: " + tab[i].el);
		        System.out.println("Zosta³ przeniesiony "+ tab[i].mov + " razy");
		        System.out.print("najpierw by³ na pozycji " + tab[i].start);
		        System.out.println(" teraz jest na pozycji " + tab[i].end+"\n");
			}

		}
	}
	
	public void merge(int pocz, int sr, int kon){
		int i,j,q;
		t=Arrays.copyOf(tab,n);
		i=pocz; 
		j=sr+1; 
		q=pocz;         
		while (i<=sr && j<=kon) {
			if (t[i].el<t[j].el){
				porow++;
				t[i].end=q;
				t[i].mov++;
				tab[q++]=t[i++];
			}
			else {
				porow++;
				t[j].end=q;
				t[j].mov++;
				tab[q++]=t[j++];
			}

		 }
			while (i<=sr){ 
				t[i].end=q;
				t[i].mov++;
				tab[q++]=t[i++];
			}
	}
	
	public void sort(int pocz, int kon){
		int sr;
		if (pocz<kon){
			sr=(pocz+kon)/2;
			sort(pocz, sr);  
		
			sort(sr+1, kon);
			
			merge(pocz, sr, kon);
			print();
		}
	}  
	
	
	public void print(){
		for(int i=0;i<n;i++)
			System.out.print(tab[i].el+ "   ");
		System.out.println();
	}
	
	public void conclusion(){
		System.out.println("\nLiczba porównañ miêdzy kluczami: "+ porow);
		int totalMov=0;
		for(int i=0;i<tab.length;i++)
			totalMov+=tab[i].mov;
		System.out.println("Liczba wszystkich przeniesieñ kluczy: "+ totalMov);
		System.out.println("klucz  start  end   mov");
		for (int i=0;i<tab.length;i++)
			System.out.println(tab[i].el+"         "+tab[i].start+"    "+tab[i].end+"     "+tab[i].mov);
		    
		klucz();
		
	}
}