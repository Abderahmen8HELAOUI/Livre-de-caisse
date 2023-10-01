package com.example.springbootjpapagingsorting.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

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

    @Column(name = "totalRecipeToday")
    private double totalRecipeToday;

    @Column(name = "operationTreasuryAnterior")
    private double operationTreasuryAnterior;

    @Column(name = "operationTreasuryToday")
    private double operationTreasuryToday;

    @Column(name = "totalOperationTreasury")
    private double totalOperationTreasury;

    @Column(name = "operationRegulationPrior")
    private double operationPreviousRegulation;

    @Column(name = "operationRegulationToday")
    private double operationRegulationToday;

    @Column(name = "totalOperationRegulation")
    private double totalOperationRegulation;

    @Column(name = "totalExpenses")
    private double totalExpenses;

    @Column(name = "dalyAccountBalance")
    private double dalyAccountBalance;

    @Column(name = "postalCurrentAccount")
    private double postCurrentAccount;

    @Column(name = "creditExpected")
    private double creditExpected;

    @Column(name = "expectedFlow")
    private double rateExpected;

    @Column(name = "finalPostalAccount")
    private double finalPostalAccount;

    @Column(name = "otherValues")
    private double otherValues;

    @Column(name = "statesRepartition")
    private double statesRepartition;

    @Column(name = "totalCash")
    private double totalCash;

    @Column(name = "moneySpecies")
    private double moneySpecies;

    @Column(name = "moneyInCoinsInCash")
    private double moneyInCoinsInCash;

    @Column(name = "published")
    private boolean published;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

    public Tutorial(String title,
                    String description,

                    double recipeToday,
                    double balancePreviousMonth,
                    double totalRecipeToday,

                    double operationTreasuryAnterior,
                    double operationTreasuryToday,
                    double totalOperationTreasury,

                    double operationPreviousRegulation,
                    double operationRegulationToday,
                    double totalOperationRegulation,

                    double totalExpenses,

                    double dalyAccountBalance,

                    double postCurrentAccount,
                    double creditExpected,
                    double rateExpected,
                    double finalPostalAccount,

                    double otherValues,
                    double statesRepartition,
                    double totalCash,

                    double moneySpecies,
                    double moneyInCoinsInCash,

                    boolean published) {
        this.title = title;
        this.description = description;

        this.recipeToday = recipeToday;
        this.balancePreviousMonth = balancePreviousMonth;
        this.totalRecipeToday = totalRecipeToday;

        this.operationTreasuryAnterior = operationTreasuryAnterior;
        this.operationTreasuryToday = operationTreasuryToday;
        this.totalOperationTreasury = totalOperationTreasury;

        this.operationPreviousRegulation = operationPreviousRegulation;
        this.operationRegulationToday = operationRegulationToday;
        this.totalOperationRegulation = totalOperationRegulation;

        this.totalExpenses = totalExpenses;

        this.dalyAccountBalance = dalyAccountBalance;

        this.postCurrentAccount = postCurrentAccount;
        this.creditExpected = creditExpected;
        this.rateExpected = rateExpected;
        this.finalPostalAccount = finalPostalAccount;

        this.otherValues = otherValues;
        this.statesRepartition = statesRepartition;
        this.totalCash = totalCash;

        this.moneySpecies = moneySpecies;
        this.moneyInCoinsInCash = moneyInCoinsInCash;

        this.published = published;
    }
}
