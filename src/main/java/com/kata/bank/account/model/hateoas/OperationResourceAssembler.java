package com.kata.bank.account.model.hateoas;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kata.bank.account.controller.OperationController;
import com.kata.bank.account.model.dto.OperationDto;

@Component
public class OperationResourceAssembler extends ResourceAssemblerSupport<OperationDto, OperationResource> {

    public OperationResourceAssembler() {
        super(OperationController.class, OperationResource.class);
    }

    @Override
    public OperationResource instantiateResource(OperationDto operation) {
        return new OperationResource(operation);
    }

    @Override
    public OperationResource toResource(OperationDto operation) {
        return createResourceWithId(operation.getId(), operation);
    }

}
