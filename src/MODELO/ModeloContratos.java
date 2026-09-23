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
public class ModeloContratos
{
    private int IDCONTRATO;
    private int IDCLIENTE;
    private int IDDIRECCION;
    private int IDPAQUETE;
    private String FREG;
    private String FINS;
    private String COMENTARIOS;
    private String ESTADO;
    private String FCOR;
    private String EQUIPO;
    private String IP;
    private int DIAS;
    private boolean CONT;
    
    private String NOMBRE;
    private String TEL1;
    
    private String CALLE;
    private String NUMERO;
    private String COLONIA;
    private String LOCALIDAD;
    private String LATITUD;
    private String LONGITUD;
    
    private int COSTO; 
    private String DESCIPCION;

    public ModeloContratos(int IDCONTRATO, int IDCLIENTE, int IDDIRECCION, int IDPAQUETE, String FREG, String FINS, String COMENTARIOS, String ESTADO, String FCOR, String EQUIPO, String IP, int DIAS, boolean CONT, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, int COSTO, String DESCIPCION) {
        this.IDCONTRATO = IDCONTRATO;
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.IDPAQUETE = IDPAQUETE;
        this.FREG = FREG;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.ESTADO = ESTADO;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.IP = IP;
        this.DIAS = DIAS;
        this.CONT = CONT;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.COSTO = COSTO;
        this.DESCIPCION = DESCIPCION;
    }

    public ModeloContratos(int IDCLIENTE, int IDDIRECCION, int IDPAQUETE, String FREG, String FINS, String COMENTARIOS, String ESTADO, String FCOR, String EQUIPO, String IP, int DIAS, boolean CONT, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, int COSTO, String DESCIPCION) {
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.IDPAQUETE = IDPAQUETE;
        this.FREG = FREG;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.ESTADO = ESTADO;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.IP = IP;
        this.DIAS = DIAS;
        this.CONT = CONT;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.COSTO = COSTO;
        this.DESCIPCION = DESCIPCION;
    }
    
    

    public ModeloContratos(int IDCONTRATO, int IDCLIENTE, int IDDIRECCION, int IDPAQUETE, String FREG, String FINS, String COMENTARIOS, String ESTADO, String FCOR, String EQUIPO, String IP, int DIAS, boolean CONT, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, int COSTO) {
        this.IDCONTRATO = IDCONTRATO;
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.IDPAQUETE = IDPAQUETE;
        this.FREG = FREG;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.ESTADO = ESTADO;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.IP = IP;
        this.DIAS = DIAS;
        this.CONT = CONT;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.COSTO = COSTO;
    }
    
    

    public ModeloContratos(int IDCONTRATO, int IDPAQUETE, String FINS, String COMENTARIOS, String FCOR, String EQUIPO, String IP, boolean CONT, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, int COSTO) {
        this.IDCONTRATO = IDCONTRATO;
        this.IDPAQUETE = IDPAQUETE;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.IP = IP;
        this.CONT = CONT;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.COSTO = COSTO;
    }
    
    

    public ModeloContratos(int IDCONTRATO, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD, String IP,boolean CONT, int DIAS,String COMENTARIOS) {
        this.IDCONTRATO = IDCONTRATO;
        this.DIAS = DIAS;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
        this.IP = IP;
        this.COMENTARIOS = COMENTARIOS;
        this.CONT = CONT;
    }

    public ModeloContratos(int IDCONTRATO, String EQUIPO, String IP) {
        this.IDCONTRATO = IDCONTRATO;
        this.EQUIPO = EQUIPO;
        this.IP = IP;
    }

    public ModeloContratos(int IDCONTRATO, String EQUIPO, String IP, String NOMBRE, String TEL1, String CALLE, String NUMERO, String COLONIA, String LOCALIDAD, String LATITUD, String LONGITUD) {
        this.IDCONTRATO = IDCONTRATO;
        this.EQUIPO = EQUIPO;
        this.IP = IP;
        this.NOMBRE = NOMBRE;
        this.TEL1 = TEL1;
        this.CALLE = CALLE;
        this.NUMERO = NUMERO;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
        this.LATITUD = LATITUD;
        this.LONGITUD = LONGITUD;
    }
    
    
           
    public ModeloContratos() {
    }

