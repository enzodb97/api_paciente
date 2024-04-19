
package com.enzodb.Paciente.controller;

import com.enzodb.Paciente.model.Paciente;
import com.enzodb.Paciente.service.IPacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PacienteController {
    
    @Autowired
    private IPacienteService pacienteServ;
    
    //1.- endpoint para crear un nuevo paciente
    @PostMapping ("/pacientes/crear")
    public String crearPaciente (@RequestBody Paciente pac){
       pacienteServ.savePaciente(pac);
       return "Paciente creado correctamente.";
    }
    //2.- traer todos los pacientes
    @GetMapping ("/pacientes/traer")
    public List<Paciente> traerPacientes(){
        return pacienteServ.getPacientes();
    }
    //3.- eliminar un paciente
    @DeleteMapping ("/pacientes/borrar/{id}")
    public String deletePaciente (@PathVariable Long id){
        pacienteServ.deletePaciente(id);
        return "Paciente eliminado correctamente.";
    }
    //4.- editar un paciente
    @PutMapping ("/pacientes/editar/{id_original}")
    public Paciente editPaciente (@PathVariable Long id_original,
            @RequestBody Paciente pacienteEditar){
        pacienteServ.editPaciente(id_original, pacienteEditar);
        Paciente pacienteEditado = pacienteServ.findPaciente(id_original);
        return pacienteEditado;
    }
    //5.- obtenemos un paciente en particular
    @GetMapping ("/pacientes/traer/{id}")
    public Paciente traerPaciente(@PathVariable Long id){
        return pacienteServ.findPaciente(id);
    }
}
