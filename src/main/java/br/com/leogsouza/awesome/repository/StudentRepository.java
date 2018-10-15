package br.com.leogsouza.awesome.repository;

import br.com.leogsouza.awesome.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByName(String name);
}
