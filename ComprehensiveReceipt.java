import java.util.ArrayList;

public class ComprehensiveReceipt implements ReceiptFormat {

	private HotelModel model;

	public ComprehensiveReceipt (HotelModel model) {
		this.model = model;
	}

	@Override
	public String format() {
		Guest usr= (Guest) model.getCurrentUser();
		ArrayList<Reservation> reservationList = usr.getReservations();
		String reservationData = usr.getName();
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