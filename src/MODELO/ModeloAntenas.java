/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author enrique.orozco
 */
public class ModeloAntenas
{

    private Integer IDANTENA;
    private String MODELO;
    private String NOMBRE;
    private String MARCA;
    private String IP;    
    private String SITIO;
    private String IPPADRE;

    public ModeloAntenas() {
    }

    public ModeloAntenas(Integer IDANTENA, String MODELO, String NOMBRE, String MARCA, String IP, String SITIO, String IPPADRE) {
        this.IDANTENA = IDANTENA;
        this.MODELO = MODELO;
        this.NOMBRE = NOMBRE;
        this.MARCA = MARCA;
        this.IP = IP;
        this.SITIO = SITIO;
        this.IPPADRE = IPPADRE;
    }

    public ModeloAntenas(String MODELO, String NOMBRE, String MARCA, String IP, String SITIO, String IPPADRE) {
        this.MODELO = MODELO;
        this.NOMBRE = NOMBRE;
        this.MARCA = MARCA;
        this.IP = IP;
        this.SITIO = SITIO;
        this.IPPADRE = IPPADRE;
    }

    public Integer getIDANTENA() {
        return IDANTENA;
    }

    public void setIDANTENA(Integer IDANTENA) {
        this.IDANTENA = IDANTENA;
    }

    public String getMODELO() {
        return MODELO;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getSITIO() {
        return SITIO;
    }

    public void setSITIO(String SITIO) {
        this.SITIO = SITIO;
    }

    public String getIPPADRE() {
        return IPPADRE;
    }

    public void setIPPADRE(String IPPADRE) {
        this.IPPADRE = IPPADRE;
    }

    
 
}
