package com.ndexsystems.phoenix.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContextView {

    private UserView user;
    private OrganizationUnitView organizationUnit;
}
