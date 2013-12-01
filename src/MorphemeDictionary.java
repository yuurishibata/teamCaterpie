
public class MorphemeDictionary {
    
    public MorphemeDictionary(){
	//何もしない
    }
    
    //男性用キーワード
    private String[] men ={ 
	"イケメン", "デッキシューズ", "ヒゲ", "ひげ",
	"ボクサー","トランクス", "カフス", "メンズ", "MENS", 
	"Mens", "MEN'S", "男", "俺", "僕","Boy", 
	"チノパン", "ミリタリー", "軍", "BOY",
    };
    //女性用キーワード
    private String[] women = { 
	"キレイ", "きれい", "リボン", "カーラー",
	"ポーチ", "化粧","マスカラ","ハーフアップ",
	"ブラジャー", "ヘアピン","カチューシャ", "サロン", 
	"ネイル", "美脚","イアリング", "ヒール", "ショーパン",
	"フリル", "レースピース", "スカート", "ブラウス", 
	"ワンピ", "かわい", "可愛", "カワイ", "女性", 
	"フェミニン", "わたし", "私", "女の子","綺麗",
	"ボーイッシュ", "ショートパンツ", "胸元", "レース", 
	"ヒップ", "ライン", "ウエスト","キャミソール", 
	"バスト", "フレア", "ガーリー", "素敵", "パンプス",
	"シルエット", "パフ", "ハート", "靴", "LOVE" ,
    };
    //不要な記号
    private String[] symbols;
    
    public String[] getMenWords(){
	return men;
    }
    
    public String[] getWomenWords(){
	return women;
    }
    
    public String[] getSymbols(){
	return this.symbols;
    }
    

}
