

/**
 * STUDIO 1: Ohjelmointikierros 2: Malliratkaisu syksy 2012
 * @author Merituuli Melkko
 * */
public enum Ilmansuunta {

	POHJOINEN, ETELA, ITA, LANSI;

	/**
	 * @return Ilmansuunta tasta ilmansuunnasta vasempaan.
	 * */
	public Ilmansuunta vasen(){
		switch (this) {
		case POHJOINEN: return LANSI;
		case LANSI: return ETELA;
		case ETELA: return ITA;
		case ITA: return POHJOINEN;
		default: return null;
		}
	}
	public int annaNumero(Ilmansuunta suunta) {
		switch (suunta) {
		case POHJOINEN: return 0;
		case ITA: return 1;
		case ETELA: return 2;
		case LANSI: return 3;
		default: return -1;
		}
	}

	public Ilmansuunta annaSuunta(int nro ) {
		switch (nro) {
		case 0: return POHJOINEN;
		case 1: return ITA;
		case 2: return ETELA;
		case 3: return LANSI;
		default: return null;
		}
	}

	/**
	 * @return Ilmansuunta joka on Ilmansuunnan this vasta-ilmansuunta
	 * */
	public Ilmansuunta vastakkainen(){

		return (this.vasen()).vasen();

	}
	public Ilmansuunta oikea(){
		if (this != null) {
			return (this.vastakkainen().vasen());
		}else{ 
			return null;
		}
	}

	public static void main(String args[]){
		Ilmansuunta suunta;
		suunta = POHJOINEN;
		System.out.println(suunta.vasen().vastakkainen());
	}

}
