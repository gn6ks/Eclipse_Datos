package examen1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.util.ArrayList;

//examen de Pablo Guilot 2DAM

public class AE01_vista {

	JFrame frmSincronitzadorDeFitxers;
	JTextField textFieldDir1, textFieldDir2;
	JTextArea textArea1, textArea2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AE01_vista window = new AE01_vista();
					window.frmSincronitzadorDeFitxers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AE01_vista() {
		initialize();
	}

	public static String ultimaModificacion(File rutaArchivo) {
		Path pathArchivo = Paths.get(rutaArchivo.getAbsolutePath());
		FileTime lastModifiedTime = null;
		SimpleDateFormat formato = new SimpleDateFormat("DD/MM/YYYY-HH:MM:SS");
		String tiempo = null;
		try {
			lastModifiedTime = Files.getLastModifiedTime(pathArchivo);
			tiempo = String.valueOf(lastModifiedTime);
			formato.parse(tiempo);
		} catch (Exception err) {
			err.printStackTrace();
		}
		return tiempo;
	}

	public static String calcularTamaño(File fichero) {
		String tamaño = "";
		if (fichero.length() < 1000) {
			tamaño = String.valueOf(fichero.length() + " B");
		} else if (fichero.length() >= 1000 && fichero.length() < 999999) {
			tamaño = String.valueOf(fichero.length() + " KB");
		} else {
			tamaño = String.valueOf(fichero.length() + " MB");
		}
		return tamaño;
	}

