package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import model.Pelicula;
import model.Usuario;

public interface Dao {

	ArrayList<Usuario> consultarLogin(String usuario, String clave) throws ClassNotFoundException, SQLException;

	void nuevoUser(String nombre, String clave) throws ClassNotFoundException, SQLException;

	ArrayList<Pelicula> verPelis() throws ClassNotFoundException, SQLException;

	void nuevaPeli(String director, String titulo, String fecha, String url, String descripcion)
			throws ClassNotFoundException, SQLException;

	void modificarPeli(int id, String director, String titulo, String fecha, String url, String descripcion)
			throws ClassNotFoundException, SQLException;

	void borrarPeli(String titulo) throws SQLException, ClassNotFoundException;

	ArrayList<Pelicula> consultarDirector(String director) throws ClassNotFoundException, SQLException;

	HashSet<String> listaDirectores();

	ArrayList<Pelicula> sacarPelis() throws ClassNotFoundException, SQLException;

}
