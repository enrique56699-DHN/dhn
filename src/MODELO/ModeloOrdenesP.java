/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloOrdenesP
{
    private int IDORDENP;
    private int IDEMPLEADO;
    private int IDPENDIENTE;
    private String FINI;
    private String FFIN;
    private String ESTADO;
    private String COMENTARIOS;

    private String EMPLEADO;
    private String CLIENTE;
    private String TEL;
    private String CALLE;
    private String NUMERO;
    private String COLONIA;
    private String LOCALIDAD;
    private String LATITUD;
    private String LONGITUD;
    private String DESCRIPCION;

    public ModeloOrdenesP(int IDORDENP, int IDEMPLEADO, int IDPENDIENTE, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO, String CLIENTE, String TEL, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, String DESCRIPCION) {
        this.IDORDENP = IDORDENP;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
        this.EMPLEADO = EMPLEADO;
        this.CLIENTE = CLIENTE;
        this.TEL = TEL;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.DESCRIPCION = DESCRIPCION;
    }

    public ModeloOrdenesP(int IDEMPLEADO, int IDPENDIENTE, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO, String CLIENTE, String TEL, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, String DESCRIPCION) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
        this.EMPLEADO = EMPLEADO;
        this.CLIENTE = CLIENTE;
        this.TEL = TEL;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.DESCRIPCION = DESCRIPCION;
    }
    
    

    public ModeloOrdenesP(int IDORDENP, int IDEMPLEADO, int IDPENDIENTE, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO, String CLIENTE, String TEL, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD) {
        this.IDORDENP = IDORDENP;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
        this.EMPLEADO = EMPLEADO;
        this.CLIENTE = CLIENTE;
        this.TEL = TEL;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
    }

    public ModeloOrdenesP(int IDEMPLEADO, int IDPENDIENTE, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO, String CLIENTE, String TEL, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
        this.EMPLEADO = EMPLEADO;
        this.CLIENTE = CLIENTE;
        this.TEL = TEL;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
    }

    public ModeloOrdenesP() {
    }

    public ModeloOrdenesP(int IDORDENP, int IDEMPLEADO, int IDPENDIENTE, String FINI, String FFIN, String ESTADO, String COMENTARIOS) {
        this.IDORDENP = IDORDENP;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
    }

    
    public ModeloOrdenesP(int IDORDENP, int IDEMPLEADO, int IDPENDIENTE, String ESTADO, String COMENTARIOS) {
        this.IDORDENP = IDORDENP;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
    }

    public ModeloOrdenesP(int IDEMPLEADO, int IDPENDIENTE, String ESTADO, String COMENTARIOS) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDPENDIENTE = IDPENDIENTE;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
    }

    public int getIDORDENP() {
        return IDORDENP;
    }

    public void setIDORDENP(int IDORDENP) {
        this.IDORDENP = IDORDENP;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getFINI() {
        return FINI;
    }

    public void setFINI(String FINI) {
        this.FINI = FINI;
    }

    public String getFFIN() {
        return FFIN;
    }

    public void setFFIN(String FFIN) {
        this.FFIN = FFIN;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public int getIDPENDIENTE() {
        return IDPENDIENTE;
    }

    public void setIDPENDIENTE(int IDPENDIENTE) {
        this.IDPENDIENTE = IDPENDIENTE;
    }

    public String getEMPLEADO() {
        return EMPLEADO;
    }

    public void setEMPLEADO(String EMPLEADO) {
        this.EMPLEADO = EMPLEADO;
    }

    public String getCLIENTE() {
        return CLIENTE;
    }

    public void setCLIENTE(String CLIENTE) {
        this.CLIENTE = CLIENTE;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getLOCALIDAD() {
        return LOCALIDAD;
    }

    public void setLOCALIDAD(String LOCALIDAD) {
        this.LOCALIDAD = LOCALIDAD;
    }

    public String getLATITUD() {
        return LATITUD;
    }

    public void setLATITUD(String LATITUD) {
        this.LATITUD = LATITUD;
    }

    public String getLONGITUD() {
        return LONGITUD;
    }

    public void setLONGITUD(String LONGITUD) {
        this.LONGITUD = LONGITUD;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
    
    
    
}
