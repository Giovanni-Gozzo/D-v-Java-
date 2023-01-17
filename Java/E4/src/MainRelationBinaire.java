public class MainRelationBinaire {


    /**
     * action : demande à l'utilisateur de saisir un entier nb > 0 et un réel p strictement compris entre 0 et 1 (avec vérification des saisie),
     * puis crée 5 relations binaires dans l'ensemble {0,1,2, ..., nb-1}, R0 vide, R1= '=', R2 = '<=', R3 une relation
     * binaire aléatoire à laquelle chaque couple a la probabilité p d'appartenir et R4 pleine, puis, pour i de 0 à 4,
     * affiche Ri sous 2 formes (matrice et ensemble de couples), puis affiche ses propriétés
     * (réflexive, ..., relation d'ordre) et les relations binaires suivantes obtenues à partir de Ri :
     * Hasse, fermeture transitive de Hasse et fermeture transitive de Hasse avec boucles (sous 2 formes aussi)
     */
    public static void main(String[] args) {
        int nb;
        double p;
        do {
            Ut.afficher("\nDonner le cardinal de E (>0) : ");
            nb = Ut.saisirEntier();
        }
        while (nb <= 0);
        do {
            Ut.afficher("\nDonner la probabilité p (0 < p < 1) : ");
            p = Ut.saisirReel();
        }
        while (p < 0 || p > 1);
        RelationBinaire[] tab = new RelationBinaire[5];
        tab[0] = new RelationBinaire(nb, p);
//        tab[1] = new RelationBinaire(nb, true);
//        tab[2] = new RelationBinaire(nb, false);
//        tab[3] = new RelationBinaire(nb, p);
//        tab[4] = new RelationBinaire(nb, 1);
        for (int i = 0; i < 1; i++) {
            System.out.println("R" + i + " : ");
            tab[i].afficheDivers();
        }
    }
} // fin MainRelationBinaire
