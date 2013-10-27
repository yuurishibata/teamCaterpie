import java.util.ArrayList;

pubic class WomenAlgorithm extends AlgorithmStrategy {

    private ArrayList<Entry> inputEntries = new ArrayList<Entry>();
    private ArrayList<Entry> outputEntries = new ArrayList<Entry>();
    private User user = new User();


    public WomenAlgorithm(User user,ArrayList<Entry> entries){

	this.user  = user;
	this.inputEntries = entries;

    }

    public ArrayList<Entry> process(){

	return this.inputEntries;

    }

}
