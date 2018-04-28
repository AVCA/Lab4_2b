package Archivo;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class BMP{
	String a;
	String r;
	int ancho, largo;
	BufferedImage imagen;
	byte[] bytes;
	String[] hexa;
	Frame ventana;
	Panel panel;
	
	//Metodo: Lee el archivo y almacena RGB, BYTE y STRING
	public void Bytes(File file) {
		int i = 0;
		FileInputStream archivo = null;
		bytes= new byte[(int)file.length()];
		hexa = new String[(int)file.length()];
		try {
			
			// Lee el archivo y guarda los BYTES en el vector
			archivo = new FileInputStream(file);
			archivo.read(bytes);// <-- Lee y guarda cada byte
			archivo.close();
			
			// Convertimos los BYTES en HEXADECIMAL
			while (true) {
				// Se almacenan en String
				String a = String.format("%02X", bytes[i]); // <--String.format me deja convertir BYTE a Hexa gracias a "%02X", tambien se puede pasar a decimal
				hexa[i] = a; // <-- Se almacena en un arreglo String
				i++;
			}

		} catch (Exception e) {
			// Manejar Error
		}
	}
	
	//Metodo: Imprime los valores de la cabezera
	public void Info() {
		try
		{
			String a = new String(new byte[] { bytes[0] });
			String b = new String(new byte[] { bytes[1] });
			System.out.println("            Cabecera:             ");
			System.out.println("Tipo de Archivo: " + a + b);
			r = hexa[5] + hexa[4] + hexa[3] + hexa[2];
			System.out.println("Tamaño del archivo: " + Integer.parseInt(r, 16) + " bytes");
			System.out.println("Inicio de la imagen: " + String.format("%02d", bytes[10] & 0xFF) + " byte");
			r = hexa[17] + hexa[16] + hexa[15] + hexa[14];
			System.out.println("Tamaño de la cabecera: " + Integer.parseInt(r, 16) + " bytes");
			r = hexa[21] + hexa[20] + hexa[19] + hexa[18];
			ancho = Integer.parseInt(r, 16);
			System.out.println("Ancho de la imagen: " + Integer.parseInt(r, 16) + " pixeles");
			r = hexa[25] + hexa[24] + hexa[23] + hexa[22];
			largo = Integer.parseInt(r, 16);
			System.out.println("Alto de la imagen: " + Integer.parseInt(r, 16) + " pixeles");
			r = hexa[29] + hexa[28];
			System.out.println("Profundidad de la imagen: " + Integer.parseInt(r, 16));
			r = hexa[33] + hexa[32] + hexa[31] + hexa[30];
			System.out.println("Metodo de compresion: " + Integer.parseInt(r, 16));
			r = hexa[37] + hexa[36] + hexa[35] + hexa[34];
			System.out.println("Tamaño de la imagen: " + Integer.parseInt(r, 16) + " bytes");
			r = hexa[49] + hexa[48] + hexa[47] + hexa[46];
			System.out.println("N° de colores de la paleta: " + Integer.parseInt(r, 16));
			System.out.println("============================");
			System.out.println("Colores RGB del 1° pixel:");
			int i=56;
			System.out.println("R: "+Integer.parseInt(hexa[i], 16));
			i--;
			System.out.println("G: "+Integer.parseInt(hexa[i--], 16));
			System.out.println("B: "+Integer.parseInt(hexa[i--], 16));
			System.out.println("==================================");
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
	}

	//Metodo: Crea la interfaz que mostrara la imagen
	public void ventana(File file) {
		ventana = new Frame(file.getName());
		ventana.setSize(ancho+25, largo+45);
		ventana.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		panel = new Panel();
		
		ventana.add(panel);
		ventana.setVisible(true);
	}
	
	//Metodo: Base para pintar la imagen
	public void Canvas()
	{
		panel.add(new MyCanvas());
		ventana.setVisible(true);
	}
	
	//Metodo: Pinta cada pixel del canvas con los RGB del archivo
	public class MyCanvas extends Canvas {

	      public MyCanvas () {
	         setSize(ancho, largo);
	      }

	      public void paint (Graphics g) {
	         Graphics2D g2;
	         g2 = (Graphics2D) g;
	         int i=((int)bytes.length)-1; //<-Total de bytes que mostrare
	         for(int x = 0; x < largo; x++) {
	             for(int y = ancho; y > 0; y--) {
	            	 //Empieza a leer desde el ultimo byte hasta el inicio
	            	 //De lo contrario la imagen se mostraria alrevez
	            	 int blue= Integer.parseInt(hexa[i--], 16);
	            	 //i--; //<-- Sin esto no lee el siguente byte
	            	 int green = Integer.parseInt(hexa[i--], 16);
	            	 int red = Integer.parseInt(hexa[i--], 16);
	            	 //Asignamos el color rgb con los ints
	                 g.setColor(new Color(blue,green,red));
	                 //Dibujamos el pixel con las coordenadas de y x
	                 //Tambien puede usarse g.drawRect(y,x,1,1) y muestra el
	                 //mismo resultado
	                 g.drawRect(y,x, 1, 1);
	             }
	         }
	         
	      }
	   }
	
}
