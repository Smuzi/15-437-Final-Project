/**
 * @file   RegisterForm.java
 * @author Jacob Olson <jholson@andrew.cmu.edu>
 * @date   4/23/2012
 * @class  15-437
 */

package formbean;

import java.util.ArrayList;
import java.util.List;

import java.util.TimeZone;

import org.mybeans.form.FormBean;

import databean.User;

public class RegisterForm extends FormBean {
	private String username;
	private String password;
	private String passwordAgain;
	private String email;
	private String timeZone;
	private String zipcode;

	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getPasswordAgain() { return passwordAgain; }
	public String getEmail() { return email; }
	public String getTimeZone() { return timeZone; }
	public String getZipcode() { return zipcode; }

	public void setUsername(String s) { username = s.trim(); }
	public void setPassword(String s) { password = s.trim(); }
	public void setPasswordAgain(String s) { passwordAgain = s.trim(); }
	public void setEmail(String s) { email = s.trim(); }
	public void setTimeZone(String s) { timeZone = s.trim(); }
	public void setZipcode(String s) { zipcode = s.trim(); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		/* Check that all of the fields are filled in. */
		if (username == null || username.length() == 0) {
			errors.add("Username is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		} else if (passwordAgain == null || passwordAgain.length() == 0) {
			errors.add("Enter your password again");
		}

		if (email == null || email.length() == 0) {
			errors.add("Email is required");
		}

		if (timeZone == null || timeZone.length() == 0) {
			errors.add("Time zone is required");
		}

		if (zipcode == null || zipcode.length() == 0) {
			errors.add("Zipcode is required");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if (!password.equals(passwordAgain)) {
			errors.add("Passwords must match");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if (username.matches(".*[<>\"].*")) {
			errors.add("Username may not contain angle brackets or quotes");
		}

		if (password.matches(".*[<>\"].*") |
		    passwordAgain.matches(".*[<>\"].*")) {
			errors.add("Password may not contain angle brackets or quotes");
		}

		/* TODO Need better email validator */
		if (email.matches(".*[<>\"].*")) {
			errors.add("Email may not contain angle brackets or quotes");
		}

		if (timeZone.matches(".*[<>\"].*")) {
			errors.add("Time zone may not contain angle brackets or quotes");
		} else {
			TimeZone gmt = TimeZone.getTimeZone("GMT");
			if (!timeZone.equals("GMT") &&
			    !timeZone.equals("Greenwich Mean Time") &&
			    TimeZone.getTimeZone(timeZone).hasSameRules(gmt)) {
				errors.add("Invalid time zone");
			}   
		}   

		/* Check the data lengths. */
        if (username.length() > User.usernameLength) {
            errors.add("Username may not contain more than " +
                    User.usernameLength + " characters.");
        }

        if (password.length() > User.passwordLength) {
            errors.add("Password may not contain more than " +
                    User.passwordLength + " characters.");
        }

        if (email.length() > User.emailLength) {
            errors.add("Email may not contain more than " +
                    User.emailLength + " characters.");
        }

        if (timeZone.length() > User.timeZoneLength) {
            errors.add("Time zone may not contain more than " +
                    User.timeZoneLength + " characters.");
        }

        if (zipcode.length() > User.zipcodeLength) {
            errors.add("Zipcode may not contain more than " +
                    User.zipcodeLength + " characters.");
        }

		return errors;
	}
}
