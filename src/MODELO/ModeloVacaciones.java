/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloVacaciones
{
    private int IDVACACION;
    private int IDEMPLEADO;
    private String FREG;
    private String FSALIDA;
    private String FREGRESO;
    private int DIAST;
    private String COMENTARIOS;
    private int PERIODO;
    
    private String NOMBRE;
    private String APELLIDOS;

    public ModeloVacaciones() {
    }

    
    public ModeloVacaciones(int IDVACACION, int IDEMPLEADO, String FREG, String FSALIDA, String FREGRESO, int DIAST, String COMENTARIOS, int PERIODO, String NOMBRE, String APELLIDOS) {
        this.IDVACACION = IDVACACION;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FREG = FREG;
        this.FSALIDA = FSALIDA;
        this.FREGRESO = FREGRESO;
        this.DIAST = DIAST;
        this.COMENTARIOS = COMENTARIOS;
        this.PERIODO = PERIODO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
    }

    public ModeloVacaciones(int IDVACACION, int IDEMPLEADO, String FREG, String FSALIDA, String FREGRESO, int DIAST, String COMENTARIOS, int PERIODO) {
        this.IDVACACION = IDVACACION;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FREG = FREG;
        this.FSALIDA = FSALIDA;
        this.FREGRESO = FREGRESO;
        this.DIAST = DIAST;
        this.COMENTARIOS = COMENTARIOS;
        this.PERIODO = PERIODO;
    }

    public ModeloVacaciones(int IDEMPLEADO, String FREG, String FSALIDA, String FREGRESO, int DIAST, String COMENTARIOS, int PERIODO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FREG = FREG;
        this.FSALIDA = FSALIDA;
        this.FREGRESO = FREGRESO;
        this.DIAST = DIAST;
        this.COMENTARIOS = COMENTARIOS;
        this.PERIODO = PERIODO;
    }
    
    

    public ModeloVacaciones(int IDVACACION, int IDEMPLEADO, String FREG, String FSALIDA, String FREGRESO, int DIAST, String COMENTARIOS, String NOMBRE, String APELLIDOS) {
        this.IDVACACION = IDVACACION;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FREG = FREG;
        this.FSALIDA = FSALIDA;
        this.FREGRESO = FREGRESO;
        this.DIAST = DIAST;
        this.COMENTARIOS = COMENTARIOS;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
    }

    public ModeloVacaciones(int IDVACACION, String FREG, String FSALIDA, String FREGRESO, int DIAST, String COMENTARIOS, String NOMBRE, String APELLIDOS) {
        this.IDVACACION = IDVACACION;
        this.FREG = FREG;
        this.FSALIDA = FSALIDA;
        this.FREGRESO = FREGRESO;
        this.DIAST = DIAST;
        this.COMENTARIOS = COMENTARIOS;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
    }

    public ModeloVacaciones(int IDVACACION, String FREG, String FSALIDA, String FREGRESO, int DIAST, String COMENTARIOS, int PERIODO, String NOMBRE, String APELLIDOS) {
        this.IDVACACION = IDVACACION;
        this.FREG = FREG;
        this.FSALIDA = FSALIDA;
        this.FREGRESO = FREGRESO;
        this.DIAST = DIAST;
        this.COMENTARIOS = COMENTARIOS;
        this.PERIODO = PERIODO;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
    }
    
    public int getIDVACACION() {
        return IDVACACION;
    }

    public void setIDVACACION(int IDVACACION) {
        this.IDVACACION = IDVACACION;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getFSALIDA() {
        return FSALIDA;
    }

    public void setFSALIDA(String FSALIDA) {
        this.FSALIDA = FSALIDA;
    }

    public String getFREGRESO() {
        return FREGRESO;
    }

    public void setFREGRESO(String FREGRESO) {
        this.FREGRESO = FREGRESO;
    }

    public int getDIAST() {
        return DIAST;
    }

    public void setDIAST(int DIAST) {
        this.DIAST = DIAST;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
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

    public String getFREG() {
        return FREG;
    }

    public void setFREG(String FREG) {
        this.FREG = FREG;
    }

    public int getPERIODO() {
        return PERIODO;
    }

    public void setPERIODO(int PERIODO) {
        this.PERIODO = PERIODO;
    }

    
    
}
