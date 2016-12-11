import java.util.ArrayList;
/**
 * SimpleReceipt frame implements ReceiptFormat as part of strategy pattern.
 * @author Harita Shroff
 *
 */
public class SimpleReceipt implements ReceiptFormat {
	
	private HotelModel model;

	public SimpleReceipt(HotelModel model) {
		this.model = model;
	}

	@Override
	public String format() {
		Guest usr= (Guest) model.getCurrentUser();
		ArrayList<Reservation> reservationList = usr.getReservations();
		String reservationData = usr.getName() + ": " + usr.getID();
		reservationData += '\n';
		int total = 0;
		for (Reservation rsvtion: reservationList) {
			if (rsvtion.getTransactionID() == model.getTranscationID()) {
				reservationData += rsvtion.toString();
				reservationData += '\n';
				total += rsvtion.getRoom().getRoomPrice() * (( rsvtion.getEndDay().getTime()- rsvtion.getStartDay().getTime()) / (1000 * 60 * 60 * 24));
			}
		}
		return reservationData += "Total amount due: " + total;
	}
}