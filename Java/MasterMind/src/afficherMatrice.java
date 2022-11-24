public class afficherMatrice {
    public static void afficherMatrice(int[][] T) {
        int c = T.length;
        int l = T[0].length;
        for (int i = 0; i < c; i++) {
            if(i>0){
                System.out.println();
            }
            for (int j = 0; j < l; j++) {
                System.out.print(T[i][j]+" ");
            }
        }
    }
    /**
     * Donnée : 1 matrice
     * Résultat :renvoie rien mais affiche l'entierete de la matrice ligne par ligne et colonne par colonne.
     */
}