package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultiPlayerGameTest {
	
	private PartieMultiJoueurs partie;

	@BeforeEach
	public void setUp(){
		partie = new PartieMultiJoueurs();
	}
	
	@Test
    void testGameFlow() {
        assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 1", partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"}));
        assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 2", partie.enregistreLancer(5));
        assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1", partie.enregistreLancer(3));
        assertEquals("Prochain tir : joueur Pierre, tour n° 2, boule n° 1", partie.enregistreLancer(10));
        assertEquals("Prochain tir : joueur Pierre, tour n° 2, boule n° 2", partie.enregistreLancer(7));
        assertEquals("Prochain tir : joueur Paul, tour n° 2, boule n° 1", partie.enregistreLancer(3));

        assertEquals(18, partie.scorePour("Pierre"));
        assertEquals(10, partie.scorePour("Paul"));

    }

}