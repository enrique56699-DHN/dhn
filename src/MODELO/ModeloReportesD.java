/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloReportesD {
    
    private int IDREPORTED;
    private int IDREPORTE;
    private String DESCRIPCION;
    private String RUTA;

    public ModeloReportesD() {
    }

    public ModeloReportesD(int IDREPORTED, int IDREPORTE, String DESCRIPCION, String RUTA) {
        this.IDREPORTED = IDREPORTED;
        this.IDREPORTE = IDREPORTE;
        this.DESCRIPCION = DESCRIPCION;
        this.RUTA = RUTA;
    }

    public ModeloReportesD(int IDREPORTE, String DESCRIPCION, String RUTA) {
        this.IDREPORTE = IDREPORTE;
        this.DESCRIPCION = DESCRIPCION;
        this.RUTA = RUTA;
    }

    public int getIDREPORTED() {
        return IDREPORTED;
    }

    public void setIDREPORTED(int IDREPORTED) {
        this.IDREPORTED = IDREPORTED;
    }

    public int getIDREPORTE() {
        return IDREPORTE;
    }

    public void setIDREPORTE(int IDREPORTE) {
        this.IDREPORTE = IDREPORTE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getRUTA() {
        return RUTA;
    }

    public void setRUTA(String RUTA) {
        this.RUTA = RUTA;
    }
    
    
}
