package musicFestival;

public abstract class Musician {

	private String name;
	private Band band;
	private String instrument;
	
	
	public abstract void play();
	
	public String getName() {
		return this.name;
	}
	
	public String getInstrument() {
		return this.instrument;
	}
	
	
	public String getBandName() {
		return this.band.getName();
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	
	public void setBand(Band band) {
		this.band = band;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
}
