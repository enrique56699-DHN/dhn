/*

 */
package MODELO;

/**
 *
 * @author win 10
 */
public class ModeloPdocs 
{
    private int IDPDOC;
    private int IDPENDIENTE;
    private String FECHA;
    private String DOC;
    private String COMENTARIOS;

    public ModeloPdocs() {
    }

    public ModeloPdocs(int IDPDOC, int IDPENDIENTE, String FECHA, String DOC, String COMENTARIOS) {
        this.IDPDOC = IDPDOC;
        this.IDPENDIENTE = IDPENDIENTE;
        this.FECHA = FECHA;
        this.DOC = DOC;
        this.COMENTARIOS = COMENTARIOS;
    }

    public ModeloPdocs(int IDPENDIENTE, String FECHA, String DOC, String COMENTARIOS) {
        this.IDPENDIENTE = IDPENDIENTE;
        this.FECHA = FECHA;
        this.DOC = DOC;
        this.COMENTARIOS = COMENTARIOS;
    }

    
    public int getIDPDOC() {
        return IDPDOC;
    }

    public void setIDPDOC(int IDPDOC) {
        this.IDPDOC = IDPDOC;
    }

    public int getIDPENDIENTE() {
        return IDPENDIENTE;
    }

    public void setIDPENDIENTE(int IDPENDIENTE) {
        this.IDPENDIENTE = IDPENDIENTE;
    }

    public String getDOC() {
        return DOC;
    }

    public void setDOC(String DOC) {
        this.DOC = DOC;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }   
    
}
