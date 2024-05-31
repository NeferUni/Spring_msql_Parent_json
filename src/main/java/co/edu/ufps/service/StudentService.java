package co.edu.ufps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entity.Student;
import co.edu.ufps.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Obtener todos los estudiantes
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    // Guardar o actualizar un estudiante
    public void saveOrUpdate(Student student) {
        studentRepository.save(student);
    }

    // Eliminar un estudiante por ID
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
    
    
}
