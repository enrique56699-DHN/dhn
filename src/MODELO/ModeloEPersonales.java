/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloEPersonales {
    private int IDEPERSONAL;
    private int IDEMPLEADO;
    private String FNACIMIENTO;
    private String SEXO;
    private String EDOCIVIL;
    private String NACIONALIDAD;
    private String CURP;
    private String RFC;
    private String NSS;
    private String CALLE;
    private String NUMERO;
    private String COLONIA;
    private String LOCALIDAD;

    public ModeloEPersonales() {
    }

    public ModeloEPersonales(int IDEPERSONAL, int IDEMPLEADO, String FNACIMIENTO, String SEXO, String EDOCIVIL, String NACIONALIDAD, String CURP, String RFC, String NSS, String CALLE, String NUM, String COLONIA, String LOCALIDAD) {
        this.IDEPERSONAL = IDEPERSONAL;
        this.IDEMPLEADO = IDEMPLEADO;
        this.FNACIMIENTO = FNACIMIENTO;
        this.SEXO = SEXO;
        this.EDOCIVIL = EDOCIVIL;
        this.NACIONALIDAD = NACIONALIDAD;
        this.CURP = CURP;
        this.RFC = RFC;
        this.NSS = NSS;
        this.CALLE = CALLE;
        this.NUMERO = NUM;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
    }

    public ModeloEPersonales(int IDEMPLEADO, String FNACIMIENTO, String SEXO, String EDOCIVIL, String NACIONALIDAD, String CURP, String RFC, String NSS, String CALLE, String NUM, String COLONIA, String LOCALIDAD) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.FNACIMIENTO = FNACIMIENTO;
        this.SEXO = SEXO;
        this.EDOCIVIL = EDOCIVIL;
        this.NACIONALIDAD = NACIONALIDAD;
        this.CURP = CURP;
        this.RFC = RFC;
        this.NSS = NSS;
        this.CALLE = CALLE;
        this.NUMERO = NUM;
        this.COLONIA = COLONIA;
        this.LOCALIDAD = LOCALIDAD;
    }

    public int getIDEPERSONAL() {
        return IDEPERSONAL;
    }

    public void setIDEPERSONAL(int IDEPERSONAL) {
        this.IDEPERSONAL = IDEPERSONAL;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getFNACIMIENTO() {
        return FNACIMIENTO;
    }

    public void setFNACIMIENTO(String FNACIMIENTO) {
        this.FNACIMIENTO = FNACIMIENTO;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getEDOCIVIL() {
        return EDOCIVIL;
    }

    public void setEDOCIVIL(String EDOCIVIL) {
        this.EDOCIVIL = EDOCIVIL;
    }

    public String getNACIONALIDAD() {
        return NACIONALIDAD;
    }

    public void setNACIONALIDAD(String NACIONALIDAD) {
        this.NACIONALIDAD = NACIONALIDAD;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
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
    
    
}
