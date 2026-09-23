/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloKitsD
{
    private int IDKITD;
    private int IDKIT;
    private int IDPRODUCTO;
    private int CANT;
    
    private String DESCRIPCION;
    private String NOMBRE;
    private float PKIT;
    private float COSTO;
    private String FOTO;    
    private String ESTADO;

    public ModeloKitsD(int IDKITD, int CANT, String DESCRIPCION, float COSTO, String FOTO, String ESTADO) {
        this.IDKITD = IDKITD;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.FOTO = FOTO;
        this.ESTADO = ESTADO;
    }
    
    

    public ModeloKitsD(int IDKITD, int IDKIT, int IDPRODUCTO, int CANT, String DESCRIPCION, String NOMBRE, float PKIT, float COSTO, String FOTO, String ESTADO) {
        this.IDKITD = IDKITD;
        this.IDKIT = IDKIT;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.NOMBRE = NOMBRE;
        this.PKIT = PKIT;
        this.COSTO = COSTO;
        this.FOTO = FOTO;
        this.ESTADO = ESTADO;
    }

    public ModeloKitsD(int IDKIT, int IDPRODUCTO, int CANT, String DESCRIPCION, String NOMBRE, float PKIT, float COSTO, String FOTO, String ESTADO) {
        this.IDKIT = IDKIT;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.NOMBRE = NOMBRE;
        this.PKIT = PKIT;
        this.COSTO = COSTO;
        this.FOTO = FOTO;
        this.ESTADO = ESTADO;
    }
    
    
    
    public ModeloKitsD() {
    }

    public ModeloKitsD(int IDKITD, int IDKIT, int IDPRODUCTO, int CANT, String DESCRIPCION, String NOMBRE, float PKIT) {
        this.IDKITD = IDKITD;
        this.IDKIT = IDKIT;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.NOMBRE = NOMBRE;
        this.PKIT = PKIT;
    }

    public ModeloKitsD(int IDKIT, int IDPRODUCTO, int CANT, String DESCRIPCION, String NOMBRE, float PKIT) {
        this.IDKIT = IDKIT;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.NOMBRE = NOMBRE;
        this.PKIT = PKIT;
    }

    public ModeloKitsD(int IDKITD, int CANT, String DESCRIPCION, float COSTO, String FOTO) {
        this.IDKITD = IDKITD;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.FOTO = FOTO;
    }
    
    

    public ModeloKitsD(int IDPRODUCTO, int CANT) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANT = CANT;
    }
    
    public ModeloKitsD(int IDKITD, int IDKIT, int IDPRODUCTO, int CANT, String DESCRIPCION, String NOMBRE) {
        this.IDKITD = IDKITD;
        this.IDKIT = IDKIT;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.NOMBRE = NOMBRE;
    }

    public ModeloKitsD(int IDKIT, int IDPRODUCTO, int CANT, String DESCRIPCION, String NOMBRE) {
        this.IDKIT = IDKIT;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CANT = CANT;
        this.DESCRIPCION = DESCRIPCION;
        this.NOMBRE = NOMBRE;
    }

    public int getIDKITD() {
        return IDKITD;
    }

    public void setIDKITD(int IDKITD) {
        this.IDKITD = IDKITD;
    }

    public int getIDKIT() {
        return IDKIT;
    }

    public void setIDKIT(int IDKIT) {
        this.IDKIT = IDKIT;
    }

    public int getIDPRODUCTO() {
        return IDPRODUCTO;
    }

    public void setIDPRODUCTO(int IDPRODUCTO) {
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public int getCANT() {
        return CANT;
    }

    public void setCANT(int CANT) {
        this.CANT = CANT;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public float getPKIT() {
        return PKIT;
    }

    public void setPKIT(float PKIT) {
        this.PKIT = PKIT;
    }

    public float getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(float COSTO) {
        this.COSTO = COSTO;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    
}
