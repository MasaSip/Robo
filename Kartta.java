//import java.util.Map;
import java.util.HashMap;
import java.awt.Point;

public class Kartta {
	private HashMap< Point, Solmu > kartta;
	
	Kartta() {
		kartta = new HashMap< Point, Solmu >();
	}
	
	public boolean onSolmu(int x, int y) {
		if (annaSolmu(x,y)==null)
			return true;
		else
			return false;
	}
	
	public Solmu annaSolmu(int x, int y) {
		Solmu node = kartta.get(new Point(x,y));
		return node;
	}
	
	public void lisaaSolmu(int x, int y, Solmu s) {
		if (onSolmu(x, y)) {
			this.poistaSolmu(x, y);
		}
		kartta.put(new Point(x,y), s);
		s.asetaKartalle(this, x, y);
	}
	
	
	public void poistaSolmu(int x, int y) {
		if (onSolmu(x, y))
			kartta.remove(new Point(x,y));
	}
	
	public static void main(String[] args) {
		
		Kartta k = new Kartta();
		Solmu njee = new Solmu();
		Solmu nhii = new Solmu();
		k.lisaaSolmu(10, -10);
		k.lisaaSolmu(10, 5);
		
		Solmu n1;
		n1 = k.annaSolmu(10, -10);
		System.out.println();
		
		n1 = k.annaSolmu(10, 5);
		System.out.println();
		n1 = k.annaSolmu(10, 6);
		if (n1==null)
			System.out.println("hulabaloo");

	}
}


