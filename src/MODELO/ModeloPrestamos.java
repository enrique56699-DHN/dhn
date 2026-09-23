/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloPrestamos
{
    private int IDPRESTAMO;
    private int IDEMPLEADO;
    private String FECHA;
    private String DESCRIPCION;
    private float MONTO;
    private int PERIODO;
    
    private String NOMBRE;
    private String APELLIDOS;
    
    public ModeloPrestamos() {
    }

    public ModeloPrestamos(int IDPRESTAMO, String NOMBRE, String APELLIDOS, String FECHA, String DESCRIPCION, float MONTO, int PERIODO)
    {
        this.IDPRESTAMO = IDPRESTAMO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }
    public ModeloPrestamos(int IDPRESTAMO, int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, String NOMBRE, String APELLIDOS, int PERIODO) {
        this.IDPRESTAMO = IDPRESTAMO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.PERIODO = PERIODO;
    }

    public ModeloPrestamos(int IDPRESTAMO, int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, String NOMBRE, int PERIODO) {
        this.IDPRESTAMO = IDPRESTAMO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.PERIODO = PERIODO;
    }

    public ModeloPrestamos(int IDPRESTAMO, int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, int PERIODO) {
        this.IDPRESTAMO = IDPRESTAMO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public ModeloPrestamos(int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, int PERIODO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public int getIDPRESTAMO() {
        return IDPRESTAMO;
    }

    public void setIDPRESTAMO(int IDPRESTAMO) {
        this.IDPRESTAMO = IDPRESTAMO;
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

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public int getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(int PERIODO) {
        this.PERIODO = PERIODO;
    }
    
    
}
