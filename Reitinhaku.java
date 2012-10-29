import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Reitinhaku {
/*
 * 
 create a list P
 add the start node S, to P giving it one element
 Until first path of P ends with G, or P is empty
	extract the first path from P
	extend the path one step to all neighbors creating X new paths
	reject all paths with loops
	add each remaining new path to P
 If G found -> success. Else -> failure.	
 */
	public ArrayList<Solmu> annaReitti(Kartta kartta, Solmu alku, Solmu maali) {
		System.out.println("\t\t FIND: " + alku + " => " + maali);
		
		// luo lista
		// lisaa ensimmainen solmu
		// kunnes P:n ensimmainen reitti paattyy G:hen
		//  tai P on tyhja
		

		LinkedList< ArrayList<Solmu> > master;
		master = new LinkedList< ArrayList< Solmu > >();
		
		// lisataan alkusolmu
		master.add( new ArrayList<Solmu>(Collections.nCopies(1, alku)) );
		ArrayList<Solmu> current;
		
		// start loop
		while (!master.isEmpty()) {

			// get the currently first list from queue
			current = master.pop();

			// just fo sho
			if (current.size() <= 0)
				return null;

			// last element of the list of nodes
			Solmu last = current.get(current.size()-1);
			// if the last is the target, return
			if (last == maali) {
				//System.out.println("LAST SAMA KUIN MAALI");
				return current;
			} else {
				Ilmansuunta i = Ilmansuunta.POHJOINEN;

				do {

					// the one to be added
					Solmu toadd = last.annaNaapuriSolmu(i);
					// if not behind wall and does exist
					if (toadd!=null && last.annaEste(i)==Este.VAPAA) {
						// if is not a loop
						if (!current.contains(toadd)) {
							/*
							 *  clone current queue, add the new Solmu,
							 *  and push to master
							 */
							ArrayList<Solmu> t =
									(ArrayList<Solmu>) current.clone();
							t.add(toadd);
							master.addLast(t);
							//System.out.println("LISATAAN: " + t);
						}
					} // not behind wall
					// turn one step until we have done all directions
					i = i.vasen();
				} while (i != Ilmansuunta.POHJOINEN); //  check directions
			} // not finished yet
		} // master is not empty
		
		return null;
	} // end of annaReitti
	
	public Ilmansuunta annaSuunta(Kartta kartta, Solmu alku, Solmu maali) {
		ArrayList< Solmu > reitti = this.annaReitti(kartta, alku, maali);
		
		
		//System.out.println("REITTI: " + reitti);
		
		
		if (reitti == null)
			return null;
		if (reitti.size() <= 0)
			return null;
		
		Solmu eka = reitti.get(0);
		Solmu toka = reitti.get(1);
		
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
	}
}
