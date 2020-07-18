package c0320h1.system.auth.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FormRegister {
	@NotEmpty
	@Size(min = 2, max = 50)
	private String last_name;

	@NotEmpty
	@Size(min = 2, max = 50)
	private String first_name;

	@NotEmpty
	@Email
	@Size(min = 10, max = 255)
	private String email;

	@NotEmpty
	@Size(min = 6, max = 255)
	private String password;

	@NotEmpty
	@Size(min = 6, max = 255)
	private String password_confirm;

	public FormRegister(){

	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

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

	public String getPassword_confirm() {
		return password_confirm;
	}

	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}

	@Override
	public String toString() {
		return "FormRegister{" +
				"last_name='" + last_name + '\'' +
				", first_name='" + first_name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", password_confirm='" + password_confirm + '\'' +
				'}';
	}
}
