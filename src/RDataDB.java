import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RDataDB {

	private HashMap<String, RData> RMap = new HashMap<String, RData>();

	public RDataDB() {

	    try {

		setRData();

	    } catch (IOException ioe) {

		ioe.printStackTrace();
		whenIOException();

	    } catch (ArrayIndexOutOfBoundsException aioobe){

		whenArrayIndexOutOfBoundsException();

	    }

	}

    private void whenArrayIndexOutOfBoundsException(){

	System.out.println("RDataDB#whenArrayIndexOutOfBoundsException()");

    }

    private void setRData() throws IOException {

	String path = "test.csv";
	BufferedReader input = new BufferedReader( new InputStreamReader( new FileInputStream(path),"UTF-8"));
	// one line
	String line;
	String[] RDataWithMorpheme;
	// each R Data
	double support;
	double confidence;
	double lift;
	// setup each RData
	while ((line = input.readLine()) != null) {

	    RDataWithMorpheme = line.split(",");

	    support = Double.parseDouble(RDataWithMorpheme[2]);
	    confidence = Double.parseDouble(RDataWithMorpheme[3]);
	    lift = Double.parseDouble(RDataWithMorpheme[4]);

	    RMap.put(RDataWithMorpheme[1], new RData(support, confidence, lift));

	}

	// close
	input.close();

	}

    private void whenIOException(){
	
	System.out.println("RDataDB#whenIOException()");
	
    }

    public RData getRData(String word) {


	if (RMap.containsKey(word) == false) {
	    return new RData();
	} else {
	    return RMap.get(word);

	}

    }

}