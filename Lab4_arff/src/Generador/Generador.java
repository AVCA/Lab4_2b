package Generador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.*;

public class Generador implements ActionListener {

	// V A R I A B L E S:
	GUI gui;
	String direccion;

	public Generador(GUI x) {
		super();
		gui = x;
		direccion = x.txt_direccion.getText();
	}
	
	// Metodo que inicia el proceso:
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Tomar una foto por persona
		
	}

}
