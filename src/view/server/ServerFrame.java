package view.server;
import javax.swing.*;

import control.server.ServerController;
import model.Order;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ServerFrame extends JFrame {
   private ServerController server;
   private HashMap<String, Order> orders;

   public ServerFrame(ServerController server) {
       this.server = server;
       this.orders = server.getOrders().getAll();

       // Creazione del pulsante
        JButton print = new JButton("Print");

        // Add action listeners to the buttons
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    printOrders();
                }
        });

  
        // Add the buttons to the frame
        add(print);

        // Set the layout of the buttons
        setLayout(new FlowLayout());

        // Set the size of the frame
        setSize(300, 100);

        // Make the frame visible
        setVisible(true);
    }

    private void printOrders() {
        orders.forEach((key, order) -> {
            System.out.println("Ordine con chiave: " + key);
            System.out.println("Ordine: " + order);
        });
    }
}
