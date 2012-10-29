import java.util.HashMap;
import java.awt.Point;

public class HermanniRobotti extends Robotti {

	private Kartta kartta; //hermanni pitaa kirjaa sokkelosta
	private int x; //kasvaa oikealle itŠŠn
	private int y; //kasvaa ylšs pohjoiseen


	public HermanniRobotti() {
		y = 0;
		x = 0;
		kartta = new Kartta();
	}

	public void tarkistaYmparisto(){

		if (!kartta.onSolmu(this.x, this.y)) {

			kartta.lisaaSolmu(x, y, new Solmu(x, y, kartta));
			Solmu solmu = kartta.annaSolmu(x, y);

			Este e;

			for ( int i = 0; i < 4; i++) {
				if (voiEdeta())
					e = Este.VAPAA;
				else e = Este.SEINA;

				Ilmansuunta s = Ilmansuunta.numerostaSuunta(annaSuunta());
				solmu.asetaEste(s, e);
				kaannyOikealle();
			} // for

			int k = kartta.onVapaa(this.x, this.y);
			//System.out.println(k);
			if (k > 1) {
				kartta.lisaaVapaa(this.x, this.y);
			}
		} // ei ole kayty viela
	} // end of tarkistaYmparisto


	// rutiini, joka etsii sopivan suunnan
	public void kaannyOikeaanSuuntaan(){
		/* HUOM while silmukka tarvitaan, koska kaannyOikeaanSuuntaan on
		 * tarkoitus kaantaa robottia, eika vain muuttaa sen luuloa omasta
		 * suunnastaan!
		 */

		// kaantaa Hermannia, kunnes oma suunta vastaa parasta suuntaa
		int s = Ilmansuunta.suunnastaNumero(etsiSopivinSuunta());
		while (s != this.annaSuunta()){
			kaannyVasemmalle();
		}
	}

	public Ilmansuunta etsiSopivinSuunta() {

		/* loppusuunnan alkuarvolla ei ole valia, koska jossain suunnassa
		 * on vakisin pienempi askelnumero, koska Hermanni on tullut jostain
		 * suunnasta.
		 */

		// Umpikuja
		if (kartta.onVapaa(this.x, this.y)==0) {
			
			//System.out.println("UMPIKUJA!");

			Reitinhaku reitinhaku = new Reitinhaku();
			Ilmansuunta s = reitinhaku.annaSuunta(kartta,
					kartta.annaSolmu(x, y), kartta.annaViimeisinVapaa());

			// aseta suunta josta tultiin umpikujaksi
			kartta.annaSolmu(this.x, this.y).annaNaapuriSolmu(s)
				.asetaEste(s.vastakkainen(), Este.UMPIKUJA);

			return s;
		} // oltiin umpikujassa
		// Enta, jos ei olla umpikujassa
		else {
			
			// tarkista, missa kaymattomia, valitse ensimmainen
			Ilmansuunta i = Ilmansuunta.POHJOINEN;
			Solmu s = kartta.annaSolmu(this.x, this.y);
			Solmu n;
			for (int c=0; c<4; c++) {
				n = s.annaNaapuriSolmu(i);
				if ((n==null) && (s.annaEste(i)==Este.VAPAA))
					return i;
				else
					i = i.vasen();
			}
			return null;

		} // ei oltu umpikujassa
	} // end of etsiSopivinSuunta

// paivittaa robotin sijainnin kartalla vuoron jalkeen
public void asetaKoordinaatit(){

	//System.out.println("x: " + this.x + " y: " + this.y);

	switch(annaSuunta()) {

	case 0: y++;
	break;
	case 1: x++;
	break;
	case 2: y--;
	break;
	case 3: x--;
	break;
	default:
	}
}


public void teeSiirto(){

	// ollaankoMaalissa();
	//System.out.println("Hermanni (" + this.x + ", " + this.y + ")");
	kartta.printVapaasuuntaiset();
	
	tarkistaYmparisto();
	kartta.paivitaKartta(this.x, this.y);

	/*
		System.out.println("P " + kartta.annaSolmu(this.x, this.y)
				.annaEste(Ilmansuunta.POHJOINEN));
		System.out.println("I " + kartta.annaSolmu(this.x, this.y)
				.annaEste(Ilmansuunta.ITA));
		System.out.println("E " + kartta.annaSolmu(this.x, this.y)
				.annaEste(Ilmansuunta.ETELA));
		System.out.println("L " + kartta.annaSolmu(this.x, this.y)
				.annaEste(Ilmansuunta.LANSI));
	 */

	kaannyOikeaanSuuntaan();
	if (etene()) {
		asetaKoordinaatit();
	}

	// ollaankoMaalissa();
}

/*
public static void main(String[] args) {
} */

}
