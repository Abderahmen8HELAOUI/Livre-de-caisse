package com.example.springbootjpapagingsorting.controller;

import com.example.springbootjpapagingsorting.model.DailyAccountingRequest;
import com.example.springbootjpapagingsorting.model.DailyAccountingService;
import com.example.springbootjpapagingsorting.model.Tutorial;
import com.example.springbootjpapagingsorting.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author USER
 * @version 1.0
 * @created 21/09/2023 - 21:43
 * @project spring-boot-jpa-paging-sorting
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TutorialController {

    private final TutorialRepository tutorialRepository;

    private final JdbcTemplate jdbcTemplate;

    private final DailyAccountingService dailyAccountingService;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/sortedtutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Tutorial> tutorials = tutorialRepository.findAll(Sort.by(orders));

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials")
    public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Tutorial> pageTuts;
            if (title == null)
                pageTuts = tutorialRepository.findAll(pagingSort);
            else
                pageTuts = tutorialRepository.findByTitleContaining(title, pagingSort);

            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<?> createTutorial(@RequestBody DailyAccountingRequest dailyAccountingRequest) {

        dailyAccountingService.createDailyAccounting(dailyAccountingRequest);

            return ResponseEntity.accepted().build();

    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();

            _tutorial.setTitle(tutorial.getTitle());

            _tutorial.setRecipeToday(tutorial.getRecipeToday());
            _tutorial.setBalancePreviousMonth(tutorial.getBalancePreviousMonth());

            _tutorial.setOperationTreasuryAnterior(tutorial.getOperationTreasuryAnterior());
            _tutorial.setOperationTreasuryToday(tutorial.getOperationTreasuryToday());

            _tutorial.setOperationPreviousRegulation(tutorial.getOperationPreviousRegulation());
            _tutorial.setOperationRegulationToday(tutorial.getOperationRegulationToday());

            _tutorial.setPostCurrentAccount(tutorial.getPostCurrentAccount());
            _tutorial.setCreditExpected(tutorial.getCreditExpected());

            _tutorial.setRateExpected(tutorial.getRateExpected());

            _tutorial.setOtherValues(tutorial.getOtherValues());
            _tutorial.setStatesRepartition(tutorial.getStatesRepartition());
            _tutorial.setMoneySpecies(tutorial.getMoneySpecies());

            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tutorials/balancePreviousMonth")
    public double getBalancePreviousMonth(){
        return tutorialRepository.balancePreviousMonth();
    }

    @GetMapping("/tutorials/postalCurrentAccount")
    public double getPostalCurrentAccount(){
        return tutorialRepository.postalCurrentAccount();
    }

    @GetMapping("/tutorials/creditExpected")
    public double getCreditExpected(){
        return tutorialRepository.creditExpected();
    }

    @GetMapping("/tutorials/expectedFlow")
    public double getExpectedFlow(){
        return tutorialRepository.expectedFlow();
    }

    @GetMapping("/tutorials/otherValues")
    public double getOtherValues(){
        return tutorialRepository.otherValues();
    }

    @GetMapping("/tutorials/statesRepartition")
    public double getStatesRepartition(){
        return tutorialRepository.statesRepartition();
    }

    @GetMapping("/tutorials/totalRecipe")
    public double getTotalRecipeDouble() {
        return tutorialRepository.totalRecipe();
    }

    @GetMapping("/tutorials/recette-total")
    public String getRecetteTotal(@RequestParam("date") String date) {
        String sql = "SELECT SUM(t.balance_previous_month + t.recipe_today) AS recette_total " +
                "FROM tutorials t " +
                "WHERE title LIKE '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }

    // --------------------------------

    @GetMapping("/tutorials/OperationsTreasuryTotalLastDay")
    public double getTotalTreasuryOperationsLastDay(){
        return tutorialRepository.totalTreasuryOperationsLastDay();
    }

    @GetMapping("/tutorials/OperationsTreasury-total")
    public String getOperationsTreasuryTotal(@RequestParam("date") String date) {
        String sql = "SELECT SUM(t.operation_treasury_anterior + t.operation_treasury_today) AS Opération_Tésor_total\n" +
                "FROM tutorials t\n" +
                "where title like '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }
    //----------------------------------------------

    @GetMapping("/tutorials/OperationsRegulation-total")
    public String getOperationsRegulationTotal(@RequestParam("date") String date) {
        String sql = "SELECT SUM(t.operation_regulation_prior + t.operation_regulation_today) AS Opération_Règlement_total\n" +
                "FROM tutorials t\n" +
                "where title like '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }

    @GetMapping("/tutorials/OperationsRegulationsTotalDoubleLastRow")
    public double getTotalOperationsRegulationsLastRow(){
        return tutorialRepository.totalOperationsRegulationsLastDay();
    };

    //-------------------------------------

    @GetMapping("/tutorials/totalExpenses")
    public String getTotalExpenses(@RequestParam("date") String date) {
        String sql = "SELECT SUM(t.operation_treasury_anterior + t.operation_treasury_today +\n" +
                "           t.operation_regulation_prior + t.operation_regulation_today)\n" +
                "    AS Dépenses_total\n" +
                "FROM tutorials t\n" +
                "where title like '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }

//----------------------------------
    @GetMapping("/tutorials/dalyAccountBalance")
    public String getDalyAccountBalance(@RequestParam("date") String date) {
        String sql = "SELECT ((t.recipe_today+t.balance_previous_month)-  (t.operation_treasury_anterior + t.operation_treasury_today +\n" +
                "           t.operation_regulation_prior + t.operation_regulation_today))\n" +
                "    AS Dépenses_total\n" +
                "FROM tutorials t\n" +
                "where title like '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }

//------------------------------
    @GetMapping("/tutorials/finalPostalAccount")
    public String getFinalPostalAccount(@RequestParam("date") String date) {
        String sql = "SELECT ((t.postal_current_account + t.credit_expected) - t.expected_flow)\n" +
                "    AS Final_Postal_Account\n" +
                "FROM tutorials t\n" +
                "where title like '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }

//------------------------------------------------
    @GetMapping("/tutorials/totalCash")
    public String getTotalCash(@RequestParam("date") String date) {
        String sql = "SELECT ((t.recipe_today+t.balance_previous_month)-  (t.operation_treasury_anterior + t.operation_treasury_today +\n" +
                "                t.operation_regulation_prior + t.operation_regulation_today)) -\n" +
                "       (((t.postal_current_account + t.credit_expected) - t.expected_flow)+ t.other_values+t.states_repartition)\n" +
                "                AS Dépenses_total\n" +
                "                FROM tutorials t\n" +
                "                where title like '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }

//-------------------------------------------
    @GetMapping("/tutorials/moneyInCoinsInCash")
    public String getMoneyInCoinsInCash(@RequestParam("date") String date) {
        String sql = "SELECT (((t.recipe_today+t.balance_previous_month)-  (t.operation_treasury_anterior + t.operation_treasury_today +\n" +
                "                t.operation_regulation_prior + t.operation_regulation_today)) -\n" +
                "       (((t.postal_current_account + t.credit_expected) - t.expected_flow)+ t.other_values+t.states_repartition))-t.money_species\n" +
                "                AS Dépenses_total\n" +
                "                FROM tutorials t\n" +
                "                where title like '%" + date + "%'";

        return jdbcTemplate.queryForObject(sql, String.class);
    }


}
