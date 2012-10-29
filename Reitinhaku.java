import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Reitinhaku {
/*
 * A* pathfinding algorithm
 * 
 * create a list P
 * add the start node S, to P giving it one element
 * Until first path of P ends with G, or P is empty
 * 		extract the first path from P
 * 		extend the path one step to all neighbors creating X new paths
 * 		reject all paths with loops
 * 		add each remaining new path to P
 * If G found -> success. Else -> failure.	
 *
 * Perustuu yllaolevaan logiikkaan, lahde:
 * http://ai-depot.com/Tutorial/PathFinding.html
 * 
 * Hieman tehottomasti implementoin, hakee aina umpikujakasittelyn jokaista
 * Solmua kohden reitin uudestaan. Jos tasta haluttaisiin tehokkaampi pitkan
 * paalle, voisi Reitinhaku-olio esimeriksi muistaa viimeksi kutsutun reitin
 * ja katsoa, onko nyt pyydetty reitti sama ja kayttaa jo laskettua reittia.
 * Implementoin englanniksi, koska se on nopeampaa, helpompaa ja kauniimpaa.
 * - Olli
 */
	@SuppressWarnings("unchecked")
	public ArrayList<Solmu> annaReitti(Kartta kartta, Solmu alku, Solmu maali) {
		System.out.println("\t\t FIND: " + alku + " => " + maali);

		// list master, has sublists which represent routes
		LinkedList< ArrayList<Solmu> > master;
		master = new LinkedList< ArrayList< Solmu > >();
		
		// add the first node
		master.add( new ArrayList<Solmu>(Collections.nCopies(1, alku)) );
		// current used as temp
		ArrayList<Solmu> current;
		
		// start loop
		while (!master.isEmpty()) {

			// pop the currently first list from queue
			current = master.pop();

			// just fo sho
			if (current.size() <= 0)
				return null;

			// last element of the list of nodes
			Solmu last = current.get(current.size()-1);
			// if the last is the target, success, return
			if (last == maali) {
				return current;
			} else {
				
				Ilmansuunta i = Ilmansuunta.POHJOINEN;
				
				// do while (i != POHJOINEN)
				do {
					// the new node to be added to the end of list
					Solmu toadd = last.annaNaapuriSolmu(i);
					
					// check if not behind wall and does exist
					if (toadd!=null && last.annaEste(i)==Este.VAPAA) {
						// if the formed route is not a loop
						if (!current.contains(toadd)) {
							
							 // clone current queue, add the new Solmu,
							 // and push to the end of master
							ArrayList<Solmu> t =
									(ArrayList<Solmu>) current.clone();
							t.add(toadd);
							master.addLast(t);
						}
					} // not behind wall
					// turn one step until we have done all directions
					i = i.vasen();
				} while (i != Ilmansuunta.POHJOINEN); // check directions
			} // not finished yet
		} // master is not empty
		
		return null;
	} // end of annaReitti
	
	
	// wrap annaReitille, palauttaa suunnan
	public Ilmansuunta annaSuunta(Kartta kartta, Solmu alku, Solmu maali) {
		
		// hanki reitti annetuilla pisteilla
		ArrayList< Solmu > reitti = this.annaReitti(kartta, alku, maali);
		
		if (reitti == null)
			return null;
		if (reitti.size() <= 0)
			return null;
		
		// reitin ensimmainen ja toinen Solmu
		Solmu eka = reitti.get(0);
		Solmu toka = reitti.get(1);
		
		// palauta suunta koordinaattien muutoksen perusteella
		
		// jos x on kasvanut yhdella
		if (( (eka.annaX() + 1) == toka.annaX())
				&& (eka.annaY()  == toka.annaY())) {
			return Ilmansuunta.ITA;
		}
		// jos x on vahentynyt yhdella
		else if (( (eka.annaX() - 1) == toka.annaX())
				&& (eka.annaY() == toka.annaY() )) {
			return Ilmansuunta.LANSI;
		}
		// jos y on kasvanut yhdella
		else if (( eka.annaX() == toka.annaX())
				&& ((eka.annaY() + 1)  == toka.annaY())) {
			return Ilmansuunta.POHJOINEN;
		}
		// jos y on vahentynyt yhdella
		else if (( eka.annaX() == toka.annaX())
				&& ((eka.annaY() - 1)  == toka.annaY())) {
			return Ilmansuunta.ETELA;
		}
		else {
			return null;
		}
	} // end of annaSuunta
} // end of class Reitinhaku
