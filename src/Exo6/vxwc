/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
//"vote électronique"
//System.out.println("Scrutin annulé, pas de votants");
//"Répartition des électeurs "
//" -> %.1f pour cent des électeurs\n"
import java.util.ArrayList;
import java.util.Random;

class Postulant {
    private String nom;
    private int nbElecteurVotantPour = 0;

    public Postulant(String nom, int nbElecteurVotantPour){
        this.nom = nom;
        this.nbElecteurVotantPour = nbElecteurVotantPour;
    }

    Postulant(Postulant postulant){
        this.nom = postulant.nom;
        this.nbElecteurVotantPour = postulant.nbElecteurVotantPour;
    }

    public void elect(){
        this.nbElecteurVotantPour ++;
    }

    public void init(){
        this.nbElecteurVotantPour = 0;
    }

    public int getVotes(){
        return this.nbElecteurVotantPour;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbElecteurVotantPour() {
        return nbElecteurVotantPour;
    }

    public void setNbElecteurVotantPour(int nbElecteurVotantPour) {
        this.nbElecteurVotantPour = nbElecteurVotantPour;
    }
}

class Scrutin {
    private ArrayList<Postulant> postulants;
    private int nombreMaxVotes;
    private int dateScrutin;
    private ArrayList<Vote> votes;

    public Scrutin (ArrayList<Postulant> postulants, int nombreMaxVotes, int dateScrutin, boolean init){
        this.postulants = postulants;
        this.nombreMaxVotes = nombreMaxVotes;
        this.dateScrutin = dateScrutin;
        this.votes = new ArrayList<Vote>();
        if(init == true){
            this.postulants.forEach(
                (postulant) -> {
                    postulant.init();
                }
            );
        }
    }

    public Scrutin (ArrayList<Postulant> postulants, int nombreMaxVotes, int dateScrutin){
        this.setPostulants(postulants);
        this.setNombreMaxVotes(nombreMaxVotes);
        this.setDateScrutin(dateScrutin);
        this.votes = new ArrayList<Vote>();
        this.getPostulants().forEach(
            (postulant) -> {
                postulant.init();
            }
        );
    }

    public Scrutin (ArrayList<Postulant> postulants, int nombreMaxVotes, int dateScrutin, ArrayList<Vote> votes){
        this.setPostulants(postulants);
        this.setNombreMaxVotes(nombreMaxVotes);
        this.setDateScrutin(dateScrutin);
        this.votes = votes;
        this.getPostulants().forEach(
            (postulant) -> {
                postulant.init();
            }
        );
    }

    public void compterVotes(){
        for (Vote vote : this.getVotes()) {
            if(vote.estValide()){
                this.getPostulants().forEach(
                    postulant -> {
                        if(postulant.getNom() == vote.getNomPostulant()){
                            postulant.elect();
                        }
                    }
                );
            }
        }
    }

    public void simuler(double tauxParticipation, int jourVote) {
        int nombreVotants =  (int)(this.getNombreMaxVotes() * tauxParticipation);

        for (int i=0; i < nombreVotants; i++){
            Random()
            int candNum = Utils.randomInt( this.getPostulants().size() - 1);
            if ( i%3 == 0){
                BulletinElectronique bulletinElectronique = new BulletinElectronique(this.getPostulants().get(candNum).getNom(), jourVote, this.getDateScrutin());
                this.getVotes().add(bulletinElectronique);
            }

            if ( i%3 == 1){
                if (i%2 == 0) {
                    BulletinPapier bulletinPapier = new BulletinPapier(this.getPostulants().get(candNum).getNom(), jourVote, this.getDateScrutin(), true);
                    this.getVotes().add(bulletinPapier);
                }else {
                    BulletinPapier bulletinPapier = new BulletinPapier(this.getPostulants().get(candNum).getNom(), jourVote, this.getDateScrutin(), false);
                    this.getVotes().add(bulletinPapier);
                }
            }

            if ( i%3 == 2){
                if (i%2 == 0){
                    BulletinCourrier bulletinCourrier = new BulletinCourrier(this.getPostulants().get(candNum).getNom(), jourVote, this.getDateScrutin(), true);
                    this.getVotes().add(bulletinCourrier);
                }else{
                    BulletinCourrier bulletinCourrier = new BulletinCourrier(this.getPostulants().get(candNum).getNom(), jourVote, this.getDateScrutin(), false);
                    this.getVotes().add(bulletinCourrier);
                }
            }
        }
        for (int i = 0; i < this.getVotes().size(); i++) {
            System.out.format(this.getVotes().get(i).toString());
        }
    }

