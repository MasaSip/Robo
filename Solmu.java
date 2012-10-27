import java.util.HashMap;

public class Solmu {
	private HashMap <Ilmansuunta, Este> esteetSuunnissa;
	private Ruutu ruutu;
	private int x, y;
	private int numero;
	private Kartta kartta;

	public void asetaKartalle(Kartta kartta, int x, int y) {
		this.kartta = kartta;
		this.x = x;
		this.y = y;
	} // asetaKartalle

	// CTOR
	public Solmu(int x, int y, Kartta kartta, int numero) {

		this.x = x;
		this.y = y;
		this.kartta = kartta;
		this.numero = numero;
		
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
	return Kartta.annaViereisenKoordinaatit(int x, int y, Ilmansuunta i);

	public int annaAskelNumero(){
		return this.numero;
	}
	
	public void asetaAskelNumero(int numero){
		this.numero = numero;
	}
}