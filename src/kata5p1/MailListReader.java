
package kata5p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MailListReader {

    private MailListReader() {}
    
    // Email address regular expression that 99.99% works 
    // https://emailregex.com/
    private static final Pattern emailPattern = Pattern.compile(
              "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?"
            + ":\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:"
            + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b"
            + "\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-"
            + "\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\."
            + ")+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2"
            + "[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4]"
            + "[0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*"
            + "[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a"
            + "\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    );

    private static boolean validateEmail(String line) {
        return emailPattern.matcher(line).matches();
    }
    
    public static List<String> read (String fileName) {
        List<String> mailList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while( ( line = br.readLine() ) != null ){
                if ( validateEmail(line) ) { mailList.add(line); }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(-1);
        }
        return mailList;
    }
    


}
