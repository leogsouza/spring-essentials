package br.com.leogsouza.awesome.javaclient;

import br.com.leogsouza.awesome.model.PageableResponse;
import br.com.leogsouza.awesome.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class JavaSpringClientTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/students")
                .basicAuthorization("mjiruca1", "1234").build();
        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
        System.out.println(student);
        // Student[] students = restTemplate.getForObject("/", Student[].class);
        // System.out.println(Arrays.toString(students));
        // ResponseEntity<List<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
        //        new ParameterizedTypeReference<List<Student>>() {
        //        });

        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("/?sort=id,desc&sort=name,asc", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {
                });
        System.out.println(exchange);

        Student studentPost = new Student();
        studentPost.setName("Ash Dolphin");
        studentPost.setEmail("ashdp@asp.edu");
        ResponseEntity<Student> exchangePost = restTemplate.exchange("/",
                HttpMethod.POST, new HttpEntity<>(studentPost, createJSONHeader()), Student.class);
        System.out.println(exchangePost);
    }

    private static HttpHeaders createJSONHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
