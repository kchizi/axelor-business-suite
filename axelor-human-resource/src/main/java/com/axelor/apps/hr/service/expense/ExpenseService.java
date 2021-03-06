/**
 * Axelor Business Solutions
 *
 * Copyright (C) 2017 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.hr.service.expense;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.mail.MessagingException;

import com.axelor.apps.account.db.Invoice;
import com.axelor.apps.account.db.InvoiceLine;
import com.axelor.apps.account.db.Move;
import com.axelor.apps.hr.db.Expense;
import com.axelor.apps.hr.db.ExpenseLine;
import com.axelor.apps.message.db.Message;
import com.axelor.exception.AxelorException;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.persist.Transactional;

public interface ExpenseService  {

	public ExpenseLine createAnalyticDistributionWithTemplate(ExpenseLine expenseLine) throws AxelorException;
	
	public ExpenseLine computeAnalyticDistribution(ExpenseLine expenseLine) throws AxelorException;
	
	public Expense compute (Expense expense);

	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public void confirm(Expense expense) throws AxelorException;
	
	public Message sendConfirmationEmail(Expense expense) throws AxelorException, ClassNotFoundException, InstantiationException, IllegalAccessException, MessagingException, IOException;
	
	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public void validate(Expense expense) throws AxelorException;
	
	public Message sendValidationEmail(Expense expense) throws AxelorException, ClassNotFoundException, InstantiationException, IllegalAccessException, MessagingException, IOException;
	
	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public void refuse(Expense expense) throws AxelorException;

	public Message sendRefusalEmail(Expense expense) throws AxelorException, ClassNotFoundException, InstantiationException, IllegalAccessException, MessagingException, IOException;
		
	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public Move ventilate(Expense expense) throws AxelorException;

	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public void cancel (Expense expense) throws AxelorException;
	
	public Message sendCancellationEmail(Expense expense) throws AxelorException, ClassNotFoundException, InstantiationException, IllegalAccessException, MessagingException, IOException;

	@Transactional(rollbackOn = {AxelorException.class, Exception.class})
	public void addPayment(Expense expense) throws AxelorException;

	public List<InvoiceLine> createInvoiceLines(Invoice invoice, List<ExpenseLine> expenseLineList, int priority) throws AxelorException;

	public List<InvoiceLine> createInvoiceLine(Invoice invoice, ExpenseLine expenseLine, int priority) throws AxelorException;

	public void getExpensesTypes(ActionRequest request, ActionResponse response);
	
	@Transactional
	public void insertExpenseLine(ActionRequest request, ActionResponse response);
	
	public BigDecimal computePersonalExpenseAmount(Expense expense);
	public BigDecimal computeAdvanceAmount(Expense expense);

	public void setDraftSequence(Expense expense);
}
