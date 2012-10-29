import java.util.HashMap;

// luokka Solmu, pitaa sisallaan tiedot vierailluista ruuduista
public class Solmu {
	private HashMap <Ilmansuunta, Este> esteetSuunnissa;
	private int x, y;
	private Kartta kartta;

	public void asetaKartalle(Kartta kartta, int x, int y) {
		this.kartta = kartta;
		this.x = x;
		this.y = y;
	} // asetaKartalle

	// CTOR
	public Solmu(int x, int y, Kartta kartta) {

		this.x = x;
		this.y = y;
		this.kartta = kartta;
		
		this.esteetSuunnissa = new HashMap<Ilmansuunta, Este>();

		this.asetaEste(Ilmansuunta.POHJOINEN, Este.VAPAA);
		this.asetaEste(Ilmansuunta.ITA, Este.VAPAA);
		this.asetaEste(Ilmansuunta.ETELA, Este.VAPAA);
		this.asetaEste(Ilmansuunta.LANSI, Este.VAPAA);
	} // end of CTOR

	public int annaX(){
		return this.x;
	} // end of annaX
	
	public int annaY(){
		return this.y;
	}
	// end of annaY
	
	// Anna Solmun Este-status pyydetylle Solmulle	
	public Este annaEste(Ilmansuunta i) {
		return this.esteetSuunnissa.get(i);
	} // end of annaEsteet

	// Aseta Solmun pyydetylle suunnalle Este-status
	public void asetaEste(Ilmansuunta i, Este este) {
		this.esteetSuunnissa.put(i, este);
	} // end of asetaEste


	// Antaa naapurisolmun pyydetysta suunnasta, tama on null jos ei ole
	public Solmu annaNaapuriSolmu(Ilmansuunta suunta) {

		// check null
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
			return kartta.annaSolmu(this.x+x, this.y+y);
		} // kartta on olemassa
		return null;
	} // end of annaNaapuriSolmu
	
	// tulosteena koordinaatit
	public String toString() {
		String ulos = "(" + this.x + ", " + this.y + ")";
		return ulos;
	}
	
}