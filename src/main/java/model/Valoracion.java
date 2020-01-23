package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Valoracion {

	@Id
	private int pk;
	private int id;
	public int valoracion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Valoracion() {
		super();
	}

	public Valoracion(int id, int valoracion) {
		super();
		this.id = id;
		this.valoracion = valoracion;
	}

}
