// example of program that detects suspicious transactions
// fraud detection algorithm
class antifraud {
	private FriendNetWork network;

	public antifraud(FriendNetWork network) {
		this.network = network;
	}

	public String[] checkIsVerifiedAsMyFriend(Object[] trans) {
		String[] results = new String[trans.length];
		for (int i = 0; i < trans.length; i++) {
			Transaction tr = (Transaction) trans[i];
			results[i] = network.isFriends(tr.getSender(),
					tr.getReceiver()) ? "trusted" : "unverified";
		}
		return results;

	}

	public String[] checkIsVerifiedAsMy2ndDegreeFriend(Object[] trans) {
		String[] results = new String[trans.length];
		for (int i = 0; i < trans.length; i++) {
			Transaction tr = (Transaction) trans[i];
			results[i] = (network.isFriends(tr.getSender(),
					tr.getReceiver()) || network.isFriendsByLevel(
					tr.getSender(), tr.getReceiver(), 2)) ? "trusted"
					: "unverified";
		}
		return results;
	}

	public String[] checkIsVerifiedAsMy3rdDegreeFriend(Object[] trans) {
		String[] results = new String[trans.length];
		for (int i = 0; i < trans.length; i++) {
			Transaction tr = (Transaction) trans[i];
			results[i] = (network.isFriends(tr.getSender(),
					tr.getReceiver())
					|| network.isFriendsByLevel(tr.getSender(),
							tr.getReceiver(), 2) || network
					.isFriendsByLevel(tr.getSender(),
							tr.getReceiver(), 3)) ? "trusted"
					: "unverified";
		}
		return results;
	}
}