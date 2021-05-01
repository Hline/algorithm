import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class TopInfo {
	int t;	// ž�� ���̰�
	int p;	// ž ��ġ

	public TopInfo(int top, int position) {
		// TODO Auto-generated constructor stub
		t = top;
		p = position;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("in"));
		int N = Integer.parseInt(br.readLine());	// ž ���� N
		int[] top = new int[N];
		Stack<TopInfo> stack = new Stack<>();	// ���� ž ����
		int[] dest = new int[N];				// �������� �´� ž ��ǥ
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			top[n] = Integer.parseInt(st.nextToken());
			dest[n] = -1;	// -1�� �ʱ�ȭ
			if (stack.isEmpty()) {	// ù��° ž�� ���, �������� ���� ž�� ������ ���
				stack.add(new TopInfo(top[n], n));
			} else {				// ������ ���� ž�� �־��� ���
				while (!stack.isEmpty()) {
					TopInfo high = stack.peek();	// ������ ���Ҵ� ž�� ���絵 ������ Ȯ��
					if (top[n] <= high.t) {
						dest[n] = high.p;
						break;
					} else {						// �ƴ� ��� ���� ž�� ���� ������ ��Ҵ� �� ����
						stack.pop();
					}
				}
				stack.add(new TopInfo(top[n], n));	// ���� ž�� ���
			}
		}

		for (int d : dest) {
			bw.write((d + 1) + " ");
		}
		bw.close();
	}

}
