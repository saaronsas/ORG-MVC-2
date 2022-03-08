package com.dev.springboot.controller;

import com.dev.springboot.exception.MarcaNotFoundException;
import com.dev.springboot.model.Marca;
import com.dev.springboot.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private IMarcaService service;

    @GetMapping("/")
    public String showHomePage() {
        return "homePage";
    }

    @GetMapping("/register")
    public String showRegistration() {
        return "registerMarcaPage";
    }

    @PostMapping("/save")
    public String saveMarca(
            @ModelAttribute Marca marca,
            Model model
    ) {
        service.saveMarca(marca);
        Integer id = service.saveMarca(marca).getId();
        String message = "Record with id : '"+id+"' is saved successfully !";
        model.addAttribute("message", message);
        return "registerMarcaPage";
    }

    @GetMapping("/getAllMarcas")
    public String getAllMarcas(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Marca> marcas= service.getAllMarcas();
        model.addAttribute("list", marcas);
        model.addAttribute("message", message);
        return "allMarcasPage";
    }

    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Integer id
    ) {
        String page = null;
        try {
            Marca marca = service.getMarcaById(id);
            model.addAttribute("marca", marca);
            page="editMarcaPage";
        } catch (MarcaNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:getAllMarcas";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateMarca(
            @ModelAttribute Marca marca,
            RedirectAttributes attributes
    ) {
        service.updateMarca(marca);
        Integer id = marca.getId();
        attributes.addAttribute("message", "Marca with id: '"+id+"' is updated successfully !");
        return "redirect:getAllMarcas";
    }

    @GetMapping("/delete")
    public String deleteMarca(
            @RequestParam Integer id,
            RedirectAttributes attributes
    ) {
        try {
            service.deleteMarcaById(id);
            attributes.addAttribute("message", "Marca with Id : '"+id+"' is removed successfully!");
        } catch (MarcaNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:getAllMarcas";
    }
}