import java.util.Scanner;

public class MasterMind_3_2 {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................

    // fonctions classiques sur les tableaux

    /** pré-requis : nb >= 0
     résultat : un tableau de nb entiers égaux à val
     */
    public static int[] initTab(int nb, int val){
        int[] tab=new int[nb];
        for(int i=0;i<nb;i++){
            tab[i]=val;
        }
        return tab;
    }

    /** pré-requis : aucun
     résultat : une copie de tab
     */
    public static int[] copieTab(int[] tab){
        return tab.clone();
    }
    //______________________________________________
    /** pré-requis : aucun
     résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
     */
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
        boolean Present=false;
        if(plusGrandIndice(t,c)>-1){
            Present=true;
        }
        return Present;
    }
    /** pré-requis : aucun
     action : affiche un doublon et 2 de ses indices dans t s'il en existe
     résultat : vrai ssi les éléments de t sont différents
     stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean elemDiff(char[] t){
        for(int i=0;i<t.length;i++){
            if(plusGrandIndice(t,t[i])!=i && t[i]!=' '){
                System.out.println(t[i]+" est présent au moins deux fois aux indice: "+i+" et "+plusGrandIndice(t,t[i])+".");
                return false;
            }
        }
        return true;
    }
    /** pré-requis : t1.length = t2.length
     résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2){
        boolean Egaux=true;
        for(int i=0;i< t1.length;i++){
            if(t1[i]!=t2[i]){
                Egaux=false;
            }
        }
        return Egaux;
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
        int nbA=0;
        for(int i=0;i<lgCode;i++) {
            nbA=(int)(Math.random() * nbCouleurs);
            codeA[i]=nbA;
        }
        return codeA;
    }

    /** pré-requis : aucun
     action : si codMot n'est pas correct, affiche pourquoi
     résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){
        boolean Correct=true;
        int indice=0;
        char[] charcodMot=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charcodMot[i] = codMot.charAt(i);
        }
        while(Correct && indice<charcodMot.length){
            Correct=estPresent(tabCouleurs, charcodMot[indice]);
            indice+=1;
        }
        if(Correct==false){
            System.out.println("un caractere dans codMot ne correspond pas a tabCouleur codMot doit avoir seulement comme caractere les suivant :");
            for (int i = 0; i < tabCouleurs.length; i++) {
                System.out.println(tabCouleurs[i]);
            }
        }
        if (codMot.length()!=lgCode){
            Correct=false;
            System.out.println("la longueur de codMot est différente de lgCode, elle doit avoir "+lgCode+" caracteres.");
        }
        return Correct;
    }
    /** pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs){
        int[] indicet=new int[codMot.length()];
        char[] charcodMot=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charcodMot[i] = codMot.charAt(i);
        }
        for(int i = 0;i<codMot.length();i++){
            indicet[i] = plusGrandIndice(tabCouleurs,charcodMot[i]);
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
        System.out.println("Écris ta proposition "+nbCoups+".");
        String proposition=scanner.nextLine();
        while(codeCorrect(proposition,lgCode,tabCouleurs)!=true){
            proposition=scanner.nextLine();
        }
        System.out.println("la proposition "+nbCoups+" est "+proposition);
        return motVersEntiers(proposition,tabCouleurs);
    }
    /* pré-requis : cod1.length = cod2.length
     * résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     * Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1,int[] cod2){
        int nbBienPlaces=0;
        for(int i=0;i<cod1.length;i++){
            if(cod1[i]==cod2[i]){
                nbBienPlaces++;
            }
        }
        return nbBienPlaces;
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
        int nbcommuns=0;
        int []tab1=tabFrequence(cod1,nbCouleurs);
        int []tab2=tabFrequence(cod2,nbCouleurs);
        for(int i=0;i<nbCouleurs;i++){
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

    /** pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
     résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
     se trouvant  (resp. ne se trouvant pas) au même indice
     Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé (le "0" à l'indice 3)
     et 2 mal placés (1 "0" et 1 "1")
     */
    public static int[] nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleurs){
        int[] nbBienMalPlaces=new int[2];
        nbBienMalPlaces[0]=nbBienPlaces(cod1,cod2);
        nbBienMalPlaces[1]=nbCommuns(cod1,cod2,nbCouleurs)-nbBienPlaces(cod1,cod2);
        return nbBienMalPlaces;
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
        System.out.println("Manche "+numManche+" humain décodeur");
        int résultat=0;
        int [] codeA = codeAleat(lgCode,tabCouleurs.length);
        int [] codeProp = initTab(lgCode,-1);
        int [][] rep = new int[nbEssaisMax][2];
        int [][] cod = new int[nbEssaisMax][lgCode];
        for(int i=0; !sontEgaux(codeA,codeProp) && (i<nbEssaisMax); i++){
            codeProp=propositionCodeHumain(i+1,lgCode,tabCouleurs);
            cod[i]=copieTab(codeProp);
            rep[i] = copieTab(nbBienMalPlaces(codeA,codeProp,tabCouleurs.length));
            System.out.println("----------------------------------");
            System.out.println( rep[i][0]+" nombre bien placés.");
            System.out.println( rep[i][1]+" nombre mal placés.");
            affichePlateau(cod,rep,i,tabCouleurs);
            résultat=i+1;
        }
        if(résultat<nbEssaisMax ||(résultat==nbEssaisMax && sontEgaux(codeA,cod[cod.length-1]))){
            System.out.println("Bravo vous avez reussi en "+résultat+" essais");
        }
        else{
            résultat=rep[rep.length-1][1]+2*(lgCode-(rep[rep.length-1][0]+rep[rep.length-1][1]));
            System.out.println("Malheuresement vous avez depassé le nbessaimax votre score est donc de "+résultat);
            System.out.println("le code était:"+entiersVersMot(codeA,tabCouleurs));
        }
        System.out.println("----------------------------------");
        return résultat;
    }
    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /** pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
     résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
     */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs){
        String motcod ="";
        for(int i=0;i<cod.length;i++){
            motcod+=tabCouleurs[cod[i]];
        }
        return motcod;
    }

    /** pré-requis : rep.length = 2
     action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont
     les nombres de bien et mal placés resp.
     résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
     */
    public static boolean repCorrecte(int[] rep, int lgCode){
        boolean repCorrecte=false;
        if(rep[0]>=0 && rep[1]>=0 && rep[0]+rep[1]<=lgCode){
            repCorrecte=true;
        }
        return repCorrecte;
    }

    /** pré-requis : aucun
     action : demande au joueur humain de saisir les nombres de bien et mal placés,
     avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     résultat : les réponses du joueur humain dans un tableau à 2 entiers
     */
    public static int[] reponseHumain(int lgCode){
        Scanner scanner=new Scanner(System.in);
        int []nbBienMalPlace= new int[2];
        System.out.println("saisie le nombre de nombres bien placé");
        nbBienMalPlace[0]=scanner.nextInt();
        if(nbBienMalPlace[0]<lgCode){
            System.out.println("saisie le nombre de nombres mal placé");
            nbBienMalPlace[1]=scanner.nextInt();
        }
        while(nbBienMalPlace[0]+nbBienMalPlace[1]>lgCode){
            System.out.println("(Attention l'addition des nombres bien et mal placé doit etre inferieur ou egal a "+lgCode+" et ils doivent etre positif)");
            System.out.println("saisie le nombre de nombres bien placé");
            nbBienMalPlace[0]=scanner.nextInt();
            if(nbBienMalPlace[0]<lgCode){
                System.out.println("saisie le nombre de nombres mal placé");
                nbBienMalPlace[1]=scanner.nextInt();
            }
        }
        return nbBienMalPlace;
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
        int indice = cod1.length - 1;
        while (indice >= 0 && cod1[indice] == nbCouleurs - 1) {
            indice--;
        }
        if (indice < 0) {
            for (int j = 0; j < cod1.length; j += 1) {
                cod1[j] = 0;
            }
            return false;
        }
        else {
            cod1[indice]++;
            indice++;
            for (indice=indice; indice < cod1.length; indice++) {
                cod1[indice] = 0;
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
        for(int i=0;i<nbCoups;i++){
            if(!sontEgaux(nbBienMalPlaces(cod1,cod[i], nbCouleurs),rep[i]) || sontEgaux(cod1,cod[i])){
                return false;
            }
        }
        return true;
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
        Scanner scanner=new Scanner(System.in);
        System.out.println("Manche "+numManche+" ordinateur décodeur");
        System.out.println("----------------------------------");
        System.out.println("choisir dans votre tête un code à "+lgCode+" couleurs parmis celle que vous avez énnumérées précédement.");        int [] cod1=new int[lgCode];
        int [][] cod =new int[nbEssaisMax][lgCode];
        int[][]rep = new int [nbEssaisMax][2];
        int[] reprop=new int[2];
        int nbCoups=0;
        int nbCouleur=tabCouleurs.length;
        int[] gagné={lgCode,0};
        boolean existecodsuiv=true;
        while(!sontEgaux(reprop,gagné) && existecodsuiv && nbCoups<nbEssaisMax){
            System.out.println("----------------------------------");
            System.out.println("proposition ordinateur n°"+(nbCoups+1)+" "+entiersVersMot(cod1,tabCouleurs));
            System.out.println("----------------------------------");
            reprop=reponseHumain(lgCode);
            rep[nbCoups]=copieTab(reprop);
            cod[nbCoups]=copieTab(cod1);
            affichePlateau(cod,rep,nbCoups,tabCouleurs);
            nbCoups++;
            if(!sontEgaux(reprop,gagné)){
                existecodsuiv=passeCodeSuivantLexicoCompat(cod1,cod,rep,nbCoups,nbCouleur);
            }
        } 
        int point=nbCoups;
        System.out.println("----------------------------------");
        if(!existecodsuiv){
            System.out.println("Fin de la manche");
            System.out.println("----------------------------------");
            System.out.println("quel est le code secret ?");
            String codeSecret=scanner.nextLine();
            while(!codeCorrect(codeSecret,lgCode,tabCouleurs)){
                System.out.println("code invalide");
                System.out.println("----------------------------------");
                System.out.println("quel est le code secret ?");
                codeSecret=scanner.nextLine();
            }
            afficheErreurs(codeSecret,cod,rep,nbCoups-1,lgCode,tabCouleurs);
            point=0;
        }
        else if(nbCoups==nbEssaisMax && !sontEgaux(rep[rep.length-1],gagné)){
            System.out.println("Fin de la manche");
            System.out.println("----------------------------------");
            System.out.println("quel est le code secret ?");
            String codeSecret=scanner.nextLine();
            while(!codeCorrect(codeSecret,lgCode,tabCouleurs)){
                System.out.println("code invalide");
                System.out.println("----------------------------------");
                System.out.println("quel est le code secret ?");
                codeSecret=scanner.nextLine();
            }
            if(!estCompat(motVersEntiers(codeSecret,tabCouleurs),cod,rep,nbCoups,tabCouleurs.length)){
                afficheErreurs(codeSecret,cod,rep,nbCoups-1,lgCode,tabCouleurs);
                point=0;
            }
            else{
                point=rep[nbCoups-1][1]+2*(lgCode-(rep[nbCoups-1][0]+rep[nbCoups-1][1]));
                System.out.println("l'ordinateur n'a pas trouvé en moins de "+nbEssaisMax+" coups et il gagne "+point+" points.");
            }
            
        }
        else{
            System.out.println("l'ordinateur a trouvé en "+nbCoups+" coups.");
        }
        return point;
    }
    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................


    /** pré-requis : aucun
     action : demande au joueur humain de saisir un entier strictement positif,
     avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     résultat : l'entier strictement positif saisi
     */
    public static int saisirEntierPositif(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("saisir un entier positif");
        int entierSaisis=scanner.nextInt();
        while(entierSaisis<=0){
            System.out.println("saisir un entier positif");
            entierSaisis=scanner.nextInt();
        }
        return entierSaisis;
    }

    //___________________________________________________________________

    /** pré-requis : aucun
     action : demande au joueur humain de saisir un entier pair strictement positif,
     avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     résultat : l'entier pair strictement positif saisi
     */
    public static int saisirEntierPairPositif(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("saisir un entier pair et positif");
        int entierSaisis=scanner.nextInt();
        while(entierSaisis<=0 || entierSaisis%2!=0){
            System.out.println("saisir un entier pair et positif");
            entierSaisis=scanner.nextInt();
        }
        return entierSaisis;

    }

    //___________________________________________________________________

    /** pré-requis : aucun
     action : demande au joueur humain de saisir le nombre de couleurs (stricement positif),
     puis les noms de couleurs aux initiales différentes,
     avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
     résultat : le tableau des initiales des noms de couleurs saisis
     */
    public static char[] saisirCouleurs(){
        System.out.print("Pour le nombre de couleur ");
        int nbCouleurs=saisirEntierPositif();
        char[] tabCouleurs=new char[nbCouleurs];
        for(int i=0;i<nbCouleurs;i++){
            tabCouleurs[i]=' ';
        }
        for(int i=0;i<nbCouleurs;i++){
            Scanner scanner=new Scanner(System.in);
            System.out.println("saisir une couleur");
            String Couleur=scanner.nextLine();
            tabCouleurs[i]=Couleur.charAt(0);
            while(!elemDiff(tabCouleurs)){
                System.out.println("saisir une couleur (l'initiale de la couleur doit être diférentes des précédentes initiales.");
                Couleur=scanner.nextLine();
                tabCouleurs[i]=Couleur.charAt(0);
            }
        }
        return tabCouleurs;

    }

    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
    0 <= nbCoups < cod.length, nbCoups < rep.length et
    les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
    action : affiche les nbCoups premières lignes de cod (sous forme
    de mots en utilisant le tableau tabCouleurs) et de rep
    */
    public static void affichePlateau(int [][] cod, int [][] rep,int nbCoups, char[] tabCouleurs){
        System.out.println("----------------------------------");
        System.out.println("Voici le plateau:");
        for(int i=0;i<=nbCoups;i++){
            System.out.print(entiersVersMot(cod[i],tabCouleurs) + "  ");
            System.out.print(rep[i][0]+" ");
            System.out.println(rep[i][1]);
        }
        System.out.println("----------------------------------");
    }

    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
    0 <= nbCoups < cod.length, nbCoups < rep.length,
    les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
    et codMot est incorrect ou incompatible avec les nbCoups
    premières lignes de cod et de rep
    action : affiche les erreurs d’incorrection ou d’incompatibilité
    */
    public static void afficheErreurs (String codMot, int [][] cod, int [][] rep, int nbCoups, int lgCode, char[] tabCouleurs) {
        int erreur =0;
        if(!estCompat(motVersEntiers(codMot,tabCouleurs),cod,rep,nbCoups,tabCouleurs.length)){
            for(int i=0;i<nbCoups;i++){
                if(!sontEgaux(nbBienMalPlaces(motVersEntiers(codMot,tabCouleurs),cod[i],tabCouleurs.length),rep[i])){
                    erreur=i+1;
                }
            }
        System.out.println("----------------------------------");
        System.out.println("le code secret ne correspond pas avec les réponses que vous avez données à partir de la ligne "+erreur+" du plateau.");
        affichePlateau(cod,rep,nbCoups,tabCouleurs);
        }
    }


    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................


    /**CHANGE : ajout de : le nombre d'essais maximum doit être strictement positif
     ******************************************************************************
     action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs,
     nbManches, nbEssaisMax),
     effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum doivent être strictement positifs.
     Le nombre de manches doit être un nombre pair strictement positif.
     Les initiales des noms de couleurs doivent être différentes.
     Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main (String[] args){
        int nbpointhumain=0;
        int nbpointordi=0;
        System.out.print("Pour la longueur du code ");
        int lgCode=saisirEntierPositif();
        System.out.println("Pour les couleurs disponibles: ");
        char[] tabCouleurs=saisirCouleurs();
        System.out.print("Pour le nombre d'essai maximum ");
        int nbEssaisMax=saisirEntierPositif();
        System.out.print("Pour le nombre de manches ");
        int nbManches=saisirEntierPairPositif();
        for(int numManche=0;numManche<nbManches;numManche+=2){
            nbpointordi+= mancheHumain(lgCode,tabCouleurs,numManche+1,nbEssaisMax);
            nbpointhumain+= mancheOrdinateur(lgCode,tabCouleurs,numManche+2,nbEssaisMax);
        }
        System.out.println("----------------------------------");
        if (nbpointordi<nbpointhumain){
            System.out.println("victoire de l'humain avec "+nbpointordi+" points pour l'ordi et "+nbpointhumain+" points pour l'humain.");
        }
        else if (nbpointordi==nbpointhumain){
            System.out.println("il n'y a pas de victoire car l'ordinateur et l'humain ont "+nbpointordi+" points");
        }
        else{
            System.out.println("victoire de l'ordinateur avec "+nbpointordi+" points pour l'ordi et "+nbpointhumain+" points pour l'humain.");
        }

    } // fin main

    //___________________________________________________________________

} // fin MasterMindBase


