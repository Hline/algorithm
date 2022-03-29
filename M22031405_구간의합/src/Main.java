import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투 포인터 알고리즘 Two Pointer
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in1"));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A[] = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = 0;
		int sum = 0;
		int cnt = 0;
		// sum 계산 후 index를 움직이므로 끝까지 가려면 N+1
		while (left < N + 1 && right < N + 1) {
			if (sum < M) {
				sum += A[right];
				right++;
			} else if (sum > M) {
				sum -= A[left];
				left++;
			} else {
				cnt++;
				sum += A[right];
				right++;
			}
		}

		System.out.println(cnt);

	}

}
