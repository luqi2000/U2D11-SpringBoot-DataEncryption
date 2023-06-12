package luqmanmohammad.U2D11SpringBootDataEncryption.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.U2D11SpringBootDataEncryption.entities.User;
import luqmanmohammad.U2D11SpringBootDataEncryption.entities.payload.UserRegistrationPayload;
import luqmanmohammad.U2D11SpringBootDataEncryption.exceptions.NotFoundException;
import luqmanmohammad.U2D11SpringBootDataEncryption.service.UserService;

@RestController
@RequestMapping("/employee")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//working get all user and order by name 
	@GetMapping("")
	public Page<User> getAllUser(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy) {
		return userService.findAll(page, size, sortBy);
	}
	
	//working
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody User body){
		return userService.create(body);
	}
	
	//just for try 
//	{
//	    "name" : "Luqman",
//	    "surname" : "Mohammad",
//	    "email" : "luqmanmohammad09@gmail.com",
//	    "password": "12345678910",
//	    "username" : "luqi"
//	}
	
	//working
	@GetMapping("/{userId}")
	public User getUser(@PathVariable UUID userId) throws NotFoundException {
		return userService.findById(userId);
	}
	
	//working
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable UUID userId, @RequestBody @Validated User body) throws Exception {
		return userService.findByIdAndUpdate(userId, body);
	}
	
	//working
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId){
		userService.findByIdAndDelete(userId);
	}

}

