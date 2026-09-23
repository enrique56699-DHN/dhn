/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloPrivilegios
{
    private int IDPRIVILEGIO;
    private String GRUPO;
    private String ITEM;

    public ModeloPrivilegios() {
    }

    public ModeloPrivilegios(int IDPRIVILEGIO, String GRUPO, String ITEM) {
        this.IDPRIVILEGIO = IDPRIVILEGIO;
        this.GRUPO = GRUPO;
        this.ITEM = ITEM;
    }

    public ModeloPrivilegios(String GRUPO, String ITEM) {
        this.GRUPO = GRUPO;
        this.ITEM = ITEM;
    }

    public int getIDPRIVILEGIO() {
        return IDPRIVILEGIO;
    }

    public void setIDPRIVILEGIO(int IDPRIVILEGIO) {
        this.IDPRIVILEGIO = IDPRIVILEGIO;
    }

    public String getGRUPO() {
        return GRUPO;
    }

    public void setGRUPO(String GRUPO) {
        this.GRUPO = GRUPO;
    }

    public String getITEM() {
        return ITEM;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }
    
    
}
