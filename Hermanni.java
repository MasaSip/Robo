
public class HermanniRobotti extends Robotti?{

	Ruutu [] [] kartta;
	Suunta suunta;
	int x;
	int y;
	
	
	suunta = annaSuunta();
	
		
	kartta.annaSolmu(x, y).asetaEsteettömyys(annaSuunta(), ruutu.suunta.voiEdeta());
	
	kaannyVasemmalle();
	
	
	
	kartta.annaSolmu(x, y).asetaEsteettömyys(annaSuunta(), ruutu.suunta.voiEdeta());
	
	kaannyVasemmalle();
	
	
	kartta.annaSolmu(x, y).asetaEsteettömyys(annaSuunta(), ruutu.suunta.voiEdeta());
	
	kaannyVasemmalle();
	
	
	kartta.annaSolmu(x, y).asetaEsteettömyys(annaSuunta(), ruutu.suunta.voiEdeta());
	
	kaannyVasemmalle();
	
	
	while(suunta != ETSISOPIVINSUUNTA){
		
		kaannyVasemmalle();
	}
		
	
	
		
		
	
	
	
}
