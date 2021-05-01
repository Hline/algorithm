import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class TopInfo {
	int t;	// 탑의 높이값
	int p;	// 탑 위치

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
		int N = Integer.parseInt(br.readLine());	// 탑 개수 N
		int[] top = new int[N];
		Stack<TopInfo> stack = new Stack<>();	// 높은 탑 보관
		int[] dest = new int[N];				// 레이저가 맞는 탑 좌표
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			top[n] = Integer.parseInt(st.nextToken());
			dest[n] = -1;	// -1로 초기화
			if (stack.isEmpty()) {	// 첫번째 탑일 경우, 이전까지 높은 탑이 없었을 경우
				stack.add(new TopInfo(top[n], n));
			} else {				// 이전에 높은 탑이 있었던 경우
				while (!stack.isEmpty()) {
					TopInfo high = stack.peek();	// 이전에 높았던 탑이 현재도 높은지 확인
					if (top[n] <= high.t) {
						dest[n] = high.p;
						break;
					} else {						// 아닐 경우 높은 탑이 나올 때까지 담았던 걸 버림
						stack.pop();
					}
				}
				stack.add(new TopInfo(top[n], n));	// 현재 탑을 기록
			}
		}

		for (int d : dest) {
			bw.write((d + 1) + " ");
		}
		bw.close();
	}

}
