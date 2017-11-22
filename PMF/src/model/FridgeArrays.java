package model;

class FridgeArrays {
	private static int arrayLength=20;
	private double[] intTempArray = new double[arrayLength];
	private double[] extTempArray = new double[arrayLength];
	private double[] intHygroArray = new double[arrayLength];
	
	private static double[] shiftArray(double[] array, double valueToAdd) {
		for(int i=arrayLength; i>0; i--) {
			array[i-1]=array[i];
		}
		array[arrayLength]=valueToAdd;
		return array;
	}
	
	public void updateArrays(double extTemp, double intTemp, double intHygro) {
		this.intTempArray=shiftArray(this.intTempArray, intTemp);
		this.extTempArray=shiftArray(this.extTempArray, extTemp);
		this.intHygroArray=shiftArray(this.intHygroArray, intHygro);
	}
}
