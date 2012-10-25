import java.util.HashMap;
import java.awt.Point;

public class HermanniRobotti extends Robotti{

	private Kartta kartta; //hermanni pitää kirjaa sokkelosta
	private Ilmansuunta suunta; //suunta johon hermanni katsoo
	private int x; //kasvaa oikealle itään
	private int y; //kasvaa ylös pohjoiseen
	private int numero;
	
	
	public HermanniRobotti(){
		
		super();
		y = 0;
		x = 0;
		numero = 0; //ruutu johon robotti syntyy, saa numeron 1 teeSiirto metodissa
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
		
		/* jottei rakas Hermanni loisi solmu jo olemassa olevan solmun päälle
		 * eikä varsinkaan antaisi uutta askelnumeroa sijainnille jossa on jo
		 * käyty. Testailin tätä  
		 * 	-Masa
		 */
		if ( kartta.onSolmu(this.x, this.y) == false){ 
			kartta.lisaaSolmu(x,y, new Solmu(x, y, kartta, numero)); // parametrit annettava TEHTY
		
		
			suunta = suunta.numerostaSuunta(annaSuunta());
			
		
			Este e;
			
			for ( int i = 0; i < 4; i++){
				if (voiEdeta()) e = Este.VAPAA;
				else e = Este.SEINA;
				kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), e);
				kaannyVasemmalle();
			}
		
