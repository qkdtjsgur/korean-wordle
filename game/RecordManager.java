package game;

import java.io.*;

public class RecordManager {

    private static final String FILE_NAME = "records.txt";

    public void saveRecord(int count) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            bw.write("성공 횟수 : " + count + "번");
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadRecords() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return "기록 없음";
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
