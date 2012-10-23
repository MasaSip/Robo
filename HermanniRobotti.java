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
		
		
		kartta.lisaaSolmu(x,y, new Solmu());
		suunta = suunta.numerostaSuunta(annaSuunta());
	
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), voiEdeta());
		kaannyVasemmalle();
		
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();	
	
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();
		
		kartta.annaSolmu(x, y).asetaEste(suunta.numerostaSuunta(annaSuunta()), ruutu.suunta.voiEdeta());	
		kaannyVasemmalle();
	
	}
	public void kaannyOikeaanSuuntaan(){
		while(suunta != ETSISOPIVINSUUNTA()){
		
			kaannyVasemmalle();
		}
	}
	
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
}
