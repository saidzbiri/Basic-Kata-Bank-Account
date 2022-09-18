package com.kata.bank.account.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kata.bank.account.exception.InsufficientFundException;
import com.kata.bank.account.exception.OperationTypeNotSupportedException;
import com.kata.bank.account.mapper.Mapper;
import com.kata.bank.account.model.domain.Account;
import com.kata.bank.account.model.domain.Client;
import com.kata.bank.account.model.domain.Operation;
import com.kata.bank.account.model.dto.OperationCreationDto;
import com.kata.bank.account.repository.OperationRepository;
import com.kata.bank.account.service.impl.OperationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {

  @Mock
  private OperationRepository operationRepository;

  @Mock
  private AccountService accountService;

  @Spy
  private Mapper mapper;

  @InjectMocks
  private OperationService operationService = new OperationServiceImpl();

  @Captor
  ArgumentCaptor<Account> accountCaptor;

  private Client client = new Client(4545L, "said", "zbiri", "said@gmail.com");

  private static Long ACCOUNT_NUMBER = 1001L;
  private static double WITHDRAWAL_AMOUNT = 100d;
  private static double OPERATION_AMOUNT = 50d;
  private static double ACCOUNT_BALANCE = 10d;
  private static String UNKKNOWN_OPERATION_TYPE = "Clearing of Cheques";

  @Before
  public void setUp() {
    System.out.println("--------------> OperationServiceTest <--------------");
  }

  @Test
  public void should_save_operation() {
    OperationCreationDto operationRequest = new OperationCreationDto(1001L, "deposit", OPERATION_AMOUNT);
    Account account = new Account(1001L, ACCOUNT_BALANCE, client);

    Operation expectedOperation = Operation.builder()
        .operationType("deposit")
        .amount(OPERATION_AMOUNT)
        .date(new Date())
        .account(account)
        .build();

    double newBalance = ACCOUNT_BALANCE + OPERATION_AMOUNT;

    when(accountService.findByAccountNumber(ACCOUNT_NUMBER))
        .thenReturn(Optional.of(account));

    when(mapper.toOperation(operationRequest, account))
        .thenReturn(expectedOperation);

    doReturn(null)
        .when(accountService)
        .saveAccount(account);

    when(operationRepository.save(Mockito.any(Operation.class)))
        .thenReturn(expectedOperation);

    Operation insertedOperation = operationService.executeOperation(operationRequest);

    verify(accountService).saveAccount(accountCaptor.capture());
    Account savedAccount = accountCaptor.getValue();

    assertThat(savedAccount.getBalance(), is(newBalance));
    assertThat(insertedOperation, is(expectedOperation));
  }

  @Test(expected = InsufficientFundException.class)
  public void should_throw_insufficient_fund_when_trying_to_withdraw_amount_greater_than_account_balance() {
    OperationCreationDto operationRequest = new OperationCreationDto(1001L, "withdrawal", WITHDRAWAL_AMOUNT);
    Account account = new Account(1001L, ACCOUNT_BALANCE, client);

    when(accountService.findByAccountNumber(ACCOUNT_NUMBER))
        .thenReturn(Optional.of(account));

    operationService.executeOperation(operationRequest);
  }

  @Test(expected = OperationTypeNotSupportedException.class)
  public void should_throw_operation_type_not_supported_when_trying_to_execute_operation_of_unknown_type() {
    OperationCreationDto operationRequest = new OperationCreationDto(1001L, UNKKNOWN_OPERATION_TYPE, 50d);
    Account account = new Account(1001L, 0d, client);

    when(accountService.findByAccountNumber(ACCOUNT_NUMBER))
        .thenReturn(Optional.of(account));

    operationService.executeOperation(operationRequest);
  }

}
