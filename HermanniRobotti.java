import java.util.HashMap;
import java.awt.Point;

public class HermanniRobotti extends Robotti{

	private Kartta kartta; //hermanni pitŠŠ kirjaa sokkelosta
	private Ilmansuunta suunta; //suunta johon hermanni katsoo
	private int x;
	private int y;
	
	
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
	
	public void tarkistaYmparisto(){
		
		
		kartta.lisaaSolmu(x,y, new Solmu()); // parametrit annettava
		suunta = suunta.numerostaSuunta(annaSuunta());
	
		Este e;
		
		// tarkista eteenpain
		if (voiEdeta()) e = Este.VAPAA;
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), e);
		kaannyVasemmalle();
		
		// tarkista oikealle
		if (voiEdeta()) e = Este.VAPAA;
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();
	
		// tarkista taakse
		if (voiEdeta()) e = Este.VAPAA;
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();
		
		// tarkista vasemmalle
		if (voiEdeta()) e = Este.VAPAA;
		else e = Este.SEINA;
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
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
		// ollaankoMaalissa();
	}
	
	
	/* TODO-lista:
	 * 
	 * int annaX(), palauttaa tamanhetkisen x-koordinaatin
	 * int annaY(), palauttaa tamanhetkisen y-koordinaatin
	 * 
	 * 
	 * -	Askeleiden numerolaskuria kasvatettava askeleen yhteydessa
	 *
	 * tarkistaYmparisto(): Tulee lisata uusi Solmu vapaasuuntaisten solmujen
	 * 		listalle, mikali tama sellainen on.
	 * 
	 * kaannyOikeaanSuuntaan():
	 * 		1)	tarkista vapaiden suuntien maara
	 * 			->	jos umpikujassa, suuntaa kohti lahinta vapaata eli
	 * 				naapuria, jolla on pienin askelnumero
	 * 				(HUOM onVapaa-metodi!, sitä saattaa joutua muuttamaan mutta
	 * 				se melkein kay sellasienaan, POHTIKAA TARKOIN (kyna ja
	 * 				paperia!)
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
