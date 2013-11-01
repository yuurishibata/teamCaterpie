import java.util.ArrayList;

public class AlgorithmStrategy {

    private ArrayList<Entry> inputEntries = new ArrayList<Entry>();
    private ArrayList<Entry> outputEntries = new ArrayList<Entry>();
    User user = new User();

    public AlgorithmStrategy(User user,ArrayList<Entry> entries){

	this.inputEntries  = entries;
	this.user = user;

    }

    public ArrayList<Entry> process(){

	return new ArrayList<Entry>();

    }

}
