/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloProductosD
{
    private String CB;
    private int IDPRODUCTO;

    private String SICAR;
    private String DESCRIPCION;
    private String CATEGORIA;
    private float PP;
    private float PI;
    private float KIT;
    
    private String CLAVESAT;
    private int INVENTARIO;
    private String ESTADO;
    private String FOTO;
    
    private String MARCA;
    private String PROVEEDOR;
    private String SKU;
    private boolean CPP;
    private boolean CPI;

    public ModeloProductosD(String CB, int IDPRODUCTO, String SICAR, String DESCRIPCION, String CATEGORIA, float PP, float PI, float KIT, String CLAVESAT, int INVENTARIO, String ESTADO, String FOTO, String MARCA, String PROVEEDOR, String SKU, boolean CPP, boolean CPI) {
        this.CB = CB;
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CLAVESAT = CLAVESAT;
        this.INVENTARIO = INVENTARIO;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
        this.MARCA = MARCA;
        this.PROVEEDOR = PROVEEDOR;
        this.SKU = SKU;
        this.CPP = CPP;
        this.CPI = CPI;
    }

    public ModeloProductosD(int IDPRODUCTO, String SICAR, String DESCRIPCION, String CATEGORIA, float PP, float PI, float KIT, String CLAVESAT, int INVENTARIO, String ESTADO, String FOTO, String MARCA, String PROVEEDOR, String SKU, boolean CPP, boolean CPI) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CLAVESAT = CLAVESAT;
        this.INVENTARIO = INVENTARIO;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
        this.MARCA = MARCA;
        this.PROVEEDOR = PROVEEDOR;
        this.SKU = SKU;
        this.CPP = CPP;
        this.CPI = CPI;
    }
    
    

    public ModeloProductosD(String CB, int IDPRODUCTO, String CLAVESAT, int INVENTARIO, String ESTADO, String FOTO) {
        this.CB = CB;
        this.IDPRODUCTO = IDPRODUCTO;
        this.CLAVESAT = CLAVESAT;
        this.INVENTARIO = INVENTARIO;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
    }

    public ModeloProductosD(String CB, int IDPRODUCTO, String SICAR, String DESCRIPCION, String CATEGORIA, float PP, float PI, float KIT, String CLAVESAT, int INVENTARIO, String ESTADO, String FOTO) {
        this.CB = CB;
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CLAVESAT = CLAVESAT;
        this.INVENTARIO = INVENTARIO;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
    }
    
    public ModeloProductosD(int IDPRODUCTO, String SICAR, String DESCRIPCION, String CATEGORIA, float PP, float PI, float KIT, String CLAVESAT, int INVENTARIO, String ESTADO, String FOTO) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CLAVESAT = CLAVESAT;
        this.INVENTARIO = INVENTARIO;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
    }
    
    public ModeloProductosD(String CB, int IDPRODUCTO, String SICAR, String DESCRIPCION, String CATEGORIA, float PP, float PI, float KIT) {
        this.CB = CB;
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
    }
    
    public ModeloProductosD(String CB, int IDPRODUCTO, String SICAR, String DESCRIPCION, String CATEGORIA) {
        this.CB = CB;
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;
    }

    public ModeloProductosD(int IDPRODUCTO, String SICAR, String DESCRIPCION, String CATEGORIA) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
        this.CATEGORIA = CATEGORIA;
    }
        
    public ModeloProductosD(String CB, int IDPRODUCTO, String SICAR, String DESCRIPCION) {
        this.CB = CB;
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
    }

    public ModeloProductosD(int IDPRODUCTO, String SICAR, String DESCRIPCION) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.DESCRIPCION = DESCRIPCION;
    }
    
    
    public ModeloProductosD() {
    }

    public ModeloProductosD(String CB, int IDPRODUCTO) {
        this.CB = CB;
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public ModeloProductosD(String CB) {
        this.CB = CB;
    }

    public String getCB() {
        return CB;
    }

    public void setCB(String CB) {
        this.CB = CB;
    }

    public int getIDPRODUCTO() {
        return IDPRODUCTO;
    }

    public void setIDPRODUCTO(int IDPRODUCTO) {
        this.IDPRODUCTO = IDPRODUCTO;
    }

    public String getSICAR() {
        return SICAR;
    }

    public void setSICAR(String SICAR) {
        this.SICAR = SICAR;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public float getPP() {
        return PP;
    }

    public void setPP(float PP) {
        this.PP = PP;
    }

    public float getPI() {
        return PI;
    }

    public void setPI(float PI) {
        this.PI = PI;
    }

    public float getKIT() {
        return KIT;
    }

    public void setKIT(float KIT) {
        this.KIT = KIT;
    }

    public String getCLAVESAT() {
        return CLAVESAT;
    }

    public void setCLAVESAT(String CLAVESAT) {
        this.CLAVESAT = CLAVESAT;
    }

    public int getINVENTARIO() {
        return INVENTARIO;
    }

    public void setINVENTARIO(int INVENTARIO) {
        this.INVENTARIO = INVENTARIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }       

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getPROVEEDOR() {
        return PROVEEDOR;
    }

    public void setPROVEEDOR(String PROVEEDOR) {
        this.PROVEEDOR = PROVEEDOR;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public boolean getCPP() {
        return CPP;
    }

    public void setCPP(boolean CPP) {
        this.CPP = CPP;
    }

    public boolean getCPI() {
        return CPI;
    }

    public void setCPI(boolean CPI) {
        this.CPI = CPI;
    }
    
    
}
