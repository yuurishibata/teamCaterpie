import java.util.ArrayList;

class Filtaration{

    public static void main(String[] args){

	
	ArrayList<Entry> inputEntries = new ArrayList<Entry>();
	ArrayList<Entry> outputEntries = new ArrayList<Entry>();
	User user = new User();

	AlgorithmStrategy alg = setAlgorithm(user,inputEntries);
	outputEntries = alg.process();

	//AlgorithmTest(user,outputEntries);

    }

    private AlgorithmStrategy setAlgorithm(User user,ArrayList<Entry> entries){
	

	if(user.isMan()==true){

	    return new MenAlgorithm(user,entries);

	}else{

	    return new WomenAlgorithm(user,entries);

	}
    }
    
    private void AlgorithmTest(User user,ArrayList<Entry> outputEntries){
	
	user.print();

	for(Entry entry : outputEntries){
	    entry.print();
	}
	
    }

}
