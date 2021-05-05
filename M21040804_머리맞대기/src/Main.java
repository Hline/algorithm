import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] user_sum;
	static int[] user;
	static int minIq = Integer.MAX_VALUE;
	static int n;
	static int t;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("�Ӹ� �´��\\3.in"));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		user = new int[n * 2];
		user_sum = new int[n * 2];
		for (int i = 0; i < n; i++) {
			int iq = Integer.parseInt(st.nextToken());
			user[i] = iq;
			user[i + n] = iq;
			// prefix �պκ�
			if (i == 0)
				user_sum[i] = user[i];
			else
				user_sum[i] = user_sum[i - 1] + user[i];

			minIq = (minIq > user[i]) ? user[i] : minIq; // ���� ���� ��
		}
		// prifx �޺κ�
		for (int i = n; i < n * 2; i++) {
			user_sum[i] = user_sum[i - 1] + user[i];
		}

		bw.write(binarySearch() + "");
		bw.close();

	}

	private static int binarySearch() {
		// TODO Auto-generated method stub
		int left = minIq;				// Head Sum�� Max ���� �� �� �ִ� �ּ�
		int right = user_sum[n - 1];	// Head Sum�� Max ���� �� �� �ִ� �ִ�
		int minHeadSum = 0;				// �������� �䱸�ϴ� "Head Sum�� MAX ���� ���� ���� ��"

		while (left <= right) {
			int mid = (left + right) / 2;
			int teamCount = CalcTeam(mid); // ���ذ����� ������ ����
			if (t == teamCount)
				minHeadSum = mid;
			if (teamCount <= t) {	// ������ ���� ��� �� ���� �ִ����� �ٿ� ���� �ø�
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return minHeadSum;
	}

	private static int CalcTeam(int maxHeadSum) {
		// TODO Auto-generated method stub
		int teamCount = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			int start = i;
			int cnt = 1;
			for (int j = i; j < i + n; j++) {
				if (user_sum[j] - user_sum[start] + user[start] > maxHeadSum) {
					start = j;
					cnt++;
				}
			}
			if (teamCount > cnt)
				teamCount = cnt;
		}

		return teamCount;
	}

}
