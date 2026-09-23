/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloUsuarios //extends ModeloFecha
{
    private int IDUSUARIO;
    private String NOMBRE;
    private String PASSWORD;
    private String PRIVILEGIOS;

    public ModeloUsuarios() {
    }

    public ModeloUsuarios(int IDUSUARIO, String NOMBRE, String PRIVILEGIOS) {
        this.IDUSUARIO = IDUSUARIO;
        this.NOMBRE = NOMBRE;
        this.PRIVILEGIOS = PRIVILEGIOS;
    }

    public ModeloUsuarios(String NOMBRE, String PRIVILEGIOS) {
        this.NOMBRE = NOMBRE;
        this.PRIVILEGIOS = PRIVILEGIOS;
    }

    public ModeloUsuarios(int IDEMPLEADO, String NOMBRE, String PASSWORD, String PRIVILEGIOS) {
        this.IDUSUARIO = IDEMPLEADO;
        this.NOMBRE = NOMBRE;
        this.PASSWORD = PASSWORD;
        this.PRIVILEGIOS = PRIVILEGIOS;
    }

    public ModeloUsuarios(String NOMBRE, String PASSWORD, String PRIVILEGIOS) {
        this.NOMBRE = NOMBRE;
        this.PASSWORD = PASSWORD;
        this.PRIVILEGIOS = PRIVILEGIOS;
    }

    public int getIDUSUARIO() {
        return IDUSUARIO;
    }

    public void setIDUSUARIO(int IDUSUARIO) {
        this.IDUSUARIO = IDUSUARIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPRIVILEGIOS() {
        return PRIVILEGIOS;
    }

    public void setPRIVILEGIOS(String PRIVILEGIOS) {
        this.PRIVILEGIOS = PRIVILEGIOS;
    }
 
}
