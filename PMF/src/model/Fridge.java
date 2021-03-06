package model;



public class Fridge {
	
	private static double a=17.271;	// Constantes pour calculer le point de rosée
	private static double b=237.7;	// Valables pour des températures entre 0 et 60°C
	
	private double internalTemp;
	private double externalTemp;
	private double internalHygro;
	private double externalDiode;
	private FridgeArrays fridgeArrays;
	private double maxTemp;
	private double tempConsigne;
	private boolean isFanOn;
	
	public Fridge() {
		this.internalTemp=0;
		this.externalTemp=0;
		this.internalHygro=0;
		this.externalDiode=0;
		this.tempConsigne=20;
		this.isFanOn=false;
		this.fridgeArrays=new FridgeArrays();
		this.maxTemp=this.calcMaxTemp();
	}
	
	/**
	public Fridge(double intTemp, double extTemp, double intHygro, double extHygro) {
		this.internalTemp=intTemp;
		this.externalTemp=extTemp;
		this.internalHygro=intHygro;
		this.externalDiode=extHygro;
	
		this.maxTemp=this.calcMaxTemp();
	}
	*/

	// calcule la température maximale permettant d'éviter la condensation
	private double calcMaxTemp() {
		if(this.internalTemp<0 || this.internalTemp>60) {
			// Envoyer une erreure de type température hors de range.
			return 0;
		}else {
			return (b*valIntermediaire(this.internalTemp, this.internalHygro))/(a-valIntermediaire(this.internalTemp, this.internalHygro));
		}
	}
	private static double valIntermediaire(double temp, double hum) {
		return (a*temp/(b+temp))+Math.log(hum/100.0);
	}
	
	// Envoie un tableau à la vue pour afficher les graphiques
	public void updateArrays() {
		this.maxTemp=this.calcMaxTemp();
		this.fridgeArrays.updateArrays(this.externalTemp, this.internalTemp, this.internalHygro);
		// ENVOYER THIS.FRIDGEARRAYS ET LES VALEURS ACTUELLES A LA VUE
		
	}
	
	
	
	// getters and setters
	
	public double getInternalTemp() {
		return internalTemp;
	}

	public void setInternalTemp(double internalTemp) {
		this.internalTemp = internalTemp;
	}

	public double getExternalTemp() {
		return externalTemp;
	}

	public void setExternalTemp(double externalTemp) {
		this.externalTemp = externalTemp;
	}

	public double getInternalHygro() {
		return internalHygro;
	}

	public void setInternalHygro(double internalHygro) {
		this.internalHygro = internalHygro;
	}

	public double getExternalDiode() {
		return externalDiode;
	}

	public void setExternalDiode(double externalDiode) {
		this.externalDiode = externalDiode;
	}
	
	public double getMaxTemp() {
		return maxTemp;
	}

	public FridgeArrays getFridgeArrays() {
		return fridgeArrays;
	}
	public void setTempConsigne(double tempConsigne) {
		this.tempConsigne=tempConsigne;
	}
	public double getTempConsigne() {
		return tempConsigne;
	}

	public boolean isFanOn() {
		return isFanOn;
	}

	public void setFanOn(boolean isFanOn) {
		this.isFanOn = isFanOn;
	}
	
}
