import java.util.ArrayList;
import java.util.*;

public class SearchMorpheme {

    // blog contents
    private String title;
    private String text;
    // keywords　<= setup by  this constructor
    private String[] menMorphemes;  
    private String[] womenMorphemes; 
    // matched word
    private ArrayList<String> MenMatchWords = new ArrayList<String>();
    private ArrayList<String> WomenMatchWords = new ArrayList<String>();
    // 形態素の各位置を格納するArrayList
    private ArrayList<Integer> allIndexs = new ArrayList<Integer>();// 全てのワードの位置（数値）
    private ArrayList<Integer> MenIndexs = new ArrayList<Integer>();// 男性のキーワードの位置
    private ArrayList<Integer> WomenIndexs = new ArrayList<Integer>();// 女のキーワードの位置
    //
    private ArrayList<Double> results = new ArrayList<Double>();//男性なら-1.0、女性なら1.0の結果を格納しておく
    //男性用の形態素なら１、女性用の形態素なら２を格納する。
    private ArrayList<Integer> wordSex = new ArrayList<Integer>();

    // コンストラクタ
    public SearchMorpheme(String title, String text) {
	//ブログのタイトル・テキストを属性値に設定する。
	this.title = title;
	this.text = text;
	//辞書オブジェクトから各単語をセットする。
	MorphemeDictionary d = new MorphemeDictionary();
	this.menMorphemes = d.getMenWords();
	this.womenMorphemes = d.getWomenWords();
	        
    }

    public void search() {

	// 男性用の形態素を探索する。
	for (String word : menMorphemes) {
	    //タイトルのテキストを捜索。
	    if (title.contains(word)) {
		                
		MenMatchWords.add(word);// その単語を文字列リストに格納。
		MenIndexs.add(title.indexOf(word));// そのインデックスを男性のみのインデックスを入れる整数型リストに格納。
		allIndexs.add(title.indexOf(word));// そのインデックスを男女両方のインデックスを入れる整数型リストに格納。
		wordSex.add(1);
	    }

	    //テキスト本文の探索
	    if (text.contains(word)) {

		                
		MenMatchWords.add(word);
		MenIndexs.add(text.indexOf(word));
		allIndexs.add(text.indexOf(word));  
		wordSex.add(1);
		                
		// もし同じ単語が複数ある場合（二つ目のインデックスと一つ目が食い違う場合）
		if (text.indexOf(word) != text.lastIndexOf(word)) {
		    //複数同じ形態素がある場合は、それを最初のものと区別して扱う。
		    MenIndexs.add(text.lastIndexOf(word));
		    allIndexs.add(text.lastIndexOf(word));
		    MenMatchWords.add(word);
		    wordSex.add(1);
		                        
		}

	    }
	}

	// 女性の形態素があるかどうかタイトルと本文を検索する。
	for (String word : womenMorphemes) {

	    //もしそのブログのタイトルにその単語が含まれていたら
	    if (title.contains(word)) {
		WomenMatchWords.add(word);
		WomenIndexs.add(title.indexOf(word));
		allIndexs.add(title.indexOf(word));
		wordSex.add(2);
		                
	    }

	    //もしそのブログの本文にその単語が含まれていたら
	    if (text.contains(word)) {
		                
		WomenMatchWords.add(word);
		WomenIndexs.add(text.indexOf(word));
		allIndexs.add(text.indexOf(word));
		wordSex.add(2);
		                
		// その本文にその単語が一つあるということは、複数ある可能性があるということ。
		//もし同じ単語が複数ある場合の対処処理をこの次に記述する。
		if (text.indexOf(word) != text.lastIndexOf(word)) {
		                        
		    WomenIndexs.add(text.lastIndexOf(word));
		    allIndexs.add(text.lastIndexOf(word));
		    WomenMatchWords.add(word);
		    wordSex.add(2);

		}

	    }
	}

    }
        
    public ArrayList<String> getMenMorphemes(){
	return this.MenMatchWords;
    }
        
    public ArrayList<String> getWomenMorphemes(){
	return this.WomenMatchWords;
    }
        
    public ArrayList<Integer> getMenIndexs(){
	return this.MenIndexs;
    }
        
    public ArrayList<Integer> getWomenIndexs(){
	return this.WomenIndexs;
    }
        
    public ArrayList<Integer> getAllIndexs(){
	return this.allIndexs;
    }
        
    public ArrayList<Integer> getWordSex(){
	return this.wordSex;
    }

}
