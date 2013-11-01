import java.util.ArrayList;

public class Entry {

    private String title = "DEFAULT_TITLE";
    private ArrayList<String> texts;
    private ArrayList<String> pictures;
    
    public Entry(){
    
	texts = new ArrayList<String>();
	pictures = new ArrayList<String>();

    }

    public void add(String text){
    
	this.texts.add(text);
    
    }

    public ArrayList<String> getTexts(){
    
	return this.texts;
    }

    public void print(){

	for(String s : texts){

	    System.out.println(s);

	}

    }
