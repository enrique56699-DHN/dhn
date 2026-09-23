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
public class ModeloClientes
{
    private int IDCLIENTE;
    private String NOMBRE;
    //private String APELLIDOS;
    private String TEL1;
    private String TEL2;
    private String TEL3;
    private String IDENTIFICACION;
    private String INE;
    private String CFE;
    
    private int ID;
    private String ESTADO;
    private int IDORDEN;

    public ModeloClientes() {
    }

    public ModeloClientes(int ID, String ESTADO, int IDORDEN) {
        this.ID = ID;
        this.ESTADO = ESTADO;
        this.IDORDEN = IDORDEN;
    }
    
    public ModeloClientes(int IDCLIENTE, String NOMBRE, String TEL1, String TEL2, String TEL3, String IDENTIFICACION, String INE, String CFE) {
        this.IDCLIENTE = IDCLIENTE;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.TEL2 = TEL2;
        this.TEL3 = TEL3;
        this.IDENTIFICACION = IDENTIFICACION;
        this.INE = INE;
        this.CFE = CFE;
    }

    public ModeloClientes(String NOMBRE, String TEL1, String TEL2, String TEL3, String IDENTIFICACION, String INE, String CFE) {
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.TEL2 = TEL2;
        this.TEL3 = TEL3;
        this.IDENTIFICACION = IDENTIFICACION;
        this.INE = INE;
        this.CFE = CFE;
    }

       

    /*public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }*/

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
    }

    public String getTEL2() {
        return TEL2;
    }

    public void setTEL2(String TEL2) {
        this.TEL2 = TEL2;
    }

    public String getTEL3() {
        return TEL3;
    }

    public void setTEL3(String TEL3) {
        this.TEL3 = TEL3;
    }
    
    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getIDENTIFICACION() {
        return IDENTIFICACION;
    }

    public void setIDENTIFICACION(String IDENTIFICACION) {
        this.IDENTIFICACION = IDENTIFICACION;
    }

    public String getINE() {
        return INE;
    }

    public void setINE(String INE) {
        this.INE = INE;
    }

    public String getCFE() {
        return CFE;
    }

    public void setCFE(String CFE) {
        this.CFE = CFE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public int getIDORDEN() {
        return IDORDEN;
    }

    public void setIDORDEN(int IDORDEN) {
        this.IDORDEN = IDORDEN;
    }

    
}
