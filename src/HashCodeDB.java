import java.util.ArrayList;
import java.util.HashMap;

public class HashCodeDB {
    //基本的な使用方法は次の通り。
    //単語の登録をして、その後にその登録された単語の組み合わせとそれに対応するRのデータを登録すれば準備完了。
    //それを文字列引数２つで呼び出せる、getRDataメソッドから取得する。getRDataメソッドは
    //RDataクラスのインスタンスを返却する。

    // 疑似的データベースの基幹的な役割を担う諸変数
    HashMap<String, Double> normalPoints = new HashMap<String, Double>();
    HashMap<String, Integer> hashcodeMap = new HashMap<String, Integer>();
    HashMap<Integer, RData> hashRData = new HashMap<Integer, RData>();

    // コンストラクタ
    public HashCodeDB() {

        // 標準ポイント（１つの形態素に対して、一つの実数を持つhashmap）を返却するhashmapを設定する。
        setPoints();

        // 各単語とそのハッシュコードの対応を確実化する
        setWords();

        // 単語の組み合わせとそのそれぞれの統計的データの実数三つの登録
        setPairs();

    }

    private void setPoints() {
	
	MorphemeDictionary d = new MorphemeDictionary();
	String[] menWords = d.getMenWords();
	String[] womenWords = d.getWomenWords();
	
	
	//男性用のキーワードを仮にポイント-1.0
	for(String s : menWords){
	    
	    normalPoints.put(s,-1.0);
	    
	}
	//女性用のキーワードを仮にポイント1.0
	for(String s : womenWords){
	    
	    normalPoints.put(s,1.0);
	    
	}
	
	

    }

    private void setWords() {

        // データとして使用する各単語を全て登録（する必要がある）
        // hashcodeMap.put("the word","the word".hashCode());で登録完了！
        hashcodeMap.put("男性", "男性".hashCode());
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
    private void setPairs() {

        hashRData.put(getHashCode("女性") + getHashCode("ワンピ"), new RData(9.8,
                                                                             9.9, 10.0));
        hashRData.put(getHashCode("ワンピ") + getHashCode("素敵"), new RData(20.0,
                                                                             30.0, 45.0));
        hashRData.put(getHashCode("リボン") + getHashCode("ワンピ"), new RData(8.3,
                                                                               7.3, 6.3));
        hashRData.put(getHashCode("レース") + getHashCode("胸元"), new RData(1.5,
                                                                             1.3, 1.2));
        hashRData.put(getHashCode("軍") + getHashCode("男性"), new RData(22.2,
                                                                         22.3, 22.4));

    }

    public double getData(String word) {

        if (normalPoints.containsKey(word) == false) {
            return 0.0;
        }

        return normalPoints.get(word);

    }

    // 文字列2つを使って、統計の数値を取得する。
    public RData getRData(String s1, String s2) {

        int hashcode = getHashCode(s1) + getHashCode(s2);

        // もし、キー値としてハッシュコードの値が登録されていなかった場合
        if (hashRData.containsKey(hashcode) == false) {

            // 値のセットされいないRDataを返却する。
            return new RData();
        }

        return hashRData.get(hashcode);

    }

    public boolean existRData(String s1, String s2) {

        int hashcode = getHashCode(s1) + getHashCode(s2);

        if (hashRData.containsKey(hashcode) == false) {
            return false;
        }

        return true;

    }

    /**
     * 
     * このメソッドの脆弱性は、形態素同士の組み合わせが見つかった場合に、
     * その組み合わせ以外にも、形態素同士のペアが引数に受け取ったリスト内部にある可能性を無視し、
     * 最初にマッチしたペアに対応するRのデータを返却してしまう点にある。
     * 
     **/
    public RData getRData(ArrayList<String> als) {

        // データベースからRのデータを取得できないもの。
        if (als.size() < 2) {

            // 空のRのデータ
            return new RData();

        } else {

            int hashcode;

            // そのArrayListに入っている形態素の各組合せ全てに対して
            // そのペアがデータベースに登録されているか、走査する。
            for (String outside : als) {
                for (String inside : als) {

                    hashcode = getHashCode(inside) + getHashCode(outside);
                    // もし、その形態素と形態素の組み合わせが存在したら、
                    if (hashRData.containsKey(hashcode) == true) {
                        // そのRDataを返却する。
                        return getRData(outside, inside);
                    }

                }

            }

            // もし、全ての組み合わせを走査して、そのペアが登録されていないのなら、
            // 空のRのデータを返却する。
            return new RData();

        }

    }
}
