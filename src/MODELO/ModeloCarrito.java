/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloCarrito
{
    private int IDCARRITO;
    private int IDPENDIENTE;
    private int IDPRODUCTO;
    private double CANTIDAD;
    private double TOTAL;
    
    private String DESCRIPCION;
    private String SICAR;
    
    private int IDEMPLEADO;
    private String ESTADO;
    private String FECHA;
    private String NOMBRE;

    public ModeloCarrito(int IDCARRITO, int IDPENDIENTE, int IDPRODUCTO, double CANTIDAD, double TOTAL, String DESCRIPCION, String SICAR, int IDEMPLEADO, String ESTADO, String FECHA, String NOMBRE) {
        this.IDCARRITO = IDCARRITO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
        this.DESCRIPCION = DESCRIPCION;
        this.SICAR = SICAR;
        this.IDEMPLEADO = IDEMPLEADO;
        this.ESTADO = ESTADO;
        this.FECHA = FECHA;
        this.NOMBRE = NOMBRE;
    }

    public ModeloCarrito(int IDCARRITO, int IDPENDIENTE, int IDPRODUCTO, double CANTIDAD, double TOTAL, String DESCRIPCION, String SICAR, String ESTADO, String FECHA, String NOMBRE) {
        this.IDCARRITO = IDCARRITO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
        this.DESCRIPCION = DESCRIPCION;
        this.SICAR = SICAR;
        this.ESTADO = ESTADO;
        this.FECHA = FECHA;
        this.NOMBRE = NOMBRE;
    }   
        
    public ModeloCarrito(int IDCARRITO) {
        this.IDCARRITO = IDCARRITO;
    }

    public ModeloCarrito(int IDCARRITO, int IDPENDIENTE, int IDPRODUCTO, double CANTIDAD, double TOTAL, String DESCRIPCION, String SICAR, int IDEMPLEADO, String ESTADO, String FECHA) {
        this.IDCARRITO = IDCARRITO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
        this.DESCRIPCION = DESCRIPCION;
        this.SICAR = SICAR;
        this.IDEMPLEADO = IDEMPLEADO;
        this.ESTADO = ESTADO;
        this.FECHA = FECHA;
    }
    
    public ModeloCarrito(int IDCARRITO, int IDPENDIENTE, int IDPRODUCTO, double CANTIDAD, double TOTAL, String DESCRIPCION, String SICAR, int IDEMPLEADO, String ESTADO) {
        this.IDCARRITO = IDCARRITO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
        this.DESCRIPCION = DESCRIPCION;
        this.SICAR = SICAR;
        this.IDEMPLEADO = IDEMPLEADO;
        this.ESTADO = ESTADO;
    }   
        
    public ModeloCarrito(int IDCARRITO, int IDPENDIENTE, int IDPRODUCTO, double CANTIDAD, double TOTAL, String DESCRIPCION, String SICAR) {
        this.IDCARRITO = IDCARRITO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
        this.DESCRIPCION = DESCRIPCION;
        this.SICAR = SICAR;
    }

    public ModeloCarrito() {
    }

    public ModeloCarrito(int IDCARRITO, int IDPENDIENTE, int IDPRODUCTO, double CANTIDAD, double TOTAL, String DESCRIPCION) {
        this.IDCARRITO = IDCARRITO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
        this.DESCRIPCION = DESCRIPCION;
    }

    public ModeloCarrito(int IDPENDIENTE, int IDPRODUCTO, double CANTIDAD, double TOTAL, String DESCRIPCION) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.TOTAL = TOTAL;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getIDCARRITO() {
        return IDCARRITO;
    }

    public void setIDCARRITO(int IDCARRITO) {
        this.IDCARRITO = IDCARRITO;
    }

    public int getIDPENDIENTE() {
        return IDPENDIENTE;
    }

    public void setIDPENDIENTE(int IDPENDIENTE) {
        this.IDPENDIENTE = IDPENDIENTE;
    }

    public int getIDPRODUCTO() {
        return IDPRODUCTO;
    }

    public void setIDPRODUCTO(int IDPRODUCTO) {
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public double getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(double CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getSICAR() {
        return SICAR;
    }

    public void setSICAR(String SICAR) {
        this.SICAR = SICAR;
    }
    
    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
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

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

}
