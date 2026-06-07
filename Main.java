import ui.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // 자바 화면(Swing)을 안전하게 실행하기 위한 표준 방법입니다.
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setLocationRelativeTo(null); // 화면 정중앙에 창 띄우기
            frame.setVisible(true);
        });
    }
}
