package Bloque1Proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador {
	
	private Modelo mod;
	private Vista vis;
	
	Controlador(Modelo mod, Vista vis) {
		this.mod = mod;
		this.vis = vis; 
	
	
	 vis.getBtnBuscar().addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String textoBuscar = vis.getTextFieldBuscar().getText();
             String textoCompleto = vis.getTextAreaInput().getText();

             int contador = mod.buscar(textoCompleto, textoBuscar);

             JOptionPane.showMessageDialog(
                     vis.getFrame(),
                     "Nº VECES REPETIDO: " + contador,
                     "RESULTADO",
                     JOptionPane.INFORMATION_MESSAGE
                 );
         }
     });
	
	 vis.getBtnRemplazar().addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String textoBuscar = vis.getTextFieldBuscar().getText();
             String textoRemplazar = vis.getTextFieldRemplazar().getText();
             String textoCompleto = vis.getTextAreaInput().getText();

             String textoModificado = mod.reemplazar(textoCompleto, textoBuscar, textoRemplazar);
             vis.getTextAreaOutput().setText(textoModificado);
         }
     });
	}
}