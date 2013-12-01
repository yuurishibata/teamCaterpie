import java.util.*;

public class MorphemeAlgorithm implements AlgorithmInterface{

    // blog contents
    private String title;
    private String text;
    
    // matched word
    private ArrayList<String> MenMatchWords = new ArrayList<String>();
    private ArrayList<String> WomenMatchWords = new ArrayList<String>();
    //形態素を検索する
    SearchMorpheme sm;
    //疑似データベース
    private HashCodeDB hcdb;
    //検索結果の格納先
    private ArrayList<Integer> allIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> WomenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> MenIndexs = new ArrayList<Integer>();
    private ArrayList<Integer> wordSex = new ArrayList<Integer>();
    //返却値を返す直前のアルゴリズム計算で用いる変数
    ArrayList<Double> results = new ArrayList<Double>();
    
    // コンストラクタ
    public MorphemeAlgorithm(String title, String text) {
	//ブログのタイトル・テキストを属性値に設定する。
        this.title = title;
        this.text = text;
        //形態素を検索するオブジェクト
        sm = new SearchMorpheme(title,text);
        //Rのデータを保持しているインスタンス生成。
        hcdb = new HashCodeDB();
        
    }

    public void process() {
	//検索開始
	sm.search();
	//検索結果を属性値に代入。つまり辞書内の形態素と合致した単語をこのクラスのArrayListに格納する。
	MenMatchWords = sm.getMenMorphemes();
	WomenMatchWords = sm.getWomenMorphemes();
	allIndexs = sm.getAllIndexs();
	wordSex = sm.getWordSex();
         
	for(String s : MenMatchWords){
	    results.add(hcdb.getData(s));
	}
         
	for(String s : WomenMatchWords){
	    results.add(hcdb.getData(s));
	}
         
    }
    
    public int getResult(){
	
	int result=0;
	
	for(double d : results){
	    
	    result += d;
	    
	}
	
	return result;
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
	
	if(MenMatchWords.size()+WomenMatchWords.size() == 0){
	    return;
	}
	
	System.out.print("sex : ");//改行
	for(int i : wordSex){
	    
	    System.out.print(i);
	    
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
        }

        // 辞書とのキーワードにマッチした単語のインデックスを全て表示する。
        if (allIndexs.isEmpty() == false) {

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
        if (MenMatchWords.isEmpty() == true && WomenMatchWords.isEmpty() == true) {
            return;
        }

        // 出力準備
        System.out.print("KEYWORDS : ");

        for (String s : MenMatchWords) {
            System.out.print("*" + s + "*");
        }
        for (String s : WomenMatchWords) {
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
