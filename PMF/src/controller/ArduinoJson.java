package controller;

public class ArduinoJson {
	private int error;
	private double dht22Temp;
	private double dht22Hygro;
	private double diode;
	
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public double getDht22Temp() {
		return dht22Temp;
	}
	public void setDht22Temp(double dht22Temp) {
		this.dht22Temp = dht22Temp;
	}
	public double getDht22Hygro() {
		return dht22Hygro;
	}
	public void setDht22Hygro(double dht22Hygro) {
		this.dht22Hygro = dht22Hygro;
	}
	public double getDiode() {
		return diode;
	}
	public void setDiode(double diode) {
		this.diode = diode;
	}
	
}
