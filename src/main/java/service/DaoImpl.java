package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Pelicula;
import model.Usuario;
import model.Valoracion;
import repository.Dao;

public class DaoImpl implements Dao {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BD_JPA");

	static HashSet<String> listaDirectores = new HashSet<>();

	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> consultarLogin(String usuario, String clave) throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager
				.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :usuario AND u.clave = :clave ");
		query.setParameter("usuario", usuario);
		query.setParameter("clave", clave);

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		lista = (ArrayList<Usuario>) query.getResultList();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public void nuevoUser(String nombre, String clave) throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		int id;

		List<Integer> lista = new ArrayList<Integer>();

		Query query = entitymanager.createQuery("SELECT MAX(u.id) FROM Usuario u");

		lista = query.getResultList();

		id = lista.get(0).intValue();

		int id2 = id + 1;

		Usuario usr = new Usuario();
		usr.setId(id2);
		usr.setNombre(nombre);
		usr.setClave(clave);

		entitymanager.persist(usr);
		entitymanager.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Pelicula> verPelis() throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("SELECT p FROM Pelicula p");

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = (ArrayList<Pelicula>) query.getResultList();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public void nuevaPeli(String director, String titulo, String fecha, String url, String descripcion)
			throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		int id;

		List<Integer> lista = new ArrayList<Integer>();

		Query query = entitymanager.createQuery("SELECT MAX(p.id) FROM Pelicula p");

		lista = query.getResultList();

		if (lista.isEmpty() || lista == null || lista.get(0) == null) {
			id = 1;
		} else {
			id = lista.get(0) + 1;
		}

		Pelicula peli = new Pelicula();
		peli.setId(id);
		peli.setDirector(director);
		peli.setTitulo(titulo);
		peli.setFecha(fecha);
		peli.setUrl(url);
		peli.setDescripcion(descripcion);

		entitymanager.persist(peli);
		entitymanager.getTransaction().commit();

	}

	public void modificarPeli(int id, String director, String titulo, String fecha, String url, String descripcion)
			throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery(
				"UPDATE Pelicula p SET p.titulo = :titulo, p.director = :director, p.fecha = :fecha, p.url = :url, p.descripcion = :descripcion WHERE p.id = :id ");
		query.setParameter("id", new Integer(id));
		query.setParameter("director", director);
		query.setParameter("titulo", titulo);
		query.setParameter("fecha", fecha);
		query.setParameter("url", url);
		query.setParameter("descripcion", descripcion);

		query.executeUpdate();

		entitymanager.getTransaction().commit();

	}

	public void borrarPeli(String id) throws SQLException, ClassNotFoundException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("DELETE FROM Pelicula p WHERE p.id = :id ");
		query.setParameter("id", new Integer(id));

		query.executeUpdate();

		entitymanager.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Pelicula> consultarDirector(String director) throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("SELECT p FROM Pelicula p WHERE p.director = :director");
		query.setParameter("director", director);

		if (director.equals("")) {
		} else {
			listaDirectores.add(director);
		}

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = (ArrayList<Pelicula>) query.getResultList();

		return lista;
	}

	public HashSet<String> listaDirectores() {
		return listaDirectores;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Pelicula> sacarPelis() throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("SELECT p FROM Pelicula p");

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = (ArrayList<Pelicula>) query.getResultList();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Pelicula> sacarPelisFiltro(String titulo) throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("SELECT p FROM Pelicula p WHERE p.titulo = :titu ");
		query.setParameter("titu", titulo);

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = (ArrayList<Pelicula>) query.getResultList();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Pelicula> infoPeli(String titulo) throws ClassNotFoundException, SQLException {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("SELECT p FROM Pelicula p WHERE p.titulo = :titu ");
		query.setParameter("titu", titulo);

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = (ArrayList<Pelicula>) query.getResultList();

		return lista;
	}

	public void evaluar(String id, String valoracion) {

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Valoracion val = new Valoracion();
		val.setId(new Integer(id));
		val.setValoracion(new Integer(valoracion));

		entitymanager.persist(val);
		entitymanager.getTransaction().commit();

		EntityManager entitymanager2 = emfactory.createEntityManager();
		entitymanager2.getTransaction().begin();

		Query query = entitymanager2.createQuery("SELECT AVG(v.valoracion) FROM Valoracion v WHERE v.id = :id ");
		query.setParameter("id", new Integer(id));

		Double media = (Double) query.getSingleResult();

		entitymanager2.getTransaction().commit();

		EntityManager entitymanager3 = emfactory.createEntityManager();
		entitymanager3.getTransaction().begin();

		Query query2 = entitymanager3.createQuery("UPDATE Pelicula p SET p.valoracion = :valoracion WHERE p.id = :id");
		query2.setParameter("valoracion", media);
		query2.setParameter("id", new Integer(id));
		query2.executeUpdate();

		entitymanager3.getTransaction().commit();

	}

}
