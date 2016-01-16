package musicFestival;

public class Act {

	private Band band;
	private String onStage;
	private String offStage;
	
	
	public Act(Band band, String onStage, String offStage) {
		this.band = band;
		this.onStage = onStage;
		this.offStage = offStage;
	}
	
	
	public void playAct() {
		
		System.out.println(this.onStage + ": " + this.band.getName() + " goes on stage.");
		this.band.perform();
		System.out.println(this.offStage + ": " + this.band.getName() + " leaves the stage.");

	}
	
	
	public void displayActInfo() {
		this.band.displayBandInfo();
		System.out.println("On stage: " + this.onStage);
		System.out.println("Off stage: " + this.offStage);
		System.out.println();
	}
	
}
