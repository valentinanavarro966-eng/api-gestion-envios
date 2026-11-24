package com.example.GestionDeEnviosLogisticosParcial.Controller;

import com.example.GestionDeEnviosLogisticosParcial.Model.RecursoLogistico;
import com.example.GestionDeEnviosLogisticosParcial.Service.RecursoLogisticoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursosLogisticos")
public class RecursoLogisticoController {

    private RecursoLogisticoService recursoLogisticoService;

    public RecursoLogisticoController(RecursoLogisticoService recursoLogisticoService) {
        this.recursoLogisticoService = recursoLogisticoService;
    }

    @GetMapping
    public List<RecursoLogistico> mostrarRecursosLogisticos ()
    {
        return recursoLogisticoService.mostrarListaRecursosLogisticos();
    }

    @PostMapping("/nuevoRecursoLogistico")
    public RecursoLogistico crearRecursoLogistico (@RequestBody RecursoLogistico recursoLogistico)
    {
        return recursoLogisticoService.altaRecursoLogistico(recursoLogistico);
    }

    @PutMapping("/modificarRecursoLogistico/{id}")
    public RecursoLogistico modificarRecursoLogistico (@RequestBody RecursoLogistico recursoLogistico, @PathVariable Integer id)
    {
        return recursoLogisticoService.updateRecursoLogistico(recursoLogistico, id);
    }

    @DeleteMapping("/borrarRecursoLogistico/{id}")
    public void borrarRecursoLogistico (@PathVariable Integer id)
    {
        recursoLogisticoService.bajaRecursoLogistico(id);
    }

    @GetMapping("/tipoCarga/{tipoCarga}")
    public List<RecursoLogistico> mostrarRecursosTipoCarga (@PathVariable String tipoCarga)
    {
        return recursoLogisticoService.mostrarRecursosLogisticosXtipoDeCarga(tipoCarga);
    }


}
