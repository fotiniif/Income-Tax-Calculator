package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {

  public MarriedFilingJointlyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    marriedFillingJointlyIncomes[0] = 36080;
    marriedFillingJointlyIncomes[1] = 90000;
    marriedFillingJointlyIncomes[2] = 143350;
    marriedFillingJointlyIncomes[3] = 254240;
    marriedFillingJointlyTax[0] = 0;
    marriedFillingJointlyTax[1] = 1930.28;
    marriedFillingJointlyTax[2] = 5731.64;
    marriedFillingJointlyTax[3] = 9492.82;
    marriedFillingJointlyTax[4] = 18197.69;
    marriedFillingJointlyTaxPercentage[0] = 5.35;
    marriedFillingJointlyTaxPercentage[1] = 7.05;
    marriedFillingJointlyTaxPercentage[2] = 7.05;
    marriedFillingJointlyTaxPercentage[3] = 7.85;
    marriedFillingJointlyTaxPercentage[4] = 9.85;
  }
}