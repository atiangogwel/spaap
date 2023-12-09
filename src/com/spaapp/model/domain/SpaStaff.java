package com.spaapp.model.domain;

public class SpaStaff {
	private int staff_id;
    private String staffName; 
    private String username;
    private String password;
    private String email;
    private String fullName;

    public SpaStaff(int staff_id, String username, String password, String email, String fullName) {
        this.staff_id = staff_id;
    	this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    // Getter methods for other fields
    public int getStaffId() {
        return staff_id;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }
    public void setStaffId(int staff_id) {
        this.staff_id = staff_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Override
    public String toString() {
        return "SpaStaff{" +
                "staffId=" + staff_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }


    // override equals and hashCode methodsfor proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpaStaff spaStaff = (SpaStaff) o;

        return username.equals(spaStaff.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
