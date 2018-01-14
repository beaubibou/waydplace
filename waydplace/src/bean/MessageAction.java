package bean;

public class MessageAction {

	boolean reponse;
	String message;
	public boolean isReponse() {
		return reponse;
	}
	public void setReponse(boolean reponse) {
		this.reponse = reponse;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageAction(boolean reponse, String message) {
		super();
		this.reponse = reponse;
		this.message = message;
	}
	public boolean isOk() {
		// TODO Auto-generated method stub
		return reponse;
	}
	
}
