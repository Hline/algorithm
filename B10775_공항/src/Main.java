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
	static int G;
	static int P;
	static boolean[] useYN;
	static int[] nextIdx;

	/*
	 * ⭐⭐당신은 i번째 비행기를 
	 * 1번부터 gi (1 ≤ gi ≤ G) 번째 게이트중 하나에 
	 * 영구적으로 도킹하려 한다.
	 * gi = 2 일 경우 1,2번 게이트 모두 가능
	 * gi = 2 일 경우 1,2,3번 게이트 모두 가능
	 * 작은 수는 공통이므로 큰 수부터 차례대로 게이트 사용다
	 */
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("in"));
		// 게이트 개수
		G = Integer.parseInt(br.readLine());
		// 비행기 개수
		P = Integer.parseInt(br.readLine());
		// 다음번에 사용가능한 게이트 위치
		nextIdx = new int[G];
		// 처음엔 자기 게이트가 사용 가능함
		for (int g = 0; g < G; g++) {
			nextIdx[g] = g;
		}
		int maxCnt = 0;
		for (int p = 0; p < P; p++) {
			int a = Integer.parseInt(br.readLine()) - 1;
			// 해당 게이트로 들어왔을 때 도킹가능한 게이트 찾기
			int useIdx = Find(a);
			if (useIdx < 0)
				break;
			maxCnt++;

			// 현재게이트는 도킹됐으니 한단계 앞게이트 사용
			nextIdx[useIdx]--;
		}

		bw.write(maxCnt + "\n");
		bw.close();
	}

	// 해당 게이트로 들어왔을 때 도킹가능한 게이트 찾기
	private static int Find(int a) {
		// -1일 경우 사용가능한 게이트가 없으므로 탐색으로 종료
		if (nextIdx[a] == a || nextIdx[a] < 0)
			return nextIdx[a];
		// 도킹가능한 게이트를 찾은걸 저장해서
		// 다음번에는 빠르게 결과에 접근가능
		return nextIdx[a] = Find(nextIdx[a]);
	}

}
