package SecurityAlert;

public class Other extends GovernmentEmployee {

	/**
	 * @param name
	 * @param sex
	 */
	public Other(String name, Sex sex) {
		this.name = name;
		this.sex = sex;
	}
	
	private static final SecurityLevel SECURITY_LEVEL = SecurityLevel.LOW;
	
	@Override
	public int compareTo(GovernmentEmployee o) {
		
		if (o.getSecurityLevel().ordinal() > SECURITY_LEVEL.ordinal()) {
			return 1;
		}
		
		if (o.getSecurityLevel().ordinal() < SECURITY_LEVEL.ordinal()) {
			return -1;
		}
		
		return 0;
	}

	@Override
	SecurityLevel getSecurityLevel() {
		return SECURITY_LEVEL;
	}

	@Override
	String getTitle() {
		
		StringBuilder sb = new StringBuilder();
		
		switch (sex) {
			case FEMALE:
				sb.append("Madame ");
				break;
			case MALE:
				sb.append("Mister ");
				break;
		}
		
		sb.append(name);
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object otherObject) {
	    // Check if we were not passed a valid reference
	    if(otherObject == null) {
	        return false;
	    }
	    // Check if the other class is not the same as our class
	    if(!this.getClass().equals(otherObject.getClass())) {
	        return false;
	    }

	    // Casting the Object to a reference of our type (for convenience)
	    Other other = (Other) otherObject;
	    return (this.sex == other.sex && this.name == other.name);
	}

}
