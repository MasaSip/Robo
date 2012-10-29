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
			/*

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
			 */

			System.out.println("USEITA VAPAITA SUUNTIA");
			int laskurit[] = {0,0,0,0};

			// Aloitetaan tarkastelu aina pohjoisesta
			//Ilmansuunta tarkastelusuunta = Ilmansuunta.POHJOINEN;

			Ilmansuunta naapurisuunta = Ilmansuunta.POHJOINEN;

			Solmu s = kartta.annaSolmu(this.x, this.y); // solmu
			Solmu n; // naapuri

			for (int c=0; c<4; c++) {

				n = s.annaNaapuriSolmu(naapurisuunta);
				System.out.println("Naapurisuunta " + naapurisuunta);

				int nx = kartta.annaViereisenX(this.x, this.y, naapurisuunta);
				int ny = kartta.annaViereisenY(this.x, this.y, naapurisuunta);
				
				// jos tutkittavaan naapurisuuntaan on VAPAA
				if ((s.annaEste(naapurisuunta) == Este.VAPAA)) {
					// kasvatetaan valmiiksi yhdella
					//laskurit[Ilmansuunta.suunnastaNumero(naapurisuunta)]++;
					// aloitetaan naapurisuunnasta vasemmalle
					Ilmansuunta tarksuunta = naapurisuunta.vasen();

					// cc on dummy
					for (int cc=0; cc<3; cc++) {
						
						//System.out.println("Tarkkailusuunta: " + tarksuunta);

						/* jos viereisella solmulla on viela viereinen:
						 * - jos tama on vapaasuuntainen
						 * - jos naiden valilla on VAPAA
						 * => kasvata laskuria
						 */
						// v on naapurin viereinen
						int vx = kartta.annaViereisenX(nx, ny, tarksuunta);
						int vy = kartta.annaViereisenY(nx, ny, tarksuunta);
						
						Solmu v = kartta.annaSolmu(vx, vy);
						if (v != null) {
							// jos v on vapaa ja jos v:n ja n:n valilla
							// ei ole seinaa
							if ( (kartta.onVapaa(v) > 0)
							&& (v.annaEste(tarksuunta.vastakkainen())
										== Este.VAPAA ))
							{
								// tallennetaan suuntaa vastaavaan laskuriin
								int k = Ilmansuunta.suunnastaNumero(
										naapurisuunta);
								laskurit[k]++;
							}
						} // n:lla on v
						tarksuunta = tarksuunta.oikea();
					} // for kay lapi viereisen viereiset Solmut
				} // viereinen solmu olemassa ja VAPAA
				naapurisuunta = naapurisuunta.oikea();
			} // for kay lapi viereiset solmut

			System.out.println("LASKURIT:");
			System.out.println("P: "+ laskurit[0]);
			System.out.println("I: "+ laskurit[1]);
			System.out.println("E: "+ laskurit[2]);
			System.out.println("L: "+ laskurit[3]);

			// katsotaan mika laskureista on suurin
			int suurinLaskureidenArvo = 0;
			int suurimmanArvonIndeksi = 0;
			
			for (int k=0; k<4; k++) {
				Ilmansuunta i = Ilmansuunta.numerostaSuunta(k);
				
				/* Jos laskurin arvo on suurempi kuin edeltajat
				 * ja haluttu suunta on kaymaton
				 * ja haluttu suunta on VAPAA
				 */
				if(
				(laskurit[k] >= suurinLaskureidenArvo) &&
				(s.annaNaapuriSolmu(i) == null ) &&
				(s.annaEste(i) == Este.VAPAA) )
				{
					suurinLaskureidenArvo = laskurit[k];
					suurimmanArvonIndeksi = k;
					//System.out.println("Suunta k: " + k + " arvo: " + laskurit[k]);
				}
			}

	// Asetetaan suurinta laskuria vastaava suunta

	/* palauttaa suunnan, johon hermannin tulisi lahtea
	 * riippumatta mihin suuntaan hermanni aluksi katsoi
	 */

	return Ilmansuunta.numerostaSuunta(suurimmanArvonIndeksi);
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
//		kartta.printVapaasuuntaiset();

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
