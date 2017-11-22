package model;



public class Fridge {
	private int internalTemp;
	private int externalTemp;
	private int internalHygro;
	private int externalHygro;
	private FridgeArrays fridgeArrays;
	private int maxTemp;
	
	public Fridge() {
		this.internalTemp=0;
		this.externalTemp=0;
		this.internalHygro=0;
		this.externalHygro=0;
		this.calcMaxTemp();
	}
	
	/**
	public Fridge(int intTemp, int extTemp, int intHygro, int extHygro) {
		this.internalTemp=intTemp;
		this.externalTemp=extTemp;
		this.internalHygro=intHygro;
		this.externalHygro=extHygro;
		this.calcMaxTemp();
	}
	*/

	// calcule la température maximale permettant d'éviter la condensation
	private void calcMaxTemp() {
		// TODO
	}
	
	// Envoie un tableau à la vue pour afficher les graphiques
	public void updateArrays() {
		this.calcMaxTemp();
		this.fridgeArrays.updateArrays(this.externalTemp, this.internalTemp, this.internalHygro);
	}
	
	// getters and setters
	
	public int getInternalTemp() {
		return internalTemp;
	}

	public void setInternalTemp(int internalTemp) {
		this.internalTemp = internalTemp;
	}

	public int getExternalTemp() {
		return externalTemp;
	}

	public void setExternalTemp(int externalTemp) {
		this.externalTemp = externalTemp;
	}

	public int getInternalHygro() {
		return internalHygro;
	}

	public void setInternalHygro(int internalHygro) {
		this.internalHygro = internalHygro;
	}

	public int getExternalHygro() {
		return externalHygro;
	}

	public void setExternalHygro(int externalHygro) {
		this.externalHygro = externalHygro;
	}
	
	public int getMaxTemp() {
		return maxTemp;
	}
	
}
