class StatEffect {
	validEffect heldEffect;
	String name;
	int durationLeft;
	
	public StatEffect(validEffect inEffect, int inDuration, String inName) {
		heldEffect = inEffect;
		name = inName;
		durationLeft = inDuration;
	}
}

