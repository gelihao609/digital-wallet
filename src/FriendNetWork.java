import java.util.*;

public class FriendNetWork {
	// Friend dictionary with levels
	private Map<String, Map<Integer, Set<String>>> multiLevelFriendMap;

	public FriendNetWork() {
		multiLevelFriendMap = new HashMap<String, Map<Integer, Set<String>>>();
	}

	public void build(Map<String, Map<Integer, Set<String>>> ids) {
		multiLevelFriendMap = ids;
	}

	// by default, build level1 friendMap
	public void build(List<Transaction> trans) {
		for (Transaction tran : trans) {
			String sender = tran.getSender();
			String receiver = tran.getReceiver();
			Map<Integer, Set<String>> currLevelMap = new HashMap<Integer, Set<String>>();
			if (multiLevelFriendMap.containsKey(sender)) {
				multiLevelFriendMap.get(sender).get(1).add(receiver);
			} else {
				Set<String> friends = new HashSet<String>();
				friends.add(receiver);
				currLevelMap.put(1, friends);
				multiLevelFriendMap.put(sender, currLevelMap);
			}
			if (multiLevelFriendMap.containsKey(receiver)) {
				multiLevelFriendMap.get(receiver).get(1).add(sender);
			} else {
				Set<String> friends = new HashSet<String>();
				friends.add(sender);
				currLevelMap.put(1, friends);
				multiLevelFriendMap.put(receiver, currLevelMap);
			}
		}
	}

	// build different level friendMap

	public void buildFriendMapWithLevel(int level) {
		if (level <= 1)
			return;
		Set<String> allUsers = multiLevelFriendMap.keySet();
		int counter = 1;
		for (String user : allUsers) {
			System.out.println("No: "+ counter++ + "CURRENT ID: " + user);
			int currLevel = 1;
//			Map<Integer, Set<String>> nextLevelMap = new HashMap<Integer, Set<String>>();
			Set<String> visited = new HashSet<String>();
			while (currLevel < level) {
				Set<String> currentFriendMap = multiLevelFriendMap.get(user)
						.get(currLevel);
				// HashMap<Integer, Set<String>>();
				Set<String> nextLevelFriendMap = new HashSet<String>();
				visited.add(user);
				for (String friend : currentFriendMap) {
					visited.add(friend);
				}
				for (String friend : currentFriendMap) {
					Set<String> currentUsersFriends = multiLevelFriendMap.get(
							friend).get(1);
					for (String currUserFriend : currentUsersFriends) {
						if (visited.contains(currUserFriend))
							continue;
						else {
							visited.add(currUserFriend);
							nextLevelFriendMap.add(currUserFriend);
						}
					}
				}
				currLevel++;
				multiLevelFriendMap.get(user).put(currLevel,nextLevelFriendMap);
			}
		}
	}
}
