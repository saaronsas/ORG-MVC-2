package com.dev.springboot.controller;

import com.dev.springboot.exception.VehiculoNotFoundException;
import com.dev.springboot.model.Vehiculo;
import com.dev.springboot.service.ISerieService;
import com.dev.springboot.service.IVehiculoService;
import com.dev.springboot.service.impl.SerieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {

    /**
     * IVehiculoService para poder llamar a las funciones
     */
    @Autowired
    private IVehiculoService service;

    /**
     * ISerieService para poder llamar a las funciones
     */
    @Autowired
    private ISerieService serieService;

    /**
     * Pagina de Inicio
     * - Method GET
     * @return homeVehiculoPage
     */
    @GetMapping("/")
    public String showHomePage() {
        return "homeVehiculoPage";
    }

    /**
     * Pagina de Registro
     * - Method GET
     * @return registerVehiculoPage
     */
    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("series", serieService.getAllSeries());
        return "registerVehiculoPage.html";
    }

    /**
     * Guarda la información
     * - Method POST
     * @param vehiculo el atributo de Vehiculo
     * @param model clase Model
     * @return registerVehiculoPage
     */
    @PostMapping("/save")
    public String saveVehiculo(
            @ModelAttribute Vehiculo vehiculo,
            Model model
    ) {
        service.saveVehiculo(vehiculo);
        Integer id = service.saveVehiculo(vehiculo).getId();
        String message = "Id : '"+id+"' ha sido guardado con exito!";
        model.addAttribute("message", message);
        return "registerVehiculoPage.html";
    }

    /**
     * Pagina de listado de Vehiculos
     * - Method GET
     * @param message mensaje a mostrar
     * @param model clase Model
     * @return allVehiculosPage
     */
    @GetMapping("/getAllVehiculos")
    public String getAllVehiculos(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Vehiculo> vehiculos= service.getAllVehiculos();
        model.addAttribute("list", vehiculos);
        model.addAttribute("message", message);
        return "allVehiculosPage";
    }

    /**
     * Pagina de edición de Vehiculos
     * - Method GET
     * @param model clase Model
     * @param attributes clase RedirectAttributes
     * @param id id de la vehiculo a modificar
     * @return page
     */
    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Integer id
    ) {
        String page = null;
        try {
            Vehiculo vehiculo = service.getVehiculoById(id);
            model.addAttribute("vehiculo", vehiculo);
            model.addAttribute("series", serieService.getAllSeries());
            page="editVehiculoPage";
        } catch (VehiculoNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:getAllVehiculos";
        }
        return page;
    }

    /**
     * Actualiza la información
     * - Method POST
     * @param vehiculo el atributo de Vehiculo editado
     * @param attributes clase RedirectAttributes
     * @return getAllVehiculos
     */
    @PostMapping("/update")
    public String updateVehiculo(
            @ModelAttribute Vehiculo vehiculo,
            RedirectAttributes attributes
    ) {
        service.updateVehiculo(vehiculo);
        Integer id = vehiculo.getId();
        attributes.addAttribute("message", "Vehiculo con id: '"+id+"' ha sido actualizado correctamente !");
        return "redirect:getAllVehiculos";
    }

    /**
     * Actualiza la vehiculo seleccionada
     * - Method GET
     * @param id indice de la vehiculo a borrar
     * @param attributes clase RedirectAttributes
     * @return getAllVehiculos
     */
    @GetMapping("/delete")
    public String deleteVehiculo(
            @RequestParam Integer id,
            RedirectAttributes attributes
    ) {
        try {
            service.deleteVehiculoById(id);
            attributes.addAttribute("message", "Vehiculo con id: '"+id+"' ha sido borrado correctamente!");
        } catch (VehiculoNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:getAllVehiculos";
    }
}