package music;

public class MusicalInstrument implements Comparable<MusicalInstrument> {

	
	private String name;
	private double price;
	private String type;
	
	
	public MusicalInstrument(String name, double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}

	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}
	
	public void viewInstrumentInfo() {
		System.out.println(this.name + "; " + this.type + "; " + this.price + " lv.");
	}


	
	@Override
	public int compareTo(MusicalInstrument arg0) {
		
		return this.name.compareToIgnoreCase(arg0.name);
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MusicalInstrument other = (MusicalInstrument) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
		
}
