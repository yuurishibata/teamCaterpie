public class Filtaration {

	public static void main(String[] args) {

		test();

	}

	public static void test() {

		System.out.println(execute("BOY'S LIFE in SHIBUYA Vol.64",
				"こんにちは！本日の、リアルコーディネイトはこちら☆odel:TSUBOTops:＜  BEAMS （ビームス ）"));
		System.out.println(execute("女性用のワンピースを新しく入荷しました！",
				"ビームス ライツ 渋谷お問い合わせはこちらまでビームス 渋谷03-5458-4129CHII"));
		System.out.println(execute("よかったら覗いてください",
				"新作ITEMを使ったコーディネートが多数掲載されていますよドキドキドキドキ"));
		System.out.println(execute("男性用のパンツ入荷しました！",
				"店頭にて無料で配布しておりますのでぜひGETして下さいね今月の表紙を飾っているのは、玉城ティナさん！！"));
		System.out
				.println(execute("値下げしましたので、スカート買いに来てください。キュートなやつです！！！",
						"明日の朝チェックして、高値の方にお譲りします。ちなみに、いつもはコメント承認制なのですが、それだと分かりづらいと思うので"));

		System.out.println(execute("", ""));

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
