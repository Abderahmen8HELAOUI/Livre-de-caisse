package com.example.springbootjpapagingsorting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author USER
 * @version 1.0
 * @created 21/09/2023 - 21:38
 * @project spring-boot-jpa-paging-sorting
 */
@Data
@Entity
@Table(name = "tutorials")
@NoArgsConstructor
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "recipeToday")
    private double recipeToday;

    @Column(name = "balancePreviousMonth")
    private double balancePreviousMonth;

    @Column(name = "operationTreasuryAnterior")
    private double operationTreasuryAnterior;

    @Column(name = "operationTreasuryToday")
    private double operationTreasuryToday;

    @Column(name = "operationRegulationPrior")
    private double operationPreviousRegulation;

    @Column(name = "operationRegulationToday")
    private double operationRegulationToday;

    @Column(name = "postalCurrentAccount")
    private double postCurrentAccount;

    @Column(name = "creditExpected")
    private double creditExpected;

    @Column(name = "expectedFlow")
    private double rateExpected;

    @Column(name = "otherValues")
    private double otherValues;

    @Column(name = "statesRepartition")
    private double statesRepartition;

    @Column(name = "moneySpecies")
    private double moneySpecies;

    @Column(name = "published")
    private boolean published;

    public Tutorial(String title,
                    String description,
                    double recipeToday,
                    double balancePreviousMonth,
                    double operationTreasuryAnterior,
                    double operationTreasuryToday,
                    double operationPreviousRegulation,
                    double operationRegulationToday,
                    double postCurrentAccount,
                    double creditExpected,
                    double rateExpected,
                    double otherValues,
                    double statesRepartition,
                    double moneySpecies,
                    boolean published) {
        this.title = title;
        this.description = description;
        this.recipeToday = recipeToday;
        this.balancePreviousMonth = balancePreviousMonth;
        this.operationTreasuryAnterior = operationTreasuryAnterior;
        this.operationTreasuryToday = operationTreasuryToday;
        this.operationPreviousRegulation = operationPreviousRegulation;
        this.operationRegulationToday = operationRegulationToday;
        this.postCurrentAccount = postCurrentAccount;
        this.creditExpected = creditExpected;
        this.rateExpected = rateExpected;
        this.otherValues = otherValues;
        this.statesRepartition = statesRepartition;
        this.moneySpecies = moneySpecies;
        this.published = published;
    }
}
