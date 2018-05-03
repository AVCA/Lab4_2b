package Generador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import Archivo.ARFF;
import Archivo.BMP;
import GUI.*;

public class Generador implements ActionListener {

	// V A R I A B L E S:
	GUI gui;
	ARFF fotos;
	String direccion;
	ArrayList<ArrayList<String>> direccion_bmp;
	int n,n_t;

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
		fotos = new ARFF();
		// Metodo que obtendra la lista que almacenara los nombres de las fotos
		fotos();
		// Metodo para obtener info
		bmp_info();
		//
		int m = direccion_bmp.size();
		System.out.println("Generando rgbfotos.arff");
		System.out.println(".....");
		fotos.Lectura(m);
		System.out.println("Listo!");
	}

	// Metodo que obtendra la lista que almacenara los nombres de las fotos
	public void fotos() {
		// Objeto File permite obtener los archivos que contenga
		// el directorio
		File directorio = new File(direccion);
		// Array que almacena los directorios de los archivos
		// que se encuentran dentro de la carpeta seleccionada
		String[] ficheros = directorio.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".bmp");
			}
		});
		n_t = ficheros.length;
		//gui.txt_n_t.setText(n_t+"");
		if (ficheros.length != 0) {
			
			// Metodo que me permite ordenar los archivos
			List scrambled = Arrays.asList(ficheros);
			Collections.sort(scrambled, new NaturalOrderComparator());

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
					// System.out.println(ficheros[i]);
					// Aumentamos el contador de bmps por persona
					j++;
					if (i == ficheros.length - 1) {
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
					j = 0;
					i--;
				}

			}
			System.out.println("Archivos obtenidos");
			for (int k = 0; k < direccion_bmp.size(); k++) {
				System.out.println(k + ":" + direccion_bmp.get(k));
			}
		}
		else
		{
			System.out.println("ERROR: La carpeta no contiene ningun bmp");
		}
	}

	public void bmp_info() {
		// Crear los archivos
		archivos_txt();
		// Ciclo por persona
		for (int i = 0; i < direccion_bmp.size(); i++) {
			// Ciclo por foto
			// System.out.println("============================================");
			// System.out.println("Persona: " + i);
			for (int j = 0; j < direccion_bmp.get(0).size(); j++) {
				// System.out.println("------------------------------");
				// System.out.println("Foto: "+j);
				String directorio = direccion + "\\" + direccion_bmp.get(i).get(j);
				String bmp = direccion_bmp.get(i).get(j);
				// System.out.println("Cuadrante 0");
				ejecutar(directorio, bmp, "cuadrante0.txt", i);
				// System.out.println("Cuadrante 1");
				ejecutar(directorio, bmp, "cuadrante1.txt", i);
				// System.out.println("Cuadrante 2");
				ejecutar(directorio, bmp, "cuadrante2.txt", i);
				// System.out.println("Cuadrante 3");
				ejecutar(directorio, bmp, "cuadrante3.txt", i);
				// 
				BMP archivo = new BMP();
				File file = new File(directorio);
				archivo.Bytes(file); // <-- Primero lee los bytes
				archivo.Info();// <-- Imprime la informacion de la cabecera
				// archivo.ventana(file);// <-- Crea un Frame para mostrar el BMP
				// archivo.Canvas();// <-- Crea un Canvas que sirve para pintar pixeles
			}
		}
	}

	public void archivos_txt() {
		try {
			FileWriter fichero = new FileWriter("cuadrante0.txt");
			FileWriter fichero1 = new FileWriter("cuadrante1.txt");
			FileWriter fichero2 = new FileWriter("cuadrante2.txt");
			FileWriter fichero3 = new FileWriter("cuadrante3.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ejecutar(String directorio, String bmp, String txt, int i) {
		File file = new File(directorio);
		File file2 = new File(txt);
		BufferedImage image;
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			if (!file2.exists()) {
				file2.createNewFile();
			}
			fichero = new FileWriter(file2.getAbsoluteFile(), true);
			pw = new PrintWriter(fichero);
			image = ImageIO.read(file);
			int ancho1 = image.getWidth() / 2;
			int alto1 = image.getHeight() / 2;
			int ancho2 = image.getWidth();
			int alto2 = image.getHeight();

			int a = 0, b = 0, d = 0, e = 0;
			switch (txt) {
			case "cuadrante0.txt":
				a = 0;
				b = 0;
				d = alto1;
				e = ancho1;
				break;
			case "cuadrante1.txt":
				a = 0;
				b = ancho1;
				d = alto1;
				e = ancho2;
				break;
			case "cuadrante2.txt":
				a = alto1;
				b = 0;
				d = alto2;
				e = ancho1;
				break;
			case "cuadrante3.txt":
				a = alto1;
				b = ancho1;
				d = alto2;
				e = ancho2;
				break;
			}

			int redacum = 0, blueacum = 0, greenacum = 0;
			double redvar = 0, greenvar = 0, bluevar = 0;

			for (int y = a; y < d; y++) {
				for (int z = b; z < e; z++) {
					Color c = new Color(image.getRGB(z, y));
					int red = c.getRed(), green = c.getGreen(), blue = c.getBlue();
					redacum += red;
					blueacum += blue;
					greenacum += green;
				}
			}

			int mediared = redacum / (ancho1 * alto1);
			int mediagreen = greenacum / (ancho1 * alto1);
			int mediablue = blueacum / (ancho1 * alto1);

			for (int y = a; y < d; y++) {
				for (int z = b; z < e; z++) {
					Color c = new Color(image.getRGB(z, y));
					int red = c.getRed(), green = c.getGreen(), blue = c.getBlue();
					redvar += Math.pow((red - mediared), 2);
					greenvar += Math.pow((green - mediagreen), 2);
					bluevar += Math.pow((blue - mediablue), 2);
				}
			}

			redvar = Math.sqrt(redvar / (ancho1 * alto1));
			greenvar = Math.sqrt(greenvar / (ancho1 * alto1));
			bluevar = Math.sqrt(bluevar / (ancho1 * alto1));

			pw.println(mediared + "," + mediagreen + "," + mediablue + "," + redvar + "," + greenvar + "," + bluevar
					+ "," + i);
			// System.out.println(bmp + ": " + mediared + "," + mediagreen + "," + mediablue
			// + "," + redvar + ","
			// + greenvar + "," + bluevar + "," + i);

			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
