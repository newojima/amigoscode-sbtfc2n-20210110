package com.amigoscode.amigoscodesbtfc2n20210110.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amigoscode.amigoscodesbtfc2n20210110.student.Student;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student){
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

		if (studentOptional.isPresent()){
			throw new IllegalStateException("Email Already Taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId){
		studentRepository.findById(studentId);

		boolean exists = studentRepository.existsById(studentId);

		if(!exists){
			throw new IllegalStateException(
				"student with id " + studentId + " does not exists"
			);
		}

		studentRepository.deleteById(studentId);
	}
}
