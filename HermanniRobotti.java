/***********************************************************************
 * 
 * H E R M A N N I robotti
 * #######################
 * OLO3-ryhman tuotos
 * Huomioikaa toki, etta toimitimme mukavan robottigrafiikan paketissa.
 * Olettehan ystavallisia ja siirratte sen robottikuvien kansioon.
 * *********************************************************************
 */

public class HermanniRobotti extends Robotti {

	private Kartta kartta; //hermanni pitaa kirjaa sokkelosta
	private int x; //kasvaa oikealle itŠŠn
	private int y; //kasvaa ylšs pohjoiseen


	// CTOR
	public HermanniRobotti() {
		y = 0;
		x = 0;
		kartta = new Kartta();
	} // end of CTOR

	// tarkistaa ympariston ja tallentaa tiedot seinista Karttaan Solmuihin
	public void tarkistaYmparisto(){

		// jos ruudussa ei ole viela vierailtu
		if (!kartta.onSolmu(this.x, this.y)) {

			// lisataan solmu
			kartta.lisaaSolmu(x, y, new Solmu(x, y, kartta));
			Solmu solmu = kartta.annaSolmu(x, y);

			
			// kay lapi kaikki suunnat ja tallentaa tiedot Esteista Solmuun
			Este e;
			for ( int c=0; c<4;c++) { // laskuri neljaan
				if (voiEdeta())
					e = Este.VAPAA;
				else e = Este.SEINA;

				//
				Ilmansuunta i = Ilmansuunta.numerostaSuunta(annaSuunta());
				solmu.asetaEste(i, e);
				kaannyOikealle();
			} // for joka kay suunnat lapi

			// jos karttalla on kaymattomia, vapaita ruutuja enemman kuin 1
			// lisaa listaan
			if (kartta.onVapaa(this.x, this.y) > 1)
				kartta.lisaaVapaa(this.x, this.y);
		} // ei ole kayty viela
	} // end of tarkistaYmparisto


	// rutiini, joka kaantaa oikeaan suuntaa
	public void kaannyOikeaanSuuntaan(){
		
		// kaantaa Hermannia, kunnes oma suunta vastaa parasta suuntaa
		int s = Ilmansuunta.suunnastaNumero(etsiSopivinSuunta());
		while (s != this.annaSuunta()){
			kaannyVasemmalle();
		}
	}

	
	/*
	 * Etsii sopivimman suunnan.
	 * 
	 * => Jos ollaan umpikujassa, kayttaa A* algoritmia ja etsii lyhimman tien
	 * viimeisimpaan ruutuun, jossa tiedetaan olleen kaymattomia suuntia.
	 * 
	 * => Jos ei olla umpikujassa, pyritaan valitsemaan suunta, joka eliminoisi
	 * mahdollisimman monta tietoa vapaasta suunnasta.
	 */
	public Ilmansuunta etsiSopivinSuunta() {

		// Umpikuja
		if (kartta.onVapaa(this.x, this.y)==0) {

			// luo uusi reitinhakija ja hae reitti
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
			
			// laskurit mielusimman suunnan laskemiseen
			int laskurit[] = {0, 0, 0, 0};
			// Aloitetaan tarkastelu aina pohjoisesta
			//Ilmansuunta tarkastelusuunta = Ilmansuunta.POHJOINEN;

			Ilmansuunta naapurisuunta = Ilmansuunta.POHJOINEN;

			Solmu s = kartta.annaSolmu(this.x, this.y); // solmu jossa ollaan

			for (int c=0; c<4; c++) {

				System.out.println("Naapurisuunta " + naapurisuunta);

				int nx = kartta.annaViereisenX(this.x, this.y, naapurisuunta);
				int ny = kartta.annaViereisenY(this.x, this.y, naapurisuunta);
				
				// jos tutkittavaan naapurisuuntaan on VAPAA
				if ((s.annaEste(naapurisuunta) == Este.VAPAA)) {
					
					// aloitetaan naapurisuunnasta vasemmalle
					Ilmansuunta tarksuunta = naapurisuunta.vasen();

					// cc on dummy laskuri
					for (int cc=0; cc<3; cc++) {		

						/* jos viereisella solmulla on viela viereinen:
						 * - jos tama on vapaasuuntainen
						 * - jos naiden valilla on VAPAA
						 * => kasvata laskuria
						 */
						
						// v on naapurin viereinen, talle koordinaatit
						int vx = kartta.annaViereisenX(nx, ny, tarksuunta);
						int vy = kartta.annaViereisenY(nx, ny, tarksuunta);
						
						// Solmu v
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
			return Ilmansuunta.numerostaSuunta(suurimmanArvonIndeksi);
		} // ei oltu umpikujassa
	} // end of etsiSopivinSuunta

	// paivittaa robotin sijainnin kartalla vuoron jalkeen
	public void asetaKoordinaatit(){
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


	// tekee siirron
	public void teeSiirto(){

		// jos ei olla maalissa
		if (!onMaalissa()) {
			tarkistaYmparisto();
			// paivittaa kartan uusien tietojen pohjalta
			kartta.paivitaKartta(this.x, this.y);
			
			// etsii oikean suunnan ja kaantyy
			kaannyOikeaanSuuntaan();
			// jos eteneminen onnistuu, asetaa uudet koordinaatit
			if (etene()) {
				asetaKoordinaatit();
			}
		} // oltiin maalissa

	} // end of teeSiirto

}
