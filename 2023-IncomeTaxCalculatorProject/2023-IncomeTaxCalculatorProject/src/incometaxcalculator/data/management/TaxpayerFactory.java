package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerFactory {

  public Taxpayer generateTaxpayer(String fullname, int taxRegistrationNumber, String status, float income) throws WrongTaxpayerStatusException {
    Taxpayer taxpayer;
    if (status.equals("Married Filing Jointly")) {
      taxpayer = new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income);
    } else if (status.equals("Married Filing Separately")) {
      taxpayer = new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income);
    } else if (status.equals("Single")) {
      taxpayer = new SingleTaxpayer(fullname, taxRegistrationNumber, income);
    } else if (status.equals("Head of Household")) {
      taxpayer = new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income);
    } else {
      throw new WrongTaxpayerStatusException();
    }
    return taxpayer;
  }
}