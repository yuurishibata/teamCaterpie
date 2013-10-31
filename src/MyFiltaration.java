public class Filtaration {

    public static void main(String[] args) {

	System.out
	    .println(execute(
			     "B'S LIFE in SHIBUYA Vol.64",
			     "こんにちは！本日の、リアルコーディネイトはこちら☆Model:TSUBOTops:＜BEAMS （ビームス ）＞品番：13-11-0109￥10,290Shoes:＜NEW BALANCE（ニューバランス）＞Welcome to SHIBUYA!!公式アカウントにBEAMSが参加中♪新鮮な情報をいち早く、皆様の元へお届けして参ります★登録お願いします！ビームス 渋谷Shop Blogビームス タイム（雑貨/ビームスT）ビームス ライツ 渋谷お問い合わせはこちらまでビームス 渋谷03-5458-4129CHII"));

    }

    public static int execute(String title, String context) {

	String[] MenKeywords = { "男性", "僕", "俺", "MEN'S", "メンズ", "BOY", "boy",
				 "ボーイ" };
	String[] WomenKeywords = { "レースピース", "ワンピース", "スカート", "ブラウス", "ショーパン",
				   "ワンピ", "かわい", "可愛", "カワイ", "女性", "フェミニン" };

	for (int i = 0; i < MenKeywords.length; i++) {

	    if (context.contains(MenKeywords[i])
		|| title.contains(MenKeywords[i])) {

		System.out.println(MenKeywords[i]);

		return 1;

	    }

	}

	for (int k = 0; k < WomenKeywords.length; k++) {

	    if (context.contains(WomenKeywords[k])
		|| title.contains(WomenKeywords[k])) {

		System.out.println(WomenKeywords[k]);

		return 2;

	    }

	}

	return 0;

    }

}
