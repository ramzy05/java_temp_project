public class Exo4 {
  public static void main(String[] args) throws Exception {
      System.out.println("Hello, World!");

  }
}
public class Transport extends OptionVoyage {

	private boolean TypeDuTrajet=false;// par defaut un transport a un trajet court.

	public final double TARIF_LONG=1500.0;

	public final double TARIF_BASE=200.0;

	public double prix;


	public Transport(String op, double prix, boolean b) {

		super(op, prix);

		if(!b) {

			TypeDuTrajet=b;

			

		}

		else{

			TypeDuTrajet= !b;

		};

		

		

		

	}

	public Transport(String op, double prix) {

		super(op, prix);

	}

	public boolean  Trajet() {

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



public class Sejour extends OptionVoyage {


	private int nombreDeNuits;

	private double prixParNuit;

	public Sejour(String op, double p) {

		super(op, p);

		

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


public class OptionVoyage {

	private String option;

	private double prix;

	

	public OptionVoyage(String op, double p) {

		this.option=op;

		this.prix=p;

		

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






import java.util.ArrayList;

import java.util.List;


public class KitVoyage {

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

		double summe =0.0;

		for (int i=0; i< list.size(); i++) {

			 erg="Voyage de < "+depart +"> a "+"<"+destination+">, avec pour Options :"+"/n"+

		"<nom Option"+i+"> "+"->"+"< prix Option"+i+"> CHF"+"/n"+ "Prix Total :";

			summe +=list.get(i).prix();

		}

		return erg + "<Prix du Kit> "+summe;

		

		

		

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