package ir.tosan.projects.modules.cmm.backend.api;

import ir.tosan.projects.modules.cmm.backend.api.dto.in.CustomerAddParam;
import ir.tosan.projects.modules.cmm.backend.api.dto.in.CustomerChangeStatusParam;
import ir.tosan.projects.modules.cmm.backend.api.dto.out.*;

public interface CustomerManagerInterface {

    CustomerAddResult addCustomer(CustomerAddParam addParam);

    CustomerGetResult getCustomer(Long code);

    CustomerDeleteResult deleteCustomer(Long code);

    CustomerUpdateResult changeStatus(Long code, CustomerChangeStatusParam param);

    CustomersList getAllCustomersList();

}
