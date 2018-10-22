package br.com.leogsouza.awesome.repository;

import br.com.leogsouza.awesome.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name);
}
