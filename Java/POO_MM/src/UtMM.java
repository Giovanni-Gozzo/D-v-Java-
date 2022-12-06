public class UtMM {
    public static char[] copieTabchar(char[] tab) {
        char[] tabchar=new char[tab.length];
        for (int i = 0; i < tab.length; i++) {
            tabchar[i]=tab[i];
        }
        return tabchar;
    }
}
