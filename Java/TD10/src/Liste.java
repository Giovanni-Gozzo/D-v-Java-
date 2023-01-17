
public class Liste {

    private Maillon tete;

    /** Constructeur d'une liste vide
     */
    public Liste(){
		tete=null;
    }

	/** Constructeur d'une liste a un seul element
     */
    public Liste (int x) {
		this.tete=new Maillon(x);
    }
    
  /** @param tabListe est un tableau contenant les elements de la liste
     * Pre-requis : aucun
     */
    public Liste (int[] tabListe) {
	this(); // Liste()
	if (tabListe.length > 0) {
	    this.tete = new Maillon (tabListe[0]);
	    Maillon curThis = this.tete;
	    for (int i = 1 ; i < tabListe.length ; i++) {
		curThis.setSuiv (new Maillon(tabListe[i])); // creation et accrochage du maillon suivant
		curThis = curThis.getSuiv();
	    }
	}
    }

   /**
     * Prerequis: aucun
     * construit une liste completement disjointe de la liste l 
     */
    public Liste (Liste l) { // constructeur par recopie profonde
	this(); 
	if (! l.estVide()) {

	    this.tete = new Maillon (l.tete.getVal());
	    Maillon curThis = this.tete;
	    Maillon curL = l.tete.getSuiv();

	    while (curL != null) {
		curThis.setSuiv (new Maillon(curL.getVal())); // creation et accrochage du maillon suivant
		curThis = curThis.getSuiv();
		curL = curL.getSuiv();
	    }
	}
    }

    public boolean estVide() {
	return (this.tete == null) ;
    }

    public void ajoutTete (int x) {
	Maillon m = new Maillon(x);
	m.setSuiv(this.tete);
	this.tete=m;
    }

    public boolean contient (int x) {
	Maillon courant = this.tete;
	while (courant != null && courant.getVal() != x) {
	    courant = courant.getSuiv(); 
	}
	return courant != null;
    }

    public String toString() {
	String s = "(";
	Maillon courant = this.tete;
	while (courant != null) {
	    s = s + (courant.getVal()) + ((courant.getSuiv() != null)?", ":"");
	    courant = courant.getSuiv();
	}
	return s + ")";
    }
	/** Résultat : le nombre d’éléments de la liste ’this’. */
	public int longueur(){
		Maillon m=this.tete;
		int x=0;
		while(m!=null){
			x++;
			m.getSuiv();
		}
		return x;
	}
	/** Résultat : la somme des éléments de la liste ’this’. */
	public int somme(){
		Maillon m=this.tete;
		int x=0;
		while (m!=null){
			x+=m.getVal();
			m.getSuiv();
		}
	}
	public int maximum(){
		Maillon m=this.tete;
		int x=this.tete.getVal();
		while (m!=null){
			if(x<m.getVal()){
				x=m.getVal();
			}
			m.getSuiv();
		}
		return x;
	}
} // end class
