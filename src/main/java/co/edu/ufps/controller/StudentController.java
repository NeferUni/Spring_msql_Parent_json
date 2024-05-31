package co.edu.ufps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entity.Colegio;
import co.edu.ufps.entity.Student;
import co.edu.ufps.service.ColegioService;
import co.edu.ufps.service.StudentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ColegioService colegioService;

    @GetMapping("/json")
    @ResponseBody
    public List<Student> getAllStudentsJson() {
        return studentService.getAllStudent(); 
    }

    @GetMapping("/html")
    public String getAllStudentsHtml(Model model) {
        List<Student> students = studentService.getAllStudent();
        model.addAttribute("students", students);
        return "students"; // Devuelve el nombre de la plantilla Thymeleaf
    }

    @GetMapping("/{studentId}")
    @ResponseBody
    public Optional<Student> getById(@PathVariable("studentId") Long studentId) {
        return studentService.getStudentById(studentId); 
    }

    @PostMapping
    public String saveOrUpdate(@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long colegio_id) {
        Optional<Colegio> colegioOptional = colegioService.getColegioById(colegio_id);
        if (colegioOptional.isPresent()) {
            Colegio colegio = colegioOptional.get();
            Student student = new Student();
            student.setNombre(nombre);
            student.setApellido(apellido);
            student.setColegio(colegio);
            studentService.saveOrUpdate(student);
            return "redirect:/colegios/" + colegio_id + "/estudiantes"; // Redirige a la página del colegio con la lista de estudiantes
        } else {
            return "error";
        }
    }

    @DeleteMapping("/{studentId}")
    public String delete(@PathVariable("studentId") long studentId) {
        Optional<Student> studentOptional = studentService.getStudentById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            Long colegioId = student.getColegio().getId();
            studentService.deleteStudent(studentId);
            return "redirect:/colegios/" + colegioId + "/estudiantes"; // Redirige a la página del colegio con la lista de estudiantes
        } else {
            return "error";
        }
    }
}
