/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Admin
 */
public class ModeloEDocs
{
    private int IDEDOC;
    private int IDEMPLEADO;
    private String NUMBER;
    private String DESCRIPCION;
    private String VIGENCIA;
    private String DOC;          
        
    private String NOMBRE;
    private String APELLIDOS;

    public ModeloEDocs() {
    }

    public ModeloEDocs(int IDEDOC, int IDEMPLEADO, String NUMBER, String DESCRIPCION, String VIGENCIA, String DOC, String NOMBRE, String APELLIDOS) {
        this.IDEDOC = IDEDOC;
        this.IDEMPLEADO = IDEMPLEADO;
        this.NUMBER = NUMBER;
        this.DESCRIPCION = DESCRIPCION;
        this.VIGENCIA = VIGENCIA;
        this.DOC = DOC;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
    }

    public ModeloEDocs(int IDEMPLEADO, String NUMBER, String DESCRIPCION, String VIGENCIA, String DOC, String NOMBRE, String APELLIDOS) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.NUMBER = NUMBER;
        this.DESCRIPCION = DESCRIPCION;
        this.VIGENCIA = VIGENCIA;
        this.DOC = DOC;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
    }

    public ModeloEDocs(int IDEDOC, int IDEMPLEADO, String NUMBER, String DESCRIPCION, String VIGENCIA, String DOC) {
        this.IDEDOC = IDEDOC;
        this.IDEMPLEADO = IDEMPLEADO;
        this.NUMBER = NUMBER;
        this.DESCRIPCION = DESCRIPCION;
        this.VIGENCIA = VIGENCIA;
        this.DOC = DOC;
    }

    public ModeloEDocs(int IDEMPLEADO, String NUMBER, String DESCRIPCION, String VIGENCIA, String DOC) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.NUMBER = NUMBER;
        this.DESCRIPCION = DESCRIPCION;
        this.VIGENCIA = VIGENCIA;
        this.DOC = DOC;
    }
    

    public int getIDEDOC() {
        return IDEDOC;
    }

    public void setIDEDOC(int IDEDOC) {
        this.IDEDOC = IDEDOC;
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getVIGENCIA() {
        return VIGENCIA;
    }

    public void setVIGENCIA(String VIGENCIA) {
        this.VIGENCIA = VIGENCIA;
    }    

    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }
    
    
}
