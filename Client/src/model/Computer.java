/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import enums.PlayerCode;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Minh Khanh
 */
public class Computer extends Player{

    private int nCol;
    private int nRow;
    private int[][] data;
    private Game game;
    
    public Computer(Game g) {
        this.name = "Computer";
        this.game = g;
        nCol = game.GetNumberColumns();
        nRow = game.GetNumberRows();
        data = new int[nRow][nCol];
        ResetBoard();
    }
    
    public Point Next()
    {
        ResetBoard();
        LuongGia(PlayerCode.Player2);
        return GetMaxNode();
    }
    
    private void ResetBoard() {
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                data[i][j] = 0;
            }
        }
    }

    //Hàm lấy vị trí có giá trị cao nhất trên bàn cờ
    private Point GetMaxNode() {
        Point n = new Point();
        int maxValue = data[0][0];
        Point[] arrMaxNodes = new Point[nCol * nRow];
        for (int i = 0; i < arrMaxNodes.length; i++) {
            arrMaxNodes[i] = new Point();
        }
        int count = 0;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (data[i][j] > maxValue) {
                    n.y = i;
                    n.x = j;
                    maxValue = data[i][j];
                }
            }
        }

        //Với mục đích không lặp lại bước đi giống như lần trước
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (data[i][j] == maxValue) {
                    n.y = i;
                    n.x = j;
                    arrMaxNodes[count] = n;
                    count++;
                }
            }
        }
        //Đường đi sẽ là ngẫu nhiên

        Random r = new Random();
        int soNgauNhien = r.nextInt(count);
        return arrMaxNodes[soNgauNhien];
    }
    public int[] PhongThu = {0, 1, 9, 85, 769};
    public int[] TanCong = {0, 2, 28, 256, 2308};

    private void LuongGia(PlayerCode player) {
        int cntHuman = 0, cntCom = 0;//Biến đếm Human,Com
        //Luong gia cho hang
        PlayerCode[][] board = game.GetBoard();
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol - 4; j++) {
                //Khởi tạo biến đếm
                cntHuman = cntCom = 0;
                //Đếm số lượng con cờ trên 5 ô kế tiếp của 1 hàng
                for (int k = 0; k < 5; k++) {
                    if (board[i][j + k] == PlayerCode.Player1) {
                        cntHuman++;
                    }
                    if (board[i][j + k] == PlayerCode.Player2) {
                        cntCom++;
                    }
                }
                //Lượng giá
                //Nếu 5 ô kế tiếp chỉ có 1 loại cờ (hoặc là Human,hoặc la Com)
                if (cntHuman * cntCom == 0 && cntHuman != cntCom) {
                    //Gán giá trị cho 5 ô kế tiếp của 1 hàng
                    for (int k = 0; k < 5; k++) {
                        //Nếu ô đó chưa có quân đi
                        if (board[i][j + k] == PlayerCode.None) {
                            //Nếu trong 5 ô đó chỉ tồn tại cờ của Human
                            if (cntCom == 0) {
                                //Nếu đối tượng lượng giá là Com
                                if (player == PlayerCode.Player2) {
                                    //Vì đối tượng người chơi là Com mà trong 5 ô này chỉ có Human
                                    //nên ta sẽ cộng thêm điểm phòng thủ cho Com
                                    data[i][j + k] += PhongThu[cntHuman];
                                } //Ngược lại cộng điểm phòng thủ cho Human
                                else {
                                    data[i][j + k] += TanCong[cntHuman];
                                }
                                //Xét trường hợp chặn 2 đầu
                                //Nếu chận 2 đầu thì gán giá trị cho các ô đó bằng 0
//                                if (j - 1 >= 0 && j + 5 <= nCol - 1 && board[i][j - 1] == PlayerCode.Player2 && board[i][j + 5] == PlayerCode.Player2) {
//                                    data[i][j + k] = 0;
//                                }

                            }
                            //Tương tự như trên
                            if (cntHuman == 0) //Nếu chỉ tồn tại Com
                            {
                                if (player == PlayerCode.Player1) //Nếu người chơi là Người
                                {
                                    data[i][j + k] += PhongThu[cntCom];
                                } else {
                                    data[i][j + k] += TanCong[cntCom];
                                }
                                //Trường hợp chặn 2 đầu
                                if (j - 1 >= 0 && j + 5 <= nCol - 1 && board[i][j - 1] == PlayerCode.Player1 && board[i][j + 5] == PlayerCode.Player1) {
                                    data[i][j + k] = 0;
                                }
                            }
                            if ((j + k - 1 > 0) && (j + k + 1 <= nCol - 1) && (cntHuman == 4 || cntCom == 4)
                                    && (board[i][j + k - 1] == PlayerCode.None || board[i][j + k + 1] == PlayerCode.None)) {
                                data[i][j + k] *= 3;
                            }
                        }
                    }
                }
            }
        }
        //Tương tự như lượng giá cho hàng
        for (int i = 0; i < nRow - 4; i++) {
            for (int j = 0; j < nCol; j++) {
                cntHuman = cntCom = 0;
                for (int k = 0; k < 5; k++) {
                    if (board[i + k][j] == PlayerCode.Player1) {
                        cntHuman++;
                    }
                    if (board[i + k][j] == PlayerCode.Player2) {
                        cntCom++;
                    }
                }
                if (cntHuman * cntCom == 0 && cntCom != cntHuman) {
                    for (int k = 0; k < 5; k++) {
                        if (board[i + k][j] == PlayerCode.None) {
                            if (cntCom == 0) {
                                if (player == PlayerCode.Player2) {
                                    data[i + k][j] += PhongThu[cntHuman];
                                } else {
                                    data[i + k][j] += TanCong[cntHuman];
                                }
                                // Truong hop bi chan 2 dau.
//                                if ((i - 1) >= 0 && (i + 5) <= nRow - 1 && board[i - 1][j] == PlayerCode.Player2 && board[i + 5][j] == PlayerCode.Player2) {
//                                    data[i + k][j] = 0;
//                                }
                            }
                            if (cntHuman == 0) {
                                if (player == PlayerCode.Player1) {
                                    data[i + k][j] += PhongThu[cntCom];
                                } else {
                                    data[i + k][j] += TanCong[cntCom];
                                }
                                // Truong hop bi chan 2 dau.
                                if (i - 1 >= 0 && i + 5 <= nRow - 1 && board[i - 1][j] == PlayerCode.Player1 && board[i + 5][j] == PlayerCode.Player1) {
                                    data[i + k][j] = 0;
                                }

                            }
                            if ((i + k - 1) >= 0 && (i + k + 1) <= nRow - 1 && (cntHuman == 4 || cntCom == 4)
                                    && (board[i + k - 1][j] == PlayerCode.None || board[i + k + 1][j] == PlayerCode.None)) {
                                data[i + k][j] *= 3;
                            }
                        }
                    }
                }
            }
        }
        //Tương tự như lượng giá cho hàng
        for (int i = 0; i < nRow - 4; i++) {
            for (int j = 0; j < nCol - 4; j++) {
                cntHuman = cntCom = 0;
                for (int k = 0; k < 5; k++) {
                    if (board[i + k][j + k] == PlayerCode.Player1) {
                        cntHuman++;
                    }
                    if (board[i + k][j + k] == PlayerCode.Player2) {
                        cntCom++;
                    }
                }
                if (cntHuman * cntCom == 0 && cntCom != cntHuman) {
                    for (int k = 0; k < 5; k++) {
                        if (board[i + k][j + k] == PlayerCode.None) {
                            if (cntCom == 0) {
                                if (player == PlayerCode.Player2) {
                                    data[i + k][j + k] += PhongThu[cntHuman];
                                } else {
                                    data[i + k][j + k] += TanCong[cntHuman];
                                }
                                // Truong hop bi chan 2 dau.
//                                if (i - 1 >= 0 && j - 1 >= 0
//                                        && i + 5 <= nRow - 1 && j + 5 <= nCol - 1
//                                        && board[i - 1][j - 1] == PlayerCode.Player2 && board[i + 5][j + 5] == PlayerCode.Player2) {
//                                    data[i + k][j + k] = 0;
//                                }

                            }
                            if (cntHuman == 0) {
                                if (player == PlayerCode.Player1) {
                                    data[i + k][j + k] += PhongThu[cntCom];
                                } else {
                                    data[i + k][j + k] += TanCong[cntCom];
                                }
                                // Truong hop bi chan 2 dau.
                                if ((i - 1) >= 0 && j - 1 >= 0
                                        && i + 5 <= nRow - 1 && j + 5 <= nCol - 1
                                        && board[i - 1][j - 1] == PlayerCode.Player1 && board[i + 5][j + 5] == PlayerCode.Player1) {
                                    data[i + k][j + k] = 0;
                                }
                            }
                            if ((i + k - 1) >= 0 && (j + k - 1) >= 0 && (i + k + 1) <= nRow - 1 && (j + k + 1) <= nCol - 1 && (cntHuman == 4 || cntCom == 4)
                                    && (board[i + k - 1][j + k - 1] == PlayerCode.None || board[i + k + 1][j + k + 1] == PlayerCode.None)) {
                                data[i + k][j + k] *= 3;
                            }
                        }
                    }
                }
            }
        }
        //Tương tự như lượng giá cho hàng
        for (int i = 4; i < nRow - 4; i++) {
            for (int j = 0; j < nCol - 4; j++) {
                cntCom = 0;
                cntHuman = 0;
                for (int k = 0; k < 5; k++) {
                    if (board[i - k][j + k] == PlayerCode.Player1) {
                        cntHuman++;
                    }
                    if (board[i - k][j + k] == PlayerCode.Player2) {
                        cntCom++;
                    }
                }
                if (cntHuman * cntCom == 0 && cntHuman != cntCom) {
                    for (int k = 0; k < 5; k++) {
                        if (board[i - k][j + k] == PlayerCode.None) {
                            if (cntCom == 0) {
                                if (player == PlayerCode.Player2) {
                                    data[i - k][j + k] += PhongThu[cntHuman];
                                } else {
                                    data[i - k][j + k] += TanCong[cntHuman];
                                }
                                // Truong hop bi chan 2 dau.
//                                if (i + 1 <= nRow - 1 && j - 1 >= 0 && i - 5 >= 0 && j + 5 <= nCol - 1 && board[i + 1][j - 1] == PlayerCode.Player2 && board[i - 5][j + 5] == PlayerCode.Player2) {
//                                    data[i - k][j + k] = 0;
//                                }
                            }
                            if (cntHuman == 0) {
                                if (player == PlayerCode.Player1) {
                                    data[i - k][j + k] += PhongThu[cntCom];
                                } else {
                                    data[i - k][j + k] += TanCong[cntCom];
                                }
                                // Truong hop bi chan 2 dau.
                                if (i + 1 <= nRow - 1 && j - 1 >= 0 && i - 5 >= 0 && j + 5 <= nCol - 1 && board[i + 1][j - 1] == PlayerCode.Player1 && board[i - 5][j + 5] == PlayerCode.Player1) {
                                    data[i - k][j + k] = 0;
                                }

                            }
                            if ((i - k + 1) <= nRow - 1 && (j + k - 1) >= 0
                                    && (i - k - 1) >= 0 && (j + k + 1) <= nCol - 1
                                    && (cntHuman == 4 || cntCom == 4)
                                    && (board[i - k + 1][j + k - 1] == PlayerCode.None || board[i - k - 1][j + k + 1] == PlayerCode.None)) {
                                data[i - k][j + k] *= 3;
                            }
                        }
                    }
                }
            }
        }
    }
}
