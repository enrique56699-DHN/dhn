/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloDirecciones
{
    private int IDDIRECCION;
    private int IDCLIENTE;
    private String CALLE;
    private String COLONIA;
    private String NUMERO;
    private String LOCALIDAD;
    private String LATITUD;
    private String LONGITUD;
    private String REFERENCIA;
    
    private String NOMBRE;

    public ModeloDirecciones() {
    }

    public ModeloDirecciones(int IDDIRECCION, int IDCLIENTE, String CALLE, String COLONIA, String NUMERO, String LOCALIDAD, String LATITUD, String LONGITUD, String REFERENCIA, String NOMBRE) {
        this.IDDIRECCION = IDDIRECCION;
        this.IDCLIENTE = IDCLIENTE;
        this.CALLE = CALLE;
        this.COLONIA = COLONIA;
        this.NUMERO = NUMERO;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.REFERENCIA = REFERENCIA;
        this.NOMBRE = NOMBRE;
        //this.CLIENTES = CLIENTES;
    }    

    public ModeloDirecciones(int IDCLIENTE, String CALLE, String COLONIA, String NUMERO, String LOCALIDAD, String LATITUD, String LONGITUD, String REFERENCIA) {
        this.IDCLIENTE = IDCLIENTE;
        this.CALLE = CALLE;
        this.COLONIA = COLONIA;
        this.NUMERO = NUMERO;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.REFERENCIA = REFERENCIA;
    }

    public int getIDDIRECCION() {
        return IDDIRECCION;
    }

    public void setIDDIRECCION(int IDDIRECCION) {
        this.IDDIRECCION = IDDIRECCION;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getREFERENCIA() {
        return REFERENCIA;
    }

    public void setREFERENCIA(String REFERENCIA) {
        this.REFERENCIA = REFERENCIA;
    }

    public String getLOCALIDAD() {
        return LOCALIDAD;
    }

    public void setLOCALIDAD(String LOCALIDAD) {
        this.LOCALIDAD = LOCALIDAD;
    }

    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
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

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    
}
