import java.io.*;
import java.util.StringTokenizer;

class Node {
    int row, col;
    Node(int a, int b) {
        row = a;
        col = b;
    }
}

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int w;
    static int h;
    static char[][] map;

    static int[][] cmds;
    static int[] invalidCmds; //잘못된 명령어일때 (동전 있는데 동전 또 던지기) 1 세팅
    static int cmdCnt;

    static int[][] coin_BossToCnt; //그룹이 가지고 있는 동전의 개수
    static int[] coin_SizeToCnt; //특정 점수가 몇개 있는지 저장
    static int coin_Result; //출력 결과 저장

    static int[][] bin_BossToCnt;
    static int[] bin_SizeToCnt;
    static int bin_Result;

    static Node[][] uArr; //유니온파인드 배열

    static void input() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new char[h][w];

        for (int row= 0; row<h; row++) {
            String line = br.readLine();
            for (int col = 0; col<w; col++) {
                map[row][col] = line.charAt(col); 
            }
        }

        cmdCnt = Integer.parseInt(br.readLine());        
        cmds = new int[cmdCnt][3];
        invalidCmds = new int[cmdCnt];

        for (int i = 0; i<cmdCnt; i++) {
            st = new StringTokenizer(br.readLine());
            cmds[i][0] = Integer.parseInt(st.nextToken());
            if (cmds[i][0] == 1) {
                cmds[i][1] = Integer.parseInt(st.nextToken());
                cmds[i][2] = Integer.parseInt(st.nextToken());
            }
            else {
                cmds[i][1] = Integer.parseInt(st.nextToken());
            }
        }

        coin_BossToCnt = new int[h][w];
        coin_SizeToCnt = new int[h * w * 2 + 2];
        bin_BossToCnt = new int[h][w];
        bin_SizeToCnt = new int[h * w + 1];
    }

    static Node getFind(int trow, int tcol) {
        Node par = uArr[trow][tcol];
        if (par.row == trow && par.col == tcol) return par; // 내가 조상이면 나를 반환
        Node ret = getFind(par.row, par.col);//조상찾기

        uArr[trow][tcol].row = ret.row;
        uArr[trow][tcol].col = ret.col;
        // 조상에게 다이렉트로 연결

        return ret;
    }

    static void setUnion(int drow, int dcol, int nrow, int ncol, int tar) {
        Node ret;
        ret = getFind(drow, dcol);
        Node a = new Node(ret.row, ret.col);

        ret = getFind(nrow, ncol);
        Node b = new Node(ret.row, ret.col);

        if (a.row == b.row && a.col == b.col) return;
        //같은 그룹이면 더 이상 진행할 필요없이 끝

        uArr[b.row][b.col].row = a.row; 
        uArr[b.row][b.col].col = a.col;
        //b를 a의 자식으로 연결
        //결과값 작업
        if (tar == '0') {
            int aCnt = coin_BossToCnt[a.row][a.col]; // a의 영역크기
            int bCnt = coin_BossToCnt[b.row][b.col]; // b의 영역크기

            coin_SizeToCnt[aCnt * 2]--; // a영역 점수에 해당하는 개수가 1개 줄어듦
            coin_SizeToCnt[bCnt * 2]--; // b영역 점수에 해당하는 개수가 1개 줄어듦

            coin_BossToCnt[a.row][a.col] += coin_BossToCnt[b.row][b.col]; 
            // a에 b영역을 합쳐 준다.

            coin_BossToCnt[b.row][b.col] = 0;
            //b는 이제 쓸모가 없어서 0으로 초기화

            int newCnt = coin_BossToCnt[a.row][a.col];
            coin_SizeToCnt[newCnt * 2]++; // 합쳐진 영역 점수에 해당하는 개수가 새로 1개 추가
        }
        else {
            int aCnt = bin_BossToCnt[a.row][a.col];
            int bCnt = bin_BossToCnt[b.row][b.col];
            bin_SizeToCnt[aCnt]--; // a영역 점수에 해당하는 개수가 1개 줄어듦(단, 동전에선 2배였지만 여기선 1배)
            bin_SizeToCnt[bCnt]--;

            bin_BossToCnt[a.row][a.col] += bin_BossToCnt[b.row][b.col];
            bin_BossToCnt[b.row][b.col] = 0;

            int newCnt = bin_BossToCnt[a.row][a.col];
            bin_SizeToCnt[newCnt]++; // 동전은 2배지만 여기선 1배
        }
    }

    static void makeUnion(int drow, int dcol) {
        int[][] direct = {{-1, 0},{1, 0},{0, 1}, {0, -1}};
        char tar = map[drow][dcol]; 

        for (int t = 0; t<4; t++) {
            int nrow = drow + direct[t][0];
            int ncol = dcol + direct[t][1];
            if (nrow < 0 || ncol < 0 || nrow >= h || ncol >= w) continue;
            if (map[nrow][ncol] != tar) continue; // 인접한 부분이 같은 동전 or 빈칸

            setUnion(drow, dcol, nrow, ncol, tar);
        }
    }

    static void insertThing(int row, int col, char tar) {
        map[row][col] = tar;

        if (tar == '0') {
            coin_SizeToCnt[2]++; // 2점에 해당하는 영역 1개 추가
            coin_BossToCnt[row][col] = 1; // 해당 영역의 코인 개수
        }
        else {
            bin_SizeToCnt[1]++; // 1점에 해당하는 영역 1개 추가
            bin_BossToCnt[row][col] = 1; // 해당 영역의 빈칸 개수
        }

    }

    static void initSetting(char tar)
    {   
        uArr = new Node[h][w];
        for (int row = 0; row<h; row++) {
            for (int col =0 ; col<w; col++) {
                uArr[row][col] = new Node(row, col); 
            }
        }
        //자기 자신이 조상이라고 초기화

        int[][] list = new int[w * h][2]; // union을 진행할 좌표
        int t = 0;

        //동전을 리스트에다가 목록화
        //맵을 아에 비워 놓음
        for (int row =0; row<h; row++) {
            for (int col = 0; col<w; col++) {
                if (map[row][col] == tar) {
                    list[t][0] = row;
                    list[t][1] = col;
                    t++;

                    if (tar == '0') map[row][col] = '_';
                    else map[row][col] = '0';
                }
            }
        }

        //동전을 하나씩 올린다.
        for (int i = 0; i<t; i++) {
            int row = list[i][0];
            int col = list[i][1];
            map[row][col] = tar;
            insertThing(row, col, tar);//각 점수별 영역에 세팅
            makeUnion(row, col);
        }
    } // 영역들 초기화 

    public static void main(String[] args) throws IOException {        

        input();

        //동전 점수
        initSetting('0');
        for (int i = 0; i < cmdCnt; i++) {            
            if (cmds[i][0] == 2) {
                coin_Result += coin_SizeToCnt[cmds[i][1]];
            }
            if (cmds[i][0] == 1) {
                //이미 동전이 있는곳에 또 올린다면
                if (map[cmds[i][1]][cmds[i][2]] == '0') {
                    invalidCmds[i] = 1;
                    continue;
                }

                //동전을 하나씩 올린다.
                insertThing(cmds[i][1], cmds[i][2], '0');
                makeUnion(cmds[i][1], cmds[i][2]);
            }
        }

        //빈칸 점수 (리플레이)
        initSetting('_');
        for (int i = cmdCnt - 1; i >= 0; i--) {   
            if (cmds[i][0] == 2) {
                bin_Result += bin_SizeToCnt[cmds[i][1]];
            }
            if (cmds[i][0] == 1) {
                if (invalidCmds[i] == 1) continue;
                insertThing(cmds[i][1], cmds[i][2], '_');
                makeUnion(cmds[i][1], cmds[i][2]);
            }
        }

        bw.write((coin_Result + bin_Result) + "\n");

        br.close();
        bw.close();
    }
}