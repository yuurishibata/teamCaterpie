import java.util.HashMap;

public class RDataDB {

    // Rのデータを管理するHashMap
    HashMap<String, RData> RMap = new HashMap<String, RData>();

    // コンストラクタ
    public RDataDB() {

        // 各単語の実数値を標準点として登録する
        setRData();
        
    }

    private void setRData() {
	
	MorphemeDictionary d = new MorphemeDictionary();
	String[] menWords = d.getMenWords();
	String[] womenWords = d.getWomenWords();
	
	
	//男性用のキーワードを仮に全てポイント-1.0とする。
	for(String s : menWords){
	    
	    RMap.put(s,new RData(-1.0,-1.0,-1.0));
	    
	}
	//女性用のキーワードを仮に全てポイント1.0とする。
	for(String s : womenWords){
	    
	    RMap.put(s,new RData(1.0,1.0,1.0));
	    
	}
	
	

    }

    public RData getRData(String word) {

	//もし、その単語の形態素を登録していない場合
        if (RMap.containsKey(word) == false) {
	    // 全ての値が「0.0」のRDataを返却
            return new RData();
        }else{
	    // 登録されているRの統計データを返却
	    return RMap.get(word);
        
        }

    }
}
