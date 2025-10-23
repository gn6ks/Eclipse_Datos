package Bloque1Proyecto;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class Vista {

	private JFrame frame;
	private JTextField textFieldBuscar;
	private JTextField textFieldRemplazar;
	private JButton btnBuscar;
	private JButton btnRemplazar;
	private JTextArea textAreaInput;
	private JTextArea textAreaOutput;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Vista window = new Vista();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { 
		frame = new JFrame();
		frame.setBounds(650, 650, 650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 21, 562, 200);
		frame.getContentPane().add(scrollPane);
		
		textAreaInput = new JTextArea();
		scrollPane.setViewportView(textAreaInput);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 381, 562, 200);
		frame.getContentPane().add(scrollPane_1);
		
		textAreaOutput = new JTextArea();
		scrollPane_1.setViewportView(textAreaOutput);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(30, 253, 411, 39);
		frame.getContentPane().add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		textFieldRemplazar = new JTextField();
		textFieldRemplazar.setColumns(10);
		textFieldRemplazar.setBounds(29, 316, 411, 39);
		frame.getContentPane().add(textFieldRemplazar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(451, 253, 140, 39);
		frame.getContentPane().add(btnBuscar);
		
		btnRemplazar = new JButton("Remplazar");
		btnRemplazar.setBounds(450, 316, 140, 39);
		frame.getContentPane().add(btnRemplazar);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JTextField getTextFieldBuscar() {
		return textFieldBuscar;
	}
	
	public JTextField getTextFieldRemplazar() {
		return textFieldRemplazar;
	}
	
	public JTextArea getTextAreaInput() {
		return textAreaInput;
	}
	
	public JTextArea getTextAreaOutput() {
		return textAreaOutput;
	}
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	
	public JButton getBtnRemplazar() {
		return btnRemplazar;
	}
	
	public void actualizarTextoArea(String reemplazar) {
		textAreaOutput.setText(reemplazar);
	}

}
