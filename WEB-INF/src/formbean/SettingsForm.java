/**
 * @file   SettingsForm.java
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

public class SettingsForm extends FormBean {
	private String email;
	private String timeZone;
	private String zipcode;
    private String phoneNumber;

	public String getEmail() { return email; }
	public String getTimeZone() { return timeZone; }
	public String getZipcode() { return zipcode; }
    public String getPhoneNumber() { return phoneNumber; }

	public void setEmail(String s) { email = s.trim(); }
	public void setTimeZone(String s) { timeZone = s.trim(); }
	public void setZipcode(String s) { zipcode = s.trim(); }
    public void setPhoneNumber(String s) { phoneNumber = s.trim(); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		/* Check that all of the fields are filled in. */
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

        /* TODO need better phone number validator */
		if (phoneNumber.matches(".*[<>\"].*")) {
			errors.add("Phone number may not contain angle brackets" +
                    " or quotes");
		}

		/* Check the data lengths. */
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

        if (phoneNumber.length() > User.phoneNumberLength) {
            errors.add("Phone number may not contain more than " +
                    User.phoneNumberLength + " characters.");
        }

		return errors;
	}
}
