import java.util.*;

public class MorphemeAlgorithm implements AlgorithmInterface{

    // 辞書の形態素とブログ記事を照合して合致した単語を格納する
    private ArrayList<String> MenWords = new ArrayList<String>();
    private ArrayList<String> WomenWords = new ArrayList<String>();
    //形態素を検索する
    SearchMorphemes sm;
    //疑似データベース
    private RDataDB hcdb;
    //検索結果の格納先
    private ArrayList<Integer> allIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> WomenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> MenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> SexDistinctions = new ArrayList<Integer>();
    //返却値を返す直前のアルゴリズム計算で用いる変数
    ArrayList<RData> results = new ArrayList<RData>();
    //executeの返却値
    private int result=0;
    //アルゴリズム計算の結果を保持する変数
    private double resultDouble=0.0;
    //適性検査の返却値
    private boolean qualification = false; 
    
    // コンストラクタ
    public MorphemeAlgorithm(String title, String text) {
        //形態素をテキストから抽出するオブジェクト
        sm = new SearchMorphemes(title,text);
        //Rの統計データを保持するオブジェクト
        hcdb = new RDataDB();
        
    }
    
    /**
     * アルゴリズムの計算準備
     * このアルゴリズムの場合はブログ記事内の形態素の取得
     */
    public void preProcess(){
	
	//検索開始
	sm.search();
	//合致した男性用形態素の取得
        MenWords = sm.getMenMorphemes();
        //合致した女性用形態素の取得
        WomenWords = sm.getWomenMorphemes();
        //合致した全ての形態素の位置の取得
        allIndexs = sm.getAllIndexs();
        //合致した全ての形態素の性別を取得
        SexDistinctions = sm.getWordSex();
	
        //テキスト内に存在した男性の形態素のRの統計データを取得する
        for(String s : MenWords){
	    
	    results.add(hcdb.getRData(s));
	    
	}
	//テキスト内に存在した女性の形態素のRの統計データを取得する
	for(String s : WomenWords){
	    
	    results.add(hcdb.getRData(s));
	    
	}
    }

    /**
     * このクラスの心臓部
     * アルゴリズムの計算内容のみを記述
     */
    public void process() {
	
	//仮のアルゴリズムの計算式 => 単純な総和
	for(RData r : results){
	    //暫定的にconfidenceの総和を求める
	    resultDouble += r.getConfidence();
	     
	}
         
	//仮のアルゴリズム
	if(resultDouble >= 2.0){
	    result = 2;
	}else if(resultDouble >= 0.0){
	    result = 0;
	}else{
	    result = 1;
	}
         
    }
    
    /**
     * ブログのアプリ適正検査を実施。
     */
    public void postProcess(){
	
	if(sm.containsException()==true){
	    this.qualification =  false;
	}else{

	    this.qualification =  true;
	    
	}
	
    }
    /**
     * ブログのアプリ適性結果を返却
     */
    public boolean isQualified(){
	
	return this.qualification;
	
    }
    
    /**
     * executeメソッドの返却(整数)
     */
    public int getIntegerResult(){
	
	return this.result;
	
    }
    /**
     * processメソッドの計算結果(実数)
     */
    public double getDoubleResult(){
	
	return this.resultDouble;
    }
    /**
     * コンソールに取得した全ての情報を出力する
     */
    public void print() {
	//マッチした形態素の性別を出力する
	printWordSex();
	// 辞書と一致した単語を出力する
        printMatchWords();
        // 辞書と一致した単語のインデックス（ブログ内の位置）を出力する
        printAllIndexs();
        // 辞書と一致した単語の分散度を出力する
        printDispersionValue();
        //マッチした形態素よって変化する各ブログの実数結果を出力する
        printResults();
    }
    
    public void printWordSex(){
	
	if(MenWords.size()+WomenWords.size() == 0){
	    return;//何もしない
	}
	
	System.out.print("\n形態素の性別 : ");//改行
	for(int sd : SexDistinctions){
	    
	    System.out.print(sd);
	    
	}
	System.out.println("");//改行
	
	
    }

    private void printAllIndexs() {

        // 出力準備
        System.out.print("INDEXS : [");

        // 辞書とのキーワードにマッチしてない
        if (allIndexs.isEmpty() == true) {
            // 何もないことを表示
            System.out.print("NONE");
            
        }else {
	    
	    //辞書とのキーワードにマッチした
            for (int i = 0; i < allIndexs.size(); i++) {
                // 最後の要素だけは、見やすさのために一工夫
                if (i == allIndexs.size() - 1) {
                    System.out.print(allIndexs.get(allIndexs.size() - 1));
                } else {
                    System.out.print(allIndexs.get(i) + ",");
                }
            }
        }
        System.out.println("]");
    }

    private void printMatchWords() {

        // もし、表示する内容が無い場合、何もしないでreturn
        if (MenWords.isEmpty() == true && WomenWords.isEmpty() == true) {
            return;
        }

        // 出力準備
        System.out.print("KEYWORDS : ");

        for (String s : MenWords) {
            System.out.print("*" + s + "*");
        }
        for (String s : WomenWords) {
            System.out.print("*" + s + "*");
        }
        // 改行
        if (allIndexs.isEmpty() == false) {
            System.out.print("\n");
        }
    }

    private void printDispersionValue() {

        // もし、男性のキーワードと一致したものが存在し、
        if (MenIndexs.isEmpty() == false) {

            // キーワード二個以上と一致している場合
            if (MenIndexs.size() >= 2) {

                Object[] o = MenIndexs.toArray();
                // 降順にソートする
                java.util.Arrays.sort(o);

                // 男性のインデックスの最小値と最大値を変数に格納
                int min = (int) o[0];
                int max = (int) o[o.length - 1];

                System.out.println("男性キーワードの分散値 : " + (max - min));

            }
        }

        if (WomenIndexs.isEmpty() == false) {

            if (WomenIndexs.size() >= 2) {

                Object[] o = WomenIndexs.toArray();
                java.util.Arrays.sort(o);

                int min = (int) o[0];
                int max = (int) o[o.length - 1];

                System.out.println("女性キーワードの分散値 : " + (max - min));
            }
        }

    }
    
    public void printResults(){
	
	double result =0;
	
	for(RData r : results){
	    
	    result += r.getConfidence();
	    
	}
	
	System.out.println("=>"+result);
	
	
    }
    
    
}
