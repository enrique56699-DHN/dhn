/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author Oscar E Gonzalez G
 */
public class ModeloPagos
{
    
    private int IDNOTA;
    private int IDCLIENTE;
    private int IDCONTRATO;
    private String FECHA;
    private String METODO;
    private float MONTO;
    private String DOC;
    private String REFERENCIA;
    private String COMENTARIOS;
    
    private String NOMBRE;
    private int IDCAJA;
    
    private int COSTO;
    private String FCOR;
    
    private String DIRECCION;
    
    public ModeloPagos() {
    }

    public ModeloPagos(int IDCONTRATO, String NOMBRE, String DIRECCION) {
        this.IDCONTRATO = IDCONTRATO;
        this.NOMBRE = NOMBRE;
        this.DIRECCION = DIRECCION;
    }

    public ModeloPagos(int IDNOTA, int IDCONTRATO, String FECHA, String METODO, float MONTO, String DOC, String COMENTARIOS, String NOMBRE, int COSTO, String FCOR) {
        this.IDNOTA = IDNOTA;
        this.IDCONTRATO = IDCONTRATO;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.DOC = DOC;
        this.COMENTARIOS = COMENTARIOS;
        this.NOMBRE = NOMBRE;
        this.COSTO = COSTO;
        this.FCOR = FCOR;
    }   

    public ModeloPagos(int IDNOTA, int IDCLIENTE, int IDCONTRATO, String FECHA, String METODO, float MONTO, String DOC, String REFERENCIA, String COMENTARIOS, String NOMBRE, int IDCAJA, int COSTO, String FCOR) {
        this.IDNOTA = IDNOTA;
        this.IDCLIENTE = IDCLIENTE;
        this.IDCONTRATO = IDCONTRATO;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.DOC = DOC;
        this.REFERENCIA = REFERENCIA;
        this.COMENTARIOS = COMENTARIOS;
        this.NOMBRE = NOMBRE;
        this.IDCAJA = IDCAJA;
        this.COSTO = COSTO;
        this.FCOR = FCOR;
    }    
    
    public ModeloPagos(int IDNOTA, int IDCONTRATO, String FECHA, String METODO, float MONTO, String COMENTARIOS, String NOMBRE, int COSTO, String FCOR) {
        this.IDNOTA = IDNOTA;
        this.IDCONTRATO = IDCONTRATO;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.COMENTARIOS = COMENTARIOS;
        this.NOMBRE = NOMBRE;
        this.COSTO = COSTO;
        this.FCOR = FCOR;
    }
    
    public ModeloPagos(int IDNOTA, int IDCONTRATO, String FECHA, String METODO, float MONTO, String COMENTARIOS, String NOMBRE, int IDCAJA) {
        this.IDNOTA = IDNOTA;
        this.IDCONTRATO = IDCONTRATO;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.COMENTARIOS = COMENTARIOS;
        this.NOMBRE = NOMBRE;
        this.IDCAJA = IDCAJA;
    } 

    public ModeloPagos(int IDNOTA, int IDCLIENTE, int IDCONTRATO, String FECHA, String METODO, float MONTO, String DOC, String REFERENCIA, String COMENTARIOS, int IDCAJA) {
        this.IDNOTA = IDNOTA;
        this.IDCLIENTE = IDCLIENTE;
        this.IDCONTRATO = IDCONTRATO;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.DOC = DOC;
        this.REFERENCIA = REFERENCIA;
        this.COMENTARIOS = COMENTARIOS;
        this.IDCAJA = IDCAJA;
    }   

    public ModeloPagos(int IDCLIENTE, int IDCONTRATO, String FECHA, String METODO, float MONTO, String DOC, String REFERENCIA, String COMENTARIOS, int IDCAJA) {
        this.IDCLIENTE = IDCLIENTE;
        this.IDCONTRATO = IDCONTRATO;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.DOC = DOC;
        this.REFERENCIA = REFERENCIA;
        this.COMENTARIOS = COMENTARIOS;
        this.IDCAJA = IDCAJA;
    }

    public ModeloPagos(int IDNOTA, int IDCONTRATO, float MONTO, String NOMBRE, int IDCAJA) {
        this.IDNOTA = IDNOTA;
        this.IDCONTRATO = IDCONTRATO;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.IDCAJA = IDCAJA;
    }

    public ModeloPagos(int IDNOTA, float MONTO, String NOMBRE, int IDCAJA) {
        this.IDNOTA = IDNOTA;
        this.MONTO = MONTO;
        this.NOMBRE = NOMBRE;
        this.IDCAJA = IDCAJA;
    }       

    public ModeloPagos(int IDNOTA, String FECHA, String METODO, float MONTO, String DOC, String COMENTARIOS) {
        this.IDNOTA = IDNOTA;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.MONTO = MONTO;
        this.DOC = DOC;
        this.COMENTARIOS = COMENTARIOS;
    }
    
    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }

    public String getMETODO() {
        return METODO;
    }

    public void setMETODO(String METODO) {
        this.METODO = METODO;
    }

    public int getIDNOTA() {
        return IDNOTA;
    }

    public void setIDNOTA(int IDNOTA) {
        this.IDNOTA = IDNOTA;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public String getREFERENCIA() {
        return REFERENCIA;
    }

    public void setREFERENCIA(String REFERENCIA) {
        this.REFERENCIA = REFERENCIA;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public int getIDCONTRATO() {
        return IDCONTRATO;
    }

    public void setIDCONTRATO(int IDCONTRATO) {
        this.IDCONTRATO = IDCONTRATO;
    }
    
    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getIDCAJA() {
        return IDCAJA;
    }

    public void setIDCAJA(int IDCAJA) {
        this.IDCAJA = IDCAJA;
    }

    public int getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(int COSTO) {
        this.COSTO = COSTO;
    }

    public String getFCOR() {
        return FCOR;
    }

    public void setFCOR(String FCOR) {
        this.FCOR = FCOR;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }
            
    
}
