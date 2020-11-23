public class Heap {
	
	Element[] tab;
	int n;
	int porow=0;
	
	public Heap(int[] t,int a){
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
	
	public void heapify (int i,int n){
		int l, r, largest;
		Element pom;
	    l = 2*i;
	    r = 2*i + 1;
	 
	    if ((l < n) && (tab[l].el > tab[i].el)){
	    	largest = l;
	    	porow++;
	    }	        
	    else
	        largest = i;
	 
	    if ((r < n) && (tab[r].el > tab[largest].el)){
	    	  largest = r;
	    	  porow++;
	    } 
	    if (largest != i){
	    	tab[i].mov++;
	    	tab[largest].mov++;
	    	tab[i].end=largest;
	    	tab[largest].end=i;
	    	pom=tab[i];
	    	tab[i]=tab[largest];
	    	tab[largest]=pom;
	        print();   
	        heapify(largest, n);
	    }
	}
	
	public void budKopiec(int n){
	for (int i=n/2;i>=0;i--)
	heapify(i, n);
	}
	
	public void sort(){		
		Element pom;
	    budKopiec(n);
	    int N=n;
	    for (int i = n-1; i >= 1; --i){
	    	tab[0].mov++;
    	    tab[i].mov++;
    	    tab[i].end=0;
    	    tab[0].end=i;
	        pom=tab[0];
	        tab[0]=tab[i];
	        tab[i]=pom;
	        print();
	    	--N;
	        heapify(0, N);
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