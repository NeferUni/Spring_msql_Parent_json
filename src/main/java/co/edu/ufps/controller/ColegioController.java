package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entity.Colegio;
import co.edu.ufps.service.ColegioService;

@Controller
@RequestMapping("/colegios")
public class ColegioController {

    @Autowired
    private ColegioService colegioService;

    @GetMapping("/json")
    @ResponseBody
    public List<Colegio> getAllColegiosJson() {
        return colegioService.getAllColegios(); 
    }

    @GetMapping("/html")
    public String getAllColegiosHtml(Model model) {
        List<Colegio> colegios = colegioService.getAllColegios();
        model.addAttribute("colegios", colegios);
        return "index"; // Devuelve el nombre de la plantilla Thymeleaf
    }

    @GetMapping("/{id}/estudiantes")
    public String getEstudiantesByColegio(@PathVariable Long id, Model model) {
        Optional<Colegio> colegioOptional = colegioService.getColegioById(id);
        if (colegioOptional.isPresent()) {
            Colegio colegio = colegioOptional.get();
            model.addAttribute("colegio", colegio);
            model.addAttribute("estudiantes", colegio.getEstudiantes());
            return "students"; // Cambiado a 'students'
        } else {
            // Manejar el caso en el que no se encuentre el colegio
            return "error";
        }
    }

    @GetMapping("/{colegioId}")
    @ResponseBody
    public Optional<Colegio> getById(@PathVariable("colegioId") Long colegioId) {
        return colegioService.getColegioById(colegioId); 
    }

    @PostMapping
    public String saveOrUpdate(@ModelAttribute Colegio colegio) {
        colegioService.saveOrUpdate(colegio);
        return "redirect:/colegios/html"; // Redirige a la página HTML con la lista de colegios
    }

    @PostMapping("/{colegioId}")
    @ResponseBody
    public String delete(@PathVariable("colegioId") long colegioId) {
        colegioService.deleteColegio(colegioId);
        return "redirect:/colegios/html"; // Redirige a la página HTML con la lista de colegios
    }
}
