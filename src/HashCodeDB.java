import java.util.HashMap;

public class HashCodeDB {

    // 疑似的データベースの基幹変数
    private HashMap<Integer, RData> hash = new HashMap<Integer, RData>();

    // コンストラクタ
    public HashCodeDB() {

	// 単語の組み合わせとそのそれぞれの統計的データの実数三つの登録
	setDB();

    }

    public void setDB() {

	// new RData (support , confidence , lift )
	hash.put("軍".hashCode() + "ミリタリー".hashCode(), new RData(2.1, 2.2, 2.3));
	hash.put("ワンピ".hashCode() + "可愛い".hashCode(), new RData(3.1, 3.2, 3.3));
	hash.put("ワンピース".hashCode() + "素敵".hashCode(), new RData(4.1, 4.2, 4.3));
	hash.put("女性".hashCode() + "コクーン".hashCode(), new RData(5.1, 5.2, 5.3));
	hash.put("フリル".hashCode() + "ウエスト".hashCode(), new RData(6.1, 6.2, 6.3));
	hash.put("フリル".hashCode() + "胸元".hashCode(), new RData(7.1, 7.2, 7.3));

    }

    // 文字列2つを使って、統計の数値を取得する。
    public RData getRData(String s1, String s2) {

	int hashcode = s1.hashCode() + s2.hashCode();

	// もし、キー値としてハッシュコードの値が登録されていなかった場合
	if (hash.containsKey(hashcode) == false) {
	    return new RData(0.0, 0.0, 0.0);
	}

	return hash.get(hashcode);

    }
}
