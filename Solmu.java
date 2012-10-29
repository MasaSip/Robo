import java.util.HashMap;

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


	/*
	 * Anna Solmun Este-status pyydetylle Solmulle
	 */
	public int annaX(){
		return x;
	}
	
	public int annaY(){
		return y;
	}
	
	public Este annaEste(Ilmansuunta i) {
		return this.esteetSuunnissa.get(i);
	} // end of annaEsteet


	/*
	 * Aseta Solmun pyydetylle suunnalle Este-status
	 */
	public void asetaEste(Ilmansuunta i, Este este) {
		this.esteetSuunnissa.put(i, este);
	} // end of asetaEste


	/*
	 * Antaa naapurisolmun pyydetysta suunnasta, tama on null jos ei ole
	 */
	public Solmu annaNaapuriSolmu(Ilmansuunta suunta) {

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
			//System.out.println("NAAPURISOLMU KOORDINAATEISSA X: " + this);
			return kartta.annaSolmu(this.x+x, this.y+y);

		} // kartta on olemassa
		return null;
	} // end of annaNaapuriSolmu
	
	public String toString() {
		String ulos = "(" + this.x + ", " + this.y + ")";
		return ulos;
	}
	
}