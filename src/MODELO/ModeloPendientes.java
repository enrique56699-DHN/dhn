/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloPendientes
{
    private int IDPENDIENTE;
    private int IDCLIENTE;
    private int IDDIRECCION;
    private String NPEDIDO;
    private String NPEDIDOF;
    private String CATEGORIA;
    private String SUBCATEGORIA;
    private String DESCRIPCION;
    private String COMENTARIOS;
    private String FREG;
    private String FEST;
    private String FFIN;    
    private String ESTADO;
    private int DIAS;
    
    private String NOMBRE;
    private String TEL1;
    
    private String CALLE;
    private String NUMERO;
    private String COLONIA;
    private String LOCALIDAD;
    private String LATITUD;
    private String LONGITUD;
    
    private String FINIORDEN;
    
    private String EMPLEADO;
    private String APELLIDOS;

    public ModeloPendientes(int IDPENDIENTE, String DESCRIPCION, String FEST, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, String EMPLEADO, String APELLIDOS) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.DESCRIPCION = DESCRIPCION;
        this.FEST = FEST;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.EMPLEADO = EMPLEADO;
        this.APELLIDOS = APELLIDOS;
    }
    
    

    public ModeloPendientes(int IDPENDIENTE, String NPEDIDO, String NPEDIDOF, String CATEGORIA, String SUBCATEGORIA, String DESCRIPCION, String COMENTARIOS, String FREG, String FEST, String FFIN, int DIAS, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.NPEDIDO = NPEDIDO;
        this.NPEDIDOF = NPEDIDOF;
        this.CATEGORIA = CATEGORIA;
        this.SUBCATEGORIA = SUBCATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.FREG = FREG;
        this.FEST = FEST;
        this.FFIN = FFIN;
        this.DIAS = DIAS;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
    }
    
    public ModeloPendientes() {
    }

    public ModeloPendientes(int IDPENDIENTE, int IDCLIENTE, int IDDIRECCION, String NPEDIDO, String NPEDIDOF, String CATEGORIA, String SUBCATEGORIA, String DESCRIPCION, String COMENTARIOS, String FREG, String FEST, String FFIN, String ESTADO, int DIAS) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.NPEDIDO = NPEDIDO;
        this.NPEDIDOF = NPEDIDOF;
        this.CATEGORIA = CATEGORIA;
        this.SUBCATEGORIA = SUBCATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.FREG = FREG;
        this.FEST = FEST;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.DIAS = DIAS;
    }

    public ModeloPendientes(int IDCLIENTE, int IDDIRECCION, String NPEDIDO, String NPEDIDOF, String CATEGORIA, String SUBCATEGORIA, String DESCRIPCION, String COMENTARIOS, String FREG, String FEST, String FFIN, String ESTADO, int DIAS) {
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.NPEDIDO = NPEDIDO;
        this.NPEDIDOF = NPEDIDOF;
        this.CATEGORIA = CATEGORIA;
        this.SUBCATEGORIA = SUBCATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.FREG = FREG;
        this.FEST = FEST;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
        this.DIAS = DIAS;
    }

    public ModeloPendientes(int IDPENDIENTE, int IDCLIENTE, int IDDIRECCION, String NPEDIDO, String NPEDIDOF, String CATEGORIA, String SUBCATEGORIA, String DESCRIPCION, String COMENTARIOS, String FREG, String FEST, String FFIN, String ESTADO) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.NPEDIDO = NPEDIDO;
        this.NPEDIDOF = NPEDIDOF;
        this.CATEGORIA = CATEGORIA;
        this.SUBCATEGORIA = SUBCATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.FREG = FREG;
        this.FEST = FEST;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
    }

    public ModeloPendientes(int IDCLIENTE, int IDDIRECCION, String NPEDIDO, String NPEDIDOF, String CATEGORIA, String SUBCATEGORIA, String DESCRIPCION, String COMENTARIOS, String FREG, String FEST, String FFIN, String ESTADO) {
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.NPEDIDO = NPEDIDO;
        this.NPEDIDOF = NPEDIDOF;
        this.CATEGORIA = CATEGORIA;
        this.SUBCATEGORIA = SUBCATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
        this.FREG = FREG;
        this.FEST = FEST;
        this.FFIN = FFIN;
        this.ESTADO = ESTADO;
    }

    public ModeloPendientes(int IDPENDIENTE, String NPEDIDO, String NPEDIDOF, String DESCRIPCION, String COMENTARIOS) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.NPEDIDO = NPEDIDO;
        this.NPEDIDOF = NPEDIDOF;
        this.DESCRIPCION = DESCRIPCION;
        this.COMENTARIOS = COMENTARIOS;
    }

    public ModeloPendientes(int IDPENDIENTE, String DESCRIPCION) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.DESCRIPCION = DESCRIPCION;
    }   

    public ModeloPendientes(int IDPENDIENTE, String DESCRIPCION, String FINIORDEN) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.DESCRIPCION = DESCRIPCION;
        this.FINIORDEN = FINIORDEN;
    }

    public String getFINIORDEN() {
        return FINIORDEN;
    }

    public void setFINIORDEN(String FINIORDEN) {
        this.FINIORDEN = FINIORDEN;
    }
    
    public int getIDPENDIENTE() {
        return IDPENDIENTE;
    }

    public void setIDPENDIENTE(int IDPENDIENTE) {
        this.IDPENDIENTE = IDPENDIENTE;
    }

    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public int getIDDIRECCION() {
        return IDDIRECCION;
    }

    public void setIDDIRECCION(int IDDIRECCION) {
        this.IDDIRECCION = IDDIRECCION;
    }

    public String getNPEDIDO() {
        return NPEDIDO;
    }

    public void setNPEDIDO(String NPEDIDO) {
        this.NPEDIDO = NPEDIDO;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getSUBCATEGORIA() {
        return SUBCATEGORIA;
    }

    public void setSUBCATEGORIA(String SUBCATEGORIA) {
        this.SUBCATEGORIA = SUBCATEGORIA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getFREG() {
        return FREG;
    }

    public void setFREG(String FREG) {
        this.FREG = FREG;
    }

    public String getFEST() {
        return FEST;
    }

    public void setFEST(String FEST) {
        this.FEST = FEST;
    }

    public String getFFIN() {
        return FFIN;
    }

    public void setFFIN(String FFIN) {
        this.FFIN = FFIN;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public int getDIAS() {
        return DIAS;
    }

    public void setDIAS(int DIAS) {
        this.DIAS = DIAS;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public String getNPEDIDOF() {
        return NPEDIDOF;
    }

    public void setNPEDIDOF(String NPEDIDOF) {
        this.NPEDIDOF = NPEDIDOF;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
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

    public String getEMPLEADO() {
        return EMPLEADO;
    }

    public void setEMPLEADO(String EMPLEADO) {
        this.EMPLEADO = EMPLEADO;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }
    
    
}
