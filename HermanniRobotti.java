import java.util.HashMap;
import java.awt.Point;

public class HermanniRobotti extends Robotti{

	private Kartta kartta; //hermanni pitŠŠ kirjaa sokkelosta
	private Ilmansuunta suunta; //suunta johon hermanni katsoo
	private int x;
	private int y;
	private int numero;
	
	
	public HermanniRobotti(){
		
		super();
		y = 0;
		x = 0;
		kartta = new Kartta();
	}
	
	public int annaX(){
		return this.x;
	}
	
	public int annaY(){
		return this.y;
	}
	
	public int annaAskelNumero(){
		return this.numero;
	}
	
	public void tarkistaYmparisto(){
		
		this.suunta = Ilmansuunta.POHJOINEN;
		
		kartta.lisaaSolmu(x,y, new Solmu(x, y, kartta, numero)); // parametrit annettava TEHTY
		suunta = suunta.numerostaSuunta(annaSuunta());
		int montaVapaata = 0;
	
		Este e;
		
		// tarkista eteenpain
		if (voiEdeta()){
			e = Este.VAPAA;
			if (!kartta.onSolmu(x, y + 1)){
				montaVapaata++;
			}
		}
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), e);
		kaannyVasemmalle();
		
		// tarkista oikealle
		if (voiEdeta()) e = Este.VAPAA;
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), e);	
		kaannyVasemmalle();
	
		// tarkista taakse
		if (voiEdeta()) e = Este.VAPAA;
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), e);	
		kaannyVasemmalle();
		
		// tarkista vasemmalle
		if (voiEdeta()) e = Este.VAPAA;
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), e);	
		kaannyVasemmalle();
	
	}
	
	// rutiini, joka etsii sopivan suunnan TODO
	public void kaannyOikeaanSuuntaan(){
		while(suunta != ETSISOPIVINSUUNTA()){
		
			kaannyVasemmalle();
		}
	}
	
	// paivittaa robotin sijainnin kartalla vuoron jalkeen
	public void asetaKoordinaatit(){
		
		switch(annaSuunta()){
		
		case 0: y++;
		case 1: x++;
		case 2: y--;
		case 3: x--;
		default:
		}
		
	}

	
	public void teeSiirto(){
		
		// ollaankoMaalissa();
		tarkistaYmparisto();
		kaannyOikeaanSuuntaan();
		etene();
		asetaKoordinaatit();
		numero++;
		// ollaankoMaalissa();
	}
	
	
	/* TODO-lista:
	 * 
	 * int annaX(), palauttaa tamanhetkisen x-koordinaatin TEHTY
	 * int annaY(), palauttaa tamanhetkisen y-koordinaatin TEHTY
	 * 
	 * 
	 * -	Askeleiden numerolaskuria kasvatettava askeleen yhteydessa TEHTY
	 *
	 * tarkistaYmparisto(): Tulee lisata uusi Solmu vapaasuuntaisten solmujen
	 * 		listalle, mikali tama sellainen on.
	 * 
	 * kaannyOikeaanSuuntaan():
	 * 		1)	tarkista vapaiden suuntien maara
	 * 			->	jos umpikujassa, eli jos ymparilla
	 * 				vain ruutuja joissa kayty, tai seinia, 
	 * 				suuntaa kohti lahinta vapaata eli
	 * 				naapuria, jolla on pienin askelnumero
	 * 				(HUOM kartan onVapaa-metodi!, sitä saattaa joutua muuttamaan mutta
	 * 				se melkein kay sellasenaan, POHTIKAA TARKOIN (kyna ja
	 * 				paperia!)
	 * 			->	hanki tietoon solmu, johon ollaan astumassa, ja aseta
	 * 				taman se suunta umpikujaksi, josta ollaan tulossa
	 * 				(annaViereisenKoordinaatit ja asetaEste)
	 * 		2)	jos vapaita suuntia enemman kuin yksi, pyri ruutuun, joka
	 * 			eliminoisi mahdollisimman monta vapaata suuntaa eli:
	 * 			2.1)Jokaista omaa vapaata suuntaa kohden muodosta koordinaatit
	 * 				Solmulle, joka olisi uusi sijainti
	 * 				(annaViereisenKoordinaatit), ja tarkistaa taman
	 * 				ymparilta, onko yksikaan naista ruuduista vapaasuuntainen
	 * 				siten, etta valissa ei ole seinaa.
	 * 				Kerryttaa talla tiedolla laskuria.
	 * 			2.2)Kun jokainen oma vapaa suunta on kayty lapi, valitse
	 * 				suunnaksi laskureita vertaamalla se, joka eliminoisi eniten
	 * 				vapaita suuntia ymparoivista
	 * 			2.3)Jos laskurit ovat tasan, miettikkaa, mika olisi seuraava
	 * 				prioriteetti en nyt heti keksi enka ehdi piirrella -Olli
	 * 
	 */
	
	
	
	
}
