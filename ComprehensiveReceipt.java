import java.util.ArrayList;
/**
 * ComprehensiveReceipt frame implements ReceiptFormat as part of strategy pattern.
 * @author Harita Shroff 
 *
 */
public class ComprehensiveReceipt implements ReceiptFormat {

	private HotelModel model;
	/**
	 * Constructor of the class.
	 * @param model
	 */
	public ComprehensiveReceipt (HotelModel model) {
		this.model = model;
	}
	
	/**
	 * Overriding the interface methods in this class.
	 */
	@Override
	public String format() {
		Guest usr= (Guest) model.getCurrentUser();
		ArrayList<Reservation> reservationList = usr.getReservations();
		String reservationData = usr.getName() + ": " +  usr.getID();
		reservationData += '\n';
		int total = 0;
		for (Reservation rsvtion: reservationList) {
			reservationData += rsvtion.toString();
			reservationData += '\n';
			total += rsvtion.getRoom().getRoomPrice() * (( rsvtion.getEndDay().getTime()- rsvtion.getStartDay().getTime()) / (1000 * 60 * 60 * 24));
		}
		return reservationData += "Total amount due: " + total;
	}
}