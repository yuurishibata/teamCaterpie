import java.util.*;

public class DispersionAlgorithm implements AlgorithmInterface{

    // blog contents
    private String title;
    private String text;
    // points
    //private double menPoints;
    //private double womenPoints;
    // keywords　<= setup by  this constructor
    private String[] menWords;  
    private String[] womenWords; 
    //疑似データベースとその結果の数値
    private HashCodeDB hcdb;
    
    // matched word
    private ArrayList<String> MmatchWords = new ArrayList<String>();
    private ArrayList<String> WmatchWords = new ArrayList<String>();
    // indexs
    private ArrayList<Integer> allIndexs = new ArrayList<Integer>();// 全てのワードのindex
    private ArrayList<Integer> MIndexs = new ArrayList<Integer>();// 男性のキーワードの各index
    private ArrayList<Integer> WIndexs = new ArrayList<Integer>();// 女のキーワードの各index
    private ArrayList<Double> results = new ArrayList<Double>();//男性なら-1.0、女性なら1.0の結果を格納しておくresults

    // コンストラクタ
    public DispersionAlgorithm(String title, String text) {
        this.title = title;
        this.text = text;
        //辞書オブジェクトから各単語をセットする。
        Dictionarys d = new Dictionarys();
        this.menWords = d.getMenWords();
        this.womenWords = d.getWomenWords();
        //Rのデータを保持している疑似データベースのインスタンス作成
        hcdb = new HashCodeDB();
        
    }

    public int process() {

        // 男性
        for (String word : menWords) {

            if (title.contains(word)) {
                System.out.print("1");
                MmatchWords.add(word);// その単語を文字列リストに格納。
                MIndexs.add(title.indexOf(word));// そのインデックスを男性のみのインデックスを入れる整数型リストに格納。
                allIndexs.add(title.indexOf(word));// そのインデックスを男女両方のインデックスを入れる整数型リストに格納。
                
                //男性のキーワードとマッチしたら、ArrayList<Double>に-1.0を格納
                results.add(hcdb.getData(word));
                
            }

            if (text.contains(word)) {

                System.out.print("1");
                MmatchWords.add(word);
                MIndexs.add(text.indexOf(word));
                allIndexs.add(text.indexOf(word));
                
                //男性のキーワードとマッチしたら、ArrayList<Double>に-1.0を格納
                results.add(hcdb.getData(word));
                
                
                // もし同じ単語が複数ある場合
                if (text.indexOf(word) != text.lastIndexOf(word)) {

                    System.out.print("1");
                    MIndexs.add(text.lastIndexOf(word));
                    allIndexs.add(text.lastIndexOf(word));
                    MmatchWords.add(word);
                    
                    
                    //男性のキーワードとマッチしたら、ArrayList<Double>に-1.0を格納
                    results.add(hcdb.getData(word));
                    
                }

            }
        }

        // 女性
        for (String word : womenWords) {

	    //もしそのブログのタイトルにその単語が含まれていたら
            if (title.contains(word)) {
                System.out.print("2");
                WmatchWords.add(word);
                WIndexs.add(title.indexOf(word));
                allIndexs.add(title.indexOf(word));
                
                
                //女性のキーワードとマッチしたら、ArrayList<Double>に1.0を格納
                results.add(hcdb.getData(word));
                
            }

            //もしそのブログの本文にその単語が含まれていたら
            if (text.contains(word)) {
                System.out.print("2");
                WmatchWords.add(word);
                WIndexs.add(text.indexOf(word));
                allIndexs.add(text.indexOf(word));

		//女性のキーワードとマッチしたら、ArrayList<Double>に1.0を格納
                results.add(hcdb.getData(word));

                
                // その本文にその単語が一つあるということは、複数ある可能性があるということ。
                //もし同じ単語が複数ある場合の対処処理
                if (text.indexOf(word) != text.lastIndexOf(word)) {
                    System.out.print("2");
                    WIndexs.add(text.lastIndexOf(word));
                    allIndexs.add(text.lastIndexOf(word));
                    WmatchWords.add(word);
                    
		    //女性のキーワードとマッチしたら、ArrayList<Double>に1.0を格納
                    results.add(hcdb.getData(word));

                }

            }
        }

        System.out.print("\n");

        // 辞書と一致した単語が何かを知りたい場合は次のメソッドのコメントアウトを外す。
        printMatchWords();
        // 辞書と一致した単語のインデックスを全て知りたい場合は次のメソッドのコメントアウトを外す。
        printAllIndexs();
        // 辞書と一致した単語の分散度を知りたい場合は次のメソッドのコメントアウトを外す。
        printDispersionValue();
        
        //マッチした単語によって変化する各ブログの結果を出力する。
        printResults();
        
        //コンパイラを通すために返却値をゼロとしておく。
        return 0;

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
        if (MmatchWords.isEmpty() == true && WmatchWords.isEmpty() == true) {
            return;
        }

        // 出力準備
        System.out.print("KEYWORDS : ");

        for (String s : MmatchWords) {
            System.out.print("*" + s + "*");
        }
        for (String s : WmatchWords) {
            System.out.print("*" + s + "*");
        }
        // 改行
        if (allIndexs.isEmpty() == false) {
            System.out.print("\n");
        }
    }

    private void printDispersionValue() {

        //int Mdispersion = 0;
        //int Wdispersion = 0;

        // もし、男性のキーワードと一致したものが存在し、
        if (MIndexs.isEmpty() == false) {

            // キーワード二個以上と一致している場合
            if (MIndexs.size() >= 2) {

                Object[] o = MIndexs.toArray();
                // 降順にソートする
                java.util.Arrays.sort(o);

                // 男性のインデックスの最小値と最大値を変数に格納
                int min = (int) o[0];
                int max = (int) o[o.length - 1];

                System.out.println("男性キーワードの分散値 : " + (max - min));

            }
        }

        if (WIndexs.isEmpty() == false) {

            if (WIndexs.size() >= 2) {

                Object[] o = WIndexs.toArray();
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
