package com.banepali.dao;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="springboot_emp_tbl")
public class EmployeeEntity {
		private int eid;
		private String username;
		private String password;
		private String email;
		private String name;
		private Date dob;
		private String salutation;
		private Timestamp datecreated;
		private String role;
		private byte[] bphoto;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		public int getEid() {
			return eid;
		}
		public void setEid(int eid) {
			this.eid = eid;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		@Column(length=200)
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSalutation() {
			return salutation;
		}
		public void setSalutation(String salutation) {
			this.salutation = salutation;
		}
		public Timestamp getDatecreated() {
			return datecreated;
		}
		public void setDatecreated(Timestamp datecreated) {
			this.datecreated = datecreated;
		}
		
		@Column(columnDefinition = "varchar(30) default 'Customer'")
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		@Column(columnDefinition = "longblob")
		public byte[] getBphoto() {
			return bphoto;
		}
		public void setBphoto(byte[] bphoto) {
			this.bphoto = bphoto;
		}
		@Override
		public String toString() {
			return "EmployeeEntity [eid=" + eid + ", username=" + username + ", password=" + password + ", email="
					+ email + ", name=" + name + ", dob=" + dob + ", salutation=" + salutation + ", datecreated="
					+ datecreated + ", role=" + role + "]";
		}
		


		
		
		
		
		
		
}
