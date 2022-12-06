public class SommeDe0aN {
    public static void somme(int n){
        float s=0;
        for(float i=1;i<n;i++){
            s+=1/i;
            System.out.println(s);
        }
    }
    public static void main(String[] args) {
        somme(10);
    }
}
