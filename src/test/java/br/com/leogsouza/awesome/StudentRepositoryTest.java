package br.com.leogsouza.awesome;

import br.com.leogsouza.awesome.model.Student;
import br.com.leogsouza.awesome.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData() {
        Student student = new Student("Adam", "adamjosh@ethic.com");
        this.studentRepository.save(student);
        Assertions.assertThat(student.getId()).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo("Adam");
        Assertions.assertThat(student.getEmail()).isEqualTo("adamjosh@ethic.com");
    }

    @Test
    public void deleteShouldRemoveData() {
        Student student = new Student("Adam", "adamjosh@ethic.com");
        this.studentRepository.save(student);
        this.studentRepository.delete(student);
        Assertions.assertThat(this.studentRepository.findById(student.getId()).orElse(null)).isNull();

    }

    @Test
    public void updateShouldChangeAndPersistData(){
        Student student = new Student("Adam", "adamjosh@ethic.com");
        this.studentRepository.save(student);
        student.setName("AdamABC");
        student.setEmail("adamabc@ethic.com");
        student = this.studentRepository.save(student);
        Assertions.assertThat(student.getName()).isEqualTo("AdamABC");
        Assertions.assertThat(student.getEmail()).isEqualTo("adamabc@ethic.com");
    }

    @Test
    public void findByNameIgnoringCaseShouldIgnoreCase() {
        Student student = new Student("Adam", "adamjosh@ethic.com");
        Student student2 = new Student("adam", "adamjosh@2ethic.com");
        this.studentRepository.save(student);
        this.studentRepository.save(student2);

        List<Student> students = this.studentRepository.findByNameIgnoreCaseContaining("adam");
        assertThat(students.size()).isEqualTo(2);
    }

    @Test
    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        // thrown.expectMessage("O campo nome do estudante é obrigatório");
        this.studentRepository.save(new Student());
    }
}
