import java.util.HashMap;

public class HashCodeDB {

    // 疑似的データベースの基幹的な役割を担う諸変数
    HashMap<String, Double> normalPoints = new HashMap<String, Double>();
    HashMap<String, Integer> hashcodeMap = new HashMap<String, Integer>();
    HashMap<Integer, RData> hashRData = new HashMap<Integer, RData>();

    // コンストラクタ
    public HashCodeDB() {
	// 標準ポイント（１つの形態素に対して存在する１つの実数の値のこと）を返却するhashmapを設定する。
	//これはR言語のデータとは全く別に、準備して置く。データの設定が急務の際に役に立つ。
	setNormalPoint();

	// 各単語とそのハッシュコードの対応を確実化する
	setEachWord();

	// 単語の組み合わせとそのそれぞれの統計的データの実数三つの登録
	setEachPair();

    }

    private void setNormalPoint() {

	normalPoints.put("ワンピース", 5.0);
	normalPoints.put("男性", 4.0);

    }

    public double getPoint(String word) {

	if (normalPoints.containsKey(word) == false) {
	    return 0.0;
	}

	return normalPoints.get(word);

    }

    private void setEachWord() {

	// データとして使用する各単語を全て登録（する必要がある）
	// hashcodeMap.put("the word","the word".hashCode());で登録完了！
	hashcodeMap.put("軍", "軍".hashCode());
	hashcodeMap.put("ミリタリー", "ミリタリー".hashCode());
	hashcodeMap.put("ワンピ", "ワンピ".hashCode());
	hashcodeMap.put("女性", "女性".hashCode());
	hashcodeMap.put("ガーリー", "ガーリー".hashCode());
	hashcodeMap.put("コクーン", "コクーン".hashCode());
	hashcodeMap.put("ウエスト", "ウエスト".hashCode());
	hashcodeMap.put("フリル", "フリル".hashCode());
	hashcodeMap.put("胸元", "胸元".hashCode());
	hashcodeMap.put("素敵", "素敵".hashCode());
	hashcodeMap.put("レース", "レース".hashCode());
	hashcodeMap.put("ショートパンツ", "ショートパンツ".hashCode());
	hashcodeMap.put("リボン", "リボン".hashCode());

    }

    // ワードをinputしたら、その登録されているhashcodeを返却する。
    private int getHashCode(String word) {

	if (hashcodeMap.containsKey(word) == false) {
	    return 0;
	}

	return hashcodeMap.get(word);

    }

    // ２つ単語の組み合わせを登録
    private void setEachPair() {

	hashRData.put(getHashCode("女性") + getHashCode("フリル"), new RData(9.8,
									     9.9, 10.0));
	hashRData.put(getHashCode("ワンピ") + getHashCode("素敵"), new RData(20.0,
									     30.0, 45.0));
	hashRData.put(getHashCode("リボン") + getHashCode("ワンピ"), new RData(8.3,
									       7.3, 6.3));

    }

    // 文字列2つを使って、統計の数値を取得する。
    public RData getRData(String s1, String s2) {

	int hashcode = getHashCode(s1) + getHashCode(s2);

	// もし、キー値としてハッシュコードの値が登録されていなかった場合
	if (hashRData.containsKey(hashcode) == false) {

	    // 値のセットされいないRDataを返却する。
	    RData rd = new RData();

	    return rd;
	}

	return hashRData.get(hashcode);

    }
}
