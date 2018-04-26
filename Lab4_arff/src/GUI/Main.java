package GUI;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		//Metodo inicial:
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Crea interfaz grafica:
					GUI frame = new GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}


}
