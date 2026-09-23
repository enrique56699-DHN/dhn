/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloCotizaciones 
{
    private int IDCOTIZACION;
    private int IDPENDIENTE;
    private String FECHA;
    private String CATEGORIA;
    private String COMENTARIOS;
    private String DOC;
    private String ESTADO;
    
    private String NOMBRE;
    private String CALLE, NUMERO, COLONIA, LOCALIDAD;
    private String TEL, TEL2;

    public ModeloCotizaciones() {
    }

    public ModeloCotizaciones(int IDCOTIZACION, int IDPENDIENTE, String FECHA, String CATEGORIA, String COMENTARIOS, String DOC, String ESTADO) {
        this.IDCOTIZACION = IDCOTIZACION;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FECHA = FECHA;
        this.CATEGORIA = CATEGORIA;
        this.COMENTARIOS = COMENTARIOS;
        this.DOC = DOC;
        this.ESTADO = ESTADO;
    }

    public ModeloCotizaciones(int IDPENDIENTE, String FECHA, String CATEGORIA, String COMENTARIOS, String DOC, String ESTADO) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.FECHA = FECHA;
        this.CATEGORIA = CATEGORIA;
        this.COMENTARIOS = COMENTARIOS;
        this.DOC = DOC;
        this.ESTADO = ESTADO;
    }

    public ModeloCotizaciones(int IDCOTIZACION, int IDPENDIENTE, String FECHA, String CATEGORIA, String COMENTARIOS, String DOC, String ESTADO, String NOMBRE, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String TEL, String TEL2) {
        this.IDCOTIZACION = IDCOTIZACION;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FECHA = FECHA;
        this.CATEGORIA = CATEGORIA;
        this.COMENTARIOS = COMENTARIOS;
        this.DOC = DOC;
        this.ESTADO = ESTADO;
        this.NOMBRE = NOMBRE;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.TEL = TEL;
        this.TEL2 = TEL2;
    }

    public ModeloCotizaciones(int IDPENDIENTE, String FECHA, String CATEGORIA, String COMENTARIOS, String DOC, String ESTADO, String NOMBRE, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String TEL, String TEL2) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.FECHA = FECHA;
        this.CATEGORIA = CATEGORIA;
        this.COMENTARIOS = COMENTARIOS;
        this.DOC = DOC;
        this.ESTADO = ESTADO;
        this.NOMBRE = NOMBRE;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.TEL = TEL;
        this.TEL2 = TEL2;
    }
            
    public int getIDCOTIZACION() {
        return IDCOTIZACION;
    }

    public void setIDCOTIZACION(int IDCOTIZACION) {
        this.IDCOTIZACION = IDCOTIZACION;
    }

    public int getIDPENDIENTE() {
        return IDPENDIENTE;
    }

    public void setIDPENDIENTE(int IDPENDIENTE) {
        this.IDPENDIENTE = IDPENDIENTE;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
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

    public String getTEL2() {
        return TEL2;
    }

    public void setTEL2(String TEL2) {
        this.TEL2 = TEL2;
    }    
    
}
