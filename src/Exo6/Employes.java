/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//"Nous avons un nouvel employé : "
//"Montant de la prime souhaitée par "
//"  A voyagé "
//" jours et apporté "
//"  A corrigé "
//"  A mené à bien "
class Employe {
    private String nom;
    private int revenuMensuel;
    private int tauxOccupation;
    private double montantPrime = 0.0;

    public Employe(String nom, int revenuMensuel, int tauxOccupation){
        this.nom = nom;
        this.revenuMensuel = revenuMensuel;
        if(tauxOccupation<10){
            this.tauxOccupation = 10;
        }else if ( tauxOccupation > 100) {
            this.tauxOccupation = 100;
        }else{
            this.tauxOccupation = tauxOccupation;
        }
        this.montantPrime = 0.0;
    }

    public void demandePrime() {
        double montantPrime = 0.0;
        int chance = 5;
        Scanner keyboard = new Scanner(System.in);
        do {
            try {
                System.out.println("Montant de la prime souhaitée par " + this.nom + "?");
                montantPrime = keyboard.nextDouble();
                if(montantPrime > 0.02*this.revenuAnnuel()){
                    System.out.println("Trop cher!");
                }else{
                    this.montantPrime = montantPrime;
                    break;
                }
            }catch (InputMismatchException e){
                System.out.println("Vous devez introduire un nombre!");
            }
            keyboard.nextLine();
            chance --;
        }while (chance > 0);
    }

    public String toString(){
        return this.nom + " :\n" + "  Taux d'occupation : " + this.tauxOccupation + "%.";
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRevenuMensuel(int revenuMensuel) {
        this.revenuMensuel = revenuMensuel;
    }

    public void setTauxOccupation(int tauxOccupation) {
        this.tauxOccupation = tauxOccupation;
    }

    public void setMontantPrime(double montantPrime) {
        this.montantPrime = montantPrime;
    }

    public String getNom() {
        return nom;
    }

    public int getRevenuMensuel() {
        return revenuMensuel;
    }

    public int getTauxOccupation() {
        return tauxOccupation;
    }

    public double getMontantPrime() {
        return montantPrime;
    }

    public double revenuAnnuel(){
        return 12*this.revenuMensuel + this.montantPrime;
    }

}

class Manager extends Employe{

    public static final int FACTEUR_GAIN_CLIENT = 500;
    public static final int FACTEUR_GAIN_VOYAGE = 100;
    private int nbJoursVoyage;
    private int nbClientsApportes;

    public Manager(String nom, int revenuMensuel, int nbJoursVoyage, int nbClientsApportes) {
        super(nom, revenuMensuel, 100);
        this.nbClientsApportes = nbClientsApportes;
        this.nbJoursVoyage = nbJoursVoyage;
        System.out.println("Nous avons un nouvel employé : " + this.getNom() + ", c'est un manager");
    }

    @Override
    public String toString(){
        if(this.getMontantPrime() != 0){
            return super.toString() + " Salaire annuel : " + String.format("%.2f", this.revenuAnnuel()) + " francs, Prime : " + String.format("%.2f", this.getMontantPrime()) + "\n" + "  A voyagé " + this.nbJoursVoyage + " jours et apporté " + this.nbClientsApportes + " clients";
        }else{
            return super.toString() + " Salaire annuel : " + String.format("%.2f", this.revenuAnnuel()) + " francs.\n" + "A voyagé " + this.nbJoursVoyage + " jours et apporté " + this.nbClientsApportes + " clients";
        }
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + FACTEUR_GAIN_CLIENT*this.nbClientsApportes + FACTEUR_GAIN_VOYAGE*this.nbJoursVoyage;
    }

    public int getNbJoursVoyage() {
        return nbJoursVoyage;
    }

    public void setNbJoursVoyage(int nbJoursVoyage) {
        this.nbJoursVoyage = nbJoursVoyage;
    }

    public int getNbClientsApportes() {
        return nbClientsApportes;
    }

    public void setNbClientsApportes(int nbClientsApportes) {
        this.nbClientsApportes = nbClientsApportes;
    }
}

class Testeur extends Employe {
    public static final int FACTEUR_GAIN_ERREURS = 10;
    int nbErreurCorrigees;

    public Testeur(String nom, int revenuMensuel, int nbErreurCorrigees, int tauxOccupation) {
        super(nom, revenuMensuel, tauxOccupation);
        this.nbErreurCorrigees = nbErreurCorrigees;
        System.out.println("Nous avons un nouvel employé : " + this.getNom() + ", c'est un testeur");
    }

    @Override
    public String toString(){
        if (this.getMontantPrime() != 0){
            return super.toString() + " Salaire annuel : " + String.format("%.2f", this.revenuAnnuel()) + " francs, Prime : " + String.format("%.2f", this.getMontantPrime()) + "\n" + "  A corrigé " + this.nbErreurCorrigees + " erreurs";
        }else{
            return super.toString() + " Salaire annuel : " + String.format("%.2f", this.revenuAnnuel()) + " francs.\n" + "  A corrigé " + this.nbErreurCorrigees + " erreurs";
        }
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + FACTEUR_GAIN_ERREURS*this.nbErreurCorrigees ;
    }

}

class Programmeur extends Employe{
    public static final int FACTEUR_GAIN_PROJETS = 200;
    int nbProjetsAcheves;

    public Programmeur(String nom, int revenuMensuel, int nbProjetsAcheves, int tauxOccupation) {
        super(nom, revenuMensuel, tauxOccupation);
        this.nbProjetsAcheves = nbProjetsAcheves;
        System.out.println("Nous avons un nouvel employé : " + this.getNom() + ", c'est un programmeur");
    }

    @Override
    public String toString(){
        if(this.getMontantPrime() != 0){
            return  super.toString() + " Salaire annuel : " + String.format("%.2f", this.revenuAnnuel()) + " francs, Prime : " + String.format("%.2f", this.getMontantPrime()) + "\n" + "  A mené à bien " + this.nbProjetsAcheves + " projets";
        }else{
            return  super.toString() + " Salaire annuel : " + String.format("%.2f", this.revenuAnnuel()) + " francs.\n" + "  A mené à bien " + this.nbProjetsAcheves + " projets";
        }
    }

    public double revenuAnnuel(){
        return super.revenuAnnuel() + FACTEUR_GAIN_PROJETS*this.nbProjetsAcheves;
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employÃ©s : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage aprÃ¨s demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

