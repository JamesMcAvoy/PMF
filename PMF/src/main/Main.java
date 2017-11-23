package main;

import controller.ArduinoLink;
//import view.MainFrame;

public final class Main {
	
	public static ArduinoLink arduinoLink;	// Pour pouvoir accÃ©der au controller depuis la vue

	public static void main(String[] args) {
		// MainFrame frame = new MainFrame();	/La vue est initialisÃ©e dans le controller
		arduinoLink = new ArduinoLink();
		arduinoLink.initialize();
		
		//Thread t=new Thread() {
			/**
			* lancer la vue, qui va lancer le controlleur et garder l'app active?
			* le tuto dit :
			* public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			* }
			*/
		/*};
		t.start();
		System.out.println("started");*/
	}

}
