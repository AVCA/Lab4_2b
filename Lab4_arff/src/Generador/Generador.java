package Generador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import Archivo.BMP;
import GUI.*;

public class Generador implements ActionListener {

	// V A R I A B L E S:
	GUI gui;
	String direccion;
	ArrayList<String> clases;
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
		clases = new ArrayList<String>();
		// Metodo que obtendra la lista que almacenara los nombres de las fotos
		fotos();
		// Metodo que organizara los nombres de las fotos en listas y ademas
		// obtendra la clase de cada persona segun el nombre del archivo
		bmp_listas();
		// Metodo para obtener info
		bmp_info();
	}

	// Metodo que obtendra la lista que almacenara los nombres de las fotos
	public void fotos() {
		// Objeto File permite obtener los archivos que contenga
		// el directorio
		File directorio = new File(direccion);
		// Array que almacena los directorios de los archivos
		// que se encuentran dentro de la carpeta seleccionada
		String[] ficheros = directorio.list();
		// Lista temporal que almacenara los bmps por persona
		ArrayList bmp = new ArrayList<String>();
		// Contador de bmps por persona
		int j = 0;
		for(int k=0;k<ficheros.length;k++) {
		}
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
				if(i== ficheros.length-1) {
					direccion_bmp.add(bmp);
				}
			} else {
				// Se llego al maximo de bmps por persona
				// por lo que se agrega la lista temporal a la
				// lista que almacena los bmps por persona
				direccion_bmp.add(bmp);
				// Inicializamos la lista temporal
				bmp = new ArrayList<String>();
				// Reiniciamos el contador de bmps por persona
				j = 0;i--;
			}

		}
	}

	// Metodo que obtendra la lista de archivos bmp y sus clases
	public void bmp_listas() {
		// Almacenamos los nombres de los archivos en una lista y ademas
		// obtenemos el valor de la clase por persona
		for(int i=0;i<direccion_bmp.size();i++) {
			// Lista temporal que almacenara los bmps por persona
			ArrayList bmp = new ArrayList<String>();
			// Obtengo su clase a partir del nombre del archivo
			// Esto nos limita a que las imagenes deban de respetar
			// el formato
			String x = direccion_bmp.get(i).get(0).charAt(1)+"";
			// Se debe verificar si la clase esta compuesta por mas
			// de un digito:
			for(int j=2; j<direccion_bmp.get(i).get(0).length(); j++) {
				// Tomamos el siguiente caracter
				char y = direccion_bmp.get(i).get(0).charAt(j);
				// Si es un digito lo concatenamos
				if(Character.isDigit(y)) {
					x+=y;
				}
				else // En caso contrario ya obtuvimos la clase
					break;
			}
			// Mostramos los datos obtenidos
			//System.out.println("Clase: ["+x+"]"+direccion_bmp.get(i));
			// Almacenamos la clase de dicha persona
			clases.add(x);
		}
	}
	
	
	public void bmp_info() {
		// Ciclo por persona
		for(int i=0;i<1;i++) {
			// Ciclo por foto
			for(int j=0;j<1;j++) {
				String directorio = direccion+"\\"+direccion_bmp.get(i).get(j);
				BMP archivo = new BMP();
				File file = new File(directorio);
				System.out.println("Persona: "+clases.get(i)+"| Foto: "+direccion_bmp.get(i).get(j));
				archivo.Bytes(file); // <-- Primero lee los bytes
				archivo.Info();// <-- Imprime la informacion de la cabecera
				//archivo.ventana(file);// <-- Crea un Frame para mostrar el BMP
				//archivo.Canvas();// <-- Crea un Canvas que sirve para pintar pixeles
			}
		}
	}
}
