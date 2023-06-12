package luqmanmohammad.U2D11SpringBootDataEncryption.entities.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;


//i have this class because this is what i want to see on my payload for example in postman and then 
//i can have some validation after
@Getter
public class UserRegistrationPayload {		
//	@NotNull(message = "username required")
//	String username;
//	@NotNull(message = "name is required")
//	String name;
//	@NotNull(message = "surname is required")
//	String surname;
//	@NotNull(message = "email is required")
//	@Email(message = "email not valid")
//	String email;
//	@NotNull(message = "password required")
//	@Size(min = 10, max = 30, message = "password not valid. Min 10 characters and max 30 characters")
//	String password;
}