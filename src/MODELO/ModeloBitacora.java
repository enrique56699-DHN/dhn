/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloBitacora
{
    private int IDBITACORA;
    private String TABLA;
    private int REGISTRO;
    private String ACCION;
    private int IDUSER;
    private String COMENTARIOS;
    private String REFERENCIA;
    
    private String USER;
    
    private String FECHA;

    public ModeloBitacora() {
    }

    public ModeloBitacora(int IDBITACORA, String TABLA, int REGISTRO, String ACCION, int IDUSER, String COMENTARIOS, String REFERENCIA, String USER, String FECHA) {
        this.IDBITACORA = IDBITACORA;
        this.TABLA = TABLA;
        this.REGISTRO = REGISTRO;
        this.ACCION = ACCION;
        this.IDUSER = IDUSER;
        this.COMENTARIOS = COMENTARIOS;
        this.REFERENCIA = REFERENCIA;
        this.USER = USER;
        this.FECHA = FECHA;
    }

    public ModeloBitacora(String ACCION, String COMENTARIOS, String REFERENCIA, String USER, String FECHA) {
        this.ACCION = ACCION;
        this.COMENTARIOS = COMENTARIOS;
        this.REFERENCIA = REFERENCIA;
        this.USER = USER;
        this.FECHA = FECHA;
    }
        
    public ModeloBitacora(int IDBITACORA, String TABLA, int REGISTRO, String ACCION, int IDUSER, String COMENTARIOS, String REFERENCIA, String USER) {
        this.IDBITACORA = IDBITACORA;
        this.TABLA = TABLA;
        this.REGISTRO = REGISTRO;
        this.ACCION = ACCION;
        this.IDUSER = IDUSER;
        this.COMENTARIOS = COMENTARIOS;
        this.REFERENCIA = REFERENCIA;
        this.USER = USER;
    }

    public ModeloBitacora(int IDBITACORA, String TABLA, int REGISTRO, String ACCION, int IDUSER, String COMENTARIOS, String REFERENCIA) {
        this.IDBITACORA = IDBITACORA;
        this.TABLA = TABLA;
        this.REGISTRO = REGISTRO;
        this.ACCION = ACCION;
        this.IDUSER = IDUSER;
        this.COMENTARIOS = COMENTARIOS;
        this.REFERENCIA = REFERENCIA;
    }

    public ModeloBitacora(String TABLA, int REGISTRO, String ACCION, int IDUSER, String COMENTARIOS, String REFERENCIA) {
        this.TABLA = TABLA;
        this.REGISTRO = REGISTRO;
        this.ACCION = ACCION;
        this.IDUSER = IDUSER;
        this.COMENTARIOS = COMENTARIOS;
        this.REFERENCIA = REFERENCIA;
    }

    public int getIDBITACORA() {
        return IDBITACORA;
    }

    public void setIDBITACORA(int IDBITACORA) {
        this.IDBITACORA = IDBITACORA;
    }

    public String getTABLA() {
        return TABLA;
    }

    public void setTABLA(String TABLA) {
        this.TABLA = TABLA;
    }

    public int getREGISTRO() {
        return REGISTRO;
    }

    public void setREGISTRO(int REGISTRO) {
        this.REGISTRO = REGISTRO;
    }

    public String getACCION() {
        return ACCION;
    }

    public void setACCION(String ACCION) {
        this.ACCION = ACCION;
    }

    public int getIDUSER() {
        return IDUSER;
    }

    public void setIDUSER(int IDUSER) {
        this.IDUSER = IDUSER;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public String getREFERENCIA() {
        return REFERENCIA;
    }

    public void setREFERENCIA(String REFERENCIA) {
        this.REFERENCIA = REFERENCIA;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }
    
    
}
