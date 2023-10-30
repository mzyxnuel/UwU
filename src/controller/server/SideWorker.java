package controller.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Method;
import model.Orders;
import model.Request;
import model.Response;


public class SideWorker extends Thread {
    private Socket conn;
    private Orders orders;
    private ObjectInputStream input;
	private ObjectOutputStream output;

    public SideWorker(Socket connectionRequest, Orders orders) {
		try {
            this.orders = orders;
			conn = connectionRequest;
			System.out.println("[server]: connection requested from: "+ conn.getInetAddress().toString() + ":" + conn.getPort());
			input = new ObjectInputStream(conn.getInputStream());
			output = new ObjectOutputStream(conn.getOutputStream());
			start();
		} catch (IOException e) { e.printStackTrace(); }
	}

    public void run() { 
        try {
            Request request = new Request(null);

            while(request.getMethod() != Method.END) {
                request = (Request) input.readObject();

                //LOGICA

                output.writeObject(new Response(Method.OK, request.getMethod().toString()));
            }

            output.writeObject(new Response(Method.END, "[server]: closing connection..."));
            System.out.println("[server]: connection ended: " + conn.getInetAddress().toString() + ":" + conn.getPort());
            conn.close(); 
        } catch (IOException e) { 
            System.out.println("[server]: client unexpectedly disconnected"); 
        } catch (ClassNotFoundException e) {
            System.out.println("[server]: corrupted class readed from client!");
        } 
    }    
}
