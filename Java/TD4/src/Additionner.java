public class Additionner{
    /**
     * Donnée : deux matrices d' entiers P et Q de même dimensions
     * Résultat : retourne une nouvelle matrice égale à P+ Q
     */
    public static int[][] additioner(int[][] P,int[][] Q){
        int c=P.length;
        int l=P[0].length;
        int[][] tad=new int[c][l];
        for(int i=0;i<c;i++){
            for(int j=0;j<l;j++){
                tad[i][j]=P[i][j]+Q[i][j];
            }
        }
        return tad;
    }
    /**
     * Donnée : deux matrices d' entiers P et Q de même dimensions
     * Résultat : retourne une nouvelle matrice égale à P+ Q
     */
}
