import java.io.IOException;
import java.util.*;

public class SearchMorphemes {

	private String title;
	private String text;

	private ArrayList<String> menMorphemes;
	private ArrayList<String> womenMorphemes;
	private String[] unnecessaryWords;

	private ArrayList<String> MenMatchWords = new ArrayList<String>();
	private ArrayList<String> WomenMatchWords = new ArrayList<String>();

	private ArrayList<Integer> AllIndexs = new ArrayList<Integer>();
	private ArrayList<Integer> MenIndexs = new ArrayList<Integer>();
	private ArrayList<Integer> WomenIndexs = new ArrayList<Integer>();
	private ArrayList<Integer> SexDistinctions = new ArrayList<Integer>();
    
    public SearchMorphemes(String title, String text) {

	if(title==null){
	    this.title = "";
	}else{
	    this.title = title; 
	}

	if(text==null){
	    this.text = "";
	}else{
	    this.text =  text;
	}

		MorphemeDictionary md = new MorphemeDictionary();
		this.menMorphemes = md.getMenWords();
		this.womenMorphemes = md.getWomenWords();
		this.unnecessaryWords = md.getUnnecessaryWords();
	}

	public void search() {

		searchMenMorphemes();
		searchWomenMorphemes();

	}


	public void searchWomenMorphemes() {

		for (String word : womenMorphemes) {

			if (title.contains(word)) {
			    
			    register(word,title.indexOf(word),false);

			}

			while (0 <= text.indexOf(word)) {

				int length = word.length();
				int index = text.indexOf(word);
				int from = index;
				int to = index + length;

				register(word, index, false);

				String Half_x = text.substring(0, from);
				String Half_y = text.substring(to, text.length());

				text = Half_x + Half_y;
			}
		}
	}

	public void searchMenMorphemes() {

		for (String word : menMorphemes) {

			if (title.contains(word)) {

				register(word, title.indexOf(word), true);
			}

			while (0 <= text.indexOf(word)) {

				int length = word.length();
				int index = text.indexOf(word);
				int from = index;
				int to = index + length;

				register(word, index, true);

				String Half_x = text.substring(0, from);
				String Half_y = text.substring(to, text.length());
				text = Half_x + Half_y;
			}

		}
	}


	public boolean containsUnnecessaryWord() {

		for (String word : unnecessaryWords) {

			if (title.contains(word)) {
				return true;
			}

			if (text.contains(word)) {
				return true;
			}

		}

		return false;
	}

	public void register(String word, int index, boolean isMan) {


		if (isMan == true) {

			registerManMorpheme(word, index);

		} else {

			registerWomenMorpheme(word, index);
		}
	}

	public void registerManMorpheme(String word, int index) {


		MenMatchWords.add(word);
		MenIndexs.add(index);
		AllIndexs.add(index);
		SexDistinctions.add(1);

	}

	public void registerWomenMorpheme(String word, int index) {

		WomenMatchWords.add(word);
		WomenIndexs.add(index);
		AllIndexs.add(index);
		SexDistinctions.add(2);

	}

	public ArrayList<String> getMenMorphemes() {
		return this.MenMatchWords;
	}

	public ArrayList<String> getWomenMorphemes() {
		return this.WomenMatchWords;
	}

	public ArrayList<Integer> getMenIndexs() {
		return this.MenIndexs;
	}

	public ArrayList<Integer> getWomenIndexs() {
		return this.WomenIndexs;
	}

	public ArrayList<Integer> getAllIndexs() {
		return this.AllIndexs;
	}

	public ArrayList<Integer> getSexDistinctions() {
		return this.SexDistinctions;
	}

}
