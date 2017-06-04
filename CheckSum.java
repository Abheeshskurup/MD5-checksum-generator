/**
 * 
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 * @author Abheesh
 *
 */
public class CheckSum {
    
    /**
     * @param args
     */
    public static void main(String [] args) {
        String inputFoldername= "D:\\CheckSumGenerator";
        File folder = new File(inputFoldername);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
              if (file.isFile() && file.getName().endsWith("txt")
                      && !file.getName().equalsIgnoreCase("readme.txt")) {
                System.out.println("File " + file.getName());
                String filename = file.getName().replaceAll(".txt", ".TXT");
                filename = inputFoldername+"\\" + filename;
                System.out.println(filename);
                if (!filename.contains("checksumresult")) {
                
                String hashTotal = generateHashTotal(filename);
                System.out.println(hashTotal);
                
                try{
                    PrintWriter writer = new PrintWriter(
                            "D:\\CheckSumGenerator\\checksumresult_"+file.getName(), "UTF-8");
                    writer.println("please see the checksum value of "+ filename +" below");
                    writer.println(hashTotal);
                    writer.close();
                } catch (IOException e) {
                   e.printStackTrace();
                } 
                
                } else if (filename.contains("checksumresult")) {
                    file.delete();
                }
              } else if (file.isDirectory()) {
                System.out.println("Directory " + file.getName());
              }
            }
    }
    
    
    /**
     * Generate hash total.
     * 
     * @param fileName
     *            the file name
     * @return the string
     */
    public static String generateHashTotal(String fileName) {
        
        String hashAlgorithm = "MD5";
        MessageDigest messageDigest;
        String hashText = "";
        
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            messageDigest = MessageDigest.getInstance(hashAlgorithm);
            String line = "";
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    messageDigest.update(line.getBytes(), 0, line.length());
                }
            }
            bufferedReader.close();
            hashText = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.getMessage();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.getMessage();
        } catch (IOException ioException) {
            ioException.getMessage();
        }
        return hashText;
    }
    
}