	public static String acumularTamaño(File fichero) {
		String tamaño = "";
		int tamañoInt = (int) fichero.length();
		tamaño = String.valueOf(tamañoInt);
		return tamaño;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSincronitzadorDeFitxers = new JFrame();
		frmSincronitzadorDeFitxers.setTitle("Sincronitzador de fitxers");
		frmSincronitzadorDeFitxers.setBounds(100, 100, 872, 503);
		frmSincronitzadorDeFitxers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSincronitzadorDeFitxers.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Directori 1 (local)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(29, 28, 146, 15);
		frmSincronitzadorDeFitxers.getContentPane().add(lblNewLabel);

		JLabel lblDirectoriDestinaci = new JLabel("Directori 2 (remot)");
		lblDirectoriDestinaci.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDirectoriDestinaci.setBounds(500, 28, 146, 15);
		frmSincronitzadorDeFitxers.getContentPane().add(lblDirectoriDestinaci);

		textFieldDir1 = new JTextField();
		textFieldDir1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldDir1.setBounds(29, 53, 318, 18);
		frmSincronitzadorDeFitxers.getContentPane().add(textFieldDir1);
		textFieldDir1.setColumns(10);

		textFieldDir2 = new JTextField();
		textFieldDir2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldDir2.setColumns(10);
		textFieldDir2.setBounds(500, 54, 341, 18);
		frmSincronitzadorDeFitxers.getContentPane().add(textFieldDir2);

		JButton btnExplorar1 = new JButton("Explorar");
		btnExplorar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rutaDirectorio1 = textFieldDir1.getText();
				File FileDirectorio1 = new File(rutaDirectorio1);
				if (rutaDirectorio1.isEmpty()) {
					JOptionPane.showMessageDialog(btnExplorar1, "no se ha especificado la ruta del directorio 1");
				} else {
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("ruta1.txt"))) {
						String[] listaArchivos = FileDirectorio1.list();
						for (int i = 0; i < listaArchivos.length; i++) {
							File ficheroTemporal = new File(listaArchivos[i]);
							String tamaño = calcularTamaño(ficheroTemporal);
							// String ultimaModificacion = ultimaModificacion(ficheroTemporal);
							String contrabarra = "\\";
							if (ficheroTemporal.isDirectory()) {
								bw.write(contrabarra + listaArchivos[i].toString() + " | " + tamaño + " | ");
							}
							if (listaArchivos[i].length() < 15) {
								bw.write(listaArchivos[i].toString() + " | " + tamaño + " | ");
							} else {
								String[] nombreFile15 = listaArchivos[i].split("", 12);
								String nuevoNombreFile15 = nombreFile15[0].concat("(...).txt" );
								bw.write(nuevoNombreFile15.toString() + " | " + tamaño + " | ");
							}
							bw.newLine();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try (BufferedReader br = new BufferedReader(new FileReader("ruta1.txt"))) {
						String linea;
						StringBuilder sb = new StringBuilder();
						while ((linea = br.readLine()) != null) {
							sb.append(linea).append("\n");
						}
						textArea1.setText(sb.toString());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnExplorar1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExplorar1.setBounds(263, 71, 84, 20);
		frmSincronitzadorDeFitxers.getContentPane().add(btnExplorar1);

		JButton btnExplorar2 = new JButton("Explorar");
		btnExplorar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rutaDirectorio2 = textFieldDir2.getText();
				File FileDirectorio2 = new File(rutaDirectorio2);
				if (rutaDirectorio2.isEmpty()) {
					JOptionPane.showMessageDialog(btnExplorar2, "no se ha especificado la ruta del directorio 2");
				} else {
					try (BufferedWriter bw = new BufferedWriter(new FileWriter("ruta2.txt"))) {
						String[] listaArchivos = FileDirectorio2.list();
						for (int i = 0; i < listaArchivos.length; i++) {
							File ficheroTemporal = new File(listaArchivos[i]);
							String tamaño = calcularTamaño(ficheroTemporal);
							// String ultimaModificacion = ultimaModificacion(ficheroTemporal);
							String contrabarra = "\\";
							if (ficheroTemporal.isDirectory()) {
								bw.write(contrabarra + listaArchivos[i].toString() + " | " + tamaño + " | ");
							}
							if (listaArchivos[i].length() < 15) {
								bw.write(listaArchivos[i].toString() + " | " + tamaño + " | ");
							} else {
								String[] nombreFile15 = listaArchivos[i].split("", 12);
								String nuevoNombreFile15 = nombreFile15[0].concat("(...).txt" );
								bw.write(nuevoNombreFile15.toString() + " | " + tamaño + " | ");
							}
							bw.newLine();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					try (BufferedReader br = new BufferedReader(new FileReader("ruta2.txt"))) {
						String linea;
						StringBuilder sb = new StringBuilder();
						while ((linea = br.readLine()) != null) {
							sb.append(linea).append("\n");
						}
						textArea2.setText(sb.toString());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnExplorar2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExplorar2.setBounds(757, 71, 84, 20);
		frmSincronitzadorDeFitxers.getContentPane().add(btnExplorar2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 119, 318, 328);
		frmSincronitzadorDeFitxers.getContentPane().add(scrollPane);

		textArea1 = new JTextArea();
		scrollPane.setViewportView(textArea1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(500, 119, 341, 328);
		frmSincronitzadorDeFitxers.getContentPane().add(scrollPane_1);

		textArea2 = new JTextArea();
		scrollPane_1.setViewportView(textArea2);

		JButton btnPush = new JButton("Pujar (push) >>>");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contador = 0;
				String totalTamañoFicheros = "";
				try (BufferedReader br = new BufferedReader(new FileReader("ruta1.txt"));
						BufferedWriter bw = new BufferedWriter(new FileWriter("ruta2.txt"))) {
					String linea;
					while ((linea = br.readLine()) != null) {
						bw.write(linea);
						bw.newLine();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try (BufferedReader br = new BufferedReader(new FileReader("ruta2.txt"))) {
					String linea;
					StringBuilder sb = new StringBuilder();
					while ((linea = br.readLine()) != null) {
						File ficheroTemporal = new File(linea);
						totalTamañoFicheros = acumularTamaño(ficheroTemporal);
						sb.append(linea).append("\n");
						contador++;
					}
					textArea2.setText(sb.toString());
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					JOptionPane.showMessageDialog(btnPush,
							"Se han subido " + contador + " de archivos, que pesan: " + totalTamañoFicheros + " bytes");
				}
			}
		});
		btnPush.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPush.setBounds(357, 228, 133, 20);
		frmSincronitzadorDeFitxers.getContentPane().add(btnPush);

		JButton btnPull = new JButton("<<< Baixar (pull)");
		btnPull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contador = 0;
				String totalTamañoFicheros = "";
				try (BufferedReader br = new BufferedReader(new FileReader("ruta2.txt"));
						BufferedWriter bw = new BufferedWriter(new FileWriter("ruta1.txt"))) {
					String linea;
					while ((linea = br.readLine()) != null) {
						bw.write(linea);
						bw.newLine();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try (BufferedReader br = new BufferedReader(new FileReader("ruta1.txt"))) {
					String linea;
					StringBuilder sb = new StringBuilder();
					while ((linea = br.readLine()) != null) {
						File ficheroTemporal = new File(linea);
						totalTamañoFicheros = acumularTamaño(ficheroTemporal);
						sb.append(linea).append("\n");
						contador++;
					}
					textArea1.setText(sb.toString());
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					JOptionPane.showMessageDialog(btnPush,
							"Se han subido " + contador + " de archivos, que pesan: " + totalTamañoFicheros + " bytes");
				}
			}
		});
		btnPull.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPull.setBounds(357, 279, 133, 20);
		frmSincronitzadorDeFitxers.getContentPane().add(btnPull);
	}

}
