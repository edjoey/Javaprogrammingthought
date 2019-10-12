package annotations.use;

import java.util.List;

import annotations.UseCase;

public class PasswordUtils {
	
	@UseCase(id = 47,description = 
			"Password must contain at least one numeric")
	public boolean validatePassword(String pwd) {
		return (pwd.matches("\\w*\\d||w*"));
	}
	
	
	@UseCase(id = 48)
	public String encryptPassWord(String passWord) {
		return new StringBuilder(passWord).reverse().toString();
	}
	
	@UseCase(id = 49,description = 
			"New passwords can' t equal previously used ones")
	public boolean checkForNewPassWord(List<String> prevPassWords,String pwd) {
		return !prevPassWords.contains(pwd);
	}
	
	
}
