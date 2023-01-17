public class RelationBinaire {

    // attributs
    //DERNIERE MODIF GH 16/12

    private int n;           // n > 0, E = {0,1,2, ..., n-1}
    private boolean[][] matAdj;  // matrice d'adjacence de R
    private int m;           // cardinal de R
    private EE[] tabSucc;    // tableau des ensembles de successeurs


    // constructeurs

    //______________________________________________

    /**
     * pré-requis : nb > 0
     * action : construit la relation binaire vide dans l'ensemble {0,1,2, ..., nb-1}
     */
    public RelationBinaire(int nb) {
        this.n = nb;
        this.matAdj = new boolean[nb][nb];
        this.m = 0;
        this.tabSucc = new EE[nb];
        for (int i = 0; i < nb; i++) this.tabSucc[i] = new EE(nb);
    }

    //______________________________________________

    /**
     * pré-requis : nb > 0 et 0 <= p <= 1
     * action : construit une relation binaire aléatoire dans l'ensemble {0,1,2, ..., nb-1}
     * à laquelle chaque couple a la probabilité p d'appartenir.
     * En particulier, construit la relation vide si p = 0 et la relation pleine si p = 1.
     * Indication : Math.random() retourne un réel de type double aléatoire de l'intervalle [0,1[
     */
    public RelationBinaire(int nb, double p) {
        this(nb);
        for (int i = 0; i < nb; i++)
            for (int j = 0; j < nb; j++)
                if (Math.random() <= p) this.ajouteCouple(i, j);
    }

    /**
     * pré-requis : nb > 0
     * action : construit la relation binaire dans l'ensemble {0,1,2, ..., nb-1}
     * '=' si egal a la valeur vrai et '<=' sinon
     */
    public RelationBinaire(int nb, boolean egal) {
        this(nb);
        if (egal) {
            for (int i = 0; i < nb; i++)
                for (int j = 0; j < nb; j++)
                    if (i == j) this.ajouteCouple(i, j);
        } else {
            for (int i = 0; i < nb; i++)
                for (int j = 0; j < nb; j++)
                    if (i <= j) this.ajouteCouple(i, j);
        }
    }

    //______________________________________________

    /**
     * pré-requis : mat est une matrice carrée de dimension > 0
     * action : construit une relation binaire dont la matrice d'adjacence
     * est une copie de mat
     * //MODIF
     */
    public RelationBinaire(boolean[][] mat) {
        this(mat.length);
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat.length; j++)
                if (mat[i][j]) this.ajouteCouple(i, j);
    }

    //______________________________________________

    /**
     * pré-requis : tab.length > 0 et pour tout i, les éléments de tab[i]
     * sont compris entre 0 et tab.length-1
     * action : construit une relation binaire dont le tableau des ensembles de successeurs
     * est une copie de tab
     */
    public RelationBinaire(EE[] tab) {
        this(tab.length);
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab.length; j++)
                if (tab[i].contient(j)) this.ajouteCouple(i, j);
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * action : construit une copie de r
     */
    public RelationBinaire(RelationBinaire r) {
        this(r.n);
        for (int i = 0; i < r.n; i++)
            for (int j = 0; j < r.n; j++)
                if (r.matAdj[i][j]) this.ajouteCouple(i, j);
    }

    //______________________________________________

    /**
     * pré-requis : m1 et m2 sont des matrices carrées de même dimension et 1 <= numConnecteur <= 5
     * résultat : la matrice obtenue en appliquant terme à terme le  connecteur de numéro numConnecteur
     * sur m1 si numConnecteur  = 3 (dans ce cas le paramètre m2 n'est pas utilisé),
     * et sur m1 et m2 dans cet ordre sinon, sachant que les connecteurs "ou","et","non",
     * "implique"et "equivalent" sont numérotés de 1 à 5 dans cet ordre
     */
    public static boolean[][] opBool(boolean[][] m1, boolean[][] m2, int numConnecteur) {
        boolean[][] res = new boolean[m1.length][m1.length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j < m1.length; j++)
                if (numConnecteur == 1) res[i][j] = m1[i][j] || m2[i][j];
                else if (numConnecteur == 2) res[i][j] = m1[i][j] && m2[i][j];
                else if (numConnecteur == 3) res[i][j] = !m1[i][j];
                else if (numConnecteur == 4) res[i][j] = !m1[i][j] || m2[i][j];
                else if (numConnecteur == 5) res[i][j] = m1[i][j] == m2[i][j];
        return res;
    }


    // méthodes

    //______________________________________________

    /**
     * pré-requis : m1 et m2 sont des matrices carrées de même dimension
     * résultat : le produit matriciel de m1 et m2
     */
    public static boolean[][] produit(boolean[][] m1, boolean[][] m2) {
        boolean[][] res = new boolean[m1.length][m1.length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j < m1.length; j++)
                for (int k = 0; k < m1.length; k++)
                    res[i][j] = res[i][j] || (m1[i][k] && m2[k][j]);
        return res;
    }

    //______________________________________________


    // A) Logique et calcul matriciel
    //-------------------------------

    /**
     * pré-requis : m est une matrice carrée
     * résultat : la matrice transposée de m
     */
    public static boolean[][] transposee(boolean[][] m) {
        boolean[][] t = new boolean[m.length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m.length; j++) t[i][j] = m[j][i];
        return t;
    }

    // B) Théorie des ensembles
    //--------------------------

    /**
     * pré-requis : aucun
     * résultat : une chaîne de caractères permettant d'afficher this par sa matrice d'adjacence
     * contenant des '0' et des '1' (plus lisibles que des 'V' et des 'F') et sa définition
     * en extension (ensemble de couples {(..,..),(..,..), ...})
     */
    public String toString() {
        String s = "Matrice d'adjacence : \n";
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.matAdj[i][j]) s += "1 ";
                else s += "0 ";
            }
            s += "\n";
        }
        s += "Définition en extension : \n";
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j]) s += "(" + i + "," + j + ") ";
        return s;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est vide
     */
    public boolean estVide() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matAdj[i][j]) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est pleine (contient tous les couples d'éléments de E)
     */
    public boolean estPleine() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (!matAdj[i][j]) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi (x,y) appartient à this
     */
    public boolean appartient(int x, int y) {
        return matAdj[x][y];
    }

    //______________________________________________

    /**
     * pré-requis : 0 <= x < this.n et 0 <= y < this.n
     * résultat : ajoute (x,y) à this s'il n'y est pas déjà
     */
    public void ajouteCouple(int x, int y) {
        if (!this.matAdj[x][y]) {
            this.matAdj[x][y] = true;
            this.m++;
        }
    }

    //______________________________________________
    public void enleveCouple(int x, int y) {
        if (!this.matAdj[x][y]) {
            this.matAdj[x][y] = true;
            this.m--;
        }
    }
    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : une nouvelle relation binaire obtenue à partir de this en ajoutant
     * les couples de la forme  (x,x) qui n'y sont pas déjà
     */
    public RelationBinaire avecBoucles() {
        RelationBinaire r = new RelationBinaire(this.n);
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j]) r.ajouteCouple(i, j);
        for (int i = 0; i < this.n; i++) r.ajouteCouple(i, i);
        return r;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : une nouvelle relation binaire obtenue à partir de this en enlèvant
     * les couples de la forme  (x,x) qui y sont
     * //DERNIERE MODIF
     */
    public RelationBinaire sansBoucles() {
        RelationBinaire r = new RelationBinaire(this.n);
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j]) r.ajouteCouple(i, j);
        for (int i = 0; i < this.n; i++) r.enleveCouple(i, i);
        return r;
    }

    //______________________________________________

    /**
     * pré-requis : this.n = r.n
     * résultat : l'union de this et r
     */
    public RelationBinaire union(RelationBinaire r) {
        RelationBinaire res = new RelationBinaire(this.n);
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j] || r.matAdj[i][j]) res.ajouteCouple(i, j);
        return res;
    }

    //______________________________________________

    /**
     * pré-requis : this.n = r.n
     * résultat : l'intersection de this et r
     */
    public RelationBinaire intersection(RelationBinaire r) {
        RelationBinaire res = new RelationBinaire(this.n);
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j] && r.matAdj[i][j]) res.ajouteCouple(i, j);
        return res;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : la relation complémentaire de this
     */
    public RelationBinaire complementaire() {
        RelationBinaire res = new RelationBinaire(this.n);
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (!this.matAdj[i][j]) res.ajouteCouple(i, j);
        return res;
    }

    //______________________________________________

    /**
     * pré-requis : this.n = r.n
     * résultat : la différence de this et r
     */
    public RelationBinaire difference(RelationBinaire r) {
        RelationBinaire res = new RelationBinaire(this.n);
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j] && !r.matAdj[i][j]) res.ajouteCouple(i, j);
        return res;
    }

    //______________________________________________

    /**
     * pré-requis : this.n = r.n
     * résultat : vrai ssi this est incluse dans r
     */
    public boolean estIncluse(RelationBinaire r) {
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j] && !r.matAdj[i][j]) return false;
        return true;
    }

    // C) Théorie des graphes orientés
    //---------------------------------

    /**
     * pré-requis : this.n = r.n
     * résultat : vrai ssi this est égale à r
     */
    public boolean estEgale(RelationBinaire r) {
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.matAdj[i][j] != r.matAdj[i][j]) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : 0 <= x < this.n
     * résultat : l'ensemble des successeurs de x dans this, "indépendant"
     * (c'est-à-dire dans une autre zône mémoire) de l'attribut this.tabSucc
     */
    public EE succ(int x) {
        EE res = new EE(n);
        for (int i = 0; i < n; i++) if (matAdj[x][i]) res.ajoutElt(i);
        return res;
    }

    // D) Relation binaire
    //---------------------

    /**
     * pré-requis : 0 <= x < this.n
     * résultat : l'ensemble des prédécesseurs de x dans this
     */
    public EE pred(int x) {
        EE res = new EE(n);
        for (int i = 0; i < n; i++) if (matAdj[i][x]) res.ajoutElt(i);
        return res;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est réflexive
     */
    public boolean estReflexive() {
        for (int i = 0; i < n; i++) if (!matAdj[i][i]) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est antiréflexive
     */
    public boolean estAntireflexive() {
        for (int i = 0; i < n; i++) if (matAdj[i][i]) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est symétrique
     */
    public boolean estSymetrique() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matAdj[i][j] != matAdj[j][i]) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est antisymétrique
     */
    public boolean estAntisymetrique() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matAdj[i][j] && matAdj[j][i] && i != j) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est transitive
     */
    public boolean estTransitive() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matAdj[i][j])
                    for (int k = 0; k < n; k++)
                        if (matAdj[j][k] && !matAdj[i][k]) return false;
        return true;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : vrai ssi this est une relation d'ordre
     */
    public boolean estRelOrdre() {
        return estReflexive() && estTransitive();
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : la relation binaire assiciée au diagramme de Hasse de this
     */
    public RelationBinaire hasse() {
        RelationBinaire res = new RelationBinaire(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matAdj[i][j] && !matAdj[j][i]) res.ajouteCouple(i, j);
        return res;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * résultat : la fermeture transitive de this
     */
    public RelationBinaire ferTrans() {
        RelationBinaire res = new RelationBinaire(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (matAdj[i][j]) res.ajouteCouple(i, j);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (res.matAdj[i][j])
                    for (int k = 0; k < n; k++)
                        if (res.matAdj[j][k]) res.ajouteCouple(i, k);
        return res;
    }

    //______________________________________________

    /**
     * pré-requis : aucun
     * action : affiche this sous 2 formes (matrice et ensemble de couples), puis affiche ses propriétés
     * (réflexive, ..., relation d'ordre) et les relations binaires suivantes obtenues à partir de this :
     * Hasse, fermeture transitive de Hasse et fermeture transitive de Hasse avec boucles (sous 2 formes aussi)
     */
    public void afficheDivers() {
        System.out.println("this = " + this);
        System.out.println("this est reflexive : " + this.estReflexive());
        System.out.println("this est antireflexive : " + this.estAntireflexive());
        System.out.println("this est symétrique : " + this.estSymetrique());
        System.out.println("this est antisymétrique : " + this.estAntisymetrique());
        System.out.println("this est transitive : " + this.estTransitive());
        System.out.println("this est une relation d'ordre : " + this.estRelOrdre());
        RelationBinaire hasse = this.hasse();
        System.out.println("Hasse de this = " + hasse);
        RelationBinaire ferTransHasse = hasse.ferTrans();
        System.out.println("fermeture transitive de Hasse de this = " + ferTransHasse);
        RelationBinaire ferTransHasseBoucles = ferTransHasse.ferTrans();
        System.out.println("fermeture transitive de Hasse de this avec boucles = " + ferTransHasseBoucles);
    }
    public static boolean booleanverifCNordre(int nbRel,int cardMax){
        double alea=Math.random();
        for(int g=0;g<nbRel;g++){
            RelationBinaire R=new RelationBinaire(cardMax,alea);
            RelationBinaire ferT= R.hasse().ferTrans().ferTrans();
            boolean result=true;
            if (R.matAdj.length != ferT.matAdj.length) {
                result= false;
            } else {
                for (int i= 0; i< R.matAdj.length; i++) {
                    if (R.matAdj[i].length != ferT.matAdj[i].length) {
                        result= false;
                    } else {
                        for (int j=0; j< R.matAdj[i].length; j++) {
                            if (R.matAdj[i][j] != ferT.matAdj[i][j]) {
                                result= false;
                            }
                        }
                    }
                }
            }
            if(R.estRelOrdre() && !result){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(booleanverifCNordre(10000000,100));
    }
} // fin RelationBinaire