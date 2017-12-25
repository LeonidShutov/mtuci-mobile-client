package com.shutovleonid.mtuci_mobile_client.operations;



public class Multiply implements Operation {
    @Override
    public double performOperation(double op1, double op2) {
        return op1 * op2;
    }
}
