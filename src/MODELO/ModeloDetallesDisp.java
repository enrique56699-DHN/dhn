/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloDetallesDisp 
{
    private int IDD;
    private String TABLA;
    private String CATEGORIA;
    private String DESCRIPCION;

    public ModeloDetallesDisp(int IDD, String TABLA, String CATEGORIA, String DESCRIPCION) {
        this.IDD = IDD;
        this.TABLA = TABLA;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
    }

    public ModeloDetallesDisp(String TABLA, String CATEGORIA, String DESCRIPCION) {
        this.TABLA = TABLA;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
    }

    public ModeloDetallesDisp() {
    }

    public int getIDD() {
        return IDD;
    }

    public void setIDD(int IDD) {
        this.IDD = IDD;
    }

    public String getTABLA() {
        return TABLA;
    }

    public void setTABLA(String TABLA) {
        this.TABLA = TABLA;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
    
    
    
}
