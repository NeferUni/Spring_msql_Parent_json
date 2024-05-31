package co.edu.ufps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entity.Colegio;
import co.edu.ufps.repository.ColegioRepository;

@Service
public class ColegioService {

    @Autowired
    private ColegioRepository colegioRepository;
    
    // Obtener todos los colegios
    public List<Colegio> getAllColegios() {
        return colegioRepository.findAll();
    }

    // Obtener un colegio por ID
    public Optional<Colegio> getColegioById(long id) {
        return colegioRepository.findById(id);
    }

    // Guardar o actualizar un colegio
    public void saveOrUpdate(Colegio colegio) {
        colegioRepository.save(colegio);
    }

    // Eliminar un colegio por ID
    public void deleteColegio(long id) {
        colegioRepository.deleteById(id);
    }
}
