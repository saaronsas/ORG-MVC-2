package com.dev.springboot.controller;

import com.dev.springboot.exception.SerieNotFoundException;
import com.dev.springboot.model.Serie;
import com.dev.springboot.service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private ISerieService service;

    @GetMapping("/")
    public String showHomePage() {
        return "homeSeriePage";
    }

    @GetMapping("/register")
    public String showRegistration() {
        return "registerSeriePage";
    }

    @PostMapping("/save")
    public String saveSerie(
            @ModelAttribute Serie serie,
            Model model
    ) {
        service.saveSerie(serie);
        Integer id = service.saveSerie(serie).getId();
        String message = "Id : '"+id+"' ha sido guardado con exito!";
        model.addAttribute("message", message);
        return "registerSeriePage";
    }

    @GetMapping("/getAllSeries")
    public String getAllSeries(
            @RequestParam(value = "message", required = false) String message,
            Model model
    ) {
        List<Serie> series= service.getAllSeries();
        model.addAttribute("list", series);
        model.addAttribute("message", message);
        return "allSeriesPage";
    }

    @GetMapping("/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam Integer id
    ) {
        String page = null;
        try {
            Serie serie = service.getSerieById(id);
            model.addAttribute("serie", serie);
            page="editSeriePage";
        } catch (SerieNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:getAllSeries";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateSerie(
            @ModelAttribute Serie serie,
            RedirectAttributes attributes
    ) {
        service.updateSerie(serie);
        Integer id = serie.getId();
        attributes.addAttribute("message", "Serie con id: '"+id+"' ha sido actualizado correctamente !");
        return "redirect:getAllSeries";
    }

    @GetMapping("/delete")
    public String deleteSerie(
            @RequestParam Integer id,
            RedirectAttributes attributes
    ) {
        try {
            service.deleteSerieById(id);
            attributes.addAttribute("message", "Serie con id: '"+id+"' ha sido borrado correctamente!");
        } catch (SerieNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:getAllSeries";
    }
}