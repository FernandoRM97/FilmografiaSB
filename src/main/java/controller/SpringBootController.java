package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Pelicula;
import model.Usuario;
import service.DaoImpl;

@Controller
@EnableAutoConfiguration
public class SpringBootController {

	DaoImpl service = new DaoImpl();

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {

		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index2() {

		return "index";
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info() {

		return "info";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}

	@RequestMapping(value = "/loguearse", method = RequestMethod.POST)
	public String loguearse(HttpServletRequest request) {

		String usuario = request.getParameter("user");
		String clave = request.getParameter("passwd");

		String pagina;

		try {

			ArrayList<Usuario> lista = new ArrayList<Usuario>();

			lista = service.consultarLogin(usuario, clave);

			if (lista.get(0).getNombre().equals(usuario) && lista.get(0).getClave().equals(clave)) {
				request.setAttribute("nombre", usuario);
				pagina = "loginCorrecto";
			} else {
				pagina = "loginIncorrecto";

			}

		} catch (ClassNotFoundException e) {
			pagina = "loginIncorrecto";
		} catch (SQLException e) {
			pagina = "loginIncorrecto";
		} catch (IndexOutOfBoundsException e) {
			pagina = "loginIncorrecto";
		}

		return pagina;
	}

	@RequestMapping(value = "/loginCorrecto", method = RequestMethod.GET)
	public String loginCorrecto() {

		return "loginCorrecto";
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public String registrar() {

		return "registrar";
	}

	@RequestMapping(value = "/registrarNuevoUser", method = RequestMethod.POST)
	public String registrarNuevoUser(HttpServletRequest request) {

		String usuario = request.getParameter("user");
		String clave = request.getParameter("passwd");

		String pagina;

		try {
			service.nuevoUser(usuario, clave);
			pagina = "loginCorrecto";
		} catch (ClassNotFoundException e) {
			pagina = "registrarFail";
		} catch (SQLException e) {
			pagina = "registrarFail";
		}

		return pagina;
	}

	@RequestMapping(value = "/mantenimiento", method = { RequestMethod.POST, RequestMethod.GET })
	public String mantenimiento(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = service.sacarPelis();

		request.setAttribute("listaPelis", lista);

		return "mantenimiento";
	}

	@RequestMapping(value = "/nuevaPeli", method = RequestMethod.GET)
	public String nuevaPeli() {

		return "nuevaPelicula";
	}

	@RequestMapping(value = "/altaPeli", method = RequestMethod.POST)
	public String altaPeli(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String director = request.getParameter("director");
		String titulo = request.getParameter("titulo");
		String fecha = request.getParameter("fecha");
		String url = request.getParameter("url");
		String descripcion = request.getParameter("descripcion");

		String pagina;

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = service.verPelis();

		request.setAttribute("listaPelis", lista);

		try {
			service.nuevaPeli(director, titulo, fecha, url, descripcion);
			pagina = "redirect:mantenimiento";
		} catch (ClassNotFoundException e) {
			pagina = "nuevaPeliculaFail";
		} catch (SQLException e) {
			pagina = "nuevaPeliculaFail";
		}

		return pagina;
	}

	@RequestMapping(value = "/borrarPeli", method = RequestMethod.POST)
	public String borrarPeli(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String id = request.getParameter("id");

		String pagina;

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = service.verPelis();

		request.setAttribute("listaPelis", lista);

		try {
			service.borrarPeli(id);
			pagina = "redirect:mantenimiento";
		} catch (ClassNotFoundException e) {
			pagina = "borrarPeliculaFail";
		} catch (SQLException e) {
			pagina = "borrarPeliculaFail";
		}

		return pagina;
	}

	@RequestMapping(value = "/modificarPeli", method = RequestMethod.POST)
	public String modificarPeli(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = service.verPelis();

		request.setAttribute("listaPelis", lista);

		String id = request.getParameter("id");
		String director = request.getParameter("director");
		String titulo = request.getParameter("titulo");
		String fecha = request.getParameter("fecha");
		String url = request.getParameter("url");
		String descripcion = request.getParameter("descripcion");

		request.setAttribute("id", id);
		request.setAttribute("director", director);
		request.setAttribute("titulo", titulo);
		request.setAttribute("fecha", fecha);
		request.setAttribute("url", url);
		request.setAttribute("descripcion", descripcion);

		return "modificarPelicula";
	}

	@RequestMapping(value = "/modificarPelicula", method = RequestMethod.POST)
	public String modificarPelicula(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = service.verPelis();

		request.setAttribute("listaPelis", lista);

		String id = request.getParameter("id2");
		String director = request.getParameter("director2");
		String titulo = request.getParameter("titulo2");
		String fecha = request.getParameter("fecha2");
		String url = request.getParameter("url2");
		String descripcion = request.getParameter("descripcion2");

		service.modificarPeli(Integer.parseInt(id), director, titulo, fecha, url, descripcion);

		return "redirect:mantenimiento";
	}

	@RequestMapping(value = "/consultar", method = RequestMethod.GET)
	public String consultar() {

		return "consultar";
	}

	@RequestMapping(value = "/consultarPelis", method = RequestMethod.POST)
	public String consultarPelis(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String director = request.getParameter("director");

		request.setAttribute("director", director);

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = service.consultarDirector(director);

		request.setAttribute("pelis", lista);

		return "consultarPelis";
	}

	@RequestMapping(value = "/finalizar", method = RequestMethod.GET)
	public String finalizar(HttpServletRequest request) {

		HashSet<String> lista;

		lista = service.listaDirectores();

		request.setAttribute("directoresConsultados", lista);

		return "finalizar";
	}

	@RequestMapping(value = "/fin", method = { RequestMethod.POST, RequestMethod.GET })
	public String fin() {

		HashSet<String> lista;

		lista = service.listaDirectores();

		lista.clear();

		return "index";
	}

	@RequestMapping(value = "/peliculas", method = RequestMethod.GET)
	public String peliculas(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		lista = service.sacarPelis();

		request.setAttribute("pelis", lista);

		return "peliculas";
	}

	@RequestMapping(value = "/filtroPelis", method = RequestMethod.POST)
	public String filtroPeliculas(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		ArrayList<Pelicula> lista = new ArrayList<Pelicula>();

		String titulo = request.getParameter("titulo");

		lista = service.sacarPelisFiltro(titulo);

		request.setAttribute("pelis", lista);

		return "peliculas";
	}

	@RequestMapping(value = "/informacion", method = RequestMethod.POST)
	public String informacion(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String titulo = request.getParameter("titulo");

		request.setAttribute("titulo", titulo);

		ArrayList<Pelicula> peli = new ArrayList<Pelicula>();

		peli = service.infoPeli(titulo);

		request.setAttribute("peli", peli);

		return "informacion";
	}

	@RequestMapping(value = "/evaluar", method = RequestMethod.POST)
	public String evaluar(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		String id = request.getParameter("id");

		String valoracion = request.getParameter("rating");

		service.evaluar(id, valoracion);

		return "redirect:peliculas";
	}

}