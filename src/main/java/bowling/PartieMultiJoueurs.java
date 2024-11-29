package bowling;

import java.util.Arrays;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {
    private PartieMonoJoueur[] lesParties;
    private String[] joueurs;
    private int tourActuel;

    @Override
    public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
        if (nomsDesJoueurs.length == 0) throw new IllegalArgumentException("Il faut au minimum 1 joueur");
        lesParties = new PartieMonoJoueur[nomsDesJoueurs.length];
        joueurs = Arrays.copyOf(nomsDesJoueurs, nomsDesJoueurs.length);
        tourActuel = 0;
        for (int i = 0; i < nomsDesJoueurs.length; i++) {
            lesParties[i] = new PartieMonoJoueur();
        }
        return tourJoueur();
    }

    @Override
    public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
        if (estTerminer()) throw new IllegalStateException("La partie est terminée");
        PartieMonoJoueur partieJoueur = lesParties[tourActuel];
        partieJoueur.enregistreLancer(nombreDeQuillesAbattues);
        if (partieJoueur.numeroProchainLancer() == 1 || partieJoueur.estTerminee())
            tourActuel = (tourActuel + 1) % joueurs.length;
        if (estTerminer()) return "Partie terminée";
        return tourJoueur();
    }

    @Override
    public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
        int joueurIndex = Arrays.asList(joueurs).indexOf(nomDuJoueur);
        if (joueurIndex == -1) throw new IllegalArgumentException("Joueur inexistant");
        return lesParties[joueurIndex].score();
    }

    private String tourJoueur() {
        return "Prochain tir : joueur " + joueurs[tourActuel] + ", tour n° " +
                lesParties[tourActuel].numeroTourCourant() +
                ", boule n° " + lesParties[tourActuel].numeroProchainLancer();
    }

    private boolean estTerminer() {
        return lesParties[joueurs.length - 1].estTerminee();
    }
}
