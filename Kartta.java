//import java.util.Map;
import java.util.HashMap;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;


/*
 * Luokka Kartta
 * Toimii robotin muistina, pitaa sailyttaa tietoja vierailluista ruuduista
 * HashMap-rakenteessa
 */

public class Kartta {
	
	
	
	private HashMap< Point, Solmu > kartta;
	/*
	 * Kartta yllapitaa listaa, jonka jasenet ovat viittauksia sellaisiin
	 * Solmuihin, joilla tiedetaan olevan sellasia suuntia vapaina, missa
	 * ei viela olla kayty. Listan viimeinen on aina viimeisin vapaasuuntainen.
	 * Pakottamalla robotti tarkistamaan vapaat suunnat kaanteisessa
	 * jarjestyksessa eli tuorein ensin, saadaan kenties turhahkoja siirtoja
	 * tekeva mutta varmasti toimiva robotti.
	 */
	private List<Solmu> vapaasuuntaiset;
	// viittaus robottiin
	private HermanniRobotti robotti;
	
	// CTOR
	public Kartta(HermanniRobotti robotti) {
		this.robotti = robotti;
		kartta = new HashMap< Point, Solmu >();
		this.vapaasuuntaiset = new ArrayList<Solmu>();
	} // end of CTOR
	
	
//////////////////////////////////////////////////////////////////
// KARTAN PERUSTOIMINNOT JA SOLMUJENKASITTELY
//////////////////////////////////////////////////////////////////
	
	// palauttaa true jos on olemassa Solmu halutuissa koordinaateissa
	public boolean onSolmu(int x, int y) {
		if (annaSolmu(x,y)!=null)
			return true;
		else
			return false;
	} // end of onSolmu
	
	// palauttaa Solmun pyydetyista koordinaateista, jos ei ole niin null
	public Solmu annaSolmu(int x, int y) {
		Solmu solmu = kartta.get(new Point(x,y));
		return solmu;
	}
	
	// lisaa solmun pyydettyihin koordinaatteihin, jos tama on jo, ylikirjoita
	public void lisaaSolmu(int x, int y, Solmu s) {
		if (onSolmu(x, y)) {
			this.poistaSolmu(x, y);
		}
		kartta.put(new Point(x,y), s);
		s.asetaKartalle(this, x, y);
	}
	
	// poista solmu koordinaateista, jos se on olemassa
	public void poistaSolmu(int x, int y) {
		if (onSolmu(x, y))
			kartta.remove(new Point(x,y));
	}
	
//////////////////////////////////////////////////////////////////
// VAPAASUUNTAISTEN SOLMUJEN TOIMINNOT
//////////////////////////////////////////////////////////////////
	
	/*
	 * Jos kusutaan ilman parametreja, oletetaan, etta halutaan robotin
	 * tamanhetkisen sijainnin vapaustieto.
	 */
	public boolean onVapaa() { //onko solmu vapaasuuntainen
		return onVapaa(robotti.annaX(), robotti.annaY());
	}
	
	// palauttaa true, jos ymparilla on vapaita suuntia
	public boolean onVapaa(int x, int y) {
		// jos ei tunneta tai ei tunneta tai ei tunneta
		Ilmansuunta i = Ilmansuunta.POHJOINEN;
		Solmu sijainti = this.annaSolmu(x, y);
		
		do {
			if (	// ollaanko halutussa suunnassa jo kayty
					sijainti.annaNaapuriSolmu(i) != null &&
					// onko halutussa suunnassa seina
					sijainti.annaEste(i) == Este.VAPAA) {
				return true;
			}
			// suunta ei ollut vapaa, kaannetaan suuntaa
			else {
				i = i.vasen();
			} // else
		} while (i!=Ilmansuunta.POHJOINEN); // kierratetaan lanteen asti,
											// koska kaantaa ennen checkia
		return false;
	} // end of onVapaa

	
	// lisaa tamanhetkinen sijainti vapaiden solmujen listaan
	public void lisaaVapaa() {
		Solmu sijainti = this.annaSolmu(robotti.annaX, robotti.annaY);
		this.vapaasuuntaiset.add(sijainti);
	} // end of lisaaVapaa
	
	
	// poista pyydetty solmu listasta
	public void poistaVapaa(Solmu poistettava) {
		if (poistettava != null)
			if (vapaasuuntaiset.contains(poistettava))
					vapaasuuntaiset.remove(poistettava);
	} // end of poistaVapaa
	
	
	// palauta viimeisin vapaa solmu, eli pinon paallimainen
	public Solmu annaViimeisinVapaa() {
		int koko = vapaasuuntaiset.size();
		if (koko > 0) { // jos listassa on mitaan
			
			Solmu s = vapaasuuntaiset.get(koko-1); // muista viimeisin
			vapaasuuntaiset.remove(koko-1); // poista viimeisin
			return s; // palauta viimeisin
		}
		return null; // muutoin palauta null
	} // end of annaViimeisinVapaa
	
	/*
	public static void main(String[] args) {
	}
	*/
	
	/*
	 * TODO
	 * 
	 * paivitaKartta()
	 * 	-	robotti kutsuu, kun ollaan astuttu uuteen ruutuun, tekee seuraavat:
	 * 		-	tutkii, ollaanko astuttu ruutuun siten, että jokin askettain
	 * 			vapaasuuntainens Solmu ei tata enaa olekaan, ts. katsoo
	 * 			ymparilleen, onko yksikaan uusi naapuri ollut vapaasuuntainen,
	 * 			kayta kenties List.contains(),
	 * 			ja tarvittaessa poistaa nama vapaasuuntaisten listalta
	 * 		-	asettaa solmulle, jossa ollaan numeron, jonka se pyytaa Hermannilta
	 * 			metodilla annaAskelNumero()
	 * 
	 static annaViereisenKoordinaatit(int x, int y, Ilmansuunta i)
// onko maailmassa
		if (kartta!=null) {

			// apukoordinaatit, rajoissa [-1,1]

			int x = 0;
			int y = 0;

			// aseta apukoordinaatit osoittamaan naapuriruudun suuntaan
			if (suunta!=null)
				switch (suunta) {
				case POHJOINEN:
					y = 1;
					break;
				case LANSI:
					x = -1;
					break;
				case ETELA:
					y = -1;
					break;
				case ITA:
					x = 1;
					break;
				default:
					// ilmansuunta ei ole sopiva
					return null;
				} // end of switch
			/*
			 * Palauta Solmu halutusta kohdasta
			 * joka on kohdassa {oma sijainti + apukoordinaatit} 
			 */
			return kartta.annaSolmu(this.y+y, this.x+x);

		} // kartta on olemassa
		return null;
	} // end of annaNaapuriSolmu
	 *  
-	ottaa parametriksi ruudun koordinaatit, palauttaa talle pyydetyssa
	 *  	ilmansuunnassa olevan solmun koordinaatit
	 *  HUOM kaytannossa ota koodi Solmun metodista annaNaapuriSolmu, korvaa
	 *  sen jalkee annaNaapuriSolmun toiminnallisuus kayttamaan tata.
	 *
	 */
}
