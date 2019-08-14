/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linecounter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Gishan-mac
 */
public class LineCounter {

    /**
     * @param args the command line arguments
     */
    static int linecount = 0, sourcelinecount, commentlinecount1 = 0;
    static String line, comments, commentsend;

    public static String lineCountWithComments(String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        linecount = 0;
        while (line != null) {
            linecount++;
            sb.append(linecount + "\t" + line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        br.close();
        fr.close();
        return sb.toString();
    }

    public static String lineCountWithoutComments(String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        linecount = 0;

//        boolean b = true;
//        while (b) {
//         b = false;
        w2:
        while (line != null) {
            boolean b = false;
            while (line.startsWith("//") || line.startsWith("\n")) {
                sb.append("\t" + line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            while (line.startsWith("/*")) {
                sb.append("\t" + line);
                sb.append(System.lineSeparator());
                while (!line.endsWith("*/")) {
                    line = br.readLine();
                    sb.append("\t" + line);
                    sb.append(System.lineSeparator());
                    b = true;
                }
//                if (!b) {
                line = br.readLine();
//                } else
                if (b) {
//                        break w2;
                    continue w2;
                }
            }
            linecount++;
            sb.append(linecount + "\t" + line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
//        }
        br.close();
        fr.close();
        return sb.toString();
    }

    public static String charLineCountWithoutComments(String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        linecount = 0;
        w1:
        while (line != null) {
            while (line.length() == 0) {
                sb.append("\t" + line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            char currentline[] = line.toCharArray();
            boolean a = true, d = true, e = true, f = true;
            for (int i = 0; i < currentline.length - 1; i++) {
                String s = String.valueOf(currentline[i]) + String.valueOf(currentline[i + 1]);
                String ss = String.valueOf(currentline[i]);
                if (!s.equals("//")) {
                    a = false;
                } else if (!s.equals("/*")) {
                    d = false;
                } else if (!ss.equals(" ")) {
                    e = false;
                }
//                System.out.println(s + "   " + (s.equals("//")));
                if (s.equals("//") && a && e) {
                    commentlinecount1++;
                    sb.append("\t" + line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                    continue w1;
                } else if (s.equals("/*") && d) {
                    commentlinecount1++;
                    boolean b = true;
                    if (line.endsWith("*/")) {
                        if (a) {
                            sb.append("\t" + line);
                            sb.append(System.lineSeparator());
                        } else {
                            linecount++;
                            sb.append(linecount + "\t" + line);
                            sb.append(System.lineSeparator());
                        }
                        line = br.readLine();
                        b = false;
//                        commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
                    }
                    while ((!line.endsWith("*/")) && b) {
                        commentlinecount1++;
                        sb.append("\t" + line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    if (line.endsWith("*/")) {
                        sb.append("\t" + line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
//                        commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
                    }
                    continue w1;
                }
            }
            linecount++;
            sb.append(linecount + "\t" + line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        br.close();
        fr.close();
        return sb.toString();
    }

    public static int lineCount() {
        return linecount;
    }

    public static String charLineCountWithoutComments1(String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        linecount = 0;
        w1:
        while (line != null) {
            while (line.length() == 0) {
                sb.append("\t" + line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            char currentline[] = line.toCharArray();
            boolean a = true, d = true, e = true, f = true;
            f1: for (int i = 0; i < currentline.length - 1; i++) {
                String s = String.valueOf(currentline[i]) + String.valueOf(currentline[i + 1]);
                String ss = String.valueOf(currentline[i]);
                if (!s.equals("//")) {
                    a = false;
                } else if (!s.equals("/*")) {
                    d = false;
                } else if (!ss.equals(" ")) {
                    e = false;
                }
//                System.out.println(s + "   " + (s.equals("//")));
                if (s.equals("//") && a && e) {
                    commentlinecount1++;
                    sb.append("\t" + line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                    continue w1;
                } else if (s.equals("/*") && d) {
                    commentlinecount1++;
                    boolean b = true;
                    for (int j = i; j < currentline.length - 1; j++) {
                        String star = String.valueOf(currentline[j]) + String.valueOf(currentline[j + 1]);
                        if (star.equals("*/")) {
                                sb.append("\t" + line);
                                sb.append(System.lineSeparator());
                            line = br.readLine();
                                break f1;
//                             }  else {
//                                linecount++;
//                                sb.append(linecount + "\t" + line);
//                                sb.append(System.lineSeparator());
//                          
                           // b = false;
//                        commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
                        }
                    }
                    while ((!line.endsWith("*/")) && b) {
                        commentlinecount1++;
                        sb.append("\t" + line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    if (line.endsWith("*/")) {
                        sb.append("\t" + line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
//                        commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
                    }
                    continue w1;
                }
            }
            linecount++;
            sb.append(linecount + "\t" + line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        br.close();
        fr.close();
        return sb.toString();
    }
    
    public static int commentCount(){
        return commentlinecount1;
    }

    public static int noOfFunctions(String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        line = br.readLine();
        int nooffunctions = 0;
        while (line != null) {
            if ((line.startsWith("void") || line.startsWith("char") || line.startsWith("int") || line.startsWith("float") || line.startsWith("short") || line.startsWith("long")) && (line.endsWith(") {") || line.endsWith("){"))) {
                nooffunctions++;
                System.out.println("Functions - " + line);
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
        return nooffunctions;
    }

    public static int noOfVariables(String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        line = br.readLine();
        int noofvariables = 0;
        while (line != null) {
            if ((line.startsWith("void") || line.startsWith("char") || line.startsWith("int") || line.startsWith("float") || line.startsWith("short") || line.startsWith("long")) && !line.endsWith(");") && line.endsWith(";")) {
                char currentline[] = line.toCharArray();
                for (int i = 0; i < currentline.length; i++) {
                    if (currentline[i] == ',' || currentline[i] == ';') {
                        noofvariables++;
                    }
                }
                System.out.println("Variables - " + line);
            }
            line = br.readLine();
        }
        br.close();
        fr.close();
        return noofvariables;
    }

    public static int commentLineCount(String filename) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        line = br.readLine();
        int commentlinecount = 0;
        boolean b, c = false;
        while (line != null) {
            char currentline[] = line.toCharArray();
            if (line.startsWith("//")) {
                commentlinecount++;
            }
            System.out.println(line.startsWith("/*"));
            while ((line != null) && (line.startsWith("/*") || c)) {
                b = true;
                for (int i = 0; i < currentline.length - 1; i++) {
                    String s = (currentline[i] + currentline[i + 1]) + "";
                    if (s == "*/") {
                        commentlinecount++;
                        b = false;
                        c = false;
                        System.out.println("*/ line - " + line);
                    }
                }
                if (b) {
                    c = true;
                    commentlinecount++;
                    line = br.readLine();
//                    if (line.length() == 0) {
//                        line = br.readLine();
//                    }
                }
                System.out.println("Line - " + line);
                System.out.println("In while loop - " + line.startsWith("/*"));
            }
            line = br.readLine();
//            if (line.length() == 0) {
//                line = br.readLine();
//            }
        }
        br.close();
        fr.close();
        return commentlinecount;
    }

}
