package model;

public class FridgeArrays {
	private static int arrayLength=20;
	private double[] intTempArray = new double[arrayLength];
	private double[] extTempArray = new double[arrayLength];
	private double[] intHygroArray = new double[arrayLength];
	
	private static double[] shiftArray(double[] array, double valueToAdd) {
		for(int i=arrayLength-1; i>0; i--) {
			array[i-1]=array[i];
		}
		array[0]=valueToAdd;
		return array;
	}
	
	public void updateArrays(double extTemp, double intTemp, double intHygro) {
		this.intTempArray=shiftArray(this.intTempArray, intTemp);
		this.extTempArray=shiftArray(this.extTempArray, extTemp);
		this.intHygroArray=shiftArray(this.intHygroArray, intHygro);
	}

	public double[] getIntTempArray() {
		return intTempArray;
	}

	public void setIntTempArray(double[] intTempArray) {
		this.intTempArray = intTempArray;
	}

	public double[] getExtTempArray() {
		return extTempArray;
	}

	public void setExtTempArray(double[] extTempArray) {
		this.extTempArray = extTempArray;
	}

	public double[] getIntHygroArray() {
		return intHygroArray;
	}

	public void setIntHygroArray(double[] intHygroArray) {
		this.intHygroArray = intHygroArray;
	}
	
}
