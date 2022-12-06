public class Suite {
    public static void représentation(){
        float A=1;
        float B=1;
        while ((((A + 1) - A) - 1)==0){
            A*=2;
            System.out.println(A);
        }

        while ((((A + B) - A) - B)!=0){
            B+=1;
            System.out.println(B);
        }
    }
    public static void main(String[] args) {
    représentation();
    }
}
