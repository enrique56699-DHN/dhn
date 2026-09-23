/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Serie 
{
    private String idSerie;
	
	private String titulo;
	
	private List<DataSerie>datos;

	public String getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(String idSerie) {
		this.idSerie = idSerie;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<DataSerie> getDatos() {
		return datos;
	}

	public void setDatos(List<DataSerie> datos) {
		this.datos = datos;
	}
    
}
