package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {

  protected int checkForReceipt(String values[])
    throws NumberFormatException {
    if (values[0].equals("Receipt")) {
      if (values[1].equals("ID:")) {
        System.out.println(values);
        int receiptId = Integer.parseInt(values[2].trim());
        return receiptId;
      }
    }
    return -1;
  }

  protected String getValueOfField(String fieldsLine) throws WrongFileFormatException {
      String values[] = fieldsLine.split(" ", 2);
      values[1] = values[1].trim();
      return values[1];
  }

}