/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloEPuestos 
{
    private int IDEPUESTO;
    private String DEPTO;
    private String PUESTO;
    private String DOC;
    private float SUELDO;

    public ModeloEPuestos() {
    }

    public ModeloEPuestos(int IDEPUESTO, String DEPTO, String PUESTO, String DOC, float SUELDO) {
        this.IDEPUESTO = IDEPUESTO;
        this.DEPTO = DEPTO;
        this.PUESTO = PUESTO;
        this.DOC = DOC;
        this.SUELDO = SUELDO;
    }

    public ModeloEPuestos(String DEPTO, String PUESTO, String DOC, float SUELDO) {
        this.DEPTO = DEPTO;
        this.PUESTO = PUESTO;
        this.DOC = DOC;
        this.SUELDO = SUELDO;
    }

    public int getIDEPUESTO() {
        return IDEPUESTO;
    }

    public void setIDEPUESTO(int IDEPUESTO) {
        this.IDEPUESTO = IDEPUESTO;
    }

    public String getDEPTO() {
        return DEPTO;
    }

    public void setDEPTO(String DEPTO) {
        this.DEPTO = DEPTO;
    }

    public String getPUESTO() {
        return PUESTO;
    }

    public void setPUESTO(String PUESTO) {
        this.PUESTO = PUESTO;
    }

    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }

    public float getSUELDO() {
        return SUELDO;
    }

    public void setSUELDO(float SUELDO) {
        this.SUELDO = SUELDO;
    }
    
    
    
}
