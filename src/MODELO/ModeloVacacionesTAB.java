/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloVacacionesTAB
{
    private int IDVACACIONTAB;
    private int DIAS;

    public ModeloVacacionesTAB(int IDVACACIONTAB, int DIAS) {
        this.IDVACACIONTAB = IDVACACIONTAB;
        this.DIAS = DIAS;
    }

    public ModeloVacacionesTAB() {
    }

    public int getIDVACACIONTAB() {
        return IDVACACIONTAB;
    }

    public void setIDVACACIONTAB(int IDVACACIONTAB) {
        this.IDVACACIONTAB = IDVACACIONTAB;
    }

    public int getDIAS() {
        return DIAS;
    }

    public void setDIAS(int DIAS) {
        this.DIAS = DIAS;
    }
    
    
    
}
