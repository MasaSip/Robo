

//import java.util.Map;
import java.util.HashMap;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public class Kartta {
	private HashMap< Point, Solmu > kartta;
	private List<Solmu> vapaasuuntaiset;
	private HermanniRobotti robo;
	
	public Kartta() {
		kartta = new HashMap< Point, Solmu >();
		this.vapaasuuntaiset = new ArrayList<Solmu>();
	}
	
	public boolean onSolmu(int x, int y) {
		if (annaSolmu(x,y)==null)
			return true;
		else
			return false;
	}
	
	public Solmu annaSolmu(int x, int y) {
		Solmu solmu = kartta.get(new Point(x,y));
		return solmu;
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
	
	//T€ST€ ALKAA VAPAASUUNTAISET SOLMUT
	
	// palauttaa true, jos ymparilla on vapaita suuntia
	public boolean onVapaa() { // lisaa uusi vapaa
		// jos ei tunneta tai ei tunneta tai ei tunneta
		Ilmansuunta i = Ilmansuunta.POHJOINEN;
		Solmu sijainti = this.annaSolmu(robo.annaX, robo.annaY);
		
		do {
			if (	// ollaanko halutussa suunnassa jo kayty
					sijainti.annaNaapuriSolmu(i) != null &&
					// onko halutussa suunnassa seina
					sijainti.annaEste(i) == Este.VAPAA) {
				return true;
			}
			// suunta ei ollut vapaa, kaannetaan suuntaa
			else {
				i = i.vasen();
			} // else
		} while (i!=Ilmansuunta.POHJOINEN); // kierratetaan lanteen asti,
											// koska kaantaa ennen checkia
		return false;
	}

	// lisaa tamanhetkinen sijainti vapaiden solmujen listaan
	public void lisaaVapaa() {
		Solmu sijainti = this.annaSolmu(robo.annaX, robo.annaY);
		this.vapaasuuntaiset.add(sijainti);
	}
	
	public void poistaVapaa(Solmu poistettava) {
		if (poistettava != null)
			if (vapaasuuntaiset.contains(poistettava))
					vapaasuuntaiset.remove(poistettava);
	} // poista pyydetty solmu listasta
	
	public Solmu annaViimeisinVapaa() {
		int koko = vapaasuuntaiset.size();
		if (koko > 0) { // jos listassa on mitaan
			
			Solmu s = vapaasuuntaiset.get(koko-1); // muista viimeisin
			vapaasuuntaiset.remove(koko-1); // poista viimeisin
			return s; // palauta viimeisin
		}
		return null; // muutoin palauta null
	} // end of annaViimeisin Vapaa
	
	public static void main(String[] args) {
		Kartta k = new Kartta();
		Solmu njee = new Solmu("jee");
		Solmu nhii = new Solmu("hii");
		k.lisaaSolmu(10, -10, njee);
		k.lisaaSolmu(10, 5, nhii);
		
		Solmu n1;
		n1 = k.annaSolmu(10, -10);
		System.out.println(n1.annaNimi());
		
		n1 = k.annaSolmu(10, 5);
		System.out.println(n1.annaNimi());
		n1 = k.annaSolmu(10, 6);
		if (n1==null)
			System.out.println("hulabaloo");

	}
}
