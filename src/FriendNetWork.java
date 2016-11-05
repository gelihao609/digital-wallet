import java.util.*;
/*
*		ID --|-- Degree1 - FriendsSet 
*			 |-- Degree2 - FriendsSet
*			 |-- Degree3 - FriendsSet
*
* 		Each ID has its friends of different degrees, all IDs are then stored in map: multiLevelFriendMap
*
*/
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
			if (multiLevelFriendMap.containsKey(sender)) {
				multiLevelFriendMap.get(sender).get(1).add(receiver);
			} else {
				Set<String> friends = new HashSet<String>();
				Map<Integer, Set<String>> currLevelMap = new HashMap<Integer, Set<String>>();
				friends.add(receiver);
				currLevelMap.put(1, friends);
				multiLevelFriendMap.put(sender, currLevelMap);
			}
			if (multiLevelFriendMap.containsKey(receiver)) {
				multiLevelFriendMap.get(receiver).get(1).add(sender);
			} else {
				Set<String> friends = new HashSet<String>();
				Map<Integer, Set<String>> currLevelMap = new HashMap<Integer, Set<String>>();
				friends.add(sender);
				currLevelMap.put(1, friends);
				multiLevelFriendMap.put(receiver, currLevelMap);
			}
		}
	}

	// build different level friendMap, e.g. if level is 2, we will have 2 levels, if level is 3, we will have 3 levels, that is level1, 2 and 3.
	public void buildFriendMapWithDegree(int level) {
		if (level <= 1)
			return;
		Set<String> allUsers = multiLevelFriendMap.keySet();
		int counter = 1;
		System.out.println("Process in forming network...");
		for (String user : allUsers) {
			System.out.println("No: " + counter++ + " CURRENT ID: " + user);
			int currLevel = 1;
			Set<String> visited = new HashSet<String>();
			while (currLevel < level) {
				Set<String> currentFriendMap = multiLevelFriendMap.get(user)
						.get(currLevel);
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
				multiLevelFriendMap.get(user)
						.put(currLevel, nextLevelFriendMap);
			}
		}
		System.out.println("Friend network is formed.");
	}

	public boolean isFriends(String sender, String receiver) {
		int FRIEND_LEVEL = 1;
		return multiLevelFriendMap.get(sender).get(FRIEND_LEVEL)
				.contains(receiver);
	}

	public boolean isFriendsByLevel(String sender, String receiver, int level) {
		return multiLevelFriendMap.get(sender).get(level).contains(receiver);
	}
}
