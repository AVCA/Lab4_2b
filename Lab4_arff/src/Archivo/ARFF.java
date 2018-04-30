package Archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class ARFF {

	public void Lectura(int n) {
		// Lectura
		File cuadrante = null;
		FileReader fr;
		// Escritura
		File file_arff = new File("rgbfotos.arff");
		FileWriter arff = null;
		PrintWriter pw = null;
		try {
			arff = new FileWriter(file_arff);
			pw = new PrintWriter(arff);
			// Comenzamos a generar el archivo ARFF
			// @relation
			pw.println("@relation rgbfotos");
			pw.println("");
			// @attribute
			pw.println("@attribute mediarojo numeric");
			pw.println("@attribute mediaverde numeric");
			pw.println("@attribute mediaazul numeric");
			pw.println("@attribute desvrojo numeric");
			pw.println("@attribute desvverde numeric");
			pw.println("@attribute desvazul numeric");
			// @attribute personas {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}
			pw.print("@attribute personas {");
			for (int i = 0; i < n; i++) {
				if (i == n - 1)
					pw.print(i);
				else
					pw.print(i + ",");
			}
			pw.print("}");
			pw.println("");
			pw.println("");
			// @data:
			pw.println("@data");
			pw.println("");

			// Cuadrante 0
			cuadrante = new File("cuadrante0.txt");
			fr = new FileReader(cuadrante); // cuadrantek.txt
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// Leemos todo el contenido del cuadrantek
			while ((linea = br.readLine()) != null) {
				pw.println(linea);
			}
			// Cuadrante 1
			cuadrante = new File("cuadrante1.txt");
			fr = new FileReader(cuadrante); // cuadrantek.txt
			br = new BufferedReader(fr);
			// Leemos todo el contenido del cuadrantek
			while ((linea = br.readLine()) != null) {
				pw.println(linea);
			}
			// Cuadrante 2
			cuadrante = new File("cuadrante2.txt");
			fr = new FileReader(cuadrante); // cuadrantek.txt
			br = new BufferedReader(fr);
			// Leemos todo el contenido del cuadrantek
			while ((linea = br.readLine()) != null) {
				pw.println(linea);
			}
			// Cuadrante 3
			cuadrante = new File("cuadrante3.txt");
			fr = new FileReader(cuadrante); // cuadrantek.txt
			br = new BufferedReader(fr);
			// Leemos todo el contenido del cuadrantek
			while ((linea = br.readLine()) != null) {
				pw.println(linea);
			}
			br.close();
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}