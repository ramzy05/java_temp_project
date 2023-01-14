package Exo4;

import java.lang.reflect.Constructor;

/***************
 * Completez le programme a partir d'ici.
 ***************/
import java.util.ArrayList;

class Vehicule {
    private String nom;
	private double vitesseMax;
	private int poids;
	private int carburant;

    public Vehicule(String name, double vitesse, int p, int nivCar) {
		nom = name;
        vitesseMax =vitesse;
        poids = p;
        carburant = nivCar;
	}

    public Vehicule() {
        nom = "Anonyme";
        vitesseMax =130;
        poids = 1000;
        carburant = 0;
	}

    public String getNom() {
        return nom;
    }

    public double getVitesseMax() {
        return vitesseMax;
    }

    public int getPoids() {
        return poids;
    }

    public void setCarburant(int nivCar) {
        carburant = nivCar;
    }

    public int getCarburant() {
        return carburant;
    }

    public String toString() {
		return  nom +" -> vitesse max = " + vitesseMax +" km/h, poids = "+ poids + " kg";
	}

    public double performance() {
		return  vitesseMax/poids;
	}

    public boolean meilleur(Vehicule autreVehicule) {
		return  performance() > autreVehicule.performance();
	}

    public boolean estDeuxRoues(){
        return false;
    }
}


class Voiture extends Vehicule {
    private String categorie;
    
    public Voiture(String name, double vitesse, int p, int nivCar,String cat){
        super(name, vitesse, p, nivCar);
        categorie = cat;
    }


    public String getCategorie() {
        return categorie;
    }

    public String toString() {
        return  super.getNom() +" -> vitesse max = " + super.getVitesseMax() +" km/h, poids = "+ super.getPoids() + " kg; Voiture de "+categorie;
       
	}
}

class Moto extends Vehicule {
    private boolean sidecar = false;

    public Moto(String name, double vitesse, int p, int nivCar){
        super(name, vitesse, p, nivCar);
    }

    public Moto(String name, double vitesse, int p, int nivCar, boolean has_sidecar){
        super(name, vitesse, p, nivCar);
        sidecar = has_sidecar;
    }

    public String toString() {
        if(sidecar == true){
            return super.getNom() +" -> vitesse max = " + super.getVitesseMax() +" km/h, poids = "+ super.getPoids() + " kg, Moto, avec un sidecar";
        }
        return super.getNom() +" -> vitesse max = " + super.getVitesseMax() +" km/h, poids = "+ super.getPoids() + " kg, Moto";
	}

    public boolean estDeuxRoues(){
        return true;
    }
    
}

abstract class Rallye{
    public abstract boolean check();
}

class GrandPrix extends Rallye{
    private ArrayList<Vehicule> vehicules = new ArrayList<>();

    public boolean check(){
        boolean prevCheck = vehicules.get(1).estDeuxRoues();
        for (int i = 1; i < vehicules.size(); i++) {
            if (vehicules.get(i).estDeuxRoues() != prevCheck){
                
                return false;
            }
        }
        return true;
    }

    public void ajouter(Vehicule option) {
		if (option!= null) {
			vehicules.add(option);
		}

       
	}

	public void run(int tours) {
        int index = -1, max_carburant=0;
        if(check() == false){
            System.out.println("Pas de Grand Prix");
            return;
        }

        vehicules.forEach((vehi) -> {
            vehi.setCarburant(vehi.getCarburant() - tours);
        });

        for (int i = 0; i < vehicules.size(); i++) {
            if(vehicules.get(i).getCarburant() > max_carburant){
                index = 0;
            }
        }

        if( index == -1){
            System.out.println("Elimination de tous les vehicules");
            return;
        }

        System.out.println("Le gagnant du grand prix est : "+vehicules.get(index).toString());

	}
}
/***************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 ***************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}
