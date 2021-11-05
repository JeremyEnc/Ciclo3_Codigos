/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Paciente;
import java.time.LocalDate;

/**
 *
 * @author jere_
 */
public class ControladorPaciente 
{
    private Paciente[] pacientes;
    private String recomendaciones[];

    public ControladorPaciente(int n) 
    {
        this.recomendaciones = new String[5];
        this.pacientes = new Paciente[n];
        
        recomendaciones[0] = "Leche Materna";
        recomendaciones[1] = "Alimentos ricos en fibra";
        recomendaciones[2] = "Alimentos ricos en vitaminas y minerales";
        recomendaciones[3] = "Consumir las calorias adecuadas de acuerdo al peso";
        recomendaciones[4] = "Disminuir el consumo de calorias debido a la reduccion del gasto energetico";
    }

    public Paciente[] getPacientes() 
    {
        return pacientes;
    }  

    public String[] getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String[] recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
  
    public void calcularEdad(int i)
    {
        LocalDate fechaActual = LocalDate.now();
        
        int edadPaciente = fechaActual.getYear() - pacientes[i].getFechaDeNacimiento().getYear();
        
        pacientes[i].setEdad(edadPaciente);      
    }
    
    public void clasificarPorEdad(int i)
    {
        if (pacientes[i].getEdad() > 0) 
        {
            if(pacientes[i].getEdad() > 59)
            {
                pacientes[i].setClasificacion("Tercera Edad");
                pacientes[i].setTratamiento(recomendaciones[4]);
            }
            else
            {
                if(pacientes[i].getEdad() > 26)
                {
                    pacientes[i].setClasificacion("Adulto");
                    pacientes[i].setTratamiento(recomendaciones[3]);
                }
                else
                {
                    if(pacientes[i].getEdad() > 11)
                    {
                        pacientes[i].setClasificacion("Joven");
                        pacientes[i].setTratamiento(recomendaciones[2]);
                    }
                    else
                    {
                        pacientes[i].setClasificacion("Niño");
                        pacientes[i].setTratamiento(recomendaciones[1]);
                    }
                }
            }
        }
        else
        {
            pacientes[i].setClasificacion("Bebe");
            pacientes[i].setTratamiento(recomendaciones[0]);
        }
    }   
}
