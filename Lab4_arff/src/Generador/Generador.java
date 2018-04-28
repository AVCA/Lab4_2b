package Generador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import GUI.*;

public class Generador implements ActionListener {

	// V A R I A B L E S:
	GUI gui;
	String direccion;
	ArrayList<ArrayList<String>> direccion_bmp;
	int n;

	public Generador(GUI x) {
		super();
		gui = x;
	}

	// Metodo que inicia el proceso:
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Inicializamos variables
		direccion = gui.txt_direccion.getText();
		n = Integer.parseInt(gui.txt_n.getText());
		direccion_bmp = new ArrayList<ArrayList<String>>();
		// Metodo que obtendra la lista que almacenara los nombres de las fotos
		fotos();
		// Metodo que obtendra la informacion necesaria de cada bmp
		bmp_lectura();
	}

	// Metodo que obtendra la lista que almacenara los nombres de las fotos
	public void fotos() {
		// Objeto File permite obtener los archivos que contenga
		// el directorio
		File directorio = new File(direccion);
		// Array que almacena los directorios de los archivos
		// que se encuentran dentro de la carpeta seleccionada
		File[] ficheros = directorio.listFiles();
		Arrays.sort(ficheros);
		for(int i=0;i<ficheros.length;i++)
		{
			System.out.println(ficheros[i]);
		}
		System.out.println("======================");
		// Lista temporal que almacenara los bmps por persona
		ArrayList bmp = new ArrayList<String>();
		// Contador de bmps por persona
		int j = 0;
		// Recorremos todos los ficheros
		for (int i = 0; i < ficheros.length; i++) {
			// Mientras que el contador de bmps por persona
			// sea menor a lo establecido almacenamos el nombre
			// de la imagen
			if (j < n) {
				// Agregamos el nombre del bmp a la lista temporal
				bmp.add(ficheros[i]);
				// Aumentamos el contador de bmps por persona
				j++;
			} else {
				// Se llego al maximo de bmps por persona
				// por lo que se agrega la lista temporal a la
				// lista que almacena los bmps por persona
				direccion_bmp.add(bmp);
				// Inicializamos la lista temporal
				bmp = new ArrayList<String>();
				// Reiniciamos el contador de bmps por persona
				j = 0;
			}

		}
	}

	// Metodo que obtendra la informacion necesaria de cada bmp
	public void bmp_lectura() {
		// Bmps por persona:
		for(int i=0;i<direccion_bmp.size();i++) {
			System.out.println(direccion_bmp.get(i));
		}
	}
	
}
