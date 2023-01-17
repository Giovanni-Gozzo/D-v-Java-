public class UtMM {
    public static char[] copieTabchar(char[] tab) {
        char[] tabchar=new char[tab.length];
        for (int i = 0; i < tab.length; i++) {
            tabchar[i]=tab[i];
        }
        return tabchar;
    }
    public static int[] copieTab(int[] tab){
        return tab.clone();
    }
    public static int[] initTab(int nb, int val){
        int[] tab=new int[nb];
        for(int i=0;i<nb;i++){
            tab[i]=val;
        }
        return tab;
    }
    public static String listElem(char[] t){
        String list="";
        for (int i = 0; i < t.length; i++) {
            if(i==0){
                list+="(";
                list+=t[i];
                list+=")";
            }
            else{
                list+=",(";
                list+=t[i];
                list+=")";
            }
        }
        return list;
    }
    public static boolean sontEgaux(int[] t1, int[] t2){
        boolean Egaux=true;
        for(int i=0;i< t1.length;i++){
            if(t1[i]!=t2[i]){
                Egaux=false;
            }
        }
        return Egaux;
    }
    public static int plusGrandIndice(char c){
        int indice=-1;
        for(int i=0;i<Couleur.getTabCouleurs().length;i++){
            if(Couleur.getTabCouleurs()[i]==c){
                indice=i;
            }
        }
        return indice;
    }

}
