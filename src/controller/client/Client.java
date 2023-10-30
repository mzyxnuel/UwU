package controller.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

import model.Method;
import model.Order;
import model.Request;
import model.Response;

public class Client {
    private Socket conn = null;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Response response;
	private String clientID = generateClientID();
	
	public Client() {
        connectToServer();

		try {
            output = new ObjectOutputStream(conn.getOutputStream());
            input = new ObjectInputStream(conn.getInputStream());
            response = new Response(null);
        } catch (Exception e) { e.printStackTrace(); } 
	}

    private void connectToServer() {
        boolean connected = false;
		while (!connected) {
			try {  
				conn = new Socket(InetAddress.getLocalHost(), 8080);
				System.out.println(conn);
				connected = true;
			} catch (IOException e) {
				System.out.println("[client]: connection failed, restarting...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			} 
		}
    }

	private String generateClientID() {
        return UUID.randomUUID().toString();
	}

	public void sendRequest(Request request) {
        try {
            output.writeObject(request);
            while (response.getMethod() != Method.END) {
                response = (Response) input.readObject();
                System.out.println(response.getMessage());
            };
            System.out.println("[client]: connection closed...");
            input.close();
            output.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) { e.printStackTrace(); } 
    }

}