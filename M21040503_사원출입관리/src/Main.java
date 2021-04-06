import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static UserInfo[] userList = new UserInfo[10000];

	static class UserInfo {
		String userId;
		String userName;
		boolean loginYN = false;

		public UserInfo(String userid, String username) {
			userId = userid;
			userName = username;
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("사원출입관리시스템\\1.in"));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			String answer = "";
			switch (type) {
			case 1:
				answer = Enroll(st.nextToken(), st.nextToken());
				break;
			case 2:
				answer = Checking(st.nextToken());
				break;
			}
			bw.write(answer + "\n");
		}

		bw.close();
	}

	private static String Checking(String userid) throws Exception {
		int idx = Integer.parseInt(userid);
		UserInfo employee = userList[idx];
		if (employee == null)
			return userid + " ERROR";
		else {
			if (employee.loginYN) {
				employee.loginYN = false;
				return employee.userId + " " + employee.userName + " EXIT";
			} else {
				employee.loginYN = true;
				return employee.userId + " " + employee.userName + " ENTER";
			}

		}
	}

	private static String Enroll(String userID, String userName) throws Exception {
		int idx = Integer.parseInt(userID);
		if (userList[idx] != null) // u.userid==userid 비교가 왜 안되지....
			return userID + " ERROR";

		userList[idx] = new UserInfo(userID, userName);
		return userID + " OK";
	}

}
