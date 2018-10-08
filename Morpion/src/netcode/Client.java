package netcode;

import java.io.IOException;
import java.net.Socket;

import ia.InputsManagerIA;

public class Client extends Thread{
	
	private Socket comm;
	private String ip;
	private InputsManagerIA iAinputs;
	
	public Client(String ip, InputsManagerIA iAinputs) {
		this.ip = ip;
		this.iAinputs = iAinputs;
	}
	
	@Override
	public void run() {
		System.out.println("[CLIENT]: Connection à : "+ip);
		
		try {
			comm = new Socket(ip,3009);
			System.out.println("[CLIENT]: Etat du socket : " + comm.isConnected());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}