package Archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ARFF {
	// V A R I A B L E S:
	public String relation = ""; // Nombre
	public ArrayList<ArrayList<String>> class_attribute; // Clases de los atributos
	public ArrayList<String> attribute; // Atributos
	public ArrayList<ArrayList<String>> data; // Conjunto de datos
	public String d="";

	// T E M P O R A L E S:
	private ArrayList<String> clases; // Lista temporal para las clases
	private ArrayList<String> instancias;
	private String clase[]; // Arreglo temporal para las clases
	private String instancia[]; // Arreglo temporal para las instancias

	/* M E T O D O S:
	// Lectura del archivo ARFF:
	//
	// Estructura que debe de leer:
	// @relation nombre_del_conjunto
	// @attribute atributo {opcion1, opcion2, ...}
	// @data opcion1, ... , opcion_n
	// % comentario
	 * 
	 * */

	public void Lectura(String direccion) {
		d=direccion;
		data = new ArrayList<ArrayList<String>>();
		data.clear();
		class_attribute = new ArrayList<ArrayList<String>>();
		class_attribute.clear();
		attribute = new ArrayList<String>();
		attribute.clear();
		File archivo = new File(direccion);
		FileReader fr;
		try {
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while (!(linea = br.readLine()).matches("@data.*")) {
				relation(br, linea);
				attribute(br, linea);
			}
			while ((linea = br.readLine()) != null) {
				data(br,linea);
			}
			Collections.shuffle(data);
			//show();
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * Metodo que busca y almacena el nombre del conjunto de datos
	 */
	public void relation(BufferedReader br, String linea) {
		// Mientras no me tope con los datos:
		// Si la linea comienza con @Atributte:
		if (linea.matches("@relation.*"))
			// Elimino la palabra @relation y me quedo solo con el nombre
			relation = linea.replaceAll("@relation\\s", "");
	}

	/*
	 * Metodo que busca y almacena las clases y atributos del conjunto de datos
	 * recibido del archivo .arff
	 */
	public void attribute(BufferedReader br, String linea) {
		// Si la linea comienza con @Atributte:
		if (linea.matches("@attribute.*")) {
			// Tomamos la linea entera:
			String name = linea.replaceAll("@attribute", "");
			// Eliminamos todo lo que no sea el nombre del atributo
			name = name.replaceAll(".[{].*[}]", "");
			// Eliminamos espacios
			name = name.replaceAll(" ", "");
			// Guardamos el atributo en la lista attribute
			attribute.add(name);
		}
		// Una vez almacenado el atributo continuaremos con sus clases
		// las clases se encontraran dentro de { }
		if (linea.matches(".*[{].*[}]")) {
			// Tomamos la linea entera y la dividimos por los { }
			// Esto se almacenara en un arreglo indefinido clase[]
			// que contendra las clases temporalmente
			// Ejemplo: {sunny, overcast, rainy} -> clase
			// clase[0] = sunny; clase[1] = overcast, clase[2]=rainy
			clase = (linea.substring(linea.indexOf('{') + 1, linea.indexOf('}'))).split(",");
			// Creamos la sublista clases para almacenarla
			// dentro del arrayList para las clases
			// Ejemplo: class_attribute[0][clases]
			// clases[0][sunny], clases[0][overcast], clases[0][rainy]
			clases = new ArrayList<String>();
			for (int i = 0; i < clase.length; i++) {
				// Quitamos cualquier espacio que nos sobre
				clases.add(clase[i].replace(" ", ""));
			}
			// Agregamos la lista de clases a la lista principal
			class_attribute.add(clases);
		}
	}

	/*
	 * Metodo que busca y almacena las instancias
	 */
	public void data(BufferedReader br, String linea) {
		instancia = linea.split(",");
		int n=attribute.size();
		instancias = new ArrayList<String>();
		for(int i=0; i<n; i++)
		{
			instancias.add(instancia[i]);
		}
		data.add(instancias);
	}

	/*
	 * Metodo que muestra toda la informacion almacenada
	 */
	public void show() {
		System.out.println("Nombre: " + relation);
		// Atributos y sus clases correspondientes:
		for (int i = 0; i < class_attribute.size(); i++) {
			System.out.println("-----");
			// Atributo:
			System.out.println("Atributo: "+ attribute.get(i));
			for (int j = 0; j < class_attribute.get(i).size(); j++) {
				// Clases por atributo:
				System.out.println("-"+class_attribute.get(i).get(j));
			}
		}
		// Instancias:
		System.out.println("");
		System.out.println("-----");
		System.out.println("Datos: ");
		for(int i=0; i<data.size(); i++) {
			System.out.print("[");
			for(int j=0; j<data.get(i).size(); j++) {
				System.out.print(data.get(i).get(j)+",");
			}
			System.out.print("]");
			System.out.println("");
		}
	}

}