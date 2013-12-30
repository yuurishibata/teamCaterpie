class RData {

	private double support = 0.0;
	private double confidence = 0.0;
	private double lift = 0.0;

	// constructor
	public RData() {
		this(0.0, 0.0, 0.0);
	}

	// constructor
	public RData(double support, double confidence, double lift) {

		this.support = support;
		this.confidence = confidence;
		this.lift = lift;
	}

	public double getSupport() {
		return this.support;
	}

	public double getConfidence() {
		return this.confidence;
	}

	public double getLift() {
		return this.lift;
	}

}