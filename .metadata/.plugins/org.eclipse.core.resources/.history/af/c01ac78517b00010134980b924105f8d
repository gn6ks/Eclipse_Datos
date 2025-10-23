package repaso1;

import java.io.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ejec5 {
    private JFrame frame;
    private JTextField txtTitulo;
    private JTextArea textAreaContenido;
    private JList<String> listInformes;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ejec5 window = new ejec5();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ejec5() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Generador de Informes");
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(20, 20, 300, 25);
        frame.getContentPane().add(txtTitulo);

        textAreaContenido = new JTextArea();
        textAreaContenido.setBounds(20, 60, 300, 218);
        frame.getContentPane().add(textAreaContenido);

        JButton btnGuardar = new JButton("Guardar Informe");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nombreInforme = txtTitulo.getText();
        		if (nombreInforme.isEmpty()) {
        			JOptionPane.showMessageDialog(btnGuardar, "no se ha puesto un nombre al informe");
        		} else {
        			try (BufferedWriter bw = new BufferedWriter(new FileWriter("informes.txt", true))) {
        				bw.write(nombreInforme);
        				bw.newLine();
        				String ingredientes = textAreaContenido.getText();
        				String contenidoInforme = textAreaContenido.getText();
        				String[] contenidoInformeArr = contenidoInforme.split("\n");
        				for (String lineaInforme : contenidoInformeArr) {
        					bw.write(lineaInforme);
        					bw.newLine();
        				}
        				JOptionPane.showMessageDialog(btnGuardar, "informe guardado correctamente");
        		} catch (Exception ex) {
        			ex.printStackTrace();
        		}
        	}}
        });
        btnGuardar.setBounds(20, 289, 300, 60);
        frame.getContentPane().add(btnGuardar);

        JButton btnCargar = new JButton("Cargar Informes");
        btnCargar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnCargar.setBounds(20, 360, 300, 60);
        frame.getContentPane().add(btnCargar);

        listInformes = new JList<>();
        listInformes.setBounds(350, 20, 200, 400);
        frame.getContentPane().add(listInformes);
    }
}
