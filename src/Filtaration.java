public class Filtaration {

    public static void main(String[] args) {

	int result = execute(
			     "'S LIFE in SHIBUYA Vol.64",
			     "こんにちは！本日の、リアルコーディネイトはこちら☆Model:TSUBOTops:＜  BEAMS （ビームス ）＞品番：13-11-0109￥10,290Shoes:＜NEW BALANCE（ニューバランス）＞Welcome to SHIBUYA!!公式アカウントにBEAMSが参加中♪新鮮な情報をいち早く、皆様の元へお届けして参ります★登録お願いします！ビームス 渋谷Shop Blogビームス タイム（雑貨/ビームスT）ビームス ライツ 渋谷お問い合わせはこちらまでビームス 渋谷03-5458-4129CHII");

	System.out.println(result);

	System.out.println("END");
    }

    public static int execute(String title, String content) {

	// アルゴリズム処理の準備
	WomenAlgorithm wAlg = new WomenAlgorithm(title, content);
	MenAlgorithm mAlg = new MenAlgorithm(title, content);

	if (wAlg.process()) {
	    // もし女性用なら
	    return 2;

	} else if (mAlg.process()) {
	    // もし男性用なら
	    return 1;

	} else {

	    // その他
	    return 0;

	}
    }
}
