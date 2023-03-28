package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTLogWriter extends FileWriter {
  
  private String[] fileData = {"Name: ","AFM: ","Income: ","Basic Tax: ","Tax Increase: ","Tax Decrease: ","Total Tax: ",
      "TotalReceiptsGathered: ","Entertainment: ","Basic: ","Travel: ","Health: ", "Other: " };
  private String[] fileData2 = {"", "", "", "", "", "", "","", "", "", "", "", ""};
  
  
  public void generateFile(String path, int taxRegistrationNumber) throws IOException {
    
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(path + "_LOG.txt"));
    String[] tempName = path.split("\\\\");
    int numTaxRegistrationNumber = Integer.parseInt(tempName[tempName.length-1]);
    generateFileData(numTaxRegistrationNumber, fileData, fileData2, outputStream);
    outputStream.close();
  }
}