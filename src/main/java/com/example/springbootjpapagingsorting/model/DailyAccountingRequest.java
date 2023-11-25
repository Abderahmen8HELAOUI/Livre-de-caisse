package com.example.springbootjpapagingsorting.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyAccountingRequest {
    private String title;
    private double recipeToday;
    private double balancePreviousMonth;
    private double operationTreasuryAnterior;
    private double operationTreasuryToday;
    private double operationPreviousRegulation;
    private double operationRegulationToday;
    private double postCurrentAccount;
    private double creditExpected;
    private double rateExpected;
    private double otherValues;
    private double statesRepartition;
    private double moneySpecies;
}
