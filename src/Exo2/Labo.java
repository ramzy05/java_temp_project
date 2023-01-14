package Exo2;
class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private int poids;
    private String couleur;
    private int age = 0;
    private int esperanceVie;
    private boolean clonee = false;

    public Souris(int pds, String col, int ag){
        poids = pds;
        couleur = col;
        age = ag;
        esperanceVie = ESPERANCE_VIE_DEFAUT;
        System.out.println("Une nouvelle souris");
    }

    public Souris(int pds, String col){
        poids = pds;
        couleur = col;
        esperanceVie = ESPERANCE_VIE_DEFAUT;
        System.out.println("Une nouvelle souris !");
    }

    public Souris(Souris obj){
        System.out.println("Clonage d'une souris !");
        poids = obj.poids;
        couleur = obj.couleur;
        age = obj.age;
        esperanceVie = obj.esperanceVie/4;
        clonee = true;
        System.out.println("Une nouvelle souris !");

    }

    public String toString(){
        String tmp = (clonee) ? ", clonee,":"";
        return "Une souris "+couleur+ tmp +" de " + age +" mois et pesant "+ poids +" grammes";
    }

    public void veillir(){
        age += 1;
        if(clonee){
            if(age > esperanceVie/2){
                couleur = "verte";
            }
        }
    }

    public void evolue(){
        while (age < esperanceVie) {
            veillir();
        }
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
