package Archivo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI;

public class Selector_archivo implements ActionListener {
	GUI dir;

	public Selector_archivo(GUI interfaz) {
		super();
		dir = interfaz;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// JFileChooser:
		JFileChooser chooser = new JFileChooser();
		// Mostrar archivos txt y arff
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("BMP Images", "bmp");
		chooser.setFileFilter(imgFilter);
		chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);
		
		int returnVal = chooser.showOpenDialog(dir);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			if (chooser.getSelectedFile().exists()) {
				dir.txt_direccion.setText(chooser.getSelectedFile().toString());
				File directorio = new File(chooser.getSelectedFile().toString());
				// Array que almacena los directorios de los archivos
				// que se encuentran dentro de la carpeta seleccionada
				String[] ficheros = directorio.list(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.toLowerCase().endsWith(".bmp");
					}
				});
				int n_t = ficheros.length;
				dir.txt_n_t.setText(n_t+"");
			} else
				JOptionPane.showMessageDialog(dir, "ERROR: Folder doesn't exist!");
		}
	}

}
