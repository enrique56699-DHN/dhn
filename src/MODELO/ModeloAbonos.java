/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloAbonos
{
    private int IDABONO;
    private int IDEMPLEADO;
    private String FECHA;
    private String DESCRIPCION;
    private float MONTO;
    private int PERIODO;
    
    private String NOMBRE;
    private String APELLIDOS;

    public ModeloAbonos(int IDABONO, String FECHA, float MONTO, int PERIODO) {
        this.IDABONO = IDABONO;
        this.FECHA = FECHA;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }
        
    public ModeloAbonos() {
    }

    public ModeloAbonos(int IDABONO, String NOMBRE, String APELLIDOS, String FECHA, String DESCRIPCION, float MONTO, int PERIODO)
    {
        this.IDABONO = IDABONO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }
    
    public ModeloAbonos(int IDABONO, int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, String NOMBRE, String APELLIDOS, int PERIODO) {
        this.IDABONO = IDABONO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.PERIODO = PERIODO;
    }

    public ModeloAbonos(int IDABONO, int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, String NOMBRE, int PERIODO) {
        this.IDABONO = IDABONO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.PERIODO = PERIODO;
    }

    public ModeloAbonos(int IDPRESTAMO, int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, int PERIODO) {
        this.IDABONO = IDPRESTAMO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public ModeloAbonos(int IDEMPLEADO, String FECHA, String DESCRIPCION, float MONTO, int PERIODO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FECHA = FECHA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.PERIODO = PERIODO;
    }

    public int getIDABONO() {
        return IDABONO;
    }

    public void setIDABONO(int IDABONO) {
        this.IDABONO = IDABONO;
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
