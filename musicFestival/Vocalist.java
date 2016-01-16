package musicFestival;

public class Vocalist extends Musician {

	
	
	
	public Vocalist(String name) {
		super.setName(name);
		super.setInstrument("Vocals");
		
	}

	@Override
	public void play() {
		System.out.println(super.getName() + " shouts: 'Yeahhhh!!! Yeahhhh!!! Let me see your hands!!! Yeahhhh, baby!!!");
		System.out.println(super.getName() + " sings: ");

	}

	
	
	
}
