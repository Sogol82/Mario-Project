package management;

import java.io.*;

public class GameManager {
    public static String username = "ali";
    public static int gameNumber;

    public static void saveNewGame(int x, int y, int id, int score) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users/"+username+".txt"));
            String line1 = br.readLine();
            String line2 = br.readLine();
            String line3 = br.readLine();
            br.close();

            String fileContents = line1 + System.lineSeparator() + line2 + System.lineSeparator() + line3;

            String newFileContents = null;
            if(gameNumber == 1) {
                newFileContents = fileContents.replaceAll(line1,"1," + x + "," + y + "," + id + "," + score);
            } else if(gameNumber == 2) {
                newFileContents = fileContents.replaceAll(line2,"2," + x + "," + y + "," + id +  "," + score);
            } else if(gameNumber == 3) {
                newFileContents = fileContents.replaceAll(line3,"3," + x + "," + y + "," + id + "," + score);
            }

            FileWriter fw1 = new FileWriter("src/users/"+username+".txt");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(newFileContents);
            bw1.close();

            String[] usersInfo = UserManager.getUsersInfo(username);
            if(score > Integer.parseInt(usersInfo[2])) {
                UserManager.changeUserScore(username,score);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGame() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users/"+username+".txt"));
            String line1 = br.readLine();
            String line2 = br.readLine();
            String line3 = br.readLine();
            br.close();

            String fileContents = line1 + System.lineSeparator() + line2 + System.lineSeparator() + line3;

            String newFileContents = null;
            if(gameNumber == 1) {
                newFileContents = fileContents.replaceAll(line1,"1,empty");
            } else if(gameNumber == 2) {
                newFileContents = fileContents.replaceAll(line2,"2,empty");
            } else if(gameNumber == 3) {
                newFileContents = fileContents.replaceAll(line3,"3,empty");
            }

            FileWriter fw1 = new FileWriter("src/users/"+username+".txt");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(newFileContents);
            bw1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] returnGame() {
        int[] res = new int[3];
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users/"+username+".txt"));
            String line1 = br.readLine();
            String line2 = br.readLine();
            String line3 = br.readLine();
            br.close();

            if(gameNumber == 1) {
                String[] line1S = line1.split(",");
                if(line1S[1].equals("empty")) {
                    res[0] = -1;
                } else {
                    res[0] = Integer.parseInt(line1S[1]);
                    res[1] = Integer.parseInt(line1S[2]);
                    res[2] = Integer.parseInt(line1S[3]);
                }
            } else if(gameNumber == 2) {
                String[] line2S = line2.split(",");
                if(line2S[1].equals("empty")) {
                    res[0] = -1;
                } else {
                    res[0] = Integer.parseInt(line2S[1]);
                    res[1] = Integer.parseInt(line2S[2]);
                    res[2] = Integer.parseInt(line2S[3]);
                }
            } else if(gameNumber == 3) {
                String[] line3S = line3.split(",");
                if(line3S[1].equals("empty")) {
                    res[0] = -1;
                } else {
                    res[0] = Integer.parseInt(line3S[1]);
                    res[1] = Integer.parseInt(line3S[2]);
                    res[2] = Integer.parseInt(line3S[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
