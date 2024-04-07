package pojo;

import java.util.List;

public class PostRequestBody {
	private String userName;
	private String job;
	private List<String> languages;
	
	public List<String> getLanguages() {
		return languages;
	}


	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}


	public PostRequestBody(){
		
		
	};
	 
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	

}
