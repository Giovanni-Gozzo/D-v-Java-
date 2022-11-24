import java.util.Scanner;

public class MasterMindBase {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

    // fonctions classiques sur les tableaux

    /** pré-requis : nb >= 0
     résultat : un tableau de nb entiers égaux à val
     */
    public static int[] initTab(int nb, int val){
        assert nb >= 0;
        int[] tab=new int[nb];
        for(int i=0;i<nb;i++){
            tab[i]=val;
        }
        return tab;
    }

    public static void afficherTableau(int[] T) {
        for (int i = 0; i < T.length; i++) {
            System.out.println(T[i]);
        }
    }
    /**
     * Donnée : 1 tableau d'entiers
     * Résultat :renvoie rien mais affiche l'entierete d'un tableau en ligne.
     */

    /** pré-requis : aucun
     résultat : une copie de tab
     */
    public static int[] copieTab(int[] tab){
//        int[] tabcopie=new int[tab.length];
//
//        for(int i=0;i<tab.length;i++){
//            tabcopie[i]=tab[i];
//        }
//        return tabcopie;
        return tab.clone();
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
    /** pré-requis : aucun
     résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
     */
    public static int plusGrandIndice(char[] t, char c){
        int indice=-1;
        for(int i=0;i<t.length;i++){
            if(t[i]==c){
                indice=i;
            }
        }
        return indice;
    }
    /** pré-requis : aucun
     résultat : vrai ssi c est un élément de t
     stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean estPresent(char[] t, char c){
        boolean r=false;
        if(plusGrandIndice(t,c)>-1){
            r=true;
        }
        return r;
    }

    /** pré-requis : aucun
     action : affiche un doublon et 2 de ses indices dans t s'il en existe
     résultat : vrai ssi les éléments de t sont différents
     stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean elemDiff(char[] t){
        boolean r=true;
        for(int i=0;i<t.length;i++){
            if(plusGrandIndice(t,t[i])!=i){
                r=false;
            }
        }
        return r;
    }
    /** pré-requis : t1.length = t2.length
     résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2){
        assert t1.length == t2.length;
        boolean r=true;
        for(int i=0;i< t1.length;i++){
            if(t1[i]!=t2[i]){
                r=false;
            }
        }


        return r;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /** pré-requis : aucun
     résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs){
        int[] codeA =new int[lgCode];
        int n=0;
        for(int i=0;i<lgCode;i++) {
            n=(int)(Math.random() * nbCouleurs-1);
            codeA[i]=n;
        }
        return codeA;
    }

    /** pré-requis : aucun
     action : si codMot n'est pas correct, affiche pourquoi
     résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){
        boolean bool=true;
        int j=0;
        char[] charat=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charat[i] = codMot.charAt(i);
        }
        while(bool && j<charat.length){
            bool=estPresent(tabCouleurs, charat[j]);
            j++;
        }
        if(bool==false){
            System.out.println("un caractere dans codMot ne correspond pas a tabCouleur codMot doit avoir seulement comme caractere les suivant :");
            afficherTableau.afficherTableauChar(tabCouleurs);
            System.out.println();
        }
        if (codMot.length()!=lgCode){
            bool=false;
            System.out.println("la longueur de codMot est différente de lgCode, elle doit avoir "+lgCode+" caracteres.");
        }
        return bool;
    }
    /** pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs){
        int[] indicet=new int[codMot.length()];
        char[] charat=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charat[i] = codMot.charAt(i);
        }
        for(int i = 0;i<codMot.length();i++){
            indicet[i] = plusGrandIndice(tabCouleurs,charat[i]);
        }
        return indicet;
    }

    /** pré-requis : aucun
     action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce
     qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
     résultat : le code saisi sous forme de tableau d'entiers
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){
        Scanner scanner=new Scanner(System.in);
        System.out.println("écris ta proposition "+nbCoups+".");
        String mot=scanner.nextLine();
        while(codeCorrect(mot,lgCode,tabCouleurs)!=true){
            mot=scanner.nextLine();
        }
        System.out.println("la proposition "+nbCoups+" est "+mot);
        return motVersEntiers(mot,tabCouleurs);
    }
    /* pré-requis : cod1.length = cod2.length
     * résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1,int[] cod2){
        int r=0;
        for(int i=0;i<cod1.length;i++){
            if(cod1[i]==cod2[i]){
                r++;
            }
        }
        return r;
    }

    /** pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
     résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
     Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
     */
    // O = 4
    // cod = (4,4,4,4)
    public static int[] tabFrequence(int[] cod, int nbCouleurs){
        int[] tab=new int[nbCouleurs];
        for(int i=0;i<cod.length;i++){
            tab[cod[i]]+=1;
        }
        return tab;
    }

    /** pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
     Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
     */
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){
        int r=0;
        int []tab1=MasterMindBase.tabFrequence(cod1,nbCouleurs);
        int []tab2=MasterMindBase.tabFrequence(cod2,nbCouleurs);
        for(int i=0;i<nbCouleurs;i++){
            if(tab1[i]==tab2[i]){
                r+=tab1[i];
            }
            else if(tab1[i]<tab2[i]){
                r+=tab1[i];
            }
            else{
                r+=tab2[i];
            }
        }
        return r;
    }

