import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MorphemeAlgorithm implements AlgorithmInterface{

    SearchMorphemes sm;
    private RDataDB rddb;

    private ArrayList<String> MenWords = new ArrayList<String>();
    private ArrayList<String> WomenWords = new ArrayList<String>();
    private ArrayList<Integer> AllIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> WomenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> MenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> SexDistinctions = new ArrayList<Integer>();

    ArrayList<RData> Rs = new ArrayList<RData>();

    double high =2;
    double low =0;

    private int result=0;
    private double resultDouble=0.0;
    private boolean qualification = false; 
    
    public MorphemeAlgorithm(String title, String text) {

        sm = new SearchMorphemes(title,text);

        rddb = new RDataDB();
        
        try{

        readFile();


        }catch(IOException ioe){

	    whenIOException();

        }catch(NumberFormatException nfe){

	    whenNumberFormatException();

	}

    }
    
    public void whenIOException(){

	System.out.println("MorphemeAlgorithm#whenIOException()");

    }

    public void whenNumberFormatException(){

	System.out.println("MorphemeAlgorithm#whenNumberFormatException()");

	high = 2.0;
	low  = 0.0;

    }

    
    public void preProcess(){

    	setMorphemes();
        setRData();

    }

    public void setMorphemes() {

    	sm.search();

        MenWords = sm.getMenMorphemes();

        MenIndexs = sm.getMenIndexs();

        WomenWords = sm.getWomenMorphemes();

        WomenIndexs = sm.getWomenIndexs();

        AllIndexs = sm.getAllIndexs();

        SexDistinctions = sm.getSexDistinctions();

    }

    public void setRData() {

        for(String s : MenWords){
    		
	    Rs.add(rddb.getRData(s));
    		
    	}

    	for(String s : WomenWords){
    		
	    Rs.add(rddb.getRData(s));
    		
    	}

    }

    public void process() {
    	
         for(RData R : Rs){

        	 resultDouble += R.getConfidence();
        	 
         }
         
         if(resultDouble >= high){
        	 result = 2;
         }else if(resultDouble >= low){
        	 result = 0;
         }else{
        	 result = 1;
         }
         
    }

    public void postProcess(){

    	if(sm.containsUnnecessaryWord()==true){

    		this.qualification =  false;
    		
    	}else{

    		this.qualification =  true;
    	
    	}

    }

    public boolean isQualified(){
    	
    	return this.qualification;
    	
    }

    public int getStandardResult(){
    	
    	return this.result;
    	
    }

    public double getPerspectiveResult(){
    	
    	return this.resultDouble;
    }

    private void readFile()throws IOException{

	String path = "number.csv";
	BufferedReader input = new BufferedReader(new FileReader(path));

	String line;
	String[] RData;
	// setup each RData
	while ((line = input.readLine()) != null) {

	    RData = line.split(",");
	    // change Type from String to Double
	    high = Double.parseDouble(RData[0]);
	    low = Double.parseDouble(RData[1]);
    			
	}
	// close
	input.close();
    	
    	
    }

	public void print() {

	printSexDistinctions();
        printMatchWords();
        printAllIndexs();
        printResults();

	}

    public void printSexDistinctions(){

	
	for(int number : SexDistinctions ){

	    System.out.print(number);

	}
	
	System.out.println("");

    }
	
	public void printWordSex(){
		
		if(MenWords.size()+WomenWords.size() == 0){
			return;
		}
		
		System.out.print("\nThe Sex Of Morphemes : ");
		for(int sd : SexDistinctions){
			
			System.out.print(sd);
			
		}
		System.out.println("");
		
		
	}

    private void printAllIndexs() {

        System.out.print("INDEXS : [");

        if (AllIndexs.isEmpty() == true) {

            System.out.print("NONE");
            
        }else {
        	
            for (int i = 0; i < AllIndexs.size(); i++) {
                if (i == AllIndexs.size() - 1) {
                    System.out.print(AllIndexs.get(AllIndexs.size() - 1));
                } else {
                    System.out.print(AllIndexs.get(i) + ",");
                }
            }
        }
        System.out.println("]");
    }

    private void printMatchWords() {

        if (MenWords.isEmpty() == true && WomenWords.isEmpty() == true) {
            return;
        }

        System.out.print("KEYWORDS : ");

        for (String s : MenWords) {
            System.out.print("*" + s + "*");
        }
        for (String s : WomenWords) {
            System.out.print("*" + s + "*");
        }
        if (AllIndexs.isEmpty() == false) {
            System.out.print("\n");
        }
    }

    private void printDispersionValue() {

    }
    
    public void printResults(){
    	
    	double result =0;
    	
    	for(RData R : Rs){
    		
    		result += R.getConfidence();
    		 		
    	}
    	
    	System.out.println("=>"+result);
    	
    	
    }
    
    
}