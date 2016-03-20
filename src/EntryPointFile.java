import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class EntryPointFile {
/*
	static SerialPort port;
	static CommPortIdentifier basicPort;
	static BufferedReader input;
	static OutputStream output;
*/


	public static void main(String [] args){
		
		System.out.println("Testing With RXTX");


		try{
			/*
			basicPort = CommPortIdentifier.getPortIdentifier("COM3");
			port =(SerialPort) basicPort.open("",1);


			port.setSerialPortParams(9600, 
					SerialPort.DATABITS_8, 
					SerialPort.STOPBITS_1, 
					SerialPort.PARITY_NONE);


			output = port.getOutputStream();
			input = new BufferedReader(new InputStreamReader(port.getInputStream()));
			port.addEventListener(new Serial());
			port.notifyOnDataAvailable(true);
*/
			SonarPingModel spm = new SonarPingModel("COM3");
			Scanner in = new Scanner(System.in);
			while(true){
				System.out.print("Enter 1 or 0:");
				String x = in.nextLine();
				spm.toggle(Integer.parseInt(x));
				
			}
		}

		catch(Exception ex){
			
			ex.printStackTrace();
		}



	}


	/*public static class Serial implements SerialPortEventListener{

		@Override
		public void serialEvent(SerialPortEvent event) {
			try{
				if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE){
					JOptionPane.showMessageDialog(null, input.readLine());

				}
			}

			catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}

		}

	}*/
}


