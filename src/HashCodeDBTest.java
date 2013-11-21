public class HashCodeDBTest {

    public static void main(String[] args) {

	// テスト関数
	HashCodeDB hcdb = new HashCodeDB();
	RData rd = hcdb.getRData("女性", "フリル");
	System.out.println(rd.getConfidence());
	RData rd2 = hcdb.getRData("ワンピ", "素敵");

	System.out.println(rd2.getConfidence());
	System.out.println(rd2.getSupport());
	System.out.println(rd2.getLift());

    }
}
