public class MainCombat {
    public void combat(){
        EE e1=new EE(100);
        Arene.setEnsOrques(e1);
        Orque.setNbOrques(0);
        Arene a1=new Arene(15);
        Arene a2=new Arene(10);
        for(int i=0;i<Orque.getTabOrques().length;i++){
            System.out.println(Orque.getTabOrques()[i]);
        }

    }
    public static void main(String[] args) {
        combat();
    }
}
