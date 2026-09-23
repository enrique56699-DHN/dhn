/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ModeloSalidas
{    
    private int IDSALIDA;
    private String DESCRIPCION;
    private float MONTO;
    private String CATEGORIA;
    private String FECHA;
    private String METODO;
    private int IDCAJA;

    public ModeloSalidas() {
    }

    public ModeloSalidas(int IDSALIDA, String DESCRIPCION, float MONTO, String CATEGORIA, String FECHA, String METODO, int IDCAJA) {
        this.IDSALIDA = IDSALIDA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.CATEGORIA = CATEGORIA;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.IDCAJA = IDCAJA;
    }

    public ModeloSalidas(String DESCRIPCION, float MONTO, String CATEGORIA, String FECHA, String METODO, int IDCAJA) {
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.CATEGORIA = CATEGORIA;
        this.FECHA = FECHA;
        this.METODO = METODO;
        this.IDCAJA = IDCAJA;
    }

    public ModeloSalidas(int IDSALIDA, String DESCRIPCION, float MONTO, int IDCAJA) {
        this.IDSALIDA = IDSALIDA;
        this.DESCRIPCION = DESCRIPCION;
        this.MONTO = MONTO;
        this.IDCAJA = IDCAJA;
    }
    
    

    public int getIDSALIDA() {
        return IDSALIDA;
    }

    public void setIDSALIDA(int IDSALIDA) {
        this.IDSALIDA = IDSALIDA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public float getMONTO() {
        return MONTO;
    }

    public void setMONTO(float MONTO) {
        this.MONTO = MONTO;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getMETODO() {
        return METODO;
    }

    public void setMETODO(String METODO) {
        this.METODO = METODO;
    }

    public int getIDCAJA() {
        return IDCAJA;
    }

    public void setIDCAJA(int IDCAJA) {
        this.IDCAJA = IDCAJA;
    }
    
}
