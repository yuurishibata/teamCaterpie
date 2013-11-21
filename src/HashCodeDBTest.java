public class HashCodeDBTest {

    public static void main(String[] args) {

	// テスト関数

	// データベースインスタンス作成
	HashCodeDB hcdb = new HashCodeDB();
	// そのインスタンスのgetRDataメソッドに引数を渡しRの統計データを取得する。
	RData rd = hcdb.getRData("女性", "フリル");
	// そのRのデータにアクセスする。
	System.out.println(rd.getConfidence());
	RData rd3 = hcdb.getRData("リボン", "ワンピ");

	System.out.println(rd3.getSupport());
	System.out.println(rd3.getLift());
	System.out.println(rd3.getConfidence());

	// 他のデータの取得（１つの形態素から、登録された実数１つを取り出す方法）
	System.out.println(hcdb.getPoint("ワンピース"));
	System.out.println(hcdb.getPoint("男性"));
	System.out.println(hcdb.getPoint("ソフトウェア開発"));// 登録されていない形態素は「zero」を返却する。

    }
}
