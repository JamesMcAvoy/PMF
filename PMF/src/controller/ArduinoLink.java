package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import model.Fridge;
import model.Error;
import view.MainFrame;

import java.util.Arrays;
import java.util.Enumeration;
import com.google.gson.*;

public class ArduinoLink implements SerialPortEventListener {
	
	private static Gson gson = new Gson();
	private Fridge fridge;
	private MainFrame frame;
	
	
	private SerialPort serialPort;
    /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
		"/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyACM0", // Raspberry Pi
		"/dev/ttyUSB0", // Linux
		"COM3", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
		CommPortIdentifier portId=null;
		Enumeration portEnum=CommPortIdentifier.getPortIdentifiers();
		
		
		// Initialise la vue
		this.frame = new MainFrame();
		
		
		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}
		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
	
			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE
			);
	
			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();
	
			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			
			
			
			// create the fridge
			this.fridge = new Fridge();
			
			
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	
	@Override
	// handle an event on the serial port
	public synchronized void serialEvent (SerialPortEvent oEvent) {
		
		if(oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				
				System.out.print(inputLine+"\n");
				
				// parse json
				
				ArduinoJson arduinoJson = gson.fromJson(inputLine, ArduinoJson.class);
				
				if(arduinoJson.getError()==0) {
					this.fridge.setInternalHygro(arduinoJson.getDht22Hygro());
					this.fridge.setInternalTemp(arduinoJson.getDht22Temp());
					this.fridge.setExternalTemp(arduinoJson.getDiodeExt());
					this.fridge.setExternalDiode(arduinoJson.getDiodeTemp());
					
					this.fridge.updateArrays();
					
					System.out.println("array temp ext: "+Arrays.toString(this.fridge.getFridgeArrays().getExtTempArray()));
					System.out.println("array temp int: "+Arrays.toString(this.fridge.getFridgeArrays().getIntTempArray()));
					System.out.println("array hygro int: "+Arrays.toString(this.fridge.getFridgeArrays().getIntHygroArray()));
					
					this.frame.updateChart(this.fridge);
					this.frame.updateValues(this.fridge);
					
				} else {
					Error err=new Error(arduinoJson.getError());
					frame.errorPopUp(err.getErrStrAct());
					
				}
				
				
			} catch(Exception e) {
				frame.errorPopUp(e.getMessage());
			}
		}
	}
	
	// Ces 3 fonctions servent à envoyer les données à l'Arduino
	public void setConsigne(double consigne) {
		this.fridge.setTempConsigne(consigne);
		sendJsonToArduino();
	}
	public void switchFan() {
		this.fridge.setFanOn(this.fridge.isFanOn()?false:true);
		sendJsonToArduino();
	}
	private void sendJsonToArduino() {
		System.out.println("hey");
		String strToSend="{\"tempConsigne\":"+Double.toString(this.fridge.getTempConsigne())+", \"ventiloConsigne\":"+(this.fridge.isFanOn()==true?"1":"0")+"}";
		try {
			output.write(strToSend.getBytes());
		} catch (IOException e) {
			frame.errorPopUp(e.getMessage());
		}
	}
}
