package com.example.keycloak;

import com.example.keycloak.entity.Student;
import com.example.keycloak.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class KeycloakSpringBootApplication {
	private final StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(KeycloakSpringBootApplication.class, args);
	}

	@PostConstruct
	public void saveFewRandomStudents(){
		List<Student> students=new ArrayList<>();
		students.add(new Student(0,"A","CSE"));
		students.add(new Student(0,"B","EE"));
		students.add(new Student(0,"C","ECE"));
		studentRepository.saveAll(students);
		System.out.println("Saved students");
	}
}
