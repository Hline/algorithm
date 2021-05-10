import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("in"));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {		// �� ��� �Է�
			arr[n] = Integer.parseInt(st.nextToken()) - 1;	// ������ 1�������� 0���� ���
		}
		Arrays.sort(arr);	// Ž���� ���� ���� ����
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			int num = Integer.parseInt(st.nextToken()) - 1;	// �˻��Ϸ��� ��

			int left = 0;		// Ž�� �ε��� ����
			int right = N - 1;	// Ž�� �ε��� ������
			int mid = (left + right) / 2;	// ���� ���Ϸ��� �� �ε���
			while (left <= right) {			// ã�� ���� ���� �� �ε����� �����Ǳ� ������ Ž��
				mid = (left + right) / 2;
				if (num == arr[mid]) {		// ã������ ���� �ִ� ���
					break;
				}
				if (num < arr[mid]) {		// ���� ���Ϸ��� ���� ū ���
					right = mid - 1;		// ���� ���� �߿��� ���ϱ� ���� �ε��� ����
				} else {					// ���� ���Ϸ��� ���� ���� ���
					left = mid + 1;			// ū ���� �߿��� ���ϱ� ���� �ε��� ����
				}
			}
			if (num == arr[mid])
				bw.write("1\n");
			else
				bw.write("0\n");
		}
		bw.close();
	}

}
