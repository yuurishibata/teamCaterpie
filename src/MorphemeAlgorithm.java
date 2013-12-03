import java.util.*;

public class MorphemeAlgorithm implements AlgorithmInterface{

    // 辞書の形態素とブログ記事を照合して合致した単語を格納する
    private ArrayList<String> MenWords = new ArrayList<String>();
    private ArrayList<String> WomenWords = new ArrayList<String>();
    //形態素を検索する
    SearchMorphemes sam;
    //疑似データベース
    private HashCodeDB hcdb;
    //検索結果の格納先
    private ArrayList<Integer> allIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> WomenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> MenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> SexDistinction = new ArrayList<Integer>();
    //返却値を返す直前のアルゴリズム計算で用いる変数
    ArrayList<Double> results = new ArrayList<Double>();
    private int result=0;
    private double resultDouble=0.0;
    
    // コンストラクタ
    public MorphemeAlgorithm(String title, String text) {
        //形態素をテキストから抽出するオブジェクト
        sam = new SearchMorphemes(title,text);
        //Rの統計データを保持するオブジェクト
        hcdb = new HashCodeDB();
        
    }
    
    public void preProcess(){
	
	//検索開始
	sam.search();
	//合致した男性用形態素の取得
        MenWords = sam.getMenMorphemes();
        //合致した女性用形態素の取得
        WomenWords = sam.getWomenMorphemes();
        //合致した全ての形態素の位置の取得
        allIndexs = sam.getAllIndexs();
        //合致した全ての形態素の性別を取得
        SexDistinction = sam.getWordSex();
	
	
    }

    public void process() {
	
	//仮の値として男性なら[-1.0]
	for(String s : MenWords){
	    
	    results.add(hcdb.getData(s));
	    
	}
	
	//仮の値として女性なら[1.0]
	for(String s : WomenWords){
	    
	    results.add(hcdb.getData(s));
	    
	}
	
	
	//仮のアルゴリズムの計算式 => 単純な総和
	for(double d : results){
	     
	    resultDouble += d;
	     
	}
         
	//仮のアルゴリズム
	if(resultDouble > 2.0){
	    result = 2;
	}else if(resultDouble > 0.0){
	    result = 0;
	}else{
	    result = 1;
	}
         
    }
    
    public void postProcess(){
    }
    
    public int getIntegerResult(){
	
	return result;
	
    }
    
    public double getDoubleResult(){
	
	return this.resultDouble;
    }

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
	for(int sd : SexDistinction){
	    
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
	
	for(double d : results){
	    
	    result += d;
	    
	}
	
	System.out.println("=>"+result);
	
	
    }
    
    
}
