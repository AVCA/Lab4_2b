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
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("datos.txt");
			pw = new PrintWriter(fichero);
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
			System.out.println("Colores RGB por ooooo:");
			int pixel_cuadrante = pixel.size() / 4;
			int p_c = pixel_cuadrante;
			int k = 0;
			cuadrantes = new ArrayList<ArrayList<ArrayList<Integer>>>();
			ArrayList<ArrayList<Integer>> cuadrante = new ArrayList<ArrayList<Integer>>();
			for (int j = 0; j < pixel.size(); j++) {
				if (j == 0) {
					System.out.println("=============================");
					System.out.println("Cuadrante :" + k);
					k++;
					cuadrante = new ArrayList<ArrayList<Integer>>();
				} else {
					if (j == pixel_cuadrante) {
						cuadrantes.add(cuadrante);
						pixel_cuadrante += p_c;
						cuadrante = new ArrayList<ArrayList<Integer>>();
						System.out.println("=============================");
						System.out.println("Cuadrante :" + k);
						k++;
					}
				}
				cuadrante.add(pixel.get(j));
				System.out.println(pixel.get(j).get(0));
				pw.println(pixel.get(j).get(0));

				if (j == pixel.size() - 1) {
					cuadrantes.add(cuadrante);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
