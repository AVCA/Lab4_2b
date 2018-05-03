package Archivo;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BMP {
	// V A R I A B L E S
	int ancho, largo;

	byte[] bytes; // Arreglo que almacena los bytes que contiene el bmp
	String[] hexa; // Arreglo que contiene los bytes convertidos en hexadecimal
	ArrayList<ArrayList<Integer>> pixel;
	ArrayList<ArrayList<ArrayList<Integer>>> cuadrantes;

	Frame ventana;
	Panel panel;

	// Metodo: Lee el archivo y almacena RGB, BYTE y STRING
	public void Bytes(File file) {
		int i = 0;
		FileInputStream archivo = null;
		bytes = new byte[(int) file.length()];
		hexa = new String[(int) file.length()];
		try {

			// Lee el archivo y guarda los BYTES en el vector
			archivo = new FileInputStream(file);
			archivo.read(bytes);// <-- Lee y guarda cada byte
			archivo.close();

			// Convertimos los BYTES en HEXADECIMAL
			while (true) {
				// Se almacenan en String
				String a = String.format("%02X", bytes[i]); // <--String.format me deja convertir BYTE a Hexa gracias a
															// "%02X", tambien se puede pasar a decimal
				hexa[i] = a; // <-- Se almacena en un arreglo String
				i++;
			}

		} catch (Exception e) {
			// Manejar Error
		}
	}

	// Metodo: Imprime los valores de la cabezera
	public void Info() {
		String a = new String(new byte[] { bytes[0] });
		String b = new String(new byte[] { bytes[1] });
		String r;
		System.out.println("Inicio de la imagen: " + String.format("%02d", bytes[10] & 0xFF) + " byte");
		r = hexa[21] + hexa[20] + hexa[19] + hexa[18];
		ancho = Integer.parseInt(r, 16);
		System.out.println("Ancho de la imagen: " + Integer.parseInt(r, 16) + " pixeles");
		r = hexa[25] + hexa[24] + hexa[23] + hexa[22];
		largo = Integer.parseInt(r, 16);
		System.out.println("Alto de la imagen: " + Integer.parseInt(r, 16) + " pixeles");
		rgb();
		calculos();
	}

	public void rgb() {
		pixel = new ArrayList<ArrayList<Integer>>();

		int i = ((int) bytes.length) - 1; // <-Total de bytes
		for (int x = 0; x < largo; x++) {
			for (int y = ancho; y > 0; y--) {
				ArrayList<Integer> RGB = new ArrayList<Integer>();
				// Empieza a leer desde el ultimo byte hasta el inicio
				// De lo contrario la imagen se mostraria alrevez
				int blue = Integer.parseInt(hexa[i--], 16);
				RGB.add(blue);
				int green = Integer.parseInt(hexa[i--], 16);
				RGB.add(green);
				int red = Integer.parseInt(hexa[i--], 16);
				RGB.add(red);
				pixel.add(RGB);
			}
		}
		System.out.println("=============================");
		System.out.println("Colores RGB:");
		cuadrante(0);
	}

	public void cuadrante(int i) {
		int pixel_cuadrante = pixel.size() / 4; // Total de pixeles por cuadrante
		cuadrantes = new ArrayList<ArrayList<ArrayList<Integer>>>(); // Lista que almacena las listas de pixeles por cuadrante
		ArrayList<ArrayList<Integer>> cuadrante = new ArrayList<ArrayList<Integer>>(); // Lista de pixeles
		// Rangos que definiran que pixeles tomar:
		// Variables que ocupo:
		// int ancho
		// int largo
		int ancho_mitad = ancho/2;
		int largo_mitad = largo/2;
		int a = 0, b = 0, d = 0, e = 0;
		switch(i) {
		case 0:
			a = 0;
			b = 0;
			d = largo;
			e = ancho;
			break;
		case 1:
			a = 0;
			b = ancho;
			d = largo;
			e = ancho_mitad;
			break;
		case 2:
			a = largo;
			b = 0;
			d = largo_mitad;
			e = ancho;
			break;
		case 3:
			a = largo;
			b = ancho;
			d = largo_mitad;
			e = ancho;
			break;
		}
		
		int redacum = 0, blueacum = 0, greenacum = 0;
		double redvar = 0, greenvar = 0, bluevar = 0;
		
		for (int y = a; y < d; y++) {
			for (int z = b; z < e; z++) {
				
				// int red = c.getRed();
				// int green = c.getGreen();
				// int blue = c.getBlue();
				//redacum += red;
				//blueacum += blue;
				//greenacum += green;
			}
		}

		//int mediared = redacum / (ancho1 * alto1);
		//int mediagreen = greenacum / (ancho1 * alto1);
		//int mediablue = blueacum / (ancho1 * alto1);
	}

	public void calculos() {
		int x = pixel.size() / 4;
		for (int i = 0; i < 1; i++) {
			System.out.println("=============================");
			System.out.println("Cuadrante " + i + ":");
			double r = 0, g = 0, b = 0;
			double mr = 0, mg = 0, mb = 0;
			for (int j = 0; j < cuadrantes.get(i).size(); j++) {
				// System.out.println(j + ": " + cuadrantes.get(i).get(j));
				r += cuadrantes.get(i).get(j).get(0);
				g += cuadrantes.get(i).get(j).get(1);
				b += cuadrantes.get(i).get(j).get(2);
			}
			mr = r / x;
			mg = g / x;
			mb = b / x;
			System.out.println(mr + "," + mg + "," + mb + ",");
		}
		// Media

		// Desviacion estandar
	}

	// Metodo: Crea la interfaz que mostrara la imagen
	public void ventana(File file) {
		ventana = new Frame(file.getName());
		ventana.setSize(ancho + 25, largo + 45);
		ventana.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		panel = new Panel();

		ventana.add(panel);
		ventana.setVisible(true);
	}

	// Metodo: Base para pintar la imagen
	public void Canvas() {
		panel.add(new MyCanvas());
		ventana.setVisible(true);
	}

	// Metodo: Pinta cada pixel del canvas con los RGB del archivo
	public class MyCanvas extends Canvas {

		public MyCanvas() {
			setSize(ancho, largo);
		}

		public void paint(Graphics g) {
			Graphics2D g2;
			g2 = (Graphics2D) g;
			int i = ((int) bytes.length) - 1; // <-Total de bytes que mostrare
			for (int x = 0; x < largo; x++) {
				for (int y = ancho; y > 0; y--) {
					// Empieza a leer desde el ultimo byte hasta el inicio
					// De lo contrario la imagen se mostraria alrevez
					int blue = Integer.parseInt(hexa[i--], 16);
					// i--; //<-- Sin esto no lee el siguente byte
					int green = Integer.parseInt(hexa[i--], 16);
					int red = Integer.parseInt(hexa[i--], 16);
					// Asignamos el color rgb con los ints
					g.setColor(new Color(blue, green, red));
					// Dibujamos el pixel con las coordenadas de y x
					// Tambien puede usarse g.drawRect(y,x,1,1) y muestra el
					// mismo resultado
					g.drawRect(y, x, 1, 1);
				}
			}

		}
	}

}
