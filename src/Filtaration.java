import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Filtaration {

    public static void main(String[] args) {

	try{
	    test();

	}catch(IOException ioe){

	    System.out.println("Filtaration#IOException!");

	}

    }

    public static void test() throws IOException {

	String path = "sample.txt";
	String allTexts;
	String[] contents;

	BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));

	int result;
	int i=0;

	while((allTexts = in.readLine()) !=null){

	    contents = allTexts.split("\t");

	    if (contents.length < 3) {
		continue;
	    }
			

	    System.out.printf("[%3d]", i + 1);
	    i++;
	    result = execute(contents[1], contents[2]);
	    System.out.println(result);

	    // SearchText st = new SearchText(i + 1, contents[1], contents[2],
	    // "");
	    // st.search();

			
	}

	in.close();

    }

    private static int execute(String title, String text) {


	AlgorithmInterface alg = new MorphemeAlgorithm(title, text);

	alg.preProcess();
	alg.process();
	alg.postProcess();
	alg.print();

	return alg.getStandardResult();
	/**
	 * if(alg.isQualified()==true){ //pass return alg.getIntegerResult();
	 * }else{ //fail return -1; }
	 **/
	}

}