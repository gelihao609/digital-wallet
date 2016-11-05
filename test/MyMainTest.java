import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyMainTest {

	public static void main(String[] args) {
		List<Transaction> trans = Utility
				.loadFile("paymo_input/batch_payment.csv");
		FriendNetWork fn = new FriendNetWork();
		fn.build(trans);
		fn.buildFriendMapWithDegree(3);

//		Map<Integer, Set<String>> user1 = new HashMap<Integer, Set<String>>();
//		Map<Integer, Set<String>> user2 = new HashMap<Integer, Set<String>>();
//		Map<Integer, Set<String>> user3 = new HashMap<Integer, Set<String>>();
//		Map<Integer, Set<String>> user4 = new HashMap<Integer, Set<String>>();
//		Map<Integer, Set<String>> user5 = new HashMap<Integer, Set<String>>();
//		Map<Integer, Set<String>> user6 = new HashMap<Integer, Set<String>>();
//		Map<Integer, Set<String>> user7 = new HashMap<Integer, Set<String>>();
//
//		user1.put(1, new HashSet<String>(Arrays.asList("2", "3", "4")));
//		user2.put(1, new HashSet<String>(Arrays.asList("1", "3", "5")));
//		user3.put(1, new HashSet<String>(Arrays.asList("2", "1", "4", "5")));
//		user4.put(1, new HashSet<String>(Arrays.asList("1", "3", "5", "6")));
//		user5.put(1,
//				new HashSet<String>(Arrays.asList("2", "3", "4", "6", "7")));
//		user6.put(1, new HashSet<String>(Arrays.asList("5", "7", "4")));
//		user7.put(1, new HashSet<String>(Arrays.asList("5", "6")));
//		Map<String, Map<Integer, Set<String>>> multiLevelFriendMap = new HashMap<String, Map<Integer, Set<String>>>();
//		multiLevelFriendMap.put("1", user1);
//		multiLevelFriendMap.put("2", user2);
//		multiLevelFriendMap.put("3", user3);
//		multiLevelFriendMap.put("4", user4);
//		multiLevelFriendMap.put("5", user5);
//		multiLevelFriendMap.put("6", user6);
//		multiLevelFriendMap.put("7", user7);
		// fn.build(multiLevelFriendMap);
		// fn.buildFriendMapWithLevel(3);
		System.out.println(fn);
	}

}
