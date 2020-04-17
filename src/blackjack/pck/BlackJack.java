package blackjack.pck;

import java.util.Scanner;

public class BlackJack {
	public static void main( String[] args)
	{
		//Wilkommen
		System.out.println("Wilkommen zu BlackJack!");
		
		//Kartenspiel erzeugen
		Deck kartenspiel = new Deck();
		kartenspiel.KartenspielErzeugen();
		kartenspiel.mischen();
		
		//Spieler Deck erstellen
		Deck spielerDeck = new Deck();
		
		//Computer Deck erstellen
		Deck computerDeck = new Deck();
		
		double spielerGeld = 100.00;
		
		Scanner scan = new Scanner(System.in);
		
		//Spiel Loop
		while(spielerGeld > 0) {
			//Einsatz des Spielers nehmen
			System.out.println("Sie haben " + spielerGeld + "€.");
			System.out.println("Wie hoch ist ihr Einsatz?");
			double EinsatzSpieler = scan.nextDouble(); 
			if (EinsatzSpieler > spielerGeld) {
				System.out.println("Sie können nicht mehr einsetzen als Sie haben.");
				break;
			}
			
			boolean endeRunde = false;
			
			//Spieler bekommt 2 Karten
			spielerDeck.ziehen(kartenspiel);
			spielerDeck.ziehen(kartenspiel);
			
			//Computer bekommt 2 Karten
			computerDeck.ziehen(kartenspiel);
			computerDeck.ziehen(kartenspiel);
			
			while(true) {
				//Spielerkarten ausgeben
				System.out.println("Ihre Karten: ");
				System.out.println(spielerDeck.toString());
				System.out.println();
				System.out.println("Ihre Punktzahl: " + spielerDeck.kartenPunkte());
			
				//Computerkarten ausgeben
				System.out.println("Computer Karten: \n");
				System.out.println(" " + computerDeck.getKarte(0).toString() + "\n [verstecke Karte]\n");
				
				//Was will der Spieler weiter machen?
				System.out.println("Wollen sie (1)Karte ziehen oder (2)Stay?");
				int antwort = scan.nextInt();
				
				//Karte ziehen
				if(antwort == 1) {
					spielerDeck.ziehen(kartenspiel);
					System.out.println("Sie haben die Karte " + spielerDeck.getKarte(spielerDeck.groesseDeck()-1).toString() + " gezogen.");
					//wenn über 21
					if(spielerDeck.kartenPunkte() > 21) {
						System.out.println("Zu viel. Punktezahl liegt bei: " + spielerDeck.kartenPunkte());
						spielerGeld -= EinsatzSpieler;
						endeRunde = true;
						break;
					}
				}
				
				if(antwort == 2) {
					break;
				}
			}
			
			//Computerkarten anzeigen
			System.out.println("Computer Karten: \n " + computerDeck.toString() + "\n");
			//Hat der Computer mehr punkte als der Spieler
			if((computerDeck.kartenPunkte() > spielerDeck.kartenPunkte()) && endeRunde == false) {
				System.out.println("Der Computer hat gewonnen.");
				spielerGeld -= EinsatzSpieler;
				endeRunde = true;
			}
			//Computer zieht bis 17 Punkten
			while(computerDeck.kartenPunkte() < 17 && endeRunde == false) {
				computerDeck.ziehen(kartenspiel);
				System.out.println("Computer zieht: " + computerDeck.getKarte(computerDeck.groesseDeck()-1).toString());
			}
			//Ausgabe Punktzahl Computer
			System.out.println("Computer Punktzahl: " + computerDeck.kartenPunkte());
			//wenn über 21
			if((computerDeck.kartenPunkte() > 21) && endeRunde == false) {
				System.out.println("Dealer hat zu viel! Sie gewinnen.");
				spielerGeld += EinsatzSpieler;
				endeRunde = true;
			}
			
			//Gleichstand
			if((spielerDeck.kartenPunkte() == computerDeck.kartenPunkte()) && endeRunde == false) {
				System.out.println("Gleichstand.");
				endeRunde = true;
			}
			
			if((spielerDeck.kartenPunkte() > computerDeck.kartenPunkte()) && endeRunde == false){
			System.out.println("Sie gewinnen diese Runde!");
			spielerGeld += EinsatzSpieler;
			endeRunde = true;
			}
			else if(endeRunde==false){
				System.out.println("Sie haben die Runde verloren.");
				spielerGeld -= EinsatzSpieler;
				endeRunde = true;
			}
			
			spielerDeck.moveAllToDeck(kartenspiel);
			computerDeck.moveAllToDeck(kartenspiel);
			System.out.println("Ende der Runde.");
		}
		
		System.out.println("Game over! Sie haben kein Geld mehr.");
		
		
	}
}
