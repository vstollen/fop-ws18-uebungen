package SecurityAlert;

public class President extends GovernmentEmployee {
	
	/**
	 * @param name
	 * @param sex
	 */
	public President(String name, Sex sex) {
		this.name = name;
		this.sex = sex;
	}
	
	private static final SecurityLevel SECURITY_LEVEL = SecurityLevel.HIGH;
	private static final String TITLE = "President";
	
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
		
		sb.append(TITLE).append(" ").append(name);
		
		return sb.toString();
	}

	
}
