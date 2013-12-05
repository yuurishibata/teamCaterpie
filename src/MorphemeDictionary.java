
public class MorphemeDictionary {
    
    public MorphemeDictionary(){
	//何もしない
    }
    
    //男性用キーワード
    private String[] men ={ "イケメン", "デッキシューズ", "ヒゲ", "ひげ",
			    "ボクサー","トランクス", "カフス", "メンズ", "MENS", "軍", "BOY", 
			    "Mens", "MEN'S", "男", "俺", "僕","Boy", 
			    "チノパン", "ミリタリー",};
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
	"シルエット", "パフ", "ハート", "靴", "LOVE" ,};
    //不要語のキーワード
    private String[] unnecessaryWords={
	"食","飲","酒","女子会","ビール","料理","食事会","アルコール","味",
    };
    //不要な記号
    private String[] symbols;
    //男性用の形態素を取得
    public String[] getMenWords(){
	return this.men;
    }
    //女性用の形態素を取得
    public String[] getWomenWords(){
	return this.women;
    }
    //不要な記号を取得
    public String[] getSymbols(){
	return this.symbols;
    }
    //不要語登録された形態素を取得
    public String[] getUnnecessaryWords(){
	return this.unnecessaryWords;
    }
    

}
