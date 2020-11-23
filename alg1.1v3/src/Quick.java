public class Quick {
	Element[] tab;
	int n;
	int porow=0;
	
	public Quick(int[] t,int a) {
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
	public void sort(int x, int y){
		Element pom; 
		int i=x;
		int j=y;
		pom=tab[(x+y)/2];
		do {
			while (tab[i].el<pom.el){
			    porow++;
				i++;
			}
			while (pom.el<tab[j].el){
			    porow++;
				j--;
			}
			if (i<=j) {
				   tab[j].mov++;			
				   tab[j].end=i; 
				   tab[i].mov++;			
				   tab[i].end=j;
				Element temp;
				temp=tab[i];
				tab[i]=tab[j];
				tab[j]=temp;
				print();
				i++;
				j--;
			}
		}
		while (i<=j);
		if (x<j)
			sort(x,j);
		if (i<y)
			sort(i,y);
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
