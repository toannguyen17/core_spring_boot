package c0320h1.system.auth;

public class Recaller {
	private String recaller;
	public Recaller(){
	}

	public Recaller(String recaller){
		this.recaller = recaller;
	}

	public void setRecaller(String recaller) {
		this.recaller = recaller;
	}

	public Long id() {
		if(recaller != null){
			return Long.parseLong(recaller.split("\\|")[0]);
		}
		return null;
	}

	public String token() {
		if(recaller != null){
			return recaller.split("\\|")[1];
		}
		return null;
	}

	public boolean valid(){
		if (recaller != null && recaller.split("\\|").length == 2){
			return true;
		}
		return false;
	}
}
