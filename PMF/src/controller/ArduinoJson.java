package controller;

public class ArduinoJson {
	private int error;
	private int dht22Temp;
	private int dht22Hygro;
	private int diode;
	
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public int getDht22Temp() {
		return dht22Temp;
	}
	public void setDht22Temp(int dht22Temp) {
		this.dht22Temp = dht22Temp;
	}
	public int getDht22Hygro() {
		return dht22Hygro;
	}
	public void setDht22Hygro(int dht22Hygro) {
		this.dht22Hygro = dht22Hygro;
	}
	public int getDiode() {
		return diode;
	}
	public void setDiode(int diode) {
		this.diode = diode;
	}
	
}
