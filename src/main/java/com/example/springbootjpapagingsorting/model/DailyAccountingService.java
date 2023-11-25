package com.example.springbootjpapagingsorting.model;

import com.example.springbootjpapagingsorting.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DailyAccountingService {

    private final TutorialRepository tutorialRepository;

    public void createDailyAccounting(DailyAccountingRequest dailyAccountingRequest){
        LocalDateTime currentLocalDateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);

        Tutorial dailyAccounting = Tutorial.builder()
                .title("OperationDate is : " + formattedDateTime)
                .recipeToday(dailyAccountingRequest.getRecipeToday())
                .balancePreviousMonth(dailyAccountingRequest.getBalancePreviousMonth())
                .operationTreasuryAnterior(dailyAccountingRequest.getOperationTreasuryAnterior())
                .operationTreasuryToday(dailyAccountingRequest.getOperationTreasuryToday())
                .operationPreviousRegulation(dailyAccountingRequest.getOperationPreviousRegulation())
                .operationRegulationToday(dailyAccountingRequest.getOperationRegulationToday())
                .postCurrentAccount(dailyAccountingRequest.getPostCurrentAccount())
                .creditExpected(dailyAccountingRequest.getCreditExpected())
                .rateExpected(dailyAccountingRequest.getRateExpected())
                .otherValues(dailyAccountingRequest.getOtherValues())
                .statesRepartition(dailyAccountingRequest.getStatesRepartition())
                .moneySpecies(dailyAccountingRequest.getMoneySpecies())
                .build();
        tutorialRepository.save(dailyAccounting);

    }
}
