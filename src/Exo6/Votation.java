import java.util.ArrayList;
import java.util.Random;

/*******************************************
 * Completez le programme Ã  partir d'ici.
 *******************************************/
class Postulant {
    private String nom;
    private int nbElecteurVotantPour = 0;

    public Postulant(String nom, int nbElecteurVotantPour){
        this.nom = nom;
        this.nbElecteurVotantPour = nbElecteurVotantPour;
    }

    Postulant(String nom){
        this.nom = nom;
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
            if(!vote.estInvalide()){
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

abstract class Vote {
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
    
    public abstract boolean estInvalide();

    public String getPostulant() {
        return this.nomPostulant;
    }

    public String toString(){
        String str = estInvalide() ? "invalide" : "valide";
        return " pour "+this.nomPostulant+" -> "+str;
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
        return this.estSigne;
    }

    public boolean estInvalide(){
        return !this.isEstSigne();
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
        return this.getDate() <= this.getDateLimite() - 2;
    }

    public boolean estInvalide(){
        return !checkDate();
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

    @Override
    public boolean estInvalide(){
        return !this.checkDate() || !super.isEstSigne();
    }

    @Override
    public boolean checkDate() {
        return this.getDate() <= this.getDateLimite();
    }

    @Override
    public String toString() {
        if(this.estInvalide()){
            return "envoi par courrier d'un vote par bulletin papier pour " + this.getNomPostulant() + " -> invalide\n";
        }else{
            return "envoi par courrier d'un vote par bulletin papier pour " + this.getNomPostulant() + " -> valide\n";
        }
    }
}

interface CheckBulletin {
    boolean checkDate();
}
/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // gÃ©nÃ¨re un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}
