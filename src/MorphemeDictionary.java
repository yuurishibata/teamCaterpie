import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MorphemeDictionary {

    // 性別を表現した定数
    private final static int MEN = 1;
    private final static int WOMEN = 2;

    // 男性の形態素辞書
    private ArrayList<String> MenMorphemes = new ArrayList<String>();
    // 女性の形態素辞書
    private ArrayList<String> WomenMorphemes = new ArrayList<String>();

    public MorphemeDictionary() {

	try {

	    setDictionary();

	} catch (IOException ioe) {
	    /**
	     * このコーディングは悪いか否か
	     */
	    ioe.printStackTrace();
	    setEmergenceyDictionary();
	    
	} finally {

	}
    }

    public void setDictionary() throws IOException {

	// path
	String path = "test.csv";
	// reader
	BufferedReader input = new BufferedReader( new InputStreamReader( new FileInputStream(path),"Shift-JIS"));
	// one line
	String set;
	String[] line;

	// setup each RData
	while ((set = input.readLine()) != null) {

	    line = set.split(",");

	    // その形態素の性別
	    int sex = Integer.parseInt(line[0]);
	    // その形態素
	    // String morpheme = line[1];

	    if (sex == MEN) {

		MenMorphemes.add(line[1]);

	    } else if (sex == WOMEN) {

		WomenMorphemes.add(line[1]);

	    } else {
		// 何もしない。
	    }

	}
	// close
	input.close();

    }

    // ファイル入出力の例外発生時に使用するメソッド
    public void setEmergenceyDictionary() {

	System.out.println("setEmergenceyDictionary");

    }

    // 不要語のキーワード
    private String[] unnecessaryWords = { "食", "飲", "酒", "女子会", "ビール", "料理",
					  "食事会", "アルコール", "味", "デザート", "スイーツ" };
    // 不要な記号
    private String[] symbols;

    // 男性用の形態素を取得
    public ArrayList<String> getMenWords() {
	return this.MenMorphemes;
    }

    // 女性用の形態素を取得
    public ArrayList<String> getWomenWords() {
	return this.WomenMorphemes;
    }

    // 不要な記号を取得
    public String[] getSymbols() {
	return this.symbols;
    }

    // 不要語登録された形態素を取得
    public String[] getUnnecessaryWords() {
	return this.unnecessaryWords;
    }

}
