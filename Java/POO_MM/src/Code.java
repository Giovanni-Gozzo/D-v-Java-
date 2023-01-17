import java.util.Scanner;

public class Code {
    private int[]code;
    private int lgCode;
    public static void codeAleat(){
        code=new int[lgCode];
        int nbA=0;
        for(int i=0;i<lgCode;i++) {
            nbA=(int)(Math.random() * Couleur.nbCouleurs() );
            code[i]=nbA;
        }
    }
    public Code(int lgCode,int[] code) {
        this.lgCode = lgCode;
        this.code = code;
    }
    public static boolean codeCorrect(String codMot){
        boolean Correct=true;
        int indice=0;
        char[] charcodMot=new char[codMot.length()];
        for(int i = 0;i<codMot.length();i++){
            charcodMot[i] = codMot.charAt(i);
        }
        while(Correct && indice<charcodMot.length){
            Correct=Couleur.contient(charcodMot[indice]);
            indice+=1;
        }
        if(Correct==false){
            System.out.println("un caractere dans codMot ne correspond pas a tabCouleur codMot doit avoir seulement comme caractere les suivant :");
            for (int i = 0; i < Couleur.nbCouleurs(); i++) {
                System.out.println(Couleur.getTabCouleurs()[i]);
            }
        }
        if (codMot.length()!=lgCode){
            Correct=false;
            System.out.println("la longueur de codMot est différente de lgCode, elle doit avoir "+lgCode+" caracteres.");
        }
        return Correct;
    }
    public static int[] propositionCodeHumain(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Écris ta proposition "+Plateau.getNbCoups()+".");
        String proposition=scanner.nextLine();
        while(Code.codeCorrect(proposition)!=true){
            proposition=scanner.nextLine();
        }
        System.out.println("la proposition "+Plateau.getNbCoups()+" est "+proposition);
        return Couleur.motVersEntiers(proposition);
    }
    public static int[] tabFrequence(int []cod1){
        int[] tab=new int[Couleur.nbCouleurs()];
        for(int i=0;i<cod1.length;i++){
            tab[cod1[i]]+=1;
        }
        return tab;
    }
}
