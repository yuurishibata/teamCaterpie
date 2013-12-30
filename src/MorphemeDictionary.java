import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MorphemeDictionary {

	private final static int MEN = 1;
	private final static int WOMEN = 2;

	private ArrayList<String> MenMorphemes = new ArrayList<String>();
	private ArrayList<String> WomenMorphemes = new ArrayList<String>();

	public MorphemeDictionary() {

	    try {

		setDictionary();

	    } catch (IOException ioe) {

		whenIOException();

	    }catch (NumberFormatException nfe){

		whenNumberFormatException();

	    }catch (ArrayIndexOutOfBoundsException aioobe){
		
		whenArrayIndexOutOfBoundsException();
		    
	    }

	}

    public void whenArrayIndexOutOfBoundsException(){

	System.out.println("MorphemeDictionary#whenArrayIndexOutOfBoundsException()");
	
    }

    public void setDictionary() throws IOException {

	String path = "test.csv";
	BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));

	String set;
	String[] line;

	while ((set = input.readLine()) != null) {

	    line = set.split(",");

	    int sex = Integer.parseInt(line[0]);

	    if (sex == MEN) {

		MenMorphemes.add(line[1]);

	    } else if (sex == WOMEN) {

		WomenMorphemes.add(line[1]);

	    } else {
			

	    }

	}

	input.close();

    }


    public void whenIOException(){

	System.out.println("MorphemeDictionary#whenIOException");

    }

    public void whenNumberFormatException(){

	System.out.println("MorphemeDictionary#whenNumberFormatException()");
	
    }

    private String[] symbols;

    public ArrayList<String> getMenWords() {
		return this.MenMorphemes;
    }

    public ArrayList<String> getWomenWords() {
	return this.WomenMorphemes;
    }

    public String[] getSymbols() {
	return this.symbols;
    }

    public String[] getUnnecessaryWords() {

    String[] a = {"test","test2"};

    return a;

    }

}
