import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class StringArrayToXML {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(new JFrame());
        String importFilePath;
        if (returnVal == JFileChooser.APPROVE_OPTION){
            importFilePath = chooser.getSelectedFile().getPath();
            System.out.println("File Path Selected: " + importFilePath);
            File importFile = new File(importFilePath);
            String exportFilePath = importFilePath.substring(0,importFilePath.length() - 3) + "xml";
            System.out.println("Output File Path: " + exportFilePath);
            File exportFile = new File(exportFilePath);
            String fileName = importFile.getName().substring(0, importFile.getName().length() - 4);
            PrintWriter printWriter = null;
            FileInputStream fis = null;
            BufferedReader reader = null;
            String line;
            if (importFile.exists()){
                try {
                    fis = new FileInputStream(importFile);
                    reader = new BufferedReader(new InputStreamReader(fis));
                    exportFile.createNewFile();
                    printWriter = new PrintWriter(exportFile);
                    printWriter.println("<resources>");
                    printWriter.println("\t<string-array name=\"" + fileName + "\">");
                    while ((line = reader.readLine()) != null) {
                        printWriter.println("\t\t<item>" + line + "</item>");
                    }
                    printWriter.println("\t</string-array>");
                    printWriter.println("</resources>");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (printWriter != null) {
                        printWriter.flush();
                        printWriter.close();
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.exit(0);
    }
}
