import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		br = new BufferedReader(new FileReader("±€±Õ√£±‚\\4.in"));
		st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		String note = "";
		for (int i = 0; i < row; i++) {
			note += br.readLine();
		}
		int n = Integer.parseInt(br.readLine());
		String nTxt = br.readLine();
		for (int i = 0; i < note.length(); i++) {
			boolean matched = true;
			for (int j = 0; j < nTxt.length(); j++) {
				if ((i + j == note.length()) || (note.charAt(i + j) != nTxt.charAt(j))) {
					matched = false;
					break;
				}
			}
			if (matched)
				bw.write("(" + (i / col) + "," + (i % col) + ")\n");
		}

		bw.close();

	}

}
