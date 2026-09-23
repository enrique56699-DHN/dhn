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
public class ModeloPaquetes
{
    private int IDPAQUETE;
    private int COSTO;
    private String DESCRIPCION;

    public ModeloPaquetes() {
    }

    public ModeloPaquetes(int IDPAQUETE, int COSTO, String DESCRIPCION) {
        this.IDPAQUETE = IDPAQUETE;
        this.COSTO = COSTO;
        this.DESCRIPCION = DESCRIPCION;
    }

    public ModeloPaquetes(int COSTO, String DESCRIPCION) {
        this.COSTO = COSTO;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getIDPAQUETE() {
        return IDPAQUETE;
    }

    public void setIDPAQUETE(int IDPAQUETE) {
        this.IDPAQUETE = IDPAQUETE;
    }

    public int getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(int COSTO) {
        this.COSTO = COSTO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
    
    
}
