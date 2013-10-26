import java.util.ArrayList;

public class MenAlgorithm extends AlgorithmStrategy{

    ArrayList<Entry> inputEntries = new ArrayList<Entry>();
    ArrayList<Entry> outputEntries = new ArrayList<Entry>();
    
    User user = new User();

    
    public MenAlgorithm(User user,ArrayList<Entry> entries){

	this.user = user;
	this.inputEntries = entries;
	
    }

    public ArrayList<Entry> process(){

	return this.inputEntries;


    }

}
