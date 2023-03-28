package incometaxcalculator.data.io;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class XMLFileReader extends FileReader {

  protected int checkForReceipt(String values[]) throws NumberFormatException {
    if (values[0].equals("<ReceiptID>")) {
       int receiptId = Integer.parseInt(values[1].trim());
       return receiptId;
    }
    return -1;
  }

  protected String getValueOfField(String fieldsLine) throws WrongFileFormatException {
      String valueWithTail[] = fieldsLine.split(" ", 2);
      String valueReversed[] = new StringBuilder(valueWithTail[1]).reverse().toString().trim()
          .split(" ", 2);
      return new StringBuilder(valueReversed[1]).reverse().toString();
  }
}