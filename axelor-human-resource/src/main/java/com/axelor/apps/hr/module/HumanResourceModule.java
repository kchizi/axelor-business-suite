package com.axelor.apps.hr.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.account.service.config.AccountConfigService;
import com.axelor.apps.base.service.batch.MailBatchService;
import com.axelor.apps.hr.service.batch.MailBatchServiceHR;
import com.axelor.apps.hr.service.config.AccountConfigHRService;
import com.axelor.apps.hr.service.employee.EmployeeService;
import com.axelor.apps.hr.service.employee.EmployeeServiceImp;
import com.axelor.apps.hr.service.timesheet.TimesheetService;
import com.axelor.apps.hr.service.timesheet.TimesheetServiceImp;


public class HumanResourceModule extends AxelorModule {

	@Override
	protected void configure() {
		
		bind(EmployeeService.class).to(EmployeeServiceImp.class);
		bind(TimesheetService.class).to(TimesheetServiceImp.class);
		bind(MailBatchService.class).to(MailBatchServiceHR.class);
		bind(AccountConfigService.class).to(AccountConfigHRService.class);
	}

}