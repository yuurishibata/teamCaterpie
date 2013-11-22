public class HashCodeDBTest {

    public static void main(String[] args) {

	// テスト関数
	HashCodeDB hcdb = new HashCodeDB();

	RData rd = hcdb.getRData("レース", "胸元");

	System.out.println(rd.getConfidence());
	System.out.println(rd.getSupport());
	System.out.println(rd.getLift());

	System.out.println(hcdb.getData("男性"));

	System.out.println(hcdb.existRData("リボン", "ワンピ"));
	System.out.println(hcdb.existRData("女性", "フリル"));
	System.out.println(hcdb.existRData("レース", "軍"));

	ArrayList<String> ma = new ArrayList<String>();
	ma.add("女性");
	ma.add("男性");
	ma.add("フリル");

	RData rd2 = hcdb.getRData(ma);

	System.out.println(rd2.getConfidence());

    }
}
