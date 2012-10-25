M U U T A M I A   H U O M I O I T A
///////////////////////////////////

- Kun committaatte githubiin, laittakaa mielellaan joku jarkeva selitys, niin on helpompi etsia eri versiot.
- Kirjoitin auki yksinkertaisimman algoritmin ja siita puuttuvat osat ja metodit,
  JAKAKAA nuo tehtavat pienryhmiin ja tehkaa ne hopihopi,
  jos ollaan nopeita voidaan viela ruveta parantamaan algoritmia. Hommaa toimivaan robottiin ei siis mun 
  nahdakseni oo tota tuossa listattua enempaa, siina on parin tunnin homma jos se vaan tehdaan, eli hyvaa
  edistysta tiistaina!
- Poistin turhia tiedostoja.

TERKUIN olli.

Seuraavassa Commit-hetkella kaikkien tiedostojen TODO-listat koostettuna
yhdessa paikassa, tasta jonkun (that means you right there, ei lusmuta! :D) on helppo jakaa tyot
ryhmille, jotka voi olla olla jotkut vanhat tai sitten jaetaan uudestaan.



HERMANNIROBOTTI.JAVA

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

KARTTA.JAVA

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
	 * static annaViereisenKoordinaatit(int x, int y, Ilmansuunta i)
	 *  -	ottaa parametriksi ruudun koordinaatit, palauttaa talle pyydetyssa
	 *  	ilmansuunnassa olevan solmun koordinaatit
	 *  HUOM kaytannossa ota koodi Solmun metodista annaNaapuriSolmu, korvaa
	 *  sen jalkee annaNaapuriSolmun toiminnallisuus kayttamaan tata.
	 *
	 */

SOLMU.JAVA

	/*
	 * TODO
	 * 
	 * annaNumero(), palauttaa numeron
	 */
