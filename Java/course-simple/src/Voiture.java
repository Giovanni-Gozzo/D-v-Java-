public class Voiture {
    private String nom;
    private int vitesse;
    private int position;

    public Voiture(String unNom, int uneVitesse){
        this.nom=unNom;
        this.vitesse=uneVitesse;
        this.position=0;
    }
    /* pré-requis : unNom doit etre un String et unevitesse doit etre un entier >= 0
     * action : crée une voiture de nom unNom et de vitesse uneVitesse
     *          placée à l’origine
     */
    public String toString(){
        String carac="la voiture "+this.nom+" est a une vitesse de "+this.vitesse+" et est positionné en "+this.position;
        return carac;
    }
    /* résultat :  retourne une chaîne de caractères contenant les caractéristiques
     *             de this (sous la forme de votre choix)
     */
    public String toString2(){
        String carac="";
        for(int i=0;i<this.position;i++){
            carac+=" ";
        }
        String nom=this.nom;
        carac=carac+nom.charAt(0);
        carac+="\n";
        return carac;
    }
    /* résultat :  retourne une chaîne de caractères formée d’une suite d’espaces
     *             suivie de la première lettre du nom de this, suivie d’un retour
     * à la ligne, le nombre d’espaces étant égal à la position de this. */
    public String leNom(){
        return this.nom;
    }
    /* résultat :  retourne le nom de this
     */
    public boolean dépasse(int limite){
        if (this.position>=limite){
            return true;
        }
        else{
            return false;
        }
    }
    /* résultat :  retourne vrai si et seulement si la position de this est
     *             supérieure ou égale à limite
     */
    public void avance(){
        this.position+=this.vitesse;
    }
    /* pré-requis : (à compléter)
     * action :  fait avancer this d’une distance égale à sa vitesse */
    public void auDépart(){
        this.position=0;
    }
    /* action :  place this au départ de la course (à l’origine) */

}
