package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {
  
  public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    headOfHouseholdIncomes[0] = 30390;
    headOfHouseholdIncomes[1] = 90000;
    headOfHouseholdIncomes[2] = 122110;
    headOfHouseholdIncomes[3] = 203390;
    headOfHouseholdTax[0] = 0;
    headOfHouseholdTax[1] = 1625.87;
    headOfHouseholdTax[2] = 5828.38;
    headOfHouseholdTax[3] = 8092.13;
    headOfHouseholdTax[4] = 14472.61;
    headOfHouseholdTaxPercentage[0] = 5.35;
    headOfHouseholdTaxPercentage[1] = 7.05;
    headOfHouseholdTaxPercentage[2] = 7.05;
    headOfHouseholdTaxPercentage[3] = 7.85;
    headOfHouseholdTaxPercentage[4] = 9.85;
  }
}