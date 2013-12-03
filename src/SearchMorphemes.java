import java.util.*;

public class SearchMorphemes {

    // ブログ記事のコンテンツ
    private String title;
    private String text;
    // 辞書に登録されている形態素
    private String[] menMorphemes;//辞書の男性用形態素  
    private String[] womenMorphemes; //辞書の女性用形態素
    private String[] exceptionalWords;//ブログ記事が適正か評価する形態素
    // 辞書内の形態素を記事から検索して、合致したものを格納するArrayList
    private ArrayList<String> MenMatchWords = new ArrayList<String>();
    private ArrayList<String> WomenMatchWords = new ArrayList<String>();
    // 形態素の各位置を格納するArrayList
    private ArrayList<Integer> allIndexs = new ArrayList<Integer>();// 全てのワードの位置（数値）
    private ArrayList<Integer> MenIndexs = new ArrayList<Integer>();// 男性のキーワードの位置
    private ArrayList<Integer> WomenIndexs = new ArrayList<Integer>();// 女のキーワードの位置
    //男性用の形態素なら[1]、女性用の形態素なら[2]を格納するArrayList
    private ArrayList<Integer> wordSex = new ArrayList<Integer>();

    // コンストラクタ
    public SearchMorphemes(String title, String text) {
	//ブログのタイトル・テキストを属性値に設定する。
	this.title = title;
	this.text = text;
	//形態素辞書オブジェクトから各単語をセットする。
	MorphemeDictionary md = new MorphemeDictionary();
	this.menMorphemes = md.getMenWords();
	this.womenMorphemes = md.getWomenWords();
	this.exceptionalWords = md.getExceptionalWords();
	        
    }

    public void search() {

	// 男性用の形態素を探索する。
	for (String word : menMorphemes) {
	    //タイトルの捜索。
	    if (title.contains(word)) {
		//男性の形態素の探索なので最後の引数はtrue
		register(word,title.indexOf(word),true);
	    }
	    //本文の探索。
	    if (text.contains(word)) {
		register(word,text.indexOf(word),true);
		//同じ単語が複数存在する場合
		if (text.indexOf(word) != text.lastIndexOf(word)) {
		    register(word,text.lastIndexOf(word),true);
		}
	    }
	}

	// 女性用の形態素を検索する。
	for (String word : womenMorphemes) {
	    //タイトルの探索。
	    if (title.contains(word)) {
		//女性の形態素の探索なので最後の引数はfalse
		register(word,title.indexOf(word),false);
	    }
	    //本文の検索。
	    if (text.contains(word)) {
		register(word,text.indexOf(word),false);
		//同じ単語が複数存在する場合
		if (text.indexOf(word) != text.lastIndexOf(word)) {
		    register(word,text.lastIndexOf(word),false);
		}
	    }
	}

    }
    /**
     * ブログ内容が出力に適格か判断するメソッド
     */
    public boolean containsException(){
	
	//不要語登録された形態素を探索
	for(String word : exceptionalWords){
	    //タイトル
	    if(title.contains(word)){
		return true;
	    }
	    //本文
	    if(text.contains(word)){
		return true;
	    }
	    
	}
	//不要語は存在しなかったので含まないと判断
	return false;
    }
        
    //クラスの属性値に記事内の形態素・位置などを登録するメソッド
    public void register(String word,int index,boolean isMan){
	//男性
	if(isMan==true){
	    //男性の形態素として登録する
	    registerManMorpheme(word, index);
            //女性
	}else{
	    //女性の形態素として登録する
	    registerWomenMorpheme(word, index);
	}
    }

    public void registerManMorpheme(String word, int index) {
	
	MenMatchWords.add(word);// その単語を文字列リストに格納。
	MenIndexs.add(index);// そのインデックスを男性のみのインデックスを入れる整数型リストに格納。
	allIndexs.add(index);// そのインデックスを男女両方のインデックスを入れる整数型リストに格納。
	wordSex.add(1);//性別を特定するための数値を整数型リストに格納。
	
    }
    
    public void registerWomenMorpheme(String word, int index) {
	
	WomenMatchWords.add(word);
	WomenIndexs.add(index);
	allIndexs.add(index);
	wordSex.add(2);
	
    }

        
    //ブログ内に存在した男性用の形態素を取得する
    public ArrayList<String> getMenMorphemes(){
	return this.MenMatchWords;
    }
    //ブログ内に存在した女性用の形態素を取得する
    public ArrayList<String> getWomenMorphemes(){
	return this.WomenMatchWords;
    }
    //ブログ内に存在した男性用の形態素の位置を取得する
    public ArrayList<Integer> getMenIndexs(){
	return this.MenIndexs;
    }
    //ブログ内に存在した女性用の形態素の位置を取得する
    public ArrayList<Integer> getWomenIndexs(){
	return this.WomenIndexs;
    }
    //ブログ内に存在した形態素の位置を全て取得する
    public ArrayList<Integer> getAllIndexs(){
	return this.allIndexs;
    }
    //ブログ内に存在した形態素の性別情報を取得する
    public ArrayList<Integer> getWordSex(){
	return this.wordSex;
    }

}
