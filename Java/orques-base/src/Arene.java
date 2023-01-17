public class Arene {
    private EE ensOrques;

    public void setEnsOrques(EE ensOrques) {
        this.ensOrques = ensOrques;
    }
    public Arene(int nbo){
        for(int i=0;i<nbo;i++){
            ensOrques.ajouter(Orque.getId());
            Orque o=new Orque(this);
        }
    }
}
