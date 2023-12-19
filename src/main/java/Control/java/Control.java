package Control.java;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {
	private static Map<String, Student> studentRepo = new HashMap<>();
	static {
		Student list = new Student();
		list.setId("1");
		list.setstudentTitle("Ashley Garcia");
		studentRepo.put(list.getId(), list);
		
		Student list1 = new Student();
		list1.setId("2");
		list1.setstudentTitle("Maricar Garcia");
		studentRepo.put(list1.getId(), list1);

	}
	
	//GET Request
	@GetMapping("/student")
	public ResponseEntity<Object> getstudentList() {
		return new ResponseEntity<>(studentRepo.values(), HttpStatus.OK);
	}
	
	// POST Request
	@PostMapping("/student/add")
	public ResponseEntity<Object> createstudent(@RequestBody Student student) {
		studentRepo.put(student.getId(), student);  
		
	    return new ResponseEntity<>("Student successfully added", HttpStatus.CREATED);
	}
	
	// PUT Request for updating a student
	@PutMapping("/student/{id}")
	public ResponseEntity<Object> updatestudent(@PathVariable("id") String id, @RequestBody Student updatedstudent) {
	    if (studentRepo.containsKey(id)) {
	        Student existingstudent = studentRepo.get(id);
	        existingstudent.setstudentTitle(updatedstudent.getstudentTitle());

	        return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Student with ID " + id + " not found", HttpStatus.NOT_FOUND);
	    }
	}
	
	// DELETE Request for deleting a Student
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Object> deletestudent(@PathVariable("id") String id) {
	    if (studentRepo.containsKey(id)) {
	    	studentRepo.remove(id);
	        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Student with ID " + id + " not found", HttpStatus.NOT_FOUND);
	    }
	}


}
