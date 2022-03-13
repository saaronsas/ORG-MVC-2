package com.dev.springboot.controller;

import com.dev.springboot.exception.SerieNotFoundException;
import com.dev.springboot.model.Serie;
import com.dev.springboot.service.IMarcaService;
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

    /**
     * ISerieService para poder llamar a las funciones
     */
    @Autowired
    private ISerieService service;

    /**
     * IMarcaService para poder llamar a las funciones
     */
    @Autowired
    private IMarcaService marcaService;

    /**
     * Pagina de Inicio
     * - Method GET
     * @return homeSeriePage
     */
    @GetMapping("/")
    public String showHomePage() {
        return "homeSeriePage";
    }

    /**
     * Pagina de Registro
     * - Method GET
     * @return registerSeriePage
     */
    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("marcas", marcaService.getAllMarcas());
        return "registerSeriePage";
    }

    /**
     * Guarda la información
     * - Method POST
     * @param serie el atributo de Serie
     * @param model clase Model
     * @return registerSeriePage
     */
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

    /**
     * Pagina de listado de Series
     * - Method GET
     * @param message mensaje a mostrar
     * @param model clase Model
     * @return allSeriesPage
     */
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

    /**
     * Pagina de edición de Series
     * - Method GET
     * @param model clase Model
     * @param attributes clase RedirectAttributes
     * @param id id de la serie a modificar
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
            Serie serie = service.getSerieById(id);
            model.addAttribute("serie", serie);
            model.addAttribute("marcas", marcaService.getAllMarcas());
            page="editSeriePage";
        } catch (SerieNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page="redirect:getAllSeries";
        }
        return page;
    }

    /**
     * Actualiza la información
     * - Method POST
     * @param serie el atributo de Serie editado
     * @param attributes clase RedirectAttributes
     * @return getAllSeries
     */
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

    /**
     * Actualiza la serie seleccionada
     * - Method GET
     * @param id indice de la serie a borrar
     * @param attributes clase RedirectAttributes
     * @return getAllSeries
     */
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