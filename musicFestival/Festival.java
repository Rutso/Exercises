package musicFestival;

public class Festival {

	private String name;
	private String date;
	private String location;
	private Act[] performances;
	private int freePlacesForPerformances;
	
	
	public Festival(String name, String date, String location, int freePlacesForPerformances) {
		this.name = name;
		this.date = date;
		this.location = location;
		
		if (freePlacesForPerformances > 0) {
			this.freePlacesForPerformances = freePlacesForPerformances;
			this.performances = new Act[freePlacesForPerformances];
		} else {
			this.performances = new Act[3];
		}
	}
	
	public void getFestivalInfo() {
		System.out.println(this.name);
		System.out.println("Date: " + this.date);
		System.out.println("Location: " + this.location);

		System.out.println("Acts:");
		int counter = 0;
		for (int i = 0; i < this.performances.length; i++) {
			if (this.performances[i] != null) {
				this.performances[i].displayActInfo();
				counter++;
			} else {
				break;
			}
		}
		if (counter == 0) {
			System.out.println("	Bands to be announced soon.");
		}
	}
	
	public void addAct(Act newAct) {
		if (newAct != null) {
			if (this.freePlacesForPerformances > 0) {
				this.performances[this.performances.length - this.freePlacesForPerformances] = newAct;
				this.freePlacesForPerformances--;
				return;
			} else {
				System.out.println(this.name + " has no free place for another act.");
			}
		} else {
			System.out.println("Null act proposed for the " + this.name + ".");
		}
	}
	
	public void startTheFestival() {
		
		System.out.println(this.name + " starts!");
		for (int i = 0; i < this.performances.length; i++) {
			audienceShouts();
			if (this.performances[i] != null) {
				this.performances[i].playAct();
				audienceShouts();
				System.out.println();
			}
		}
		System.out.println(this.name + " ends!");
	}
	
	
	public void audienceShouts() {
		System.out.println("Audience shouts like hell!!!");
	}
}
