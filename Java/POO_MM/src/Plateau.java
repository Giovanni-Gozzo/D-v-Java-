public class Plateau {
    private static int nbEssaisMax;
    private Code[] cod;
    private int[][] rep;
    private int nbCoups;
    public void setNbCoups(int nb) {
        nbCoups = nb;
    }
    public int getNbCoups(){
        return nbCoups;
    }
    public int nbBienPlaces(int[] cod2){
        int nbBienPlaces=0;
        for(int i=0;i<cod2.length;i++){
            if(Code.codegener()[i]==cod2[i]){
                nbBienPlaces++;
            }
        }
        return nbBienPlaces;
    }
    public int nbCommuns(int[] cod2){
        int nbcommuns=0;
        int []tab1=Code.tabFrequence(Code.codegener());
        int []tab2=Code.tabFrequence(cod2);
        for(int i=0;i<Couleur.nbCouleurs();i++){
            if(tab1[i]==tab2[i]){
                nbcommuns+=tab1[i];
            }
            else if(tab1[i]<tab2[i]){
                nbcommuns+=tab1[i];
            }
            else{
                nbcommuns+=tab2[i];
            }
        }
        return nbcommuns;
    }
    public int[] nbBienMalPlaces(int[] cod2){
        int[] nbBienMalPlaces=new int[2];
        nbBienMalPlaces[0]=Plateau.nbBienPlaces(cod2);
        nbBienMalPlaces[1]=Plateau.nbCommuns(cod2)-Plateau.nbBienPlaces(cod2);
        return nbBienMalPlaces;
    }
}
