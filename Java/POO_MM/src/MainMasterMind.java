public class MainMasterMind {
    public static void main(String[] args) {
        char[] tabchar = {'a', 'b', 'c'};
        Couleur.setTabCouleurs(tabchar);
        System.out.println(Couleur.nbCouleurs());
    }
}
