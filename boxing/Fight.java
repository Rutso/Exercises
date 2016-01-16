package boxing;

public class Fight {

	private Competition event;
	private String category;
	private Boxer opponent1;
	private Boxer opponent2;
	private Boxer winner;
	
	
	public Fight(Competition competition, Boxer opponent1, Boxer opponent2) {
		if (competition == null) {
			System.out.println("Null competition error. Can't create fight.");
			return;
		} else {
			this.event = competition;
		}
		if (opponent1 == null) {
			System.out.println("Null opponent 1 error. Can't create fight.");
			return;
		} else {
			this.opponent1 = opponent1;
		}
		if (opponent2 == null) {
			System.out.println("Null opponent 2 error. Can't create fight.");
			return;
		} else {
			if ( !(opponent2.equals(this.opponent1)) )  {
				this.opponent2 = opponent2;
			} else {
				System.out.println("Opponent 2 equals opponent 1. Can't create fight.");
				return;
			}
			
		}
		
		
	}

	public void setWinner(Boxer winner) {
		this.winner = winner;
	}

	
	public void setCategory(String category) {
		
	}

	protected Competition getEvent() {
		return event;
	}

	protected Boxer getOpponent1() {
		return opponent1;
	}

	protected Boxer getOpponent2() {
		return opponent2;
	}

	protected Boxer getWinner() {
		return winner;
	}
	
	public void showFightInfo() {
		System.out.println(this.event.getDate() + ", " + this.event.getName());
		System.out.println(this.category + " category");
		System.out.println(this.opponent1.getName() + " VS. " + this.opponent2.getName());
		System.out.println("Winner: " + this.winner.getName());
		System.out.println();
	}


}
