import java.util.Scanner;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Tirelire{
    private double montant;

    public double getMontant(){
        return this.montant;
    }

    public void afficher(){
        if(this.montant <= 0){
            System.out.print("Vous etes sans le sou.\n");
        } else {
            System.out.printf("Vous avez : %.1f euros dans votre tirelire.\n", this.montant);
        }
    }

    public void secouer(){
        System.out.printf("Bing bing\n");
    }

    public void remplir(double montant){
        if(montant > 0){
            this.montant += montant;
        }
    }

    public void vider(){
        this.montant = 0;
    }

    public void puiser(double montant){
        if(montant > 0){
            if(montant > this.montant){
                this.vider();
            } else {
                this.montant -= montant;
            }
        }
    }

    public double calculerSolde(double budget){
        if(budget < 0){
            return this.montant;
        }
        return this.montant - budget;
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
public class TestTirelire {

    public static void main(String[] args) {
        Tirelire piggy = new Tirelire();

        piggy.vider();
        piggy.secouer();
        piggy.afficher();

        piggy.puiser(20.0);
        piggy.secouer();
        piggy.afficher();

        piggy.remplir(550.0);
        piggy.secouer();
        piggy.afficher();

        piggy.puiser(10.0);
        piggy.puiser(5.0);
        piggy.afficher();

        System.out.println();

        // le budget de vos vacances de rÃªves.
        double budget;
        Scanner clavier = new Scanner(System.in);

        System.out.println("Donnez le budget de vos vacances : ");
        budget = clavier.nextDouble();

        // ce qui resterait dans la tirelire aprÃ¨s les
        // vacances
        double solde = piggy.calculerSolde(budget);

        if (solde >= 0) {
            System.out.println("Vous etes assez riche pour partir en vacances !");
            System.out.print(" il vous restera " + solde + " euros");
            System.out.print(" a la rentree \n");
            piggy.puiser(budget);
        }

        else {
            System.out.print("Il vous manque " + (-solde) + " euros");
            System.out.print(" pour partir en vacances !\n");
        }
        clavier.close();
    }
}