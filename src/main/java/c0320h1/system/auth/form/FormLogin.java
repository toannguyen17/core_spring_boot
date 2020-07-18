package c0320h1.system.auth.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FormLogin {
	@NotEmpty
	@Size(min = 10, max = 255)
	private String email;

	@NotEmpty
	@Size(min = 6, max = 255)
	private String password;

	public FormLogin(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
