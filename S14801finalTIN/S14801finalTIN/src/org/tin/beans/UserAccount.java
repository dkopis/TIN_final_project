package org.tin.beans;

public class UserAccount {
	 
	   public static final String GENDER_MALE ="M";
	   public static final String GENDER_FEMALE = "F";
	    
	   private String userName;
	   private String gender;
	   private String password;
	   private String respName;
	    
	 
	   public UserAccount() {
	        
	   }
	    
	   public String getUserName() {
	       return userName;
	   }
	 
	   public void setUserName(String userName) {
	       this.userName = userName;
	   }
	 
	   public String getGender() {
	       return gender;
	   }
	 
	   public void setGender(String gender) {
	       this.gender = gender;
	   }
	 
	   public String getPassword() {
	       return password;
	   }
	 
	   public void setPassword(String password) {
	       this.password = password;
	   }
	   
	   public String getRespName() {
	       return respName;
	   }
	 
	   public void setRespName(String respName) {
	       this.respName = respName;
	   }
	 
	}