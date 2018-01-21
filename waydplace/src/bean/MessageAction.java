package bean;

public class MessageAction {

	boolean reponse;
	String message;
	Object reponseObject;
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
	public MessageAction(boolean reponse, String message, Object objectReponse) {
	
		super();
		this.reponse = reponse;
		this.message = message;
		this.reponseObject=objectReponse;
	}
	public boolean isOk() {
		// TODO Auto-generated method stub
		return reponse;
	}
	public Object getReponseObject() {
		return reponseObject;
	}
	public void setReponseObject(Object reponseObject) {
		this.reponseObject = reponseObject;
	}
	
}
