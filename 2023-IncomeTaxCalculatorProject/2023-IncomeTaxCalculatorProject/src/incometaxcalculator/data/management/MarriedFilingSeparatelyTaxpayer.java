package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {

  public MarriedFilingSeparatelyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    marriedFillingSeperatelyIncomes[0] = 18040;
    marriedFillingSeperatelyIncomes[1] = 71680;
    marriedFillingSeperatelyIncomes[2] = 90000;
    marriedFillingSeperatelyIncomes[3] = 127120;
    marriedFillingSeperatelyTax[0] = 0;
    marriedFillingSeperatelyTax[1] = 965.14;
    marriedFillingSeperatelyTax[2] = 4746.76;
    marriedFillingSeperatelyTax[3] = 6184.88;
    marriedFillingSeperatelyTax[4] = 9098.80;
    marriedFillingSeperatelyTaxPercentage[0] = 5.35;
    marriedFillingSeperatelyTaxPercentage[1] = 7.05;
    marriedFillingSeperatelyTaxPercentage[2] = 7.85;
    marriedFillingSeperatelyTaxPercentage[3] = 7.85;
    marriedFillingSeperatelyTaxPercentage[4] = 9.85;

  }
}