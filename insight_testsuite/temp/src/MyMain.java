import java.io.IOException;
import java.util.*;

public class MyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputBatch = "insight_testsuite/tests/test-1-paymo-trans/paymo_input/batch_payment.txt";
		String inputStream = "insight_testsuite/tests/test-1-paymo-trans/paymo_input/stream_payment.txt";
		String outputText1 = "insight_testsuite/tests/test-1-paymo-trans/paymo_output/1.txt";
		String outputText2 = "insight_testsuite/tests/test-1-paymo-trans/paymo_output/2.txt";
		String outputText3 = "insight_testsuite/tests/test-1-paymo-trans/paymo_output/3.txt";

		List<Transaction> batch = Utility.loadFile(inputBatch);
		List<Transaction> stream = Utility.loadFile(inputStream);
		FriendNetWork fn = new FriendNetWork();
		antifraud af = new antifraud(fn);
		fn.build(batch);
		fn.buildFriendMapWithDegree(3);
		String[] verifiedResults = af.checkIsVerifiedAsMyFriend(stream
				.toArray());
		String[] verified2ndDegreeResults = af
				.checkIsVerifiedAsMy2ndDegreeFriend(stream.toArray());
		String[] verified3rdDegreeResults = af
				.checkIsVerifiedAsMy3rdDegreeFriend(stream.toArray());
		try {
			Utility.writeFile(outputText1, verifiedResults);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Utility.writeFile(outputText2, verified2ndDegreeResults);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Utility.writeFile(outputText3, verified3rdDegreeResults);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
