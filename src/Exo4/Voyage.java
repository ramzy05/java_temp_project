package Exo4;

/***************
 * Completez le programme a partir d'ici.
 ***************/
import java.util.ArrayList;

import java.util.List;
class OptionVoyage {

	private String option;

	private double prix;


	public OptionVoyage(String op, double prix) {
		this.option=op;

		this.prix=prix;
	}

	public String getNom() {
        return option;
	}

	public double prix() {
		return prix;
	}


	public String toString() {
		return "<nom> ->"+option +"prix:"+prix;
	}


}


class Sejour extends OptionVoyage {

	private int nombreDeNuits;

	private double prixParNuit;

	public Sejour(String op, double prix) {

		super(op, prix);

	}

	public Sejour(String nom, double prix, int nombre, double prixParNuit) {
		super(nom, prix);
		this.nombreDeNuits= nombre;
		this.prixParNuit=prixParNuit;
	}

	public double prixSejour() {
		return this.nombreDeNuits * this.prixParNuit + super.prix();
	}


}

class Transport extends OptionVoyage {

	private boolean TypeDuTrajet=false;// par defaut un transport a un trajet court.

	public final double TARIF_LONG=1500.0;

	public final double TARIF_BASE=200.0;

	public double prix;


	public Transport(String op, double prix, boolean est_long) {
		super(op, prix);
        // TypeDuTrajet = est_long;
		if(!est_long) {
			TypeDuTrajet=est_long;
		}
		else{
			TypeDuTrajet= !est_long;
		}

	}

	public Transport(String op, double prix) {
		super(op, prix);
	}

	public boolean Trajet() {
		if(!TypeDuTrajet) {
			return true;
		}
		return false;

	}

	public void setTrajetLong() {
		this.TypeDuTrajet=true;
		prix=this.TARIF_LONG;
	}


}

class KitVoyage {

	private String depart;

	private String destination;

	private List<OptionVoyage> list= new ArrayList<>();

	

	public KitVoyage(String depart,String destination) {

		this.depart=depart;

		this.destination=destination;

	}

	public double prix() {

		double resultat=0.0;

		for( OptionVoyage o: list) {
			resultat += o.prix();
		}

		return resultat;
	}

	public String toString() {
		String erg=null;
		double somme =0.0;

		for (int i=0; i< list.size(); i++) {
			erg="Voyage de < "+depart +"> a "+"<"+destination+">, avec pour Options :"+"/n"+

		"<nom Option"+i+"> "+"->"+"< prix Option"+i+"> CHF"+"/n"+ "Prix Total :";

			somme +=list.get(i).prix();
		}

		return erg + "<Prix du Kit> "+somme;
		
	}

	public void ajouterOption(OptionVoyage o) {

		if (o!= null) {

			list.add(o);

		}

		

	}

	public void annuler() {

		list.clear();

	}

	public int getNbOptions() {
		return list.size();
	}
}
/***************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 ***************/

public class Voyage {
    public static void main(String args[]) {

        // TEST 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        OptionVoyage option1 = new OptionVoyage("SÃ©jour au camping", 40.0);
        System.out.println(option1.toString());

        OptionVoyage option2 = new OptionVoyage("Visite guidÃ©e : London by night" , 50.0);
        System.out.println(option2.toString());
        System.out.println();

        // FIN TEST 1


        // TEST 2
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        Transport transp1 = new Transport("Trajet en car ", 250.0);
        System.out.println(transp1.toString());

        Transport transp2 = new Transport("CroisiÃ¨re", 1500.0);
        System.out.println(transp2.toString());

        Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
        System.out.println(sejour1.toString());
        System.out.println();

        // FIN TEST 2


        // TEST 3
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
        kit1.ajouterOption(new Transport("Trajet en train", 50.0));
        kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
        System.out.println(kit1.toString());
        System.out.println();
        kit1.annuler();

        KitVoyage kit2 = new KitVoyage("Zurich", "New York");
        kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
        kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
        System.out.println(kit2.toString());
        kit2.annuler();

        // FIN TEST 3
    }
}