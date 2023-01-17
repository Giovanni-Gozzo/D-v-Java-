public class Couleur {
    private static char[]tabCouleurs;
    public static void setTabCouleurs(char[]tab){
        tabCouleurs=UtMM.copieTabchar(tab);
    }
    public static char[] getTabCouleurs(){
        return tabCouleurs;
    }
    public static int nbCouleurs(){
        int nbCouleurs=tabCouleurs.length;
        return nbCouleurs;
    }
    public static char entiersVersChar(int entier,char[]tabchar){
        char c=tabchar[entier];
        return c;
    }
    public static int charVersEntiers(char c,char[]tabchar){
        for(int i=0;i<tabchar.length;i++){
            if(tabchar[i]==c){
                return i;
            }
        }
        return -1;
    }
    public static boolean contient(char c){
        boolean Present=false;
        if(Couleur.plusGrandIndice(c)>-1){
            Present=true;
        }
        return Present;
    }
    public static int[] motVersEntiers(String codMot){
        int[] indicet=new int[codMot.length()];
        char[] charcodMot=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charcodMot[i] = codMot.charAt(i);
        }
        for(int i = 0;i<codMot.length();i++){
            indicet[i] = Couleur.plusGrandIndice(charcodMot[i]);
        }
        return indicet;
    }
}
