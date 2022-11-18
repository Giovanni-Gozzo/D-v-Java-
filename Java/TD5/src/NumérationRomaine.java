public class NumérationRomaine {
    public static String unités(int n) {
        String nb = "";
        if (n < 4) {
            for (int i = 0; i < n; i++) {
                nb += "I";
            }
        } else if (n == 4) {
            nb = "IV";
        } else if (n == 9) {
            nb = "IX";
        } else {
            nb = "V";
            for (int i = 0; i < n - 5; i++) {
                nb += "I";
            }
        }
        return nb;
    }

    /**
     * Donnée : un Entier compris entre 1 et 9
     * Résultat :renvoie un string qui sera qui sera un entier de 1 a 9 en
     * chiffres romains qui correspondra aux unités.(1,2...9)
     */
    public static String dizaines(int n) {
        String nb = "";
        int unit = n % 10;
        n = (n - (n % 10)) / 10;
        if (n < 4) {
            for (int i = 0; i < n; i++) {
                nb += "X";
            }
        } else if (n == 4) {
            nb += "XL";
        } else if (n == 9) {
            nb += "XC";
        } else {
            nb += "L";
            for (int i = 0; i < n - 5; i++) {
                nb += "X";
            }
        }
        nb += " ";
        nb += unités(unit);
        return nb;
    }

    /**
     * Donnée : un Entier compris entre 1 et 99
     * Résultat :renvoie un string qui sera qui sera un entier de 1 a 99 en
     * chiffres romains avec un espace entre chaque chiffres.
     */
    public static String centaines(int n) {
        String nb = "";
        int diz = n % 100;
        n = (n - (n % 100)) / 100;
        if (n < 4) {
            for (int i = 0; i < n; i++) {
                nb += "C";
            }
        } else if (n == 4) {
            nb = "CD";
        } else if (n == 9) {
            nb = "CM";
        } else {
            nb = "D";
            for (int i = 0; i < n - 5; i++) {
                nb += "C";
            }
        }
        nb += " ";
        nb += dizaines(diz);
        return nb;
    }

    /**
     * Donnée : un Entier compris entre 1 et 999
     * Résultat :renvoie un string qui sera qui sera un entier de 1 a 999 en
     * chiffres romains avec un espace entre chaque chiffres.
     */
    public static void afficher(int n) {
        String nb = "";
        int cent = n % 1000;
        n = (n - (n % 1000)) / 1000;
        if (n < 4) {
            for (int i = 0; i < n; i++) {
                nb += "M";
            }
        }
        nb += " ";
        nb += centaines(cent);
        System.out.println(nb);
    }
    /**
     * Donnée : un Entier compris entre 1 et 3999
     * Résultat :renvoie rien mais affiche n en chiffre romain avec un espace entre chaque chiffres.
     */
}
