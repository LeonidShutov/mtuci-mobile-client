package com.shutovleonid.mtuci_mobile_client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.shutovleonid.mtuci_mobile_client.operations.Add;
import com.shutovleonid.mtuci_mobile_client.operations.Divide;
import com.shutovleonid.mtuci_mobile_client.operations.Multiply;
import com.shutovleonid.mtuci_mobile_client.operations.Operation;
import com.shutovleonid.mtuci_mobile_client.operations.Subtract;


public final class MainActivity extends AppCompatActivity {

    private String oldValue = "";
    private boolean isNewOp = true;
    private String currentOp;

    public final String getOldValue() {
        return oldValue;
    }

    public final void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public final boolean isNewOp() {
        return isNewOp;
    }

    public final void setNewOp(boolean newOp) {
        isNewOp = newOp;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
    }

    public final void onOperation(View view) {
        this.oldValue = ((EditText) findViewById(R.id.result)).getText().toString();
        currentOp = ((Button) view).getText().toString();
        this.isNewOp = true;
    }

    public final Operation getOperationClass(String operationString) {
        switch (operationString) {
            case "+":
                return new Add();
            case "-":
                return new Subtract();
            case "*":
                return new Multiply();
            case "/":
                return new Divide();
            default:
                return new Add();
        }
    }

    public final void onNumber(View view) {
        EditText editText = findViewById(R.id.result);
        if (isNewOp) {
            editText.setText("");
        }
        isNewOp = false;
        editText.setText(editText.getText().append(((Button) view).getText().toString()));
    }

    public final void onEqual(View view) {
        String newValue = ((EditText) findViewById(R.id.result)).getText().toString();
        Operation operationClass = getOperationClass(currentOp);
        double finalResult = operationClass.performOperation(Double.parseDouble(oldValue), Double.parseDouble(newValue));
        ((EditText) findViewById(R.id.result)).setText(String.valueOf(finalResult));
        this.isNewOp = true;
    }

    public void onAc(View view) {
        ((EditText) findViewById(R.id.result)).setText(String.valueOf("0"));
        isNewOp = true;
    }

    public void onPercent(View view) {
        String text = ((EditText) findViewById(R.id.result)).getText().toString();
        Double number = Double.parseDouble(text) / 100;
        ((EditText) findViewById(R.id.result)).setText(number.toString());
        isNewOp = true;
    }

    public void sendToServer(View view) {
        Toast.makeText(getApplicationContext(), "Sending data to server", Toast.LENGTH_LONG).show();
    }

    public void getFromServer(View view) {
        Toast.makeText(getApplicationContext(), "Retrieving data from server", Toast.LENGTH_LONG).show();
    }
}
