package musicFestival;

public class Drummer extends Musician {

	
	public Drummer(String name) {
		super.setName(name);
		super.setInstrument("Drums");
		
	}

	@Override
	public void play() {
		System.out.println(super.getName() + " pounds the drums like a battering ram!!!");
		
	}

	
}