    public ModeloContratos(int IDCONTRATO, int IDCLIENTE, int IDDIRECCION, int IDPAQUETE, String FREG, String FINS, String COMENTARIOS, String ESTADO, String FCOR,String EQUIPO, int DIAS, boolean CONT) {
        this.IDCONTRATO = IDCONTRATO;
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.IDPAQUETE = IDPAQUETE;
        this.FREG = FREG;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.ESTADO = ESTADO;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.DIAS = DIAS;
        this.CONT = CONT;
    }

    public ModeloContratos(int IDCLIENTE, int IDDIRECCION, int IDPAQUETE, String FREG, String FINS, String COMENTARIOS, String ESTADO, String FCOR, String EQUIPO, int DIAS, boolean CONT) {
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.IDPAQUETE = IDPAQUETE;
        this.FREG = FREG;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.ESTADO = ESTADO;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.DIAS = DIAS;
        this.CONT = CONT;
    }
    
    public ModeloContratos(int IDCONTRATO, int IDCLIENTE, int IDDIRECCION, int IDPAQUETE, String FREG, String FINS, String COMENTARIOS, String ESTADO, String FCOR, String EQUIPO, boolean CONT) {
        this.IDCONTRATO = IDCONTRATO;
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.IDPAQUETE = IDPAQUETE;
        this.FREG = FREG;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.ESTADO = ESTADO;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.CONT = CONT;
    }

    public ModeloContratos(int IDCLIENTE, int IDDIRECCION, int IDPAQUETE, String FREG, String FINS, String COMENTARIOS, String ESTADO, String FCOR, String EQUIPO, boolean CONT) {
        this.IDCLIENTE = IDCLIENTE;
        this.IDDIRECCION = IDDIRECCION;
        this.IDPAQUETE = IDPAQUETE;
        this.FREG = FREG;
        this.FINS = FINS;
        this.COMENTARIOS = COMENTARIOS;
        this.ESTADO = ESTADO;
        this.FCOR = FCOR;
        this.EQUIPO = EQUIPO;
        this.CONT = CONT;
    }

    public ModeloContratos(int IDCONTRATO, int IDCLIENTE, String FINS) {
        this.IDCONTRATO = IDCONTRATO;
        this.IDCLIENTE = IDCLIENTE;
        this.FINS = FINS;
    }
    
    public ModeloContratos(int IDCONTRATO, int IDCLIENTE, String FINS, String FCOR) {
        this.IDCONTRATO = IDCONTRATO;
        this.IDCLIENTE = IDCLIENTE;
        this.FINS = FINS;
        this.FCOR = FCOR;
    }

    public ModeloContratos(int IDCONTRATO, String FCOR, int DIAS, String NOMBRE, int COSTO) {
        this.IDCONTRATO = IDCONTRATO;
        this.FCOR = FCOR;
        this.DIAS = DIAS;
        this.NOMBRE = NOMBRE;
        this.COSTO = COSTO;
    }
          
    public int getIDCONTRATO() {
        return IDCONTRATO;
    }

    public void setIDCONTRATO(int IDCONTRATO) {
        this.IDCONTRATO = IDCONTRATO;
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

    public int getIDPAQUETE() {
        return IDPAQUETE;
    }

    public void setIDPAQUETE(int IDPAQUETE) {
        this.IDPAQUETE = IDPAQUETE;
    }

    public String getEQUIPO() {
        return EQUIPO;
    }

    public void setEQUIPO(String EQUIPO) {
        this.EQUIPO = EQUIPO;
    }
    
    public String getFREG() {
        return FREG;
    }

    public void setFREG(String FREG) {
        this.FREG = FREG;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    public String getFINS() {
        return FINS;
    }

    public void setFINS(String FINS) {
        this.FINS = FINS;
    }

    public String getFCOR() {
        return FCOR;
    }

    public void setFCOR(String FCOR) {
        this.FCOR = FCOR;
    }

    public int getDIAS() {
        return DIAS;
    }

    public void setDIAS(int DIAS) {
        this.DIAS = DIAS;
    }

    public boolean getCONT() {
        return CONT;
    }

    public void setCONT(boolean CONT) {
        this.CONT = CONT;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
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

    public int getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(int COSTO) {
        this.COSTO = COSTO;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
    }

    public String getDESCIPCION() {
        return DESCIPCION;
    }

    public void setDESCIPCION(String DESCIPCION) {
        this.DESCIPCION = DESCIPCION;
    }
    
    
    
}
