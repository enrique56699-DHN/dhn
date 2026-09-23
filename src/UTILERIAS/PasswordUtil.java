/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILERIAS;

/**
 *
 * @author Admin
 */
/*
 * Utilidad centralizada para manejo seguro de contraseñas.
 * Usa BCrypt (libreria jBCrypt) en vez de texto plano.
 *
 * DEPENDENCIA NECESARIA:
 *   Agregar jBCrypt al proyecto (NetBeans: clic derecho en Libraries > Add JAR/Folder)
 *   Maven coord equivalente: org.mindrot:jbcrypt:0.4
 *   Descarga directa: https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar
 *
 * COLUMNA DE BASE DE DATOS:
 *   El hash de BCrypt mide 60 caracteres. Verifica que la columna
 *   USUARIOS.PASSWORD sea VARCHAR(60) o mas (ideal VARCHAR(100) por margen).
 *   Ejemplo: ALTER TABLE usuarios MODIFY PASSWORD VARCHAR(100);
 */

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil
{
    /**
     * Genera un hash BCrypt nuevo para guardar en BD.Usar SIEMPRE que se cree o cambie una contraseña.
     * @param passwordPlano
     * @return 
     */
    public static String hash(String passwordPlano)
    {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt(12));
    }

    /**
     * Verifica una contraseña contra un hash BCrypt.
     * @param passwordPlano
     * @param hashGuardado
     * @return 
     */
    public static boolean verificar(String passwordPlano, String hashGuardado)
    {
        try
        {
            return BCrypt.checkpw(passwordPlano, hashGuardado);
        }
        catch (IllegalArgumentException ex)
        {
            // El valor guardado no tiene formato de hash BCrypt valido
            // (esto pasa con cuentas viejas que aun tienen texto plano)
            return false;
        }
    }

    /**
     * Detecta si un valor ya esta hasheado con BCrypt (empieza con $2a$, $2b$ o $2y$)
     * vs si sigue siendo texto plano de la version anterior del sistema.Esto permite migrar usuarios existentes sin forzar un reseteo masivo de passwords.
     * @param valor
     * @return 
     */
    public static boolean esHashBCrypt(String valor)
    {
        return valor != null
                && valor.length() == 60
                && (valor.startsWith("$2a$") || valor.startsWith("$2b$") || valor.startsWith("$2y$"));
    }
}
