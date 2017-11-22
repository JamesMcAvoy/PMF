package model;



public class Fridge {
	private double internalTemp;
	private double externalTemp;
	private double internalHygro;
	private double externalHygro;
	private FridgeArrays fridgeArrays;
	private double maxTemp;
	
	public Fridge() {
		this.internalTemp=0;
		this.externalTemp=0;
		this.internalHygro=0;
		this.externalHygro=0;
		this.maxTemp=this.calcMaxTemp();
	}
	
	/**
	public Fridge(double intTemp, double extTemp, double intHygro, double extHygro) {
		this.internalTemp=intTemp;
		this.externalTemp=extTemp;
		this.internalHygro=intHygro;
		this.externalHygro=extHygro;
	
		this.maxTemp=this.calcMaxTemp();
	}
	*/

	// calcule la température maximale permettant d'éviter la condensation
	private double calcMaxTemp() {
		if(this.internalTemp<0 || this.internalTemp>60) {
			// Envoyer une erreure de type température hors de range.
			return 0;
		}else {
			return this.maxTemp=237.7*valIntermediaire(this.internalTemp, this.internalHygro)/(17.27-valIntermediaire(this.internalTemp, this.internalHygro));
		}
	}
	private static double valIntermediaire(double temp, double hum) {
		return 17.27*temp/(237.7+temp) + Math.log(hum);
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

	public double getExternalHygro() {
		return externalHygro;
	}

	public void setExternalHygro(double externalHygro) {
		this.externalHygro = externalHygro;
	}
	
	public double getMaxTemp() {
		return maxTemp;
	}
	
}
