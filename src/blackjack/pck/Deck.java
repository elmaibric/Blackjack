package blackjack.pck;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	//Instanzvariablen
	private ArrayList<Karten> karten;
	
	//Konstruktor
	public Deck() {
		this.karten = new ArrayList<Karten>();
	}
	
	public void KartenspielErzeugen() {
		//Karten erstellen
		for(Zeichen karteZeichen : Zeichen.values()) {
			for(Zahl karteZahl : Zahl.values()) {
				//Neue Karte hinzufügen zum Kartenspiel Array
				this.karten.add(new Karten(karteZeichen, karteZahl));
				
			}
		}
	}
	
	public void mischen() {
		//Temporäres Deck benutzen um die karten mischen zu können
		ArrayList<Karten>tmpDeck = new ArrayList<Karten>();
		//Random benutzen
		Random random = new Random();
		int randomKarteIndex = 0;
		int originalSize = this.karten.size();
		for(int i=0; i < originalSize; i++) {
			//Random Index generieren: rand.nextInt((max - min) + 1) + min
			randomKarteIndex = random.nextInt((this.karten.size()-1-0) + 1) + 0;
			//in tempöräres Deck hinzufügen
			tmpDeck.add(this.karten.get(randomKarteIndex));
			//Vom ursprünglichen Deck entfernen
			this.karten.remove(randomKarteIndex);
		}
		this.karten = tmpDeck;
	}
	
	public String toString() {
		String kartenListeAusgabe = "";
		for(Karten aKarten : this.karten) {
			kartenListeAusgabe += "\n " + aKarten.toString();
		}
		return kartenListeAusgabe;
	}
	
	public void KarteEntfernen(int i) {
		this.karten.remove(i);
	}
	
	public Karten getKarte(int i) {
		return this.karten.get(i);
	}
	
	public void addKarte(Karten addKarte) {
		this.karten.add(addKarte);
	}
	
	//Vom Deck ziehen
	public void ziehen(Deck kommtVon) {
		this.karten.add(kommtVon.getKarte(0));
		kommtVon.KarteEntfernen(0);
	}
	
	//Gibt die Punkte der Karten im Deck aus
	public int kartenPunkte() {
		int gesamtPunkte = 0;
		int asse = 0;
		
		for(Karten aKarten : this.karten) {
			switch(aKarten.getZahl()) {
			case ZWEI: gesamtPunkte += 2; break;
			case DREI: gesamtPunkte += 3; break;
			case VIER: gesamtPunkte += 4; break;
			case FUENF: gesamtPunkte += 5; break;
			case SECHS: gesamtPunkte += 6; break;
			case SIEBEN: gesamtPunkte += 7; break;
			case ACHT: gesamtPunkte += 8; break;
			case NEUN: gesamtPunkte += 9; break;
			case ZEHN: gesamtPunkte += 10; break;
			case BUBE: gesamtPunkte += 10; break;
			case DAME: gesamtPunkte += 10; break;
			case KOENIG: gesamtPunkte += 10; break;
			case ASS: asse += 1; break;
			}
		}
		
		for(int i = 0; i < asse; i++) {
			if(gesamtPunkte > 10) {
				gesamtPunkte += 1;
			}
			else {
				gesamtPunkte += 11;
			}
		}
		
		return gesamtPunkte;
	}
	
	public int groesseDeck() {
		return this.karten.size();
	}
	
	public void moveAllToDeck(Deck moveTo) {
		int thisDecksize = this.karten.size();
		
		for(int i = 0; i < thisDecksize; i++) {
			moveTo.addKarte(this.getKarte(i));
		}
		
		for(int i = 0; i < thisDecksize; i++) {
			this.KarteEntfernen(0);
		}
	}
	
}
