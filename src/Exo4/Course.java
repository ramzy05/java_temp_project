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
		this.nom = name;
        this.vitesseMax =vitesse;
        this.poids = p;
        this.carburant = nivCar;
	}

    public Vehicule() {
        this.nom = "Anonyme";
        this.vitesseMax =130;
        this.poids = 1000;
        this.carburant = 0;
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
        this.carburant = nivCar;
    }

    public int getCarburant() {
        return carburant;
    }

    public String toString() {
		return  nom +" -> vitesse max = " + vitesseMax +" km/h, poids = "+ poids + " kg";
	}

    private double performance() {
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
        this.categorie = cat;
    }


    public String getCategorie() {
        return this.categorie;
    }

    @Override
    public String toString() {
        return  super.getNom() +" -> vitesse max = " + super.getVitesseMax() +" km/h, poids = "+ super.getPoids() + " kg, Voiture de "+this.categorie;
       
	}
}

class Moto extends Vehicule {
    private boolean sidecar = false;

    public Moto(String name, double vitesse, int p, int nivCar){
        super(name, vitesse, p, nivCar);
    }

    public Moto(String name, double vitesse, int p, int nivCar, boolean hasSidecar){
        super(name, vitesse, p, nivCar);
        this.sidecar = hasSidecar;
    }

    @Override
    public String toString() {
        if(this.sidecar == true){
            return super.getNom() +" -> vitesse max = " + super.getVitesseMax() +" km/h, poids = "+ super.getPoids() + " kg, Moto, avec sidecar";
        }
        return super.getNom() +" -> vitesse max = " + super.getVitesseMax() +" km/h, poids = "+ super.getPoids() + " kg, Moto";
	}

    public boolean estDeuxRoues(){
        return !this.sidecar;
    }
    
}

abstract class Rallye{
    public abstract boolean check();
}

class GrandPrix extends Rallye{
    private ArrayList<Vehicule> vehicules = new ArrayList<>();

    public boolean check(){
        boolean initCheck = this.vehicules.get(0).estDeuxRoues();
        for (int i = 1; i < this.vehicules.size(); i++) {
            if (this.vehicules.get(i).estDeuxRoues() != initCheck ){
                return false;
            }
        }
        return true;
    }

    public void ajouter(Vehicule option) {
		if (option!= null) {
			this.vehicules.add(option);
		}

       
	}

	public void run(int tours) {
        int index = -1, maxCarburant=0, tmp=0;
        ArrayList<Vehicule> vehiculesArrives = new ArrayList<>();
        if(check() == false){
            System.out.println("Pas de Grand Prix\n");
            return;
        }

        this.vehicules.forEach((vehi) -> {
            vehi.setCarburant(vehi.getCarburant() - tours);
        });

        for (int i = 0; i < vehiculesArrives.size(); i++) {
            tmp = vehiculesArrives.get(i).getCarburant() - tours;
            if(tmp > 0){
                vehiculesArrives.get(i).setCarburant(tmp);
            }
        }

        for (int i = 0; i < vehicules.size(); i++) {
            if(this.vehicules.get(i).getCarburant() > maxCarburant){
                index = i;
                maxCarburant = this.vehicules.get(i).getCarburant();
            }
        }

        if( index == -1){
            System.out.println("Elimination de tous les vehicules\n");
            return;
        }

        System.out.println("Le gagnant du grand prix est : \n"+this.vehicules.get(index).toString()+"\n");

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
