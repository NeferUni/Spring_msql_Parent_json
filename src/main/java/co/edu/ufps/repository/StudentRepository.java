package co.edu.ufps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entity.Colegio;
import co.edu.ufps.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByColegio(Colegio colegio);
}
