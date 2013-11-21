import java.util.HashMap;

public class HashCodeDB {

    // 疑似的データベースの基幹変数
    HashMap<Integer, RData> hashRData = new HashMap<Integer, RData>();
    HashMap<String, Integer> hashcodeMap = new HashMap<String, Integer>();

    // コンストラクタ
    public HashCodeDB() {

	// 各単語とそのハッシュコードの対応を確実化する
	setEachWord();

	// 単語の組み合わせとそのそれぞれの統計的データの実数三つの登録
	setEachPair();

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
