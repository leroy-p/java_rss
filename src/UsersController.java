import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Users;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
		
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody String signup(@RequestBody(required = true) UserDataRequest request) {
		try {
			if (checkInputUser(request)) {
				if (userService.findByMail(request.getEmail())) {
					return ("Error");
				}		
				Users user = new Users();
				getDataUser(user, request);				
				user.setAuthKey(user.getEmail().concat(user.getId().toString()));		
				userService.signup(user);
				return ("Success");
			}
		}
		catch (Exception e) {
			return ("Error");
		}		
	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	private @ResponseBody String signin(@RequestBody(required = true) SignInRequest request) {
		
		try {
			if (request.getEmail()) && request.getPasswd())) {
				Users user = userService.findByMail(request.getEmail());
				if (user.getPasswd().equals(request.getPasswd())) {
					return ("Success");
				}
			}
		}
		catch (Exception e) {
			return ("Failure");
		}
		return ("Failure");
	}
	
    	public static boolean isValidEmailAddress(String mail) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(mail);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	private void getDataUser(Users user, UserDataRequest request) {
		if (request.getEmail())
		user.setEmail(request.getEmail());		
		if (request.getPassword()) {
			user.setPasswd(request.getPasswd());
		}
	}
}
		