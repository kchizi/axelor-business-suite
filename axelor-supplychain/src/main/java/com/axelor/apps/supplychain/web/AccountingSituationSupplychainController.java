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
package com.axelor.apps.supplychain.web;

import com.axelor.apps.account.db.AccountingSituation;
import com.axelor.apps.base.db.Partner;
import com.axelor.apps.base.db.repo.PartnerRepository;
import com.axelor.apps.sale.db.SaleOrder;
import com.axelor.apps.sale.db.repo.SaleOrderRepository;
import com.axelor.apps.supplychain.service.AccountingSituationSupplychainServiceImpl;
import com.axelor.exception.AxelorException;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class AccountingSituationSupplychainController {

    private PartnerRepository partnerRepo;
    private AccountingSituationSupplychainServiceImpl accountingSituationService;

    @Inject
    public AccountingSituationSupplychainController(AccountingSituationSupplychainServiceImpl accountingSituationService, PartnerRepository partnerRepo) {
        this.accountingSituationService = accountingSituationService;
        this.partnerRepo = partnerRepo;
    }

    public void computeUsedCredit(ActionRequest request, ActionResponse response) {
        AccountingSituation accountingSituation = request.getContext().asType(AccountingSituation.class);
        accountingSituation = accountingSituationService.computeUsedCredit(accountingSituation);
        response.setValues(accountingSituation);
    }

    public void updateCustomerCreditFromSaleOrder(ActionRequest request, ActionResponse response) throws AxelorException {
        SaleOrder saleOrder = request.getContext().asType(SaleOrder.class);
        saleOrder = Beans.get(SaleOrderRepository.class).find(saleOrder.getId());
        if (saleOrder.getClientPartner() != null) {
            Partner partner = saleOrder.getClientPartner();
            Map<String, Object> map = accountingSituationService.updateCustomerCreditFromSaleOrder(partnerRepo.find(partner.getId()), saleOrder);
            response.setValues(map);
        }
    }
    
}