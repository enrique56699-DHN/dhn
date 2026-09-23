/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ModeloUsuarios;
import UTILERIAS.PasswordUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class ControlUsuarios extends ControlAbstracto
{
    /*private final String TABLA = "USUARIOS";

    public ControlUsuarios() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }
   
    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloUsuarios> lista = new ArrayList<>();
        sql = "select * from "+TABLA+"";
        rs = mane.consultar(sql);
        ModeloUsuarios mdl;
        while(rs.next())
        {
            mdl = new ModeloUsuarios();
            mdl.setIDUSUARIO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            mdl.setPASSWORD(rs.getString(3));
            mdl.setPRIVILEGIOS(rs.getString(4));       
            
            lista.add(mdl);

        }
        mane.limpiarRecursos();
        return lista;
    }

    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloUsuarios mdl = (ModeloUsuarios)model;
        sql = "INSERT INTO "+TABLA+" (IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS)"
                + "VALUES (" + 
                mdl.getIDUSUARIO() + ",'" +
                mdl.getNOMBRE() + "','" +
                //ModeloFecha.encriptarPassword(mdl.getPASSWORD()) + "','" +
                mdl.getPASSWORD() + "','" +
                mdl.getPRIVILEGIOS() + "' )"; 
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloUsuarios mdl = (ModeloUsuarios)model;
        sql = "update "+TABLA+" set "
                + "NOMBRE='" + mdl.getNOMBRE()
                //+ "',PASSWORD='" + ModeloFecha.encriptarPassword(mdl.getPASSWORD())
                + "',PASSWORD='" + mdl.getPASSWORD()
                + "',PRIVILEGIOS='" + mdl.getPRIVILEGIOS()
                + "' where IDUSUARIO=" + mdl.getIDUSUARIO() + " ";
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }
    
    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloUsuarios mdl = (ModeloUsuarios)model;
        sql=("delete from "+TABLA+" where IDUSUARIO =" + mdl.getIDUSUARIO() + "");
        mane.Ejecutar(sql);
        mane.limpiarRecursos();
    }

    public ModeloUsuarios getRegistros(int IDUSUARIO, String NOMBRE) throws Exception 
    {
       
        if(IDUSUARIO == 0)
        {
            sql = "select * from "+TABLA+" WHERE NOMBRE = '"+NOMBRE+"' ";
        }
        else
            sql = "select * from "+TABLA+" WHERE IDUSUARIO = "+IDUSUARIO+" ";
        
        rs = mane.consultar(sql);
        ModeloUsuarios mdl = null;
        while(rs.next())
        {
            mdl = new ModeloUsuarios();
            mdl.setIDUSUARIO(rs.getInt(1));
            mdl.setNOMBRE(rs.getString(2));
            //mdl.setPASSWORD(ModeloFecha.desEncriptarPassword(rs.getString(3)));
            mdl.setPASSWORD(rs.getString(3));
            mdl.setPRIVILEGIOS(rs.getString(4));
        }
        mane.limpiarRecursos();
        return mdl;
    }
    
    public boolean getSADMIN(String NOMBRE, String PASSWORD) throws Exception
    {
        String PRIVILEGIOS="SADMIN";
        sql = "SELECT idusuario, nombre, password, privilegios from usuarios where nombre='"+NOMBRE+"' and PASSWORD ='"+PASSWORD+"' and PRIVILEGIOS ='"+PRIVILEGIOS+"'";
        rs = mane.consultar(sql);
        ModeloUsuarios x = null;
        if(rs.next())
        {
            x = new ModeloUsuarios();
            x.setIDUSUARIO(rs.getInt(1));
            x.setNOMBRE(rs.getString(2));
            x.setPASSWORD(rs.getString(3));
            x.setPRIVILEGIOS(rs.getString(4));
            rs.close();
            return true;
        }
        return false;
        
    }*/
    
    private final String TABLA = "USUARIOS";

    public ControlUsuarios() throws ClassNotFoundException, SQLException {
        this.mane = new Manejador();
    }

    @Override
    public List getRegistros() throws Exception {
        ArrayList<ModeloUsuarios> lista = new ArrayList<>();
        sql = "select IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS from " + TABLA;
        rs = mane.consultar(sql);
        ModeloUsuarios mdl;
        while (rs.next()) {
            mdl = mapearFila(rs);
            lista.add(mdl);
        }
        mane.limpiarRecursos();
        return lista;
    }

    /**
     * Alta de usuario.El PASSWORD que llega en el modelo debe ser texto plano
 (tal como lo captura el formulario); aqui se hashea antes de guardar.NUNCA guardar un password sin pasar por PasswordUtil.hash().
     * @param model
     * @throws java.lang.Exception
     */
    @Override
    public void guardarRegistro(Object model) throws Exception {
        ModeloUsuarios mdl = (ModeloUsuarios) model;
        String hash = PasswordUtil.hash(mdl.getPASSWORD());

        sql = "INSERT INTO " + TABLA + " (IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS) VALUES (?, ?, ?, ?)";
        mane.EjecutarSeguro(sql, mdl.getIDUSUARIO(), mdl.getNOMBRE(), hash, mdl.getPRIVILEGIOS());
        mane.limpiarRecursos();
    }

    /**
     * Actualiza NOMBRE/PRIVILEGIOS y el PASSWORD (con nuevo hash) de un usuario.Si en tu formulario el cambio de password es opcional, ver actualizarSinPassword().
     * @param model
     * @throws java.lang.Exception
     */
    @Override
    public void actualizarRegistro(Object model) throws Exception {
        ModeloUsuarios mdl = (ModeloUsuarios) model;
        String hash = PasswordUtil.hash(mdl.getPASSWORD());

        sql = "UPDATE " + TABLA + " SET NOMBRE = ?, PASSWORD = ?, PRIVILEGIOS = ? WHERE IDUSUARIO = ?";
        mane.EjecutarSeguro(sql, mdl.getNOMBRE(), hash, mdl.getPRIVILEGIOS(), mdl.getIDUSUARIO());
        mane.limpiarRecursos();
    }

    /**
     * Igual que actualizarRegistro pero sin tocar el password (para cuando el
     * formulario de edicion deja el campo de contraseña en blanco = "no cambiar").
     * @param mdl
     * @throws java.lang.Exception
     */
    public void actualizarSinPassword(ModeloUsuarios mdl) throws Exception {
        sql = "UPDATE " + TABLA + " SET NOMBRE = ?, PRIVILEGIOS = ? WHERE IDUSUARIO = ?";
        mane.EjecutarSeguro(sql, mdl.getNOMBRE(), mdl.getPRIVILEGIOS(), mdl.getIDUSUARIO());
        mane.limpiarRecursos();
    }

    @Override
    public void eliminarRegistro(Object model) throws Exception {
        ModeloUsuarios mdl = (ModeloUsuarios) model;
        sql = "DELETE FROM " + TABLA + " WHERE IDUSUARIO = ?";
        mane.EjecutarSeguro(sql, mdl.getIDUSUARIO());
        mane.limpiarRecursos();
    }

    public ModeloUsuarios getRegistros(int IDUSUARIO, String NOMBRE) throws Exception {
        ModeloUsuarios mdl = null;
        if (IDUSUARIO == 0) {
            sql = "SELECT IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS FROM " + TABLA + " WHERE NOMBRE = ?";
            rs = mane.consultarSeguro(sql, NOMBRE);
        } else {
            sql = "SELECT IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS FROM " + TABLA + " WHERE IDUSUARIO = ?";
            rs = mane.consultarSeguro(sql, IDUSUARIO);
        }
        if (rs.next()) {
            mdl = mapearFila(rs);
        }
        mane.limpiarRecursos();
        return mdl;
    }

    /**
     * METODO NUEVO — usar este para el login en vez de comparar PASSWORD a mano.Devuelve el ModeloUsuarios si NOMBRE + passwordPlano son validos, o null
 si el usuario no existe o el password es incorrecto.No hay ningun valor
 "comodin" que autentique: si no coincide, no coincide.Migracion transparente: si la cuenta todavia tiene password en texto
 plano (dato heredado), y coincide, se re-hashea con BCrypt automaticamente
 antes de retornar.Cuentas nuevas siempre se crean ya con hash (ver
 guardarRegistro), asi que este camino solo aplica a cuentas viejas.
     *
     * @param NOMBRE
     * @param passwordPlano
     * @return 
     * @throws java.lang.Exception
     */
    public ModeloUsuarios autenticar(String NOMBRE, String passwordPlano) throws Exception {
        sql = "SELECT IDUSUARIO, NOMBRE, PASSWORD, PRIVILEGIOS FROM " + TABLA + " WHERE NOMBRE = ?";
        rs = mane.consultarSeguro(sql, NOMBRE);

        ModeloUsuarios mdl = null;
        if (rs.next()) {
            mdl = mapearFila(rs);
        }
        mane.limpiarRecursos();

        if (mdl == null) {
            return null; // usuario no existe
        }

        String valorGuardado = mdl.getPASSWORD();

        if (PasswordUtil.esHashBCrypt(valorGuardado)) {
            // Caso normal: cuenta ya migrada
            return PasswordUtil.verificar(passwordPlano, valorGuardado) ? mdl : null;
        } else {
            // Caso heredado: password aun en texto plano
            if (valorGuardado != null && valorGuardado.equals(passwordPlano)) {
                // Password correcto: migrar a BCrypt silenciosamente
                String nuevoHash = PasswordUtil.hash(passwordPlano);
                sql = "UPDATE " + TABLA + " SET PASSWORD = ? WHERE IDUSUARIO = ?";
                mane.EjecutarSeguro(sql, nuevoHash, mdl.getIDUSUARIO());
                mane.limpiarRecursos();
                mdl.setPASSWORD(nuevoHash);
                return mdl;
            }
            return null;
        }
    }

    public boolean getSADMIN(String NOMBRE, String PASSWORD) throws Exception {
        ModeloUsuarios mdl = autenticar(NOMBRE, PASSWORD);
        return mdl != null && "SADMIN".equals(mdl.getPRIVILEGIOS());
    }

    private ModeloUsuarios mapearFila(ResultSet rs) throws SQLException {
        ModeloUsuarios mdl = new ModeloUsuarios();
        mdl.setIDUSUARIO(rs.getInt("IDUSUARIO"));
        mdl.setNOMBRE(rs.getString("NOMBRE"));
        mdl.setPASSWORD(rs.getString("PASSWORD"));
        mdl.setPRIVILEGIOS(rs.getString("PRIVILEGIOS"));
        return mdl;
    }
}
