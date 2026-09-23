/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloOrdenes extends ModeloFecha
{
    private int IDORDEN;
    private int IDEMPLEADO;
    private int IDCONTRATO;
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

    public ModeloOrdenes(int IDORDEN, int IDCONTRATO, String FINI, String FFIN, String COMENTARIOS, String EMPLEADO, String CLIENTE, String TEL, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD) {
        this.IDORDEN = IDORDEN;
        this.IDCONTRATO = IDCONTRATO;
        this.FINI = FINI;
        this.FFIN = FFIN;
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
    
    public ModeloOrdenes(int IDORDEN, int IDEMPLEADO, int IDCONTRATO, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO, String CLIENTE, String TEL, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD) {
        this.IDORDEN = IDORDEN;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDCONTRATO = IDCONTRATO;
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

    public ModeloOrdenes(int IDEMPLEADO, int IDCONTRATO, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO, String CLIENTE, String TEL, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDCONTRATO = IDCONTRATO;
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
    
    

    public ModeloOrdenes(int IDORDEN, int IDEMPLEADO, int IDCONTRATO, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO) {
        this.IDORDEN = IDORDEN;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDCONTRATO = IDCONTRATO;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
        this.EMPLEADO = EMPLEADO;
    }

    public ModeloOrdenes(int IDEMPLEADO, int IDCONTRATO, String FINI, String FFIN, String ESTADO, String COMENTARIOS, String EMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDCONTRATO = IDCONTRATO;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
        this.EMPLEADO = EMPLEADO;
    }
    
    
    public ModeloOrdenes() {
    }

    public ModeloOrdenes(int IDORDEN, int IDEMPLEADO, int IDCONTRATO, String FINI, String FFIN, String ESTADO, String COMENTARIOS) {
        this.IDORDEN = IDORDEN;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDCONTRATO = IDCONTRATO;
        this.FINI = FINI;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
    }

    
    public ModeloOrdenes(int IDORDEN, int IDEMPLEADO, int IDCONTRATO, String ESTADO, String COMENTARIOS) {
        this.IDORDEN = IDORDEN;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDCONTRATO = IDCONTRATO;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
    }

    public ModeloOrdenes(int IDEMPLEADO, int IDCONTRATO, String ESTADO, String COMENTARIOS) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDCONTRATO = IDCONTRATO;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
    }

    public int getIDORDEN() {
        return IDORDEN;
    }

    public void setIDORDEN(int IDORDEN) {
        this.IDORDEN = IDORDEN;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public int getIDCONTRATO() {
        return IDCONTRATO;
    }

    public void setIDCONTRATO(int IDCONTRATO) {
        this.IDCONTRATO = IDCONTRATO;
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

    
}
