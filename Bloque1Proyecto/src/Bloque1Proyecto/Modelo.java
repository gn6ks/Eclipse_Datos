package Bloque1Proyecto;

import java.io.BufferedReader;
import java.io.FileReader;

public class Modelo {
//	try (BufferedReader br = new BufferedReader(new FileReader(textoCompleto))) {
//		String linea; 
//		while ((linea = br.readLine()) != null) {
//			
//		} 
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	
    public int buscar(String textoCompleto, String textoBuscar) {
        if (textoBuscar == null || textoBuscar.isEmpty()) {
            return 0;
        }
        /*
         * se limpia el contenido del archivo para hacer que se cogan los elementos previos al texto que se busca 
         * mas el texto a buscar, dando como resultado el numero de veces que se encuentra
         * */
        String[] palabras = textoCompleto.split(textoBuscar, -1);
        int contador = palabras.length - 1; //contador por defecto ya que corta elemento que no corresponde

        String ultimaPalabra = palabras[palabras.length - 1 ]; //ultimo elemento array
        
        if (ultimaPalabra.isEmpty() && contador > 0) {
        	return contador;
        }
        
        if (palabras.length == 0) {	
        	contador = 1; //para caso especifico de final de strings
        } 		
        return contador;
    }

    public String reemplazar(String textoCompleto, String textoBuscar, String textoRemplazar) {
        if (textoBuscar == null || textoBuscar.isEmpty()) {
            return textoCompleto;
        }

        return textoCompleto.replace(textoBuscar, textoRemplazar);
    }
}	
