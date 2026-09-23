/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloKitsCategorias
{
    private String CATEGORIA;
    private String CATEGORIAANT;

    public ModeloKitsCategorias(String CATEGORIA, String CATEGORIAANT) {
        this.CATEGORIA = CATEGORIA;
        this.CATEGORIAANT = CATEGORIAANT;
    }

    
    public ModeloKitsCategorias() {
    }

    public ModeloKitsCategorias(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getCATEGORIAANT() {
        return CATEGORIAANT;
    }

    public void setCATEGORIAANT(String CATEGORIAANT) {
        this.CATEGORIAANT = CATEGORIAANT;
    }
    
    
}
