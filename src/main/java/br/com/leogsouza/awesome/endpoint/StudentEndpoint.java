package br.com.leogsouza.awesome.endpoint;

import br.com.leogsouza.awesome.error.CustomErrorType;
import br.com.leogsouza.awesome.error.ResourceNotFoundException;
import br.com.leogsouza.awesome.model.Student;
import br.com.leogsouza.awesome.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(studentDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<?> listStudentById(@PathVariable("id") Long id) {
        verifyIfStudentsExists(id);
        Student student = studentDAO.findById(id)
                .orElse(null);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable String name) {
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {

        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        verifyIfStudentsExists(id);
        studentDAO.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentsExists(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void verifyIfStudentsExists(Long id) {
        if (!studentDAO.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Student not found for ID: "+ id);
        }
    }

}
