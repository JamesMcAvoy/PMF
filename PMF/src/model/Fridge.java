package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;



public class Fridge {
	private int internalTemp;
	private int externalTemp;
	private int internalHygro;
	private int externalHygro;
	private int maxTemp;
	
	public Fridge(int intTemp, int extTemp, int intHygro, int extHygro) {
		this.internalTemp=intTemp;
		this.externalTemp=extTemp;
		this.internalHygro=intHygro;
		this.externalHygro=extHygro;
		this.calcMaxTemp();
	}

	private void calcMaxTemp() {
		// TODO
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
