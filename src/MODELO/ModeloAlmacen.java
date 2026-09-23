/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloAlmacen extends ModeloFecha
{
    private int IDALMACEN;
    private int IDEMPLEADO;
    private String FECHA;
    private String ESTADO;
    
    private String NOMBRE;

    public ModeloAlmacen(int IDALMACEN, String ESTADO, String NOMBRE) {
        this.IDALMACEN = IDALMACEN;
        this.ESTADO = ESTADO;
        this.NOMBRE = NOMBRE;
    }
    
    

    public ModeloAlmacen() {
    }

    public ModeloAlmacen(int IDALMACEN, int IDEMPLEADO, String FECHA, String ESTADO) {
        this.IDALMACEN = IDALMACEN;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.ESTADO = ESTADO;
    }

    /*public ModeloAlmacen(int IDEMPLEADO, String FECHA, String ESTADO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.ESTADO = ESTADO;
    }*/

    public int getIDALMACEN() {
        return IDALMACEN;
    }

    public void setIDALMACEN(int IDALMACEN) {
        this.IDALMACEN = IDALMACEN;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    
    
}
