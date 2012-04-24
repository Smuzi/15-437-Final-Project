/**
 * @file   LoginForm.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String username;
	private String password;

	public String getUsername() { return username; }
	public String getPassword() { return password; }

	public void setUsername(String s) { username = s.trim(); }
	public void setPassword(String s) { password = s.trim(); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		/* Check that all of the fields are filled in. */
		if (username == null || username.length() == 0) {
			errors.add("Username is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		return errors;
	}
}
