package netcode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ia.InputsManagerIA;
import renderer.MasterRenderer;

public class Server extends Thread{
	
	private ServerSocket ServerSocket;
	private Socket comm;
	private InputsManagerIA iAinputs;
	
	public Server(InputsManagerIA iAinputs) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("[SERVER]: Lancement du serveur");
		
		try {
			ServerSocket = new ServerSocket(3009); //Close depuis un autre thread pour couper le .accept()
			MasterRenderer.updateNetworkStatus(StatusID.WAITING);
			ServerSocket.accept();
			MasterRenderer.updateNetworkStatus(StatusID.CONNECTED);
		} catch (IOException e) {
			e.printStackTrace();
			MasterRenderer.updateNetworkStatus(StatusID.ERROR);

		}

	}


}