			if (kartta.onVapaa()) kartta.lisaaVapaa();	
	
		}
	}
	
	
	// rutiini, joka etsii sopivan suunnan TODO
	public void kaannyOikeaanSuuntaan(){
		/* while silmukkaa ei tarvita koska etsiSopivinSuunta() palauttaa
		 * parhaan suunnan riippumatta mihin Hermanni katsoo tällä hetkellä
		 * 
		 * while(suunta != etsiSopivinSuunta()){
		
			kaannyVasemmalle();
		}*/
		this.suunta = this.etsiSopivinSuunta();
	
	}
	
	public Ilmansuunta etsiSopivinSuunta() {
		
		/* loppuSuunnan alkuarvolla ei ole väliä, koska jossain suunnassa
		 * on väkisin pienempi askelnumero, koska Hermanni on tullut jostain
		 * suunnasta.
		 */
		Ilmansuunta loppuSuunta = Ilmansuunta.POHJOINEN;
		
		// Umpikuja
		if (!kartta.onVapaa()) {
			
			//Naapuri, jolla pienin askelnumero
			int pieninNumero = numero;
			for(int i = 0; i < 4; i++){
				// suunnassa i oleva
				Solmu s = kartta.annaSolmu(this.x, this.y).annaNaapuriSolmu(
						Ilmansuunta.numerostaSuunta(i));
				int n ;
				
				if (s!=null) {
					n = s.annaAskelNumero();
					if ( n < pieninNumero) {
						loppuSuunta = Ilmansuunta.numerostaSuunta(i);
						pieninNumero = n;
					} // uusin numero oli pienin
				} // Solmu on olemassa
			} // for-silmukka
			
			kartta.annaSolmu(x,y).annaNaapuriSolmu(loppuSuunta)
			.asetaEste(loppuSuunta.vastakkainen(), Este.UMPIKUJA);
			return loppuSuunta;
		} // oltiin umpikujassa
		// Enta, jos ei olla umpikujassa
		else {
			
			// Luodaan laskurit, jotka pitavat kirjaa naapureiden mahdollisista suunnista
			int laskurit[] = {0,0,0,0};
			
			// Aloitetaan tarkastelu aina pohjoisesta
			Ilmansuunta tarkastelusuunta = Ilmansuunta.POHJOINEN;
			
			
			for (int i = 0 ; i < 4 ; i++){
				
				// Tarkastellaan vain naapureita, joihin voidaan edeta
				if(kartta.annaSolmu(x,y).annaEste(tarkastelusuunta) == Este.VAPAA){
					
					// Asetetaan tarkasteltavan ruudun koordinaatit
					int viereinenX = annaX();
					int viereinenY = annaY();
					switch(tarkastelusuunta){
				
						case POHJOINEN: viereinenY++;
						case ITA: viereinenX++;
						case ETELA: viereinenY--;
						case LANSI: viereinenX--;
					
					}
					
										
					// Jos tarkasteltavan solmun ymparilla on solmu, johon on vapaa yhteys, laskuri kasvaa
					
					//viViereinen eli viereisen viereinen
					
					//taulukoiden järjestys: pohjoinen, itä, etelä, länsi

					int viViereinenX[] = 
						{viereinenX, viereinenX +1, viereinenX, viereinenX -1};
					int viViereinenY[] = 
						{viereinenY + 1, viereinenY, viereinenY -1, viereinenY};
					
					
					for (int j = 0; j < 4; j++){
						
						if (kartta.onSolmu(viViereinenX[j], viViereinenY[j])
								== false){ //jos viVireistä ei ole, ei tehdä mitään
						}
							
						else {
							//viViereisen ja Viereisen välinen Este enum.
							Este viViereinenJaViereinen = kartta.
									annaSolmu(viViereinenX[j], viViereinenY[j]).
									annaEste(Ilmansuunta.numerostaSuunta(j).
											vastakkainen() );
				
					
							//jos ei seinää ja viViereinen on vapasuuntainen
							if (this.kartta.
								onVapaa(viViereinenX[j], viViereinenY[j]) &&
								viViereinenJaViereinen == Este.VAPAA ){
									
								laskurit[i]++;
							}	
					
						}
					}				//bujakashaa, me tehtiin tää! by Masa, Atro, Mikko
									//kiitos vinkeistä Olli :)
				/**			ATRON VERSIO YLLÄOLEVASTA
				 * 				vaihtoehtoinen koodi pätkälle, joka alkaa:
				 * 				int viViereinenX[] =
				 * 			
				 	
				 
					if ((kartta.onSolmu(viereinenX-1, viereinenY)) &&
						kartta.annaSolmu(viereinenX-1, viereinenY).annaEste(Ilmansuunta.ITA)==Este.VAPAA)&&
						kartta.onVapaa(){
						laskurit[i]++;
					}
					if ((kartta.onSolmu(viereinenX+1, viereinenY)) &&					
						kartta.annaSolmu(viereinenX+1, viereinenY).annaEste(Ilmansuunta.LANSI)==Este.VAPAA){
						laskurit[i]++;
					}
					if ((kartta.onSolmu(viereinenX, viereinenY-1)) &&
						kartta.annaSolmu(viereinenX, viereinenY-1).annaEste(Ilmansuunta.POHJOINEN)==Este.VAPAA){
						laskurit[i]++;
					}	
					if ((kartta.onSolmu(viereinenX, viereinenY+1)) &&
						kartta.annaSolmu(viereinenX, viereinenY+1).annaEste(Ilmansuunta.ETELA)==Este.VAPAA){
						laskurit[i]++;
					} **/
					
					tarkastelusuunta.vasen();
				}
			}
			// Katsotaan mika laskureista on suurin
			int suurinLaskureidenArvo = 0;
			int suurimmanArvonIndeksi = 0;
			for (int k = 0 ; k < 4 ; k++){
				
				if(laskurit[k]>suurinLaskureidenArvo){
					
					suurinLaskureidenArvo = laskurit[k];
					suurimmanArvonIndeksi = k;
				}
			}
			
			// Asetetaan suurinta laskuria vastaava suunta
			
			/* palauttaa suunnan, johon hermannin tulisilähteä,
			 * riippumatta mihin suunta hermanni aluksi katsoi
			 * 
			 */
			
			return Ilmansuunta.numerostaSuunta(suurimmanArvonIndeksi);
			
			// Luodaan viereisen koordinaatit
			// Lasketaan kuinka monta kertaa viereinen koordinaatti loytyy
			// vapaatSuunnat-listasta
			// Repeat
			// Paata suunta
			// ???
			// Profit!
			
			/*2.1)Jokaista omaa vapaata suuntaa kohden muodosta koordinaatit
			 * 				Solmulle, joka olisi uusi sijainti
			 * 				(annaViereisenKoordinaatit), ja tarkistaa taman
			 * 				ymparilta, onko yksikaan naista ruuduista vapaasuuntainen
			 * 				siten, etta valissa ei ole seinaa.
			 * 				Kerryttaa talla tiedolla laskuria. */
		}
	} // end of etsiSopivinSuunta
	
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
		
		numero++; //kasvatetaan askelnumeroa
		
		// ollaankoMaalissa();
		tarkistaYmparisto();
		kaannyOikeaanSuuntaan();
		etene();
		asetaKoordinaatit();
		
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
	 * 		listalle, mikali tama sellainen on. TEHTY
	 * 
	 * kaannyOikeaanSuuntaan():
	 * 		1)	tarkista vapaiden suuntien maara
	 * 			->	jos umpikujassa, eli jos ymparilla
	 * 				vain ruutuja joissa kayty, tai seinia, 
	 * 				suuntaa kohti lahinta vapaata eli
	 * 				naapuria, jolla on pienin askelnumero
	 * 				(HUOM kartan onVapaa-metodi!, sita saattaa joutua muuttamaan mutta
	 * 				se melkein kay sellasenaan, POHTIKAA TARKOIN (kyna ja
	 * 				paperia!)JAWOHL!!!!!!!
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
	 * 				KAIKKI TEHTY: jos laskurit ovat tasan Hermanni valitsee 
	 * 						ilmansuunnan jota vastaava numero on pienin
	 * 						eli järjestys: pohjonen, itä, etelä, lansi
	 * 
	 * 
	 */
	
	
	
	
}
