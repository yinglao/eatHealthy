package review.model;
import java.util.Date;

public class Reservations {
	protected int reservationId;
	protected Date start;
	protected Date end;
	protected int size;
	protected Users user;
	protected SitDownRestaurant sitDownRestaurant;
	public Reservations(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
	public Reservations(int reservationId, Date start, Date end, int size, Users user, SitDownRestaurant sitDownRestaurant) {
		super();
		this.reservationId = reservationId;
		this.start = start;
		this.end = end;
		this.size = size;
		this.user = user;
		this.sitDownRestaurant = sitDownRestaurant;
	}
	
	public Reservations(Date start, Date end, int size, Users user, SitDownRestaurant sitDownRestaurant) {
		super();
		this.start = start;
		this.end = end;
		this.size = size;
		this.user = user;
		this.sitDownRestaurant = sitDownRestaurant;
	}
	
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public SitDownRestaurant getSitDownRestaurant() {
		return sitDownRestaurant;
	}
	public void setSitDownRestaurant(SitDownRestaurant sitDownRestaurant) {
		this.sitDownRestaurant = sitDownRestaurant;
	}
	

}
