package com.dev.springboot.controller;

import com.dev.springboot.exception.VehiculoNotFoundException;
import com.dev.springboot.model.Vehiculo;
import com.dev.springboot.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {

    @Autowired
    private IVehiculoService service;

    @GetMapping("/")
    public String showHomePage() {
        return "homeVehiculoPage";
    }

    @GetMapping("/register")
    public String showRegistration() {
        return "registerVehiculoPage";
    }

    @PostMapping("/save")
    public String saveSerie(
            @ModelAttribute Vehiculo vehiculo,
            Model model
    ) {
        service.saveVehiculo(vehiculo);
        Integer id = service.saveVehiculo(vehiculo).getId();
        String message = "Id : '"+id+"' ha sido guardado con exito!";
        model.addAttribute("message", message);
        return "registerVehiculoPage";
    }

    @GetMapping("/getAllVehiculo")
    public String getAllVehiculos(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Vehiculo> vehiculos= service.getAllVehiculos();
        model.addAttribute("list", vehiculos);
        model.addAttribute("message", message);
        return "allVehiculosPage";
    }

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
            page="editVehiculoPage";
        } catch (VehiculoNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:getAllVehiculos";
        }
        return page;
    }

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