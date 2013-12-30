public interface AlgorithmInterface {
	public void preProcess();
	public void process();
	public void postProcess();
	public boolean isQualified();
	public void print();
	public int getStandardResult();
	public double getPerspectiveResult();
}