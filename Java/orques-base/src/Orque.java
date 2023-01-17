public class Orque {
    private int id;
    private Arene arene;
    private int nbOrques;
    private Orque [] tabOrques = new Orque [1000];
    public Orque(Arene arene){
        this.arene=arene;
        this.id=nbOrques;
        nbOrques++;
        tabOrques[this.id]=this;
    }
    public int getId() {
        return id;
    }
    public Orque[] getTabOrques() {
        return tabOrques;
    }
    public void setNbOrques(int nbOrques) {
        Orque.nbOrques = nbOrques;
    }

    public Orque Combats(Orque O1, Orque O2){
        int rdm=Ut.randomMinMax(0,1);
        if(rdm==0){
            return O1;
        }
        return O2;
    }
    public Orque getOrqueById(int ident){
        return tabOrques[ident];
    }
    public String toString(){
        System.out.println("l'Orques numéro "+this.id);
    }
}
