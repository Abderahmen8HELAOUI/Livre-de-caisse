package com.example.springbootjpapagingsorting.repository;

import com.example.springbootjpapagingsorting.model.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author USER
 * @version 1.0
 * @created 21/09/2023 - 21:41
 * @project spring-boot-jpa-paging-sorting
 */

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    Page<Tutorial> findByTitleContaining(String title, Pageable pageable);

    List<Tutorial> findByTitleContaining(String title, Sort sort);

    @Query(value = "SELECT e.balance_previous_month FROM tutorials e WHERE e.id = (SELECT MAX(e2.id) FROM tutorials e2)", nativeQuery = true)
    public Double balancePreviousMonth();

    @Query(value = "SELECT e.postal_current_account FROM tutorials e WHERE e.id = (SELECT MAX(e2.id) FROM tutorials e2)", nativeQuery = true)
    public Double postalCurrentAccount();

    @Query(value = "SELECT e.credit_expected FROM tutorials e WHERE e.id = (SELECT MAX(e2.id) FROM tutorials e2)", nativeQuery = true)
    public Double creditExpected();

    @Query(value = "SELECT e.expected_flow FROM tutorials e WHERE e.id = (SELECT MAX(e2.id) FROM tutorials e2)", nativeQuery = true)
    public Double expectedFlow();

    @Query(value = "SELECT e.other_values FROM tutorials e WHERE e.id = (SELECT MAX(e2.id) FROM tutorials e2)", nativeQuery = true)
    public Double otherValues();

    @Query(value = "SELECT e.states_repartition FROM tutorials e WHERE e.id = (SELECT MAX(e2.id) FROM tutorials e2)", nativeQuery = true)
    public Double statesRepartition();

    @Query(value = "SELECT CAST(SUM(t.balance_previous_month + t.recipe_today) as decimal(10,3)) AS recette_total\n" +
            "FROM tutorials t\n" +
            "WHERE TO_CHAR(current_date, 'dd-mm-yyyy') = SUBSTRING(t.title, 20, 10);", nativeQuery = true)
    public Double totalRecipe();

    @Query(value = "SELECT CAST((t.operation_treasury_anterior + t.operation_treasury_today)as decimal(10,3)) AS operation_treasury_lastday\n" +
            "\t\tFROM tutorials t\n" +
            "                where t.id = (SELECT MAX(e2.id) FROM tutorials e2);",nativeQuery = true)
    public Double totalTreasuryOperationsLastDay();

    @Query(value = "SELECT CAST((t.operation_regulation_prior + t.operation_regulation_today)as decimal(10,3)) AS operation_treasury_lastday\n" +
            "\t\tFROM tutorials t\n" +
            "                where t.id = (SELECT MAX(e2.id) FROM tutorials e2);", nativeQuery = true)
    public Double totalOperationsRegulationsLastDay();

}
