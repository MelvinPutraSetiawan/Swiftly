package observer;

public class Trader extends User implements Observer{
	private Event event;

	public Trader(int id, String name, String email, String password) {
		super(id, name, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Event event) {
		// TODO Auto-generated method stub
		this.event = event;
		System.out.println("========================================");
		System.out.println("Observer Name : " + this.getName());
		System.out.println("Notified Event : " + event.getName());
		System.out.println("========================================");
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
