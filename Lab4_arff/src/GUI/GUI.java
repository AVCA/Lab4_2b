package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTree;

import Archivo.Selector_archivo;
import Generador.Generador;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	private JTextField textField;
	private JTextField txt_n;
	
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
		// Evento:
		btn_generar.addActionListener(new Generador(this));
		
		
		panel_conjunto.add(btn_generar, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel_conjunto.add(panel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		textField.setText("5");
		textField.setColumns(2);
		
		JLabel label = new JLabel("Numero de fotos por persona");
		GroupLayout gl_panel = new GroupLayout(panel);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_north.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.WEST);
		
		txt_n = new JTextField();
		panel_2.add(txt_n);
		txt_n.setText("5");
		txt_n.setColumns(3);
		
		JLabel lblNumeroDeFotos = new JLabel("Numero de fotos por persona");
		panel_1.add(lblNumeroDeFotos);
		
		JPanel panel_espacio = new JPanel();
		panel_north.add(panel_espacio, BorderLayout.SOUTH);
		
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
}
