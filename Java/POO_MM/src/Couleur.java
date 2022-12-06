public class Couleur {
    private static char[]tabCouleurs;
    public static void setTabCouleurs(char[]tab){
        tabCouleurs=UtMM.copieTabchar(tab);
    }
    public static int nbCouleurs(){
        int nbCouleurs=tabCouleurs.length;
        return nbCouleurs;
    }


}
