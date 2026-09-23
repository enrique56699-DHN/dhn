/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloKits
{
    private int IDKIT;
    private String NOMBRE;
    private String CATEGORIA;
    private float COSTO;
    private float PRECIO;
    private String FOTO;

    public ModeloKits(int IDKIT, String NOMBRE, String CATEGORIA, float COSTO, float PRECIO, String FOTO) {
        this.IDKIT = IDKIT;
        this.NOMBRE = NOMBRE;
        this.CATEGORIA = CATEGORIA;
        this.COSTO = COSTO;
        this.PRECIO = PRECIO;
        this.FOTO = FOTO;
    }

    public ModeloKits(String NOMBRE, String CATEGORIA, float COSTO, float PRECIO, String FOTO) {
        this.NOMBRE = NOMBRE;
        this.CATEGORIA = CATEGORIA;
        this.COSTO = COSTO;
        this.PRECIO = PRECIO;
        this.FOTO = FOTO;
    }
    
    public ModeloKits() {
    }

    public ModeloKits(int IDKIT, String NOMBRE, String CATEGORIA, float COSTO, float PRECIO) {
        this.IDKIT = IDKIT;
        this.NOMBRE = NOMBRE;
        this.CATEGORIA = CATEGORIA;
        this.COSTO = COSTO;
        this.PRECIO = PRECIO;
    }

    public ModeloKits(String NOMBRE, String CATEGORIA, float COSTO, float PRECIO) {
        this.NOMBRE = NOMBRE;
        this.CATEGORIA = CATEGORIA;
        this.COSTO = COSTO;
        this.PRECIO = PRECIO;
    }

    public int getIDKIT() {
        return IDKIT;
    }

    public void setIDKIT(int IDKIT) {
        this.IDKIT = IDKIT;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public float getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(float COSTO) {
        this.COSTO = COSTO;
    }

    public float getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(float PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }
    
    
}
