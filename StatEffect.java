class StatEffect {
	ValidEffect heldEffect;
	String name;
	int durationLeft;
	
	public StatEffect(ValidEffect inEffect, int inDuration, String inName) {
		heldEffect = inEffect;
		name = inName;
		durationLeft = inDuration;
	}
}

