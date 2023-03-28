package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class XMLLogWriter extends FileWriter {
  
  private String[] fileData = {"<Name> ","<AFM> ","<Income> ","<BasicTax> ","<TaxIncrease> ","<TaxDecrease> ","<TotalTax> ",
      "<Receipts> ","<Entertainment> ","<Basic> ","<Travel> ","<Health> ", "<Other> " };
  private String[] fileData2 = {" </Name> "," </AFM> "," </Income> "," </BasicTax> "," </TaxIncrease> "," </TaxDecrease> "," </TotalTax> ",
      " </Receipts> "," </Entertainment> "," </Basic> "," </Travel> "," </Health> ", " </Other> " };

  public void generateFile(String path, int taxRegistrationNumber) throws IOException {
    
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(path + "_LOG.xml"));
    String[] tempName = path.split("\\\\");
    int numTaxRegistrationNumber = Integer.parseInt(tempName[tempName.length-1]);
    generateFileData(numTaxRegistrationNumber, fileData, fileData2, outputStream);
    outputStream.close();
  }
}