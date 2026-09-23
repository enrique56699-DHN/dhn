/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloEContactos 
{
    private int IDECONTACTO;
    private int IDEMPLEADO;
    private String NOMBRE;
    private String TEL;
    private String EMAIL;

    public ModeloEContactos() {
    }

    public ModeloEContactos(int IDECONTACTO, int IDEMPLEADO, String NOMBRE, String TEL, String EMAIL) {
        this.IDECONTACTO = IDECONTACTO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.NOMBRE = NOMBRE;
        this.TEL = TEL;
        this.EMAIL = EMAIL;
    }

    public ModeloEContactos(int IDEMPLEADO, String NOMBRE, String TEL, String EMAIL) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.NOMBRE = NOMBRE;
        this.TEL = TEL;
        this.EMAIL = EMAIL;
    }

    public int getIDECONTACTO() {
        return IDECONTACTO;
    }

    public void setIDECONTACTO(int IDECONTACTO) {
        this.IDECONTACTO = IDECONTACTO;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
    
    
}
