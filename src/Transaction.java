/*
*	data structure for transaction
*/
public class Transaction {
	public Transaction(String time, String sender, String receiver,
			String amount, String message) {
		this.time = time;
		this.senderId = sender;
		this.receiverId = receiver;
		this.amount = Double.parseDouble(amount);
		this.message = message;
	}
	private String time;
	private String senderId;
	private String receiverId;
	private double amount;
	private String message;
	
	
	public String getSender() {
		return senderId;
	}
	public String getReceiver() {
		return receiverId;
	}
}
