import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

 /**
 * @author Jupiter
 * 
 *
 */
public class SonarPingModel {
	SerialPort port;
	CommPortIdentifier basicPort;
	static BufferedReader input;
	static OutputStream output;

	SonarPingModel(String comPortName){
		try{
			//Identifying the port
			this.basicPort = CommPortIdentifier.getPortIdentifier(comPortName);
			this.port = (SerialPort) this.basicPort.open("", 1);
			
			//Port Settings
			port.setSerialPortParams(9600, 
					SerialPort.DATABITS_8, 
					SerialPort.STOPBITS_1, 
					SerialPort.PARITY_NONE);
			
			//Input and Output Streams to write to the port
			output = port.getOutputStream();
			input = new BufferedReader(new InputStreamReader(this.port.getInputStream()));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//Switching between the added event listener
	public void toggle(int x){
		
		if(x == 1){
			this.createConnection();
		}else if(x == 0){
			this.breakConnection();
		}
	}
	
	//Adds the event listener
	private void createConnection(){
		try{
			this.port.addEventListener(new Serial());
			this.port.notifyOnDataAvailable(true);
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//Removes the event listener
	private void breakConnection(){
		try{
			this.port.removeEventListener();
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static class Serial implements SerialPortEventListener{

		public synchronized void serialEvent(SerialPortEvent event) {
			try{
				
				// On the Arrivial of a new Value
				// Create a new SonarPingEmailModel as a new Thread and run it 
				if(event.getNewValue()){
					
					try{
						String x = input.readLine();
						if(x.equals("Motion Comming From Main Door!")){
							MongoModel model = new MongoModel();
							String emails = model.getAllEmails();
							SonarPingEmailModel runner = new SonarPingEmailModel(emails, 
									"john.orion.ray@gmail.com", "john.orion.ray@gmail.com", "phantom1237");
							runner.sessionInitialize();
							runner.run();
							
						}
					
						
					}
					
					catch(Exception ex){
						ex.printStackTrace();
					}
	
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
