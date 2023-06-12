package luqmanmohammad.U2D11SpringBootDataEncryption.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import luqmanmohammad.U2D11SpringBootDataEncryption.entities.User;
import luqmanmohammad.U2D11SpringBootDataEncryption.entities.payload.UserRegistrationPayload;
import luqmanmohammad.U2D11SpringBootDataEncryption.exceptions.BadRequestException;
import luqmanmohammad.U2D11SpringBootDataEncryption.exceptions.NotFoundException;
import luqmanmohammad.U2D11SpringBootDataEncryption.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	// 1. create user
	public User create(UserRegistrationPayload u) {
		userRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException("email already register");
		});
		
		//this is what back-end will create from front end 
		User newUser = new User(u.getUsername(), u.getName(), u.getSurname(),u.getEmail(), u.getPassword());
		return userRepo.save(newUser);
	}
	// 2. search all users
	public Page<User> findAll(int page, int size, String sortBy){
		if (size<0) size = 10;
		if (size>100) size = 100;
		Pageable pageable = PageRequest.of(page, size,Sort.by(sortBy));
		return userRepo.findAll(pageable);
	}
	
	//3 search by id
	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException("employee not found!"));
	}
	
	//3.2 search by email
	public User findByEmail(String email) throws NotFoundException {
		return userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("email not found"));
	}

	
	// 4. find by id and update
	public User findByIdAndUpdate(UUID id, UserRegistrationPayload body) throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setName(body.getName());
		found.setSurname(body.getSurname());
		found.setEmail(body.getEmail());
		found.setUsername(body.getUsername());

		return userRepo.save(found);
	}
	//5. find by id and delete
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepo.delete(found);
	}
}
