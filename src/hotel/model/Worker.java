package hotel.model;

import java.util.Date;

public class Worker extends CommonModel {

	private String name;
	private String surname;
	private Date dob;
	private String fatherName;
	private String phone;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Worker [name=" + name + ", surname=" + surname + ", dob=" + dob + ", fatherName=" + fatherName
				+ ", phone=" + phone + ", email=" + email + "]";
	}

}
