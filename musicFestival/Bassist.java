package musicFestival;

public class Bassist extends Musician {

	
	public Bassist(String name) {
		super.setName(name);
		super.setInstrument("Bass");
		
	}
	
	
	
	@Override
	public void play() {
		System.out.println(super.getName() + " plays an earth-shaking rithm on his bass!!!");
			
	}

}
