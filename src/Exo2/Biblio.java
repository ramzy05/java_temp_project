package Exo2;
import java.util.ArrayList;

class Auteur {

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
	// Completer la classe Auteur ici
    private String nom;
    private boolean prix;
    
    public Auteur(String name, boolean est_prime){
        nom = name;
        prix = est_prime;
    }

    public String getNom() {
        return nom;
    }

    public Boolean getPrix() {
        return prix;
    }

}

class Oeuvre
{
 	// Completer la classe Oeuvre ici
     private String titre;
     private Auteur auteur;
     private String langue;

     public Oeuvre(String title, Auteur autor, String lang){
        titre = title;
        auteur = autor;
        langue = lang;
     }

     public Oeuvre(String title, Auteur autor){
        titre = title;
        auteur = autor;
        langue = "français";
     }

     public String getTitre() {
         return titre;
     }

     public Auteur getAuteur() {
         return auteur;
     }

     public String getLangue() {
         return langue;
     }

     public void afficher() {
        System.out.println(titre+", "+auteur.getNom()+", en "+langue);
    }
}

// completer les autres classes ici
class Exemplaire{
    private Oeuvre oeuvre;

    public Exemplaire(Oeuvre obj){
        oeuvre = obj;
        System.out.println("Nouvel exemplaire -> " + obj.getTitre()+", "+obj.getAuteur().getNom()+", en "+obj.getLangue());
    }

    public Exemplaire(Exemplaire autre){
        oeuvre = autre.oeuvre;
        System.out.println("Copie d'exemplaire -> " + autre.oeuvre.getTitre()+", "+autre.oeuvre.getAuteur().getNom()+", en "+autre.oeuvre.getLangue());
    }
  
    public void afficher() {
        System.out.println("Un exemplaire de " + oeuvre.getTitre()+", "+oeuvre.getAuteur().getNom()+", en "+oeuvre.getLangue());
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }
}

class Bibliotheque{
    private String nom;
    private ArrayList<Exemplaire> exemplaires = new ArrayList<>();
    public Bibliotheque(String name){
        nom = name;
        System.out.println("La bibliothèque "+nom+" est ouverte !");
    }

    public String getNom() {
        return nom;
    }

    public int getNbExemplaires() {
        return exemplaires.size();
    }

    public void stocker (Oeuvre obj,int n){
        for (int i = 1; i <= n; i++) {
            exemplaires.add(new Exemplaire(obj));
        }
    }
    
    public void stocker (Oeuvre obj){
        
        exemplaires.add(new Exemplaire(obj));
    }
    
    public  ArrayList<Exemplaire> listerExemplaires(String lang){
        ArrayList<Exemplaire> exemplairesFiltres = new ArrayList<>();

        for (Exemplaire exemplaire : exemplaires) {
            if(exemplaire.getOeuvre().getLangue() == lang){
                exemplairesFiltres.add(exemplaire);
            }
        }
        return exemplairesFiltres;
    }
    
    public  ArrayList<Exemplaire> listerExemplaires(){
        return exemplaires;
    }

    public int compterExemplaires(Oeuvre obj){
        int compteur = 0;
        for (Exemplaire exemplaire : exemplaires) {
            if(exemplaire.getOeuvre().getTitre() == obj.getTitre()){
                compteur++;
            }
        }
        return compteur;
    }

    public void afficherAuteur(boolean ayant_prix){
        exemplaires.forEach(exemp ->{
                
            if(exemp.getOeuvre().getAuteur().getPrix() == ayant_prix){
                System.out.println(exemp.getOeuvre().getAuteur().getNom());
            }
        });
    }

    public void afficherAuteur(){
        exemplaires.forEach(exemp ->{
            if(exemp.getOeuvre().getAuteur().getPrix() == true){
                System.out.println(exemp.getOeuvre().getAuteur().getNom());
            }
        });
        
    }
   
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

public class Biblio {

    public static void afficherExemplaires(ArrayList<Exemplaire> exemplaires) {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.print("\t");
            exemplaire.afficher();;
        }
    }

    public static void main(String[] args) {
        // create and store all the exemplaries
        Auteur a1 = new Auteur("Victor Hugo", false);
        Auteur a2 = new Auteur("Alexandre Dumas", false);
        Auteur a3 = new Auteur("Raymond Queneau", true);

        Oeuvre o1 = new Oeuvre("Les Miserables", a1, "francais");
        Oeuvre o2 = new Oeuvre("L\'Homme qui rit", a1, "francais");
        Oeuvre o3 = new Oeuvre("Le Comte de Monte-Cristo", a2, "francais");
        Oeuvre o4 = new Oeuvre("Zazie dans le metro", a3, "francais");
        Oeuvre o5 = new Oeuvre("The count of Monte-Cristo", a2, "anglais");

        Bibliotheque biblio = new Bibliotheque("municipale");
        biblio.stocker(o1, 2);
        biblio.stocker(o2);
        biblio.stocker(o3, 3);
        biblio.stocker(o4);
        biblio.stocker(o5);

        // ...
        System.out.println("La bibliotheque " + biblio.getNom() + " offre ");
        afficherExemplaires(biblio.listerExemplaires());
        String langue = "anglais";
        System.out.println("Les exemplaires en " + langue + " sont  ");
        afficherExemplaires(biblio.listerExemplaires(langue));
        System.out.println("Les auteurs a succes sont  ");
        biblio.afficherAuteur();
        System.out.print("Il y a " + biblio.compterExemplaires(o3) + " exemplaires");
        System.out.println(" de  " + o3.getTitre());
    }
}

