/*
 * Kaytetyt ilmansuunnat.
 */
public enum Ilmansuunta {
	POHJOINEN, LANSI, ETELA, ITA;
	
	public Ilmansuunta vasen() {
		
		switch (this) {
			case POHJOINEN:
				return LANSI;
			case LANSI:
				return ETELA;
			case ETELA:
				return ITA;
			case ITA:
				return POHJOINEN;
			default:
				return (Ilmansuunta) null;
		} // end of switch
	} // end of vasen()
	
	/*
	 * Palauttaa vastakkaisen ilmansuunnan. KESKI palauttaa null.
	 */
	public Ilmansuunta vastakkainen() {
		return vasen().vasen();
	} // end of vastakkainen()
	
	/*
	 * Palauttaa oikealla olevan ilmansuunnan.
	 */
	public Ilmansuunta oikea() {
		return vastakkainen().vasen();
	} // end of oikea()
	
	/*
	 * Palauttaa ilmansuuntaa vastaavan numeroarvon [0, 3]
	 */
	public int suunnastaNumero(Ilmansuunta suunta) {
		switch (suunta) {
		case POHJOINEN: return 0;
		case ITA: return 1;
		case ETELA: return 2;
		case LANSI: return 3;
		default: return -1;
		}
	}

	/*
	 * Palauttaa numeroa vastaavan ilmansuunan
	 */
	public Ilmansuunta numerostaSuunta(int nro ) {
		switch (nro) {
		case 0: return POHJOINEN;
		case 1: return ITA;
		case 2: return ETELA;
		case 3: return LANSI;
		default: return null;
		}
	}
/*
	public static void main(String args[]){
		Ilmansuunta suunta;
		suunta = POHJOINEN;
		System.out.println(suunta.vasen().vastakkainen());
	}
*/

}
