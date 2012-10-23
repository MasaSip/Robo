import java.util.HashMap;
import java.awt.Point;

public class HermanniRobotti extends Robotti{

	private Ruutu ruutu;
	private Kartta kartta;
	private Ilmansuunta suunta;
	private int x;
	private int y;
	
	
	public HermanniRobotti(){
		
		super();
		kartta = new Kartta();
	}
	
	
	
	public void tarkistaYmparisto(){
	
		suunta = suunta.annaSuunta(annaSuunta());
	
		kartta.annaSolmu(x, y).asetaEsteettomyys(suunta.annaSuunta(annaSuunta()), voiEdeta());
		kaannyVasemmalle();
		
		kartta.annaSolmu(x, y).asetaEsteettomyys(suunta.annaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();	
	
		kartta.annaSolmu(x, y).asetaEsteettomyys(suunta.annaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();
		
		kartta.annaSolmu(x, y).asetaEsteettomyys(suunta.annaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();
	
	}
	public void kaannyOikeaanSuuntaan(){
		while(suunta != ETSISOPIVINSUUNTA()){
		
			kaannyVasemmalle();
		}
	}

	
	public void teeSiirto(){
		
		// ollaankoMaalissa();
		tarkistaYmparisto();
		kaannyOikeaanSuuntaan();
		etene();
		// ollaankoMaalissa();
	}
}
