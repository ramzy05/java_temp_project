/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/

import java.util.ArrayList;

class Piece{
    private String nom;

    public Piece(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }

    public String toString(){
        return this.getNom();
    }
}

class Composant{
    private Piece piece;
    private int quantite;

    public Composant(Piece piece, int quantite){
        this.piece = piece;
        this.quantite = quantite;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public int getQuantite(){
        return this.quantite;
    }
}

class Simple extends Piece{
    private String orientation = "aucune";

    public Simple(String nom){
        super(nom);
    }

    public Simple(String nom, String orientation){
        super(nom);
        this.orientation = orientation;
    }

    public String getOrientation(){
        return this.orientation;
    }

    public String toString(){
        String finalString = super.getNom();
        if(!this.orientation.equals("aucune")){
            finalString = finalString + " " + this.getOrientation();
        }
        return finalString;
    }
}

class Composee extends Piece{
    private ArrayList<Piece> pieces;
    private final int maxPieces;

    public Composee(String nom, int maxPieces){
        super(nom);
        this.maxPieces = maxPieces;
        this.pieces = new ArrayList<Piece>();
    }

    public int taille(){
        return this.pieces.size();
    }

    public int tailleMax(){
        return this.maxPieces;
    }

    public void construire(Piece piece){
        if(this.taille() < this.tailleMax()){
            this.pieces.add(piece);
        } else {
            System.out.println("Construction impossible");
        }
    }

    public String toString(){
        String finalString = this.getNom();
        if(this.taille() > 0){
            finalString += " (";
            for (Piece piece : this.pieces){
                finalString += piece.toString() + ",";
            }
            finalString = finalString.substring(0, finalString.length()-1);
            finalString += ")";
        }
        return finalString;
    }
}

class Construction{
    private ArrayList<Composant> composants;
    private final int maxComposants;

    public Construction(int maxComposants){
        this.maxComposants = maxComposants;
        this.composants = new ArrayList<Composant>();
    }

    public int taille(){
        return this.composants.size();
    }

    public int tailleMax(){
        return this.maxComposants;
    }

    public void ajouterComposant(Piece piece, int quantite){
        if(this.taille() < this.tailleMax()){
            this.composants.add(new Composant(piece, quantite));
        } else {
            System.out.println("Ajout de composant impossible");
        }
    }

    public String toString(){
        String finalString = "";
        for(Composant composant : this.composants){
            finalString += composant.getPiece().toString() + " (quantite "+ composant.getQuantite() +")" + "\n";
        }

        return finalString;
    }
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

class Lego {

    public static void main(String[] args) {
        // declare un jeu de construction de 10 pieces
        Construction maison = new Construction(10);

        // ce jeu a pour premier composant: 59 briques standard
        // une brique standard a par defaut "aucune" comme orientation
        maison.ajouterComposant(new Simple("brique standard"), 59);

        // declare une piece dont le nom est "porte", composee de 2 autres pieces
        Composee porte = new Composee("porte", 2);

        // cette piece composee est constituee de deux pieces simples:
        // un cadran de porte orient'e a gauche
        // un battant orient'e a gauche
        porte.construire(new Simple("cadran porte", "gauche"));
        porte.construire(new Simple("battant", "gauche"));

        // le jeu a pour second composant: 1 porte
        maison.ajouterComposant(porte, 1);

        // dÃ©clare une piece composee de 3 autres pieces dont le nom est "fenetre"
        Composee fenetre = new Composee("fenetre", 3);

        // cette piece composee est constitu'ee des trois pieces simples:
        // un cadran de fenetre (aucune orientation)
        // un volet orient'e a gauche
        // un volet orient'e a droite
        fenetre.construire(new Simple("cadran fenetre"));
        fenetre.construire(new Simple("volet", "gauche"));
        fenetre.construire(new Simple("volet", "droit"));

        // le jeu a pour troisieme composant: 2 fenetres
        maison.ajouterComposant(fenetre, 2);

        // affiche tous les composants composants (nom de la piece et quantit'e)
        // pour les pieces compos'ees : affiche aussi chaque piece les constituant
        System.out.println("Affichage du jeu de construction : ");
        System.out.println(maison);
    }
}