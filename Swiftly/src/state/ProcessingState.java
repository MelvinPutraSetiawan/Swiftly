package state;

public class ProcessingState implements State{

	@Override
	public String handle(Order order) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String cancel(Order order) {
		// TODO Auto-generated method stub
		return "Already on processing state, cannot be canceled!";
	}

	@Override
	public String getStateName() {
		// TODO Auto-generated method stub
		return "Processing State";
	}

}