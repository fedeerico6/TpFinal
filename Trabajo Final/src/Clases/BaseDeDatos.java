package Clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
	
	public void escribirArchivoV2(HashMap<K, T> aguardar) {
		/*
		 * METOO EN EL CUAL SE VUELVE A GUARDAR TODO EN EL MAPA
		 */
		ObjectOutputStream archivo = null;
		try {
			archivo = new ObjectOutputStream(new FileOutputStream(ruta));
			Iterator it = aguardar.entrySet().iterator();
			while(it.hasNext()) {
				Entry<K, T> mapa = (Entry<K, T>)it.next();
				archivo.writeObject(mapa.getValue());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			ObjectOutputStream abrir = null;
			try {
				abrir = new ObjectOutputStream(new FileOutputStream(ruta));
				abrir.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
	
	public ArrayList<T> leerArhivoV2(){
		/*
		 * METODO EN EL CUAL SE LEE LO QUE CONTIENE EN UN ARCHIVO
		 * 
		 */
		ObjectInputStream archivo = null;
		ArrayList<T> leer = new ArrayList<T>();
		T guardar;
		try {
			archivo = new ObjectInputStream(new FileInputStream(ruta));
			while((guardar = (T) archivo.readObject()) != null){
				leer.add(guardar);
			}
		}catch(EOFException e) {
			return leer;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			ObjectOutputStream abrir = null;
			try {
				abrir = new ObjectOutputStream(new FileOutputStream(ruta));
				abrir.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//e.printStackTrace();
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
