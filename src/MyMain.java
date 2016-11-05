import java.io.IOException;
import java.util.*;
public class MyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputBatch = args[0];
		String inputStream = args[1];
		String outputText1 = args[2];
		String outputText2 = args[3];
		String outputText3 = args[4];
		//load file from system
		List<Transaction> batch = Utility.loadFile(inputBatch);
		List<Transaction> stream = Utility.loadFile(inputStream);
		//build friendNetWork
		FriendNetWork fn = new FriendNetWork();
		antifraud af = new antifraud(fn);
		fn.build(batch);
		fn.buildFriendMapWithDegree(3);
		//get results
		String[] verifiedResults = af.checkIsVerifiedAsMyFriend(stream
				.toArray());
		String[] verified2ndDegreeResults = af
				.checkIsVerifiedAsMy2ndDegreeFriend(stream.toArray());
		String[] verified3rdDegreeResults = af
				.checkIsVerifiedAsMy3rdDegreeFriend(stream.toArray());
		//write to file
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
