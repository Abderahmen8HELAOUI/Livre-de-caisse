package com.example.springbootjpapagingsorting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tutorials")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

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

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;


    @CreatedBy
    @Column(
            nullable = true,
            updatable = false
    )
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;

}
