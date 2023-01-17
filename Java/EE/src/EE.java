public class EE {
    private int[] enstab;
    private int cardinal;
    public int getCardinal() {
        return this.cardinal;
    }
    public EE(int max){
        this.cardinal=0;
        this.enstab=new int[max];
    }
    public EE(int max, int[] T){
        this.cardinal=0;
        this.enstab=new int[max];
        for(int i=0;i<T.length;i++){
            this.enstab[cardinal]=T[cardinal];
            this.cardinal++;
        }
    }
    public EE(EE A){
        this.enstab=A.enstab.clone();
        this.cardinal=A.cardinal;
    }
    public String toString() {
        String a="";
        a+="{";
        for(int i = 0; i < this.cardinal; i++){
            if(i==0){a+=this.enstab[i];}
            else{a+=","+this.enstab[i];}
        }
        a+="}";
        return a;
    }
    public boolean contient(int i){
        for(int j=0;j<this.cardinal;j++){
            if(this.enstab[j]==i){
                return true;
            }
        }
        return false;
    }
    public boolean estVide(){
        if(this.cardinal==0){
            return true;
        }
        return false;
    }
    public boolean estPlein(){
        if (this.cardinal==this.enstab.length){
            return true;
        }
        return false;
    }
    public int ajouter(int i){
        if(this.estPlein()){
            return -1;
        }
        else if(this.contient(i)){
            return 0;
        }
        else{
            this.enstab[cardinal]=i;
            cardinal++;
            return 1;
        }
    }
    public boolean retirer(int elt){
        if(!this.contient(elt))return false;
        int j=0;
        for (int i=0;i<this.cardinal ;i++ ) {
            if(this.ensTab[i]==elt){
                j=i;
                this.ensTab[i]=this.ensTab[this.cardinal-1];
            }
        }
        this.cardinal--;
        return true;
    }
    public boolean estInclus(EE A){
        if(this.cardinal>A.getCardinal()){
            return false;
        }
        for(int i=0;i<this.cardinal;i++){
            if(!A.contient(this.enstab[i])){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] EE={1,2,3,4,5,6,7};
        EE ensemble1=new EE(10);
        EE ensemble2=new EE(10,EE);
        EE ensemble3= new EE(ensemble2);
        System.out.println(ensemble1);
        System.out.println(ensemble2);
        System.out.println(ensemble3);
        ensemble2.ajouter(8);
        System.out.println(ensemble2);
        ensemble2.retirer(8);
        System.out.println(ensemble2);
    }
}