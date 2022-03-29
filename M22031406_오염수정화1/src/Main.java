import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Water implements Comparable<Water> {
	int num;
	boolean plus;

	@Override
	public int compareTo(Water r) {
		if (num > r.num)
			return -1;
		if (num < r.num)
			return 1;
		return 0;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in4"));
		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		A[N] = 1000000001;

		Arrays.sort(A);
		A[N] = 0;

		int left = 0;
		int right = N - 1;
		int sum = 0;
		int min = Integer.MAX_VALUE;
		int minL = 0;
		int minR = 0;
		// ��ȿ�� ����
		while (left < right) {
			sum = A[left] + A[right];
			if (sum == 0) {
				System.out.println(A[left] + " " + A[right]);
				break;
			}
			if (Math.abs(min) > Math.abs(sum)) {
				// �� ������ ���밪�� ���� ���� ū ���� ����Ѵ�
				min = sum;
				minL = A[left];
				minR = A[right];
			}
			if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}

		if (sum != 0)
			System.out.println(minL + " " + minR);
	}

}
