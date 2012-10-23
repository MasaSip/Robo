import java.util.HashMap;


public class Solmu {

	private HashMap <Ilmansuunta, Boolean> esteettomyysSuunnissa;
	private Ruutu ruutu;
	private String nimi; // mock
	private int x, y;
	private Kartta kartta;
	
	
	public Solmu(String nimi) {
		this.nimi = nimi;
	}
	
	public String annaNimi() {
		return this.nimi;
	}
	
	public void asetaKartalle(Kartta kartta, int x, int y) {
		this.kartta = kartta;
		this.x = x;
		this.y = y;
	}
	/*
	public String toString() {
		return this.nimi;
	}
	*/
	

	
	public Solmu(){
		this.esteettomyysSuunnissa = new HashMap<Ilmansuunta, Boolean>();
		this.esteettomyysSuunnissa.put(Ilmansuunta.POHJOINEN,
				new Boolean (false) );
		
	}
}
