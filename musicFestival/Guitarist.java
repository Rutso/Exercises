package musicFestival;

public class Guitarist extends Musician {

	
	
	public Guitarist(String name) {
		super.setName(name);
		super.setInstrument("Guitars");
		
	}
	
	
	@Override
	public void play() {
		int option = (int) (Math.random()*3);
		
		switch (option) {
			case 1:
				System.out.println(super.getName() + " plays badass guitar rif!!!");
				break;
			case 2:
				System.out.println(super.getName() + " plays killer guitar solo!!!");
				break;
		}
		
		
	}

	
	
}
