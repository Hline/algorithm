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
		for (int n = 0; n < N; n++) {		// 수 목록 입력
			arr[n] = Integer.parseInt(st.nextToken()) - 1;	// 문제는 1부터지만 0부터 사용
		}
		Arrays.sort(arr);	// 탐색을 위해 순차 정렬
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			int num = Integer.parseInt(st.nextToken()) - 1;	// 검색하려는 수

			int left = 0;		// 탐색 인덱스 왼쪽
			int right = N - 1;	// 탐색 인덱스 오른쪽
			int mid = (left + right) / 2;	// 현재 비교하려는 수 인덱스
			while (left <= right) {			// 찾는 값이 없어 두 인덱스가 반전되기 전까지 탐색
				mid = (left + right) / 2;
				if (num == arr[mid]) {		// 찾으려는 수가 있는 경우
					break;
				}
				if (num < arr[mid]) {		// 현재 비교하려는 수가 큰 경우
					right = mid - 1;		// 작은 수들 중에서 비교하기 위해 인덱스 변경
				} else {					// 현재 비교하려는 수가 작은 경우
					left = mid + 1;			// 큰 수들 중에서 비교하기 위해 인덱스 변경
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
