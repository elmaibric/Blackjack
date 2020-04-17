package blackjack.pck;

public class Karten {
	
	private Zeichen zeichen;
	private Zahl zahl;
	
	public Karten(Zeichen zeichen, Zahl zahl){
		this.zeichen = zeichen;
		this.zahl = zahl;
	}
	
	public String toString() {
		return this.zeichen.toString() + "-" + this.zahl.toString();
	}
	
	public Zahl getZahl() {
		return this.zahl;
	}
}
