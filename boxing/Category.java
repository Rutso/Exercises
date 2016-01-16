package boxing;

public class Category {

	public final String name;
	public final double maxWeight;
	public final double minWeight;
	private Competition event;
	private Boxer[] competitors = new Boxer[8];
	private Boxer[] semiFinalists = new Boxer[4];
	private Boxer[] finalists = new Boxer[2];
	private Fight[] quarterFinals = new Fight[4];
	private Fight[] semiFinals = new Fight[2];
	private Fight theFinal;
	private Boxer champion;
	
	
	public Category(String name, double maxWeight, double minWeight, Competition competition) {
		//TODO: implement data checks!!!
		this.name = name;
		this.maxWeight = maxWeight;
		this.minWeight = minWeight;
		this.event = competition;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public void addCompetitor(Boxer newCompetitor) {
		if (newCompetitor.getWeight() < this.maxWeight && newCompetitor.getWeight() >= this.minWeight) {
			int counter = 0;
			for (int i = 0; i < this.competitors.length; i++) {
				if (this.competitors[i] != null && this.competitors[i].equals(newCompetitor)) {
					System.out.println(newCompetitor.getName() + " is already added to this category.");
					return;
				}
				if (this.competitors[i] == null) {
					this.competitors[i] = newCompetitor;
					return;
				}
				counter++;
			}
			if (counter == this.competitors.length) {
				System.out.println("Can't add " + newCompetitor.getName() + " to " + this.name + " category. Category already full.");
			}
		} else {
			System.out.println(newCompetitor.getName() + " is too heavy for " + this.name + " category.");
		}
	}
	
	public void setChampion(Boxer champion) {
		this.champion = champion;
	}
	
	
	public static Boxer resolveFight(Fight fight) {
		System.out.println(fight.getOpponent1().getName() + " VS. " + fight.getOpponent2().getName() + ":");
		while (fight.getOpponent1().getStamina() > 0 && fight.getOpponent2().getStamina() > 0) {
			fight.getOpponent1().hitOpponent(fight.getOpponent2());
			fight.getOpponent2().hitOpponent(fight.getOpponent1());
		}
		
		if (fight.getOpponent1().getStamina() == 0) {
			fight.setWinner(fight.getOpponent2());
			fight.getOpponent1().lose();
			fight.getOpponent1().addFightToRecord(fight);
			fight.getOpponent2().win();
			fight.getOpponent2().addFightToRecord(fight);
			System.out.println(fight.getOpponent2().getName() + " WINS!");
			fight.getOpponent1().returnStamina();
			fight.getOpponent2().returnStamina();
			System.out.println();
			return fight.getOpponent2();
			
		} else {
			fight.setWinner(fight.getOpponent1());
			fight.getOpponent2().lose();
			fight.getOpponent2().addFightToRecord(fight);
			fight.getOpponent1().win();
			fight.getOpponent1().addFightToRecord(fight);
			System.out.println(fight.getOpponent1().getName() + " WINS!");
			fight.getOpponent1().returnStamina();
			fight.getOpponent2().returnStamina();
			System.out.println();
			return fight.getOpponent1();
		}
		
		
		
	}
	
	
	
	
	
	
	private void resolveQuarterFinals() {
		System.out.println("Quarterfinal fights in " + this.name + " category:");
		int m=0;
		int n=1;
		for (int i = 0; i < quarterFinals.length; i++) {
			quarterFinals[i] = new Fight(this.event, competitors[m], competitors[n]);
			quarterFinals[i].setCategory(this.name);
			m+=2;
			n+=2;
		}
		
		for (int i = 0; i < quarterFinals.length; i++) {
			semiFinalists[i] = resolveFight(quarterFinals[i]);
		}
		
		for (int i = 0; i < semiFinalists.length; i++) {
			if (i < semiFinalists.length-1) {
				System.out.print(semiFinalists[i].getName() + ", ");
			} else {
				System.out.print(semiFinalists[i].getName() + " qualify for the semifinals.");

			}
		}
		
		System.out.println();
		
	}
	
	private void resolveSemiFinals() {
		System.out.println("Semifinal fights in " + this.name + " category:");
		int m=0;
		int n=1;
		for (int i = 0; i < semiFinals.length; i++) {
			semiFinals[i] = new Fight(this.event, semiFinalists[m], semiFinalists[n]);
			semiFinals[i].setCategory(this.name);
			m+=2;
			n+=2;
		}
		
		for (int i = 0; i < semiFinals.length; i++) {
			finalists[i] = resolveFight(semiFinals[i]);
		}
		
		for (int i = 0; i < finalists.length; i++) {
			if (i < finalists.length-1) {
				System.out.print(finalists[i].getName() + ", ");
			} else {
				System.out.print(finalists[i].getName() + " qualify for the final.");

			}
		}
		
		System.out.println();
	}
	
	private void resolveFinal() {
		System.out.println("Final fight in " + this.name + " category:");
		this.theFinal = new Fight(this.event, this.finalists[0], this.finalists[1]);
		this.theFinal.setCategory(this.name);
		this.champion = resolveFight(theFinal);
		this.champion.addTitle(this.event.getTitle());
		System.out.println(this.champion.getName() + " is the champion in " + this.name + " category at " + this.event.getName() + ".");
		
	}
	
	
	public void resolveCategory() {
		System.out.println("The competition in " + this.name + " category begins!!!");
		this.resolveQuarterFinals();
		this.resolveSemiFinals();
		this.resolveFinal();
		System.out.println();
	}
	
	
	public void showCompetitors() {
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				competitors[i].showBoxerStats();
				competitors[i].showBoxerFights();
			} else {
				return;
			}
		}
	}
	
}
