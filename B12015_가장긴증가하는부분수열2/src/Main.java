import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N;
	static int[] A;
	static ArrayList<Integer> board = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
//		br = new BufferedReader(new FileReader("in"));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		board.add(0);
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
			if (board.get(board.size() - 1) < A[n])
				board.add(A[n]);
			else {
				// ���� ū������ ���� ��� ���� ������ ����
				int search = BinarySearch(A[n]);
				board.set(search, A[n]);
			}

		}
		bw.write((board.size() - 1) + "\n");
		bw.close();
	}

	// Lower Bound
	// ã�� ���� ��ġ�ϴ� ���� ���� ���� �ְ� ���� ���� ����
	private static int BinarySearch(int n) {
		int left = 0;
		int right = board.size() - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (n <= board.get(mid)) {
				// 0��°���� 0���� Ž�������� ���� ����̹Ƿ� mid ������ ������� �ʴ´�
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		// ���� mid��
		return (left + right) / 2;
	}
}