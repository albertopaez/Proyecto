/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Alberto
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejaArchivos {
	
	public static List<String> manejaArchivos(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		List<String> lista = new ArrayList<String>();
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			int cont=1;
			while (cont <= 4) {
					lista.add(entrada.readLine());
				cont++;
			}
			entrada.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return lista;
	}
}