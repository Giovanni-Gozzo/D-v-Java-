public class Main {
    public static void main(String[] args) {
        char[] tabCouleur={'R','B','J','V','O','N'};
        int lgCode=4;
        int[][] Cod={{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        int[][] rep={{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}}; //4433
        int nbCoups=1;
        int[] cod1={0,0,0,0};


       // MasterMindBase.mancheOrdinateur(4,tabCouleur,10,7);
        MasterMindBase.passeCodeSuivantLexicoCompat(cod1,Cod,rep,nbCoups,6);
        MasterMindBase.afficherTableau(cod1);
    }
}