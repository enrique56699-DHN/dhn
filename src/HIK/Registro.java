/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

import java.time.LocalTime;

/**
 *
 * @author win 10
 */
public class Registro 
{
        public String numEmpleado;
        public String nombre;
        public String fecha;
        public LocalTime hora;

        public Registro(String numEmpleado, String nombre, String fechaHora) {
            this.numEmpleado = numEmpleado;
            this.nombre = nombre;
            
            String[] partes = fechaHora.split(" ");
            this.fecha = partes[0];
            this.hora = LocalTime.parse(partes[1]);
        }
    }