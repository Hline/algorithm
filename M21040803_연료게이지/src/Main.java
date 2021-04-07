import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int oil = 0;
			int empty = 0;
			String line = br.readLine();
			for (char l : line.toCharArray()) {
				if (l == '#')
					oil++;
				if (l == '_')
					empty++;
			}
			bw.write(oil * 100 / (oil + empty) + "%\n");
		}
		bw.close();
	}

}