    /** pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
     se trouvant  (resp. ne se trouvant pas) au même indice
     Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé (le "0" à l'indice 3)
     et 2 mal placés (1 "0" et 1 "1")
     */
    public static int[] nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleurs){
        int[] tab=new int[2];
        tab[0]=nbBienPlaces(cod1,cod2);
        tab[1]=nbCommuns(cod1,cod2,nbCouleurs)-nbBienPlaces(cod1,cod2);
        return tab;
    }

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................

    /** pré-requis : numManche >= 1
     action : effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
     (le paramètre numManche ne sert que pour l'affichage)
     résultat :
     - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet),
     s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
     - sinon le nombre de codes proposés par le joueur humain
     */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax){
        assert numManche >= 1;
        int r;
        int [] x = codeAleat(4,6);
        int [] y = new int[6];
        int [] b = new int[2];
        for(int i=2; (sontEgaux(x,y)!=true) && (numManche<=nbEssaisMax); i++){
            y=propositionCodeHumain(numManche,lgCode,tabCouleurs);
            b= nbBienMalPlaces(x,y,tabCouleurs.length);
            System.out.println( b[0]+" nombre bien placés.");
            System.out.println( b[1]+" nombre mal placés.");
            numManche=i;
        }
        if(numManche<nbEssaisMax){
            r=numManche-1;
            System.out.println("Bravo vous avez reussi en "+r+" essais");
        }
        else{
            r=numManche-1+((lgCode-nbBienPlaces(x,y))+2*(lgCode-(nbBienPlaces(x,y)+(lgCode-nbBienPlaces(x,y)))));
            System.out.println("Malheuresement vous avez depassé le nbessaimax bouuhhhh votre score est donc de "+r);
        }
        return r;
    }
    public static String entiersVersMot(int[] cod, char[] tabCouleurs){
        String mot ="";
        for(int i=0;i<cod.length;i++){
            mot+=tabCouleurs[cod[i]];
        }
        return mot;
    }

    /** pré-requis : rep.length = 2
     action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont
     les nombres de bien et mal placés resp.
     résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode){
        boolean r=false;
        if(rep[0]>=0 && rep[1]>=0 && rep[0]+rep[1]<=lgCode){
            r=true;
        }
        return r;
    }

    /** pré-requis : aucun
     action : demande au joueur humain de saisir les nombres de bien et mal placés,
     avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     résultat : les réponses du joueur humain dans un tableau à 2 entiers
     */
    public static int[] reponseHumain(int lgCode){
        Scanner scanner=new Scanner(System.in);
        int []tab= new int[2];
        for(int i=0; i<1 || !repCorrecte(tab,lgCode);i++){
            System.out.println("(Attention l'addition des nombres bien et mal placé doit etre inferieur ou egal a "+lgCode+" et ils doivent etre positif)");
            System.out.println("saisie le nombre de nombres bien placé");
            tab[0]=scanner.nextInt();
            System.out.println("saisie le nombre de nombres mal placé");
            tab[1]=scanner.nextInt();
        }
        return tab;
    }
    //___________________________________________________________________

    /**CHANGE : action si le code suivant n'existe pas
     *************************************************
     pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
     des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs) {
        int i = cod1.length - 1;
        while (i >= 0 && cod1[i] == nbCouleurs - 1) {
            i--;
        }
        if (i < 0) {
            for (int j = 0; j < cod1.length; j += 1) {
                cod1[j] = 0;
            }
            return false;
        } else {
            cod1[i]++;
            i++;
            for (i = i; i < cod1.length; i++) {
                cod1[i] = 0;
            }
            return true;
        }
    }

        /**CHANGE : ajout du paramètre cod1 et modification des spécifications
         *********************************************************************
         pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
         nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
         résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
         c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
         propositions de cod seraient les nbCoups premières réponses de rep resp.
         */
        public static boolean estCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups,int  nbCouleurs){
            boolean v=true;
            for(int i=0;i<nbCoups;i++){
                if(sontEgaux(nbBienMalPlaces(cod1,cod[i], nbCouleurs),rep[i])!=true){
                    v=false;
                }
            }
            return v;
        }
        /**CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
         ajout du paramètre cod1 et modification des spécifications
         **************************************************************************
         pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
         nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
         action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
         des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
         cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
         sinon met dans cod1 le code ne contenant que des "0" et retourne faux
         */
        public static boolean passeCodeSuivantLexicoCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
            System.out.println("_cod1");
            afficherTableau(cod1);
            System.out.println("_cod");
            afficherMatrice.afficherMatrice(cod);
            System.out.println("_rep");
            afficherMatrice.afficherMatrice(rep);
            System.out.println("_coups");
            System.out.println(nbCoups);
            System.out.println("_couleur");
            System.out.println(nbCouleurs);
            int[] cod2=new int[cod1.length];
            passeCodeSuivantLexico(cod1,nbCouleurs);
            while(estCompat(cod1,cod,rep,nbCoups,nbCouleurs)!=true && sontEgaux(cod1,cod2)!=true){
                passeCodeSuivantLexico(cod1,nbCouleurs);
            }
            if(sontEgaux(cod1,cod2)!=true){
                return true;
            }
            else{
                return false;
            }
        }

        /** pré-requis : numManche >= 2
         action : effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
         (le paramètre numManche ne sert que pour l'affichage)
         résultat :
         - 0 si le programme détecte une erreur dans les réponses du joueur humain
         - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet),
         s'il n'a toujours pas trouvé au bout du nombre maximum d'essais
         - sinon le nombre de codes proposés par l'ordinateur
         */

    public static int mancheOrdinateur(int lgCode,char[] tabCouleurs, int numManche, int nbEssaisMax) {
        assert numManche >= 2;
        int [] cod1=new int[lgCode];
        int [][] cod =new int[10][lgCode];
        int[][]rep = new int [10][2];
        int r;
        int[] a=new int[]{};
        int nbCoups=0;
        int nbCouleur=tabCouleurs.length;
        int[] gagné={lgCode,0};
        while(a!=gagné){
            afficherTableau(cod1);
            a=reponseHumain(lgCode);
            rep[nbCoups]=a;
            cod[nbCoups]=cod1;
            nbCoups++;
            passeCodeSuivantLexicoCompat(cod1,cod,rep,nbCoups,nbCouleur);
        }
        return nbCoups;
    }

    //___________________________________________________________________

} // fin MasterMindBase