package repaso1;

import java.io.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ejec2 {
    private JFrame frame;
    private JTextField txtError;
    private JTextArea textAreaLogs;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ejec2 window = new ejec2();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ejec2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Registrador de Logs");
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtError = new JTextField();
        txtError.setBounds(20, 20, 300, 25);
        frame.getContentPane().add(txtError);

        JButton btnRegistrar = new JButton("Registrar Error");
        btnRegistrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String error = txtError.getText();
                if (error.isEmpty()) {
                    JOptionPane.showMessageDialog(btnRegistrar, "No se ha puesto un error");
                } else {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("errores.log", true))) {
                        bw.write(error);
                        bw.newLine();
                        JOptionPane.showMessageDialog(btnRegistrar, "error registrado con exito");
                        txtError.setText("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        	}
        });
        btnRegistrar.setBounds(330, 20, 140, 25);
        frame.getContentPane().add(btnRegistrar);

        JButton btnMostrar = new JButton("Mostrar Logs");
        btnMostrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try (BufferedReader br = new BufferedReader(new FileReader("errores.log"))) {
        			String linea;
        			StringBuilder sb = new StringBuilder();
        			while ((linea = br.readLine()) != null) {
        				sb.append(linea).append("\n");
        			}
        			textAreaLogs.setText(sb.toString());
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}
        	}
        });
        btnMostrar.setBounds(20, 60, 120, 25);
        frame.getContentPane().add(btnMostrar);

        textAreaLogs = new JTextArea();
        textAreaLogs.setBounds(20, 100, 430, 250);
        frame.getContentPane().add(textAreaLogs);
    }
}
