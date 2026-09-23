/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloProductos
{
    private int IDPRODUCTO;
    private String SICAR;
    private String CATEGORIA;
    private String DESCRIPCION;
    private double COSTO;
    private String MONEDA;
    private boolean IVA;
    private double PP;
    private double PI;
    
    private String KIT;
    
    private String CB;
    private String CLAVESAT;
    private String ESTADO;
    private String FOTO;
    
    private String MARCA;
    private String PROVEEDOR;
    private String SKU;
    private boolean CPP;
    private boolean CPI;

    public ModeloProductos(int IDPRODUCTO, String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT, String ESTADO, String FOTO, String MARCA, String PROVEEDOR, String SKU, boolean CPP, boolean CPI) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
        this.MARCA = MARCA;
        this.PROVEEDOR = PROVEEDOR;
        this.SKU = SKU;
        this.CPP = CPP;
        this.CPI = CPI;
    }

    public ModeloProductos(String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT, String ESTADO, String FOTO, String MARCA, String PROVEEDOR, String SKU, boolean CPP, boolean CPI) {
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
        this.MARCA = MARCA;
        this.PROVEEDOR = PROVEEDOR;
        this.SKU = SKU;
        this.CPP = CPP;
        this.CPI = CPI;
    }

    public ModeloProductos(int IDPRODUCTO, String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String CB, String CLAVESAT, String ESTADO, String FOTO, String MARCA, String PROVEEDOR, String SKU, boolean CPP, boolean CPI) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
        this.MARCA = MARCA;
        this.PROVEEDOR = PROVEEDOR;
        this.SKU = SKU;
        this.CPP = CPP;
        this.CPI = CPI;
    }
    
    

    public ModeloProductos(int IDPRODUCTO, String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT, String ESTADO, String FOTO) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
    }

    public ModeloProductos(String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT, String ESTADO, String FOTO) {
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
        this.ESTADO = ESTADO;
        this.FOTO = FOTO;
    }

    public ModeloProductos(String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT, String FOTO) {
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
        this.FOTO = FOTO;
    }

    public ModeloProductos(int IDPRODUCTO, String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT, String FOTO) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
        this.FOTO = FOTO;
    }

    public ModeloProductos(int IDPRODUCTO, String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
    }

    public ModeloProductos(String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT, String CB, String CLAVESAT) {
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
        this.CB = CB;
        this.CLAVESAT = CLAVESAT;
    }
    
    

    public ModeloProductos(int IDPRODUCTO, String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
    }

    public ModeloProductos(String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI, String KIT) {
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
        this.KIT = KIT;
    }
    
    public ModeloProductos() {
    }

    public ModeloProductos(int IDPRODUCTO, String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
    }

    public ModeloProductos(String SICAR, String CATEGORIA, String DESCRIPCION, double COSTO, String MONEDA, boolean IVA, double PP, double PI) {
        this.SICAR = SICAR;
        this.CATEGORIA = CATEGORIA;
        this.DESCRIPCION = DESCRIPCION;
        this.COSTO = COSTO;
        this.MONEDA = MONEDA;
        this.IVA = IVA;
        this.PP = PP;
        this.PI = PI;
    }

    public ModeloProductos(int IDPRODUCTO, String DESCRIPCION, double PP) {
        this.IDPRODUCTO = IDPRODUCTO;
        this.DESCRIPCION = DESCRIPCION;
        this.PP = PP;
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

    public double getCOSTO() {
        return COSTO;
    }

    public void setCOSTO(double COSTO) {
        this.COSTO = COSTO;
    }

    public String getMONEDA() {
        return MONEDA;
    }

    public void setMONEDA(String MONEDA) {
        this.MONEDA = MONEDA;
    }

    public boolean getIVA() {
        return IVA;
    }

    public void setIVA(boolean IVA) {
        this.IVA = IVA;
    }

    public double getPP() {
        return PP;
    }

    public void setPP(double PP) {
        this.PP = PP;
    }

    public double getPI() {
        return PI;
    }

    public void setPI(double PI) {
        this.PI = PI;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }

    public String getKIT() {
        return KIT;
    }

    public void setKIT(String KIT) {
        this.KIT = KIT;
    }   

    public String getCB() {
        return CB;
    }

    public void setCB(String CB) {
        this.CB = CB;
    }

    public String getCLAVESAT() {
        return CLAVESAT;
    }

    public void setCLAVESAT(String CLAVESAT) {
        this.CLAVESAT = CLAVESAT;
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
