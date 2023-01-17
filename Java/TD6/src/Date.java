public class Date {
    private int jour;
    private int mois;
    private int année;
    private static String[] moisLettres={"janvier","février","mars","avril","mai","juin","juillet","aout","septembre","octobre","novembre","décembre"};
    /*  Action : constructeur a partir de 3 entiers.
     *  Pre-requis : jour>=1 && jour<=31 mois>=1 && mois<=12 année est un entier quelconque.
     */

    public Date(int jour, int mois, int année){
        this.jour=jour;
        this.mois=mois;
        this.année=année;
    }
    public Date(Date X){
        this.jour=X.jour;
        this.mois=X.mois;
        this.année=X.année;
    }
    public String toString(){
        return(this.jour+" "+moisLettres[this.mois-1]+" "+this.année+".");
    }
    public int nbJoursMois(){
        if(this.mois==1 || this.mois==3 || this.mois==5 || this.mois==7 || this.mois==8 || this.mois==10 || this.mois==12){
            return 31;
        }
        else if (this.mois==4 || this.mois==6 || this.mois==9 || this.mois==11) {
            return 30;
        }
        else if (Ut.estBissextile(this.année)){
            return 29;
        }
        else{
            return 28;
        }
    }
    public void incrémenter(){
        this.jour+=1;
        if (this.nbJoursMois()<this.jour){
            this.jour=1;
            this.mois+=1;
            if(this.mois>12){
                this.mois=1;
                this.année+=1;
            }
        }
    }
    public boolean estAprès(Date D){
        if((this.année<D.année) || (this.année==D.année && this.mois<D.mois)||(this.année==D.année && this.mois==D.mois && this.jour<=D.jour)){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean égale(Date D){
        if(this.année==D.année && this.mois==D.mois && this.jour==D.jour){
            return true;
        }
        else{
            return false;
        }
    }
    public int jusquà(Date d) {
        if (this.estAprès(d)) {
            return -d.jusquà(this);
        }
        else {
            int nb = 0;
            Date copieThis = new Date(this);
            while (!copieThis.égale(d)){
                copieThis.incrémenter();
                nb ++;
            }
            return nb;
        }
    }


}
