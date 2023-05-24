package management;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class UserManager {
    public static File users = new File("src/main/java/users/Users.txt");
    public static boolean doesUserExist(String username) {
        FileReader f = null;
        try {
            f = new FileReader(users);
            BufferedReader br = new BufferedReader(f);
            while(true) {
                String nextLine = null;
                nextLine = br.readLine();
                if(nextLine != null) {
                    String[] split = nextLine.split(",");
                    if(split[0].equals(username)) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isPasswordCorrect(String username, String password) {
        FileReader f = null;
        try {
            f = new FileReader(users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(f);

        while(true) {
            String nextLine = null;
            try {
                nextLine = br.readLine();
                if(nextLine != null) {
                    String[] split = nextLine.split(",");
                    if(split[0].equals(username)) {
                        if(split[1].equals(password)) {
                            return true;
                        }
                        return false;
                    }
                } else {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    public static void addUser(String username, String password) {

        try {
            FileWriter fw1 = new FileWriter(users,true);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(username + "," + password + ",0,0,1;" + "\n");
            bw1.close();

            File f1 = new File("src/main/java/users/"+username+".txt");
            f1.createNewFile();

            FileWriter fw2 = new FileWriter(f1,true);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write(1 + ",empty" + "\n");
            bw2.write(2 + ",empty" + "\n");
            bw2.write(3 + ",empty" + "\n");
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void changeUserScore(String username, int newScore) {

        String fileContents = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(users));
            while(true) {
                String nextLine = br.readLine();
                if(nextLine != null) {
                    fileContents += nextLine + System.lineSeparator();
                } else {
                    break;
                }
            }

            br.close();

            String[] usersInfo = getUsersInfo(username);

            String newFileContents = null;
            newFileContents = fileContents.replaceAll(usersInfo[0]+","+usersInfo[1]+","+usersInfo[2]+","+usersInfo[3]
                    ,usersInfo[0]+","+usersInfo[1]+","+newScore+","+usersInfo[3]);


            FileWriter fw1 = new FileWriter(users);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(newFileContents);
            bw1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void changeUserCoins(String username, int addingCoins) {

        String fileContents = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(users));
            while(true) {
                String nextLine = br.readLine();
                if(nextLine != null) {
                    fileContents += nextLine + System.lineSeparator();
                } else {
                    break;
                }
            }

            br.close();

            String[] usersInfo = getUsersInfo(username);

            int coins = Integer.parseInt(usersInfo[3]) + addingCoins;

            String newFileContents = null;
            newFileContents = fileContents.replaceAll(usersInfo[0]+","+usersInfo[1]+","+usersInfo[2]+","+usersInfo[3]
                    ,usersInfo[0]+","+usersInfo[1]+","+usersInfo[2]+","+coins);


            FileWriter fw1 = new FileWriter(users);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(newFileContents);
            bw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUserCharacter(String username, int characterId) {

        String fileContents = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(users));
            while(true) {
                String nextLine = br.readLine();
                if(nextLine != null) {
                    fileContents += nextLine + System.lineSeparator();
                } else {
                    break;
                }
            }
            br.close();

            String[] usersInfo = getUsersInfo(username);

            String newFileContents = null;
            newFileContents = fileContents.replaceAll(usersInfo[0]+","+usersInfo[1]+","+usersInfo[2]+","+usersInfo[3]+","+usersInfo[4]
                    ,usersInfo[0]+","+usersInfo[1]+","+usersInfo[2]+","+usersInfo[3]+","+usersInfo[4]+characterId+";");


            FileWriter fw1 = new FileWriter(users);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write(newFileContents);
            bw1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String[] getUsersInfo(String username) {
        String[] res = new String[5];
        res[0] = username;

        FileReader f = null;
        try {
            f = new FileReader(users);
            BufferedReader br = new BufferedReader(f);
            while(true) {
                String nextLine = null;
                nextLine = br.readLine();
                if(nextLine != null) {
                    String[] split = nextLine.split(",");
                    if(split[0].equals(username)) {
                        res[1] = split[1];
                        res[2] = split[2];
                        res[3] = split[3];
                        res[4] = split[4];
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static int[] getUsersCharacters(String username) {
        String[] usersInfo = getUsersInfo(username);

        String[] res = usersInfo[4].split(";");

        int[] result = new int[res.length];

        for(int i = 0; i < res.length ; i++) {
            result[i] = Integer.parseInt(res[i]);
        }

        return result;
    }
    public static boolean doesUserHaveThisCharacter(int id) {
        int[] usersCharacters = getUsersCharacters(GameManager.username);

        for(int usersCharacter : usersCharacters) {
            if (usersCharacter == id) {
                return true;
            }
        }

        return false;
    }
    public static void leaderBoard() {
        TextArea textArea = new TextArea(20,60);
        textArea.setFont(new Font("MV Boli",Font.BOLD,30));
        textArea.setForeground(Color.BLACK);
        textArea.setEditable(false);
        textArea.setBackground(Data.blue);
        textArea.setCaretPosition(10000);


        HashMap<String,Integer> userAndScore = new HashMap<>();

        try {
            FileReader f = new FileReader(users);
            BufferedReader br = new BufferedReader(f);
            while(true) {
                String nextLine = br.readLine();
                if(nextLine != null) {
                    String[] split = nextLine.split(",");
                    userAndScore.put(split[0],Integer.parseInt(split[2]));
                } else {
                    br.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        while(userAndScore.size() > 0) {
            int max = 0;
            String maxUsername = null;
            for(String a : userAndScore.keySet()) {
                if(userAndScore.get(a) >= max) {
                    maxUsername = a;
                    max = userAndScore.get(a);
                }
            }
            textArea.append(maxUsername + " : " + max + System.lineSeparator());
            userAndScore.remove(maxUsername);
        }

        JScrollPane scrollPane = new JScrollPane(textArea);

        JFrame jFrame = new JFrame();

        jFrame.add(scrollPane);
        jFrame.setTitle("Highest Scores");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setBackground(Data.blue);
        jFrame.setLayout(new FlowLayout());
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }
}