    public int calculerVotants(){
        int votant = 0;
        for( Postulant postulant : this.getPostulants()){
            votant = votant + postulant.getNbElecteurVotantPour();
        }
        return votant;
    }

    public String gagnant (){
        int max = 0;
        int index = 0;
        for(Postulant postulant : this.getPostulants()){
            if (postulant.getNbElecteurVotantPour() >= max){
                max = postulant.getNbElecteurVotantPour();
                index = this.getPostulants().indexOf(postulant);
            }
        }
        return this.postulants.get(index).getNom();
    }

    public void resultats() {
        if(this.calculerVotants() == 0){
            System.out.format("Scrutin annulé, pas de votants");
        }else{
            System.out.format("Taux de participation -> %.1f", ((double)this.calculerVotants() / this.getNombreMaxVotes()*100));
            System.out.format(" pour cent\n" + "Nombre effectif de votants %d", this.calculerVotants() );
            System.out.format("\n" + "Le chef choisi est -> " + this.gagnant() + "\n\n");
            System.out.format("Répartition des électeurs\n");
            for( Postulant postulant : this.getPostulants()){
                System.out.format( postulant.getNom() + " -> %.1f", (double)postulant.getNbElecteurVotantPour() / this.calculerVotants()*100);
                System.out.format(" pour cent des électeurs\n\n");
            }
        }
    }

    public ArrayList<Postulant> getPostulants() {
        return postulants;
    }

    public void setPostulants(ArrayList<Postulant> postulants) {
        this.postulants = postulants;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public int getNombreMaxVotes() {
        return nombreMaxVotes;
    }

    public void setNombreMaxVotes(int nombreMaxVotes) {
        this.nombreMaxVotes = nombreMaxVotes;
    }

    public int getDateScrutin() {
        return dateScrutin;
    }

    public void setDateScrutin(int dateScrutin) {
        this.dateScrutin = dateScrutin;
    }
}

class Vote {
    private String nomPostulant;
    private int date;
    private int dateLimite;

    public Vote(String nomPostulant, int date, int dateLimite){
        this.nomPostulant = nomPostulant;
        this.date = date;
        this.dateLimite = dateLimite;
    }

    public String getNomPostulant() {
        return this.nomPostulant;
    }

    public void setNomPostulant(String nomPostulant) {
        this.nomPostulant = nomPostulant;
    }

    public int getDate() {
        return date;
    }

    public void setDataDepot(int dataDepot) {
        this.date = dataDepot;
    }

    public int getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(int dateLimite) {
        this.dateLimite = dateLimite;
    }

    public boolean estValide(){
        return true;
    }
}

class BulletinPapier extends Vote{
    private boolean estSigne;
    public BulletinPapier(String nomPostulant, int date, int dateLimite, boolean estSigne) {
        super(nomPostulant, date, dateLimite);
        this.estSigne = estSigne;
    }


    public void setEstSigne(boolean estSigne) {
        this.estSigne = estSigne;
    }

    public boolean isEstSigne() {
        return estSigne;
    }

    public boolean estInvalide(){
        if(this.isEstSigne()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String toString() {
        if(this.estInvalide()){
            return "vote par bulletin papier pour " + this.getNomPostulant() + " -> invalide\n";
        }else{
            return "vote par bulletin papier pour " + this.getNomPostulant() + " -> valide\n";
        }
    }
}

class BulletinElectronique extends Vote implements CheckBulletin{

    public BulletinElectronique(String nomPostulant, int date, int dateLimite) {
        super(nomPostulant, date, dateLimite);
    }

    @Override
    public boolean checkDate() {
        if(this.getDate() > this.getDateLimite() - 2){
            return true;
        }else{
            return false;
        }
    }

    public boolean estInvalide(){
        if(this.checkDate()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        if(this.estInvalide()){
            return "vote electronique pour " + this.getNomPostulant() + " -> invalide\n";
        }else{
            return "vote electronique pour " + this.getNomPostulant() + " -> valide\n";
        }
    }
}

class BulletinCourrier extends BulletinPapier implements CheckBulletin{

    public BulletinCourrier(String nomPostulant, int date, int dateLimite, boolean estSigne) {
        super(nomPostulant, date, dateLimite, estSigne);
    }

    public boolean estInvalide(){
        if(this.checkDate() && super.estInvalide()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean checkDate() {
        if(this.getDate() > this.getDateLimite() - 2){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        if(this.estInvalide()){
            return "envoie par courrier d'un vote par bulletin papier pour " + this.getNomPostulant() + " -> invalide\n";
        }else{
            return "envoie par courrier d'un vote par bulletin papier pour " + this.getNomPostulant() + " -> valide\n";
        }
    }
}

interface CheckBulletin {
    boolean checkDate();
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

