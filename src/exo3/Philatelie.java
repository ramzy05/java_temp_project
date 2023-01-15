import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code = CODE_DEFAUT;
    private int anneeEmission = ANNEE_COURANTE;
    private String pays = PAYS_DEFAUT;
    private double valeurFaciale = VALEUR_TIMBRE_DEFAUT;

    public Timbre(){

    }
    
    public Timbre(String code){
        this.code = code;
    }

    public Timbre(String code, int anneeEmission){

        this.code = code;
        this.anneeEmission = anneeEmission;
    }

    public Timbre(String code, int anneeEmission, String pays){
        this.code = code;
        this.anneeEmission = anneeEmission;
        this.pays = pays;
    }

    public Timbre(String code, int anneeEmission, String pays, double valeurFaciale){
        this.code = code;
        this.anneeEmission = anneeEmission;
        this.pays = pays;
        this.valeurFaciale = valeurFaciale;
    }

    public double vente(){
        int age = this.age();
        if( age< 5){
            return this.valeurFaciale;
        }
        else{
            return this.valeurFaciale * age * 2.5;
        }
    }

    public int age(){
        return ANNEE_COURANTE - this.anneeEmission ;
    }

    public String toString(){
        return "Timbre de code "+ this.getCode() + " datant de " + this.getAnnee() + " (provenance " + this.getPays() + ") ayant pour valeur faciale " + this.getValeurFaciale() + " francs";
    }

    public String getCode(){
        return this.code;
    }

    public int getAnnee(){
        return this.anneeEmission;
    }

    public String getPays(){
        return this.pays;
    }

    public double getValeurFaciale(){
        return this.valeurFaciale;
    }
}

class Rare extends Timbre{
    private int nbExemplaires = 0;
    
    public Rare(int exemplaires){
        this.nbExemplaires = exemplaires;
    }

    public Rare(String code, int exemplaires){
        super(code);
        this.nbExemplaires = exemplaires;
    }

    public Rare(String code, int anneeEmission, int exemplaires){
        super(code, anneeEmission);
        this.nbExemplaires = exemplaires;
    }

    public Rare(String code, int anneeEmission, String pays, int exemplaires){
        super(code, anneeEmission, pays);
        this.nbExemplaires = exemplaires;
    }

    public Rare(String code, int anneeEmission, String pays, double valeurFaciale, int exemplaires){
        super(code, anneeEmission, pays, valeurFaciale);
        this.nbExemplaires = exemplaires;
    }

    public int getExemplaires(){
        return this.nbExemplaires;
    }

    public String toString(){
        return super.toString() + "\n" + "Nombre d’exemplaires -> " + this.getExemplaires();
    }

    public double vente(){
        int exemplaires = this.getExemplaires();
        double prix_base;
        if(exemplaires < 100){
            prix_base = super.PRIX_BASE_1;
        }
        else if(exemplaires >= 100 && exemplaires < 1000){
            prix_base = super.PRIX_BASE_2;
        }
        else{
            prix_base = super.PRIX_BASE_3;
        }

        return prix_base * ( super.age() / 10.0);
    }
}

class Commemoratif extends Timbre{
    public Commemoratif(){

    }

    public Commemoratif(String code){
        super(code);
    }

    public Commemoratif(String code, int anneeEmission){
        super(code, anneeEmission);
    }

    public Commemoratif(String code, int anneeEmission, String pays){
        super(code, anneeEmission, pays);
    }

    public Commemoratif(String code, int anneeEmission, String pays, double valeurFaciale){
        super(code, anneeEmission, pays, valeurFaciale);
    }

    public String toString(){
        return super.toString() + "\n" + "Timbre celebrant un evenement";
    }

    public double vente(){
        return 2 * super.vente();
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}
