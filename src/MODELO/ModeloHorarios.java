/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloHorarios {
    
    private int IDHORARIO;
    private String DESCRIPCION;
    private String HENTRADA;
    private String HSALIDA;
    private String HSCOMIDA;
    private String HECOMIDA;
    private String DESCANSOS;

    public ModeloHorarios() {
    }

    public ModeloHorarios(String DESCRIPCION, String HENTRADA, String HSALIDA, String HSCOMIDA, String HECOMIDA, String DESCANSOS) {
        this.DESCRIPCION = DESCRIPCION;
        this.HENTRADA = HENTRADA;
        this.HSALIDA = HSALIDA;
        this.HSCOMIDA = HSCOMIDA;
        this.HECOMIDA = HECOMIDA;
        this.DESCANSOS = DESCANSOS;
    }

    public ModeloHorarios(int IDHORARIO, String DESCRIPCION, String HENTRADA, String HSALIDA, String HSCOMIDA, String HECOMIDA, String DESCANSOS) {
        this.IDHORARIO = IDHORARIO;
        this.DESCRIPCION = DESCRIPCION;
        this.HENTRADA = HENTRADA;
        this.HSALIDA = HSALIDA;
        this.HSCOMIDA = HSCOMIDA;
        this.HECOMIDA = HECOMIDA;
        this.DESCANSOS = DESCANSOS;
    }

    public int getIDHORARIO() {
        return IDHORARIO;
    }

    public void setIDHORARIO(int IDHORARIO) {
        this.IDHORARIO = IDHORARIO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getHENTRADA() {
        return HENTRADA;
    }

    public void setHENTRADA(String HENTRADA) {
        this.HENTRADA = HENTRADA;
    }

    public String getHSALIDA() {
        return HSALIDA;
    }

    public void setHSALIDA(String HSALIDA) {
        this.HSALIDA = HSALIDA;
    }

    public String getHSCOMIDA() {
        return HSCOMIDA;
    }

    public void setHSCOMIDA(String HSCOMIDA) {
        this.HSCOMIDA = HSCOMIDA;
    }

    public String getHECOMIDA() {
        return HECOMIDA;
    }

    public void setHECOMIDA(String HECOMIDA) {
        this.HECOMIDA = HECOMIDA;
    }

    public String getDESCANSOS() {
        return DESCANSOS;
    }

    public void setDESCANSOS(String DESCANSOS) {
        this.DESCANSOS = DESCANSOS;
    }
    
    
}
