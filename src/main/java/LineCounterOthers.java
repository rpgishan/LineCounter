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
public class LineCounterOthers {

    static int linecount = 0, sourcelinecount;
    static String line, comments, commentsend;

    public static String lineCountWithoutComments1(String filename) throws FileNotFoundException, IOException {
        // TODO code application logic here
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        comments = line.substring(0, 2);

        while (comments == "//") {
            sb.append("\t" + line);
            sb.append(System.lineSeparator());
            line = br.readLine();
            comments = (line != null && line.length() >= 2) ? line.substring(0, 2) : line;
        }

        while (comments == "/*") {
            sb.append("\t" + line);
            sb.append(System.lineSeparator());
            commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
            if (commentsend != "*/") {
                line = br.readLine();
                commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
            }
            while (commentsend != "*/") {
                sb.append("\t" + line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        }
        while (line != null) {
            linecount++;
            sb.append(linecount + "\t" + line);
            sb.append(System.lineSeparator());
            line = br.readLine();
            comments = (line != null && line.length() >= 2) ? line.substring(0, 2) : line;
            while (comments == "//") {
                sb.append("\t" + line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                comments = (line != null && line.length() >= 2) ? line.substring(0, 2) : line;
            }

            while (comments == "/*") {
                sb.append("\t" + line);
                sb.append(System.lineSeparator());
                commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
                if (commentsend != "*/") {
                    line = br.readLine();
                    commentsend = (line != null && line.length() >= 2) ? line.substring(line.length() - 2, line.length()) : line;
                }
                while (commentsend != "*/") {
                    sb.append("\t" + line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                line = br.readLine();
            }

        }
        br.close();
        return sb.toString();
    }
}
