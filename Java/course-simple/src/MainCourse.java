public class MainCourse {
    public static void courseAller(String[] args) {
        Voiture v1=new Voiture("v1",10);
        Voiture v2=new Voiture("v2",10);
        int longueur=100;
        Course monaco=new Course(v1,v2,longueur);
        monaco.deroulement();
    }
}