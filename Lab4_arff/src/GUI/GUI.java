package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import Archivo.Selector_archivo;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI extends JFrame{

	// E L E M E N T O S:
	
	// TextArea
	public JTextArea txt_area_ARFF;
	// TextField
	public JTextField txt_direccion;
	// Frame
	public JFrame frame;
	// Panel
	public JPanel panel_north;
	public JPanel panel_west;
	public JPanel panel_center;
	// Button
	public JButton btn_cargar;
	public JButton btn_generar;
	// Scroll Panel
	public JScrollPane scrollPane;
	
	// Se crea el objeto GUI
	public GUI() {
		initialize();
	}

	// Inicializo la interfaz:
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 651, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_north = new JPanel();
		frame.getContentPane().add(panel_north, BorderLayout.NORTH);
		panel_north.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_conjunto = new JPanel();
		panel_north.add(panel_conjunto, BorderLayout.NORTH);
		panel_conjunto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Conjunto de imagenes BMP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_conjunto.setLayout(new BorderLayout(0, 0));
		
		JButton btn_cargar = new JButton("Selecciona la carpeta");
		// Evento:
		btn_cargar.addActionListener(new Selector_archivo(this));
		
		panel_conjunto.add(btn_cargar, BorderLayout.WEST);
		
		txt_direccion = new JTextField();
		panel_conjunto.add(txt_direccion, BorderLayout.CENTER);
		txt_direccion.setText("Direccion de la carpeta que contiene las imagenes bmp");
		txt_direccion.setColumns(10);
		
		JButton btn_generar = new JButton("Generar");
		//Evento:
		btn_generar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("eeeeeeeeeeeee");
			}
		});
		
		panel_conjunto.add(btn_generar, BorderLayout.EAST);
		
		JPanel panel_espacio = new JPanel();
		panel_north.add(panel_espacio, BorderLayout.SOUTH);
		
		JPanel panel_west = new JPanel();
		frame.getContentPane().add(panel_west, BorderLayout.WEST);
		panel_west.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_tree = new JPanel();
		panel_tree.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Archivos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_west.add(panel_tree);
		panel_tree.setLayout(new BorderLayout(0, 0));
		
		JTree tree = new JTree();
		panel_tree.add(tree);
		
		JPanel panel_center = new JPanel();
		frame.getContentPane().add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_ARFF = new JPanel();
		panel_ARFF.setBorder(new TitledBorder(null, "ARFF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_center.add(panel_ARFF, BorderLayout.CENTER);
		panel_ARFF.setLayout(new BorderLayout(0, 0));
		
		JTextArea txt_area_ARFF = new JTextArea();
		txt_area_ARFF.setEditable(false);
		txt_area_ARFF.setText("[Formato ARFF]");
		txt_area_ARFF.setRows(10);
		
		JScrollPane scrollPane = new JScrollPane(txt_area_ARFF);
		panel_ARFF.add(scrollPane, BorderLayout.CENTER);
	}

	//Eventos:
	private void cargar()
	{
		new Selector_archivo(this);
		ArrayList<String> direcciones = new ArrayList<String>();
		
	}
	
	private void generar()
	{
		
	}
}
