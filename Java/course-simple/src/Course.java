public class Course {
    private Voiture voit1;
    private Voiture voit2;
    private int longueurPiste;
    public Course (Voiture uneVoit1, Voiture uneVoit2, int longueur){
        this.voit1=uneVoit1;
        this.voit2=uneVoit2;
        this.longueurPiste=longueur;
    }
    /* pré-requis : (à compléter)
     * action :     (à compléter)
     */
    public String toString(){
        String course=voit1+" , "+voit2+" ,ils sont tout les deux sur une piste de longueur "+longueurPiste;
        return course;
    }
    /* résultat :  retourne une chaîne de caractères contenant les caractéristiques
     *             de this (sous la forme de votre choix)
     */
    public Voiture deroulement(){
        this.voit1.auDépart();
        this.voit2.auDépart();
        Ut.clearConsole();
        while(!this.voit1.dépasse(this.longueurPiste)&&(!this.voit2.dépasse(this.longueurPiste))){
            int choix=Ut.randomMinMax(1,2);
            if(choix==1){
                this.voit1.avance();
            }
            else{
                this.voit2.avance();
            }
            System.out.println(voit1.toString2());
            System.out.println(voit2.toString2());
            Ut.pause(1500);
        }
        if(this.voit1.dépasse(longueurPiste)){
            System.out.println("la "+this.voit1.leNom()+" gagne");
            return this.voit1;
        }
        else{
            System.out.println("la "+this.voit2.leNom()+" gagne");
            return this.voit2;
        }
    }
    /*
     * action : Simule le déroulement d’une course entre this.voit1 et this.voit2
     * sur une piste de longueur this.longueurPiste.
     * this.voit1 et this.voit2 sont d’abord placées sur la ligne de départ.
     *     Ensuite, jusqu’à ce qu’une voiture franchisse la ligne d’arrivée, l’une
     * des deux voitures est itérativement sélectionnée aléatoirement et avance.
     *     Un affichage des deux voitures (représentées par la première lettre de leur
     * nom) à leur position respective à chaque étape permet de suivre la course.
     * résultat : la voiture gagnante.
     */
}
