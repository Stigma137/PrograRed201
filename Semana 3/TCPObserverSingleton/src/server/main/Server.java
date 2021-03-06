package server.main;

import java.util.Calendar;

import server.communication.TCPConnection;

public class Server implements TCPConnection.OnMessageListener{

	public static void main(String[] args) {
		Server server = new Server();
	}

	TCPConnection conexion;
	
	public Server() {
		conexion = TCPConnection.getInstance();
		conexion.setPuerto(5000);
		conexion.setObserver(this);
		conexion.waitForConnection();
		conexion.initReceiver();
		conexion.initSender();
		
		while( conexion.receiveMessage() ) {
			
		}
	}

	@Override
	public void onMessageReceived(String msg) {
		
		if(msg.equals(":DIS:")) {
			conexion.close();
			return;
		}
		//SimpleDateFormat("HH:mm:ss")
		conexion.sendMessage(Calendar.getInstance().getTime()+msg);
	}

}
