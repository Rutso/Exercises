package boxing;

public class Boxer {

	private String name;
	private double weight;
	private int stamina = 100;
	private int maxDamage = 5;
	private int minDamage = 1;
	private String currentCategory;
	private Fight[] boxingRecord = new Fight[50];
	private int totalFights = 0;
	private int wins = 0;
	private int losses = 0;
	private String[] titles = new String[25];
	
	
	public Boxer(String name, double weight) {
		if (name != null && name.length() != 0) {
			this.name = name;
		} else {
			System.out.println("'" + name + "'" + " is an invalid name for a boxer.");
			return;
		}
		
		if (weight >= 53.5) {
			this.weight = weight;
		} else {
			System.out.println(weight + " is unacceptable weight for a boxer.");
		}
	}
	
	
	protected String getName() {
		return name;
	}
	protected double getWeight() {
		return weight;
	}
	protected int getStamina() {
		return stamina;
	}
	protected int getMaxDamage() {
		return maxDamage;
	}
	protected int getMinDamage() {
		return minDamage;
	}
	protected String getCurrentCategory() {
		return currentCategory;
	}
	protected Fight[] getBoxingRecord() {
		return boxingRecord;
	}
	protected int getTotalFights() {
		return totalFights;
	}
	protected int getWins() {
		return wins;
	}
	protected int getLosses() {
		return losses;
	}
	protected String[] getTitles() {
		return titles;
	}

	
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Boxer) {
			Boxer otherBoxer = (Boxer) obj;
			if (this.name.equalsIgnoreCase(otherBoxer.name)) {
				return true;
			}
		} 
			return false;
		
	
	}


	public void addFightToRecord(Fight newFight) {
		int counter = 0;
		for (int i = 0; i < this.boxingRecord.length; i++) {
			if (this.boxingRecord[i] == null) {
				this.boxingRecord[i] = newFight;
				return;
			}
			counter++;
		}
		if (counter == this.boxingRecord.length) {
			System.out.println("It's time for " + this.name + " to retire.");
		}
	}

	public void addTitle(String newTitle) {
		int counter = 0;
		for (int i = 0; i < this.titles.length; i++) {
			if (this.titles[i] == null) {
				this.titles[i] = newTitle;
				return;
			}
			counter++;
		}
		if (counter == this.titles.length) {
			System.out.println(this.name + " already has 25 titles and is a great champion.");
		}
	}

	
	
	public void showBoxerStats() {
		System.out.println("Name: " + this.name + "; weight: " + this.weight + " kg");
		System.out.println("Fights: " + this.totalFights + "; wins: " + this.wins + "; losses " + this.losses);
		System.out.println("Titles:");
		for (int i = 0; this.titles[i] != null && i < this.titles.length; i++) {
			System.out.println(this.titles[i]);
		}

	}
	
	public void showBoxerFights() {
		System.out.println("FIGHTS:");
		for (int i = 0; i < this.boxingRecord.length; i++) {
			if (this.boxingRecord[i] != null) {
				this.boxingRecord[i].showFightInfo();
			}
		}
	}
	
	
	public void trainHard() {
		this.stamina++;
		this.minDamage++;
		this.maxDamage++;
	}

	public void takeDamage(int damage) {
		this.stamina-=damage;
	}
	
	public void hitOpponent(Boxer opponent) {
		if (this.stamina > 0) {
			int chance = (int) (Math.random()*101);
			
			if (chance <= 50) {
				System.out.println(this.name + " misses " + opponent.getName());
			} else {
				int damage = ((int) (Math.random()*this.maxDamage)) + this.minDamage;
				opponent.takeDamage(damage);
				System.out.println(this.name + " inflicts " + damage + " damage to " + opponent.getName() + ": stamina left " + opponent.getStamina());

			}
			
			
			
		}
	}

	public void loseWeight(double kgLost) {
		if (kgLost > 0) {
			this.weight-=kgLost;
		} else {
			System.out.println("Invalid value for kg to lose.");
		}
	}
	
	
	public void gainWeight(double kgGained) {
		if (kgGained > 0) {
			this.weight+=kgGained;
		} else {
			System.out.println("Invalid value for kg to gain.");
		}
	}

	public void win() {
		this.wins++;
		this.totalFights++;
	}

	public void lose() {
		this.losses++;
		this.totalFights++;
	}

	public void returnStamina() {
		this.stamina = 100;
	}
}
