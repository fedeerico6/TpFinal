package Clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class BaseDeDatos<K,T> {
	
	private String ruta;
	
	public BaseDeDatos(String ruta){
		this.ruta = ruta;
	}
	
	public void escribirArchivo(HashMap<K, T> aGuardar) {
		/*
		 * METODO EN EL CUAL SE GUARDA UN MAPA EN UN ARCHIVO
		 */
		ObjectOutputStream archivo = null;
		try {
			archivo = new ObjectOutputStream(new FileOutputStream(ruta));
			archivo.writeObject(aGuardar);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				archivo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public HashMap<K, T> leerArchivo(){
		/*
		 * METODO EN EL CUAL SE LEE LO QUE CONTIENE EN UN ARCHIVO
		 * 
		 */
		ObjectInputStream archivo = null;
		HashMap<K, T> leer = null;
		try {
			archivo = new ObjectInputStream(new FileInputStream(ruta));
			leer = (HashMap<K,T>)archivo.readObject();
		}catch(EOFException e) {
			return leer;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				archivo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return leer;
	}
}
