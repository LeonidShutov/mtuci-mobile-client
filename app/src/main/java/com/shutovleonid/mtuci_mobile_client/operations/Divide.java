package com.shutovleonid.mtuci_mobile_client.operations;

public class Divide implements Operation {
    @Override
    public double performOperation(double op1, double op2) {
        return op2 == 0.0 ? 0.0 : op1 / op2;
    }
}
