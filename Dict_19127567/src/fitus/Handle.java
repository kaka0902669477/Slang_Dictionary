package fitus;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class Handle {
    private TreeMap<String, Vector<String>> dictionary = new TreeMap<String, Vector<String>>();

    public  Handle() throws IOException {
        FileReader f = new FileReader("slang.txt");
        int buf = f.read();

        String line = "";
        while (buf != -1) {
            if ((char)buf == '\n') {
                String[] split = line.split("`");

                if (split.length == 2) {
                    String[] split2 = split[1].split("\\|");
                    Vector<String> v = new Vector<>();
                    for (String s : split2) {
                        v.add(s);
                    }
                    dictionary.put(split[0], v);

                    line = "";
                    buf = f.read();
                    continue;
                }
            }

            line += (char)buf;
            buf = f.read();
        }
    }

    public Vector<String[]> SearchbySlang(String slang) {
        Vector<String> found = dictionary.get(slang);

        Vector<String[]> result = new Vector<>();

        for(int i = 0; i < (found != null ? found.size() : 0); i++) {
            String[] str = new String[2];
            str[0] = slang;
            str[1] = found.get(i);

            result.add(str);
        }

        return result;
    }

    public Vector<String[]> SearchbyMeaning(String meaning) {
        Set<String> keys = dictionary.keySet();
        Vector<String[]> found = new Vector<>();

        for(String k : keys) {
            if (dictionary.get(k).toString().toLowerCase().contains(meaning.toLowerCase())) {
                String[] str = new String[2];
                str[0] = k;
                str[1] = dictionary.get(k).toString();
                found.add(str);
            }
        }

        return found.isEmpty() ? null : found;
    }

    public void WriteHistory(Vector<String[]> str) {
        FileWriter fw;
        try {
            File file = new File("history.txt");
            fw = new FileWriter(file, true);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            for (String[] s : str) {
                fw.write(s[0] + "`" + s[1] + "`" + dtf.format(now) + "\n");
            }

            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Vector<String[]> getHistory() {
        FileReader f = null;
        Vector<String[]> str = null;
        try {
            f = new FileReader("history.txt");
            int buf = f.read();
            str = new Vector<String[]>();

            String line = "";
            while (buf != -1) {
                if ((char)buf == '\n') {
                    String[] split = line.split("`");

                    if (split.length == 3) {
                        str.add(split);
                        line = "";
                        buf = f.read();
                        continue;
                    }
                }

                line += (char)buf;
                buf = f.read();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    private void saveFile() {
        FileWriter fw;
        try {
            fw = new FileWriter("slang.txt");
            Set<String> tempkey = dictionary.keySet();
            for(String tk:tempkey){
                fw.write(tk + "`");
                int n = dictionary.get(tk).size();
                for (int i = 0; i < n - 1; i++) {
                    fw.write(dictionary.get(tk).get(i).toString() + "|");
                }
                fw.write(dictionary.get(tk).get(n - 1).toString() + "\n");
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String key) {
        dictionary.remove(key);
        this.saveFile();
    }

    public String RandomSlangWord() {
        Set<String> keySet = dictionary.keySet();
        Vector<String> keyList = new Vector<>(keySet);
        int size = keyList.size() - 1;
        int randIndex = new Random().nextInt(size);
        return keyList.get(randIndex) + ": " + dictionary.get(keyList.get(randIndex)).get(0);
    }

    public void Reset() {
        try {
            File file = new File("slang.txt");
            File source = new File("origin.txt");

            InputStream is = new FileInputStream(source);
            OutputStream os = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkExisted(String slang) {
        if (dictionary.get(slang) == null)
            return false;
        return  true;
    }

    public void duplicate(String slang, String definition) {
        dictionary.get(slang).add(definition);
        this.saveFile();
    }

    public void add(String slang, String definition) {
        Vector<String> v = new Vector<>();
        v.add(definition);
        dictionary.put(slang, v);
        this.saveFile();
    }

    public String[][] quiz() {
        String[][] quiz = new String[4][2];
        Set<String> keySet = dictionary.keySet();
        Vector<String> keyList = new Vector<>(keySet);
        int count = 0;
        while (count < 4) {
            int min = 0;
            int max = dictionary.size() - 1;
            int rand = (min + (int) (Math.random() * max));

            Vector<String> value = dictionary.get(keyList.get(rand));
            quiz[count][0] = keyList.get(rand);
            quiz[count][1] = value.get(0);
            count++;
        }

        return quiz;
    }
}
