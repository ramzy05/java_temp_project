package Exo6;

import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

class Position {
    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Position(){
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Neurone {
    Position position;
    double signal;
    double facteur;
    ArrayList<Neurone> connexions;

    public Neurone(Position position, double facteur){
        this.position = position;
        this.signal = 0;
        this.facteur = facteur;
        this.connexions = new ArrayList<Neurone>();
    }

    public Position getPosition(){
        return this.position;
    }

    public int getNbConnexions(){
        return this.connexions.size();
    }

    public Neurone getConnexion(int index){
        return this.connexions.get(index);
    }

    public double getAttenuation(){
        return this.facteur;
    }

    public double getSignal(){
        return this.signal;
    }

    public void connexion(Neurone n){
        this.connexions.add(n);
    }

    protected void propage(){
        this.connexions.forEach(
            (neurone) -> {
                neurone.recoitStimulus(this.signal);
            }
        );
    }

    public void recoitStimulus(double stimulus){
        this.signal = stimulus * this.facteur;
        this.propage();
    }

    @Override
    public String toString(){
        String txt = "Le neurone en position " + position.toString() + " avec attenuation " + this.facteur + " en connexion avec\n";
        if(this.getNbConnexions() != 0){
            for(Neurone n : this.connexions)
                txt = txt + "- un neurone en position " + n.position.toString() + "\n";
            return txt ;
        }else{
            return txt + "  sans connexion\n";
        }
    }

}

class NeuroneCumulatif extends Neurone {
    public NeuroneCumulatif(Position position, double facteur) {
        super(position, facteur);
    }

    public void recoitStimulus(double stimulus){
        this.signal = this.signal + stimulus * this.facteur;
        this.propage();
    }
}

class Cerveau {
    private ArrayList<Neurone> cerveau;

    public Cerveau(){
        this.cerveau = new ArrayList<Neurone>();
    }

    public int getNbNeurones(){
        return this.cerveau.size();
    }

    Neurone getNeurone(int index){
        return this.cerveau.get(index);
    }

    public void ajouterNeurone(Position pos, double attenuation){
        Neurone neurone = new Neurone(pos, attenuation);
        this.cerveau.add(neurone);
    }

    public void stimuler(int index, double stimulus){
        getNeurone(index).recoitStimulus(stimulus);
    }

    public double sonder(int index){
        return getNeurone(index).getSignal();
    }

    public void creerConnexions(){
        if(getNbNeurones() > 1) {
            cerveau.get(0).connexion(cerveau.get(1));
        }
        if (getNbNeurones() > 2) {
            cerveau.get(0).connexion(cerveau.get(2));
        }
        for(int i = 1; i <= getNbNeurones()-2; i ++){
            if( i%2 != 0){
                cerveau.get(i).connexion(cerveau.get(i+1));
                cerveau.get(i+1).connexion(cerveau.get(i+2));
            }
        }
    }

    @Override
    public String toString(){

        String txt = "\n*----------*\n" + "Le cerveau contient " + getNbNeurones() + " neurone(s)\n";
        for( int i=0; i < getNbNeurones(); i++){
            txt = txt + cerveau.get(i).toString() + "\n";
        }
        return txt;
    }

    public void ajouterNeuroneCumulatif(Position pos, double attenuation) {
        NeuroneCumulatif neuroneCumulatif = new NeuroneCumulatif(pos, attenuation);
        this.cerveau.add(neuroneCumulatif);
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());
        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}
