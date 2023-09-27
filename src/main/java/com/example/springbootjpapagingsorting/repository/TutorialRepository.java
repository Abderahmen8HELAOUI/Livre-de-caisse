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
    Page<Tutorial> findByPublished(boolean published, Pageable pageable);

    Page<Tutorial> findByTitleContaining(String title, Pageable pageable);

    List<Tutorial> findByTitleContaining(String title, Sort sort);

    @Query(value = "SELECT CAST(SUM(t.balance_previous_month + t.recipe_today) as decimal(10,3)) AS recette_total\n" +
            "FROM tutorials t\n" +
            "WHERE TO_CHAR(current_date, 'dd-mm-yyyy') = SUBSTRING(t.title, 20, 10);", nativeQuery = true)
    public double totalRecipe();
}
