package com.shutovleonid.mtuci_mobile_client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.shutovleonid.mtuci_mobile_client.operations.Add;
import com.shutovleonid.mtuci_mobile_client.operations.Divide;
import com.shutovleonid.mtuci_mobile_client.operations.Multiply;
import com.shutovleonid.mtuci_mobile_client.operations.Operation;
import com.shutovleonid.mtuci_mobile_client.operations.Subtract;

import org.jetbrains.annotations.NotNull;


public final class MainActivity extends AppCompatActivity {
    @NotNull
    private String oldValue = "";
    private boolean isNewOp = true;

    @NotNull
    public final String getOldValue() {
        return this.oldValue;
    }

    public final void setOldValue(@NotNull String var1) {
        this.oldValue = var1;
    }

    public final boolean isNewOp() {
        return this.isNewOp;
    }

    public final void setNewOp(boolean var1) {
        this.isNewOp = var1;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
    }

    public final void onOperation(@NotNull View view) {
        this.oldValue = ((EditText) findViewById(R.id.result)).getText().toString();
        this.isNewOp = true;
    }

    @NotNull
    public final Operation getOperationClass(@NotNull String operationString) {
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

    public final void onNumber(@NotNull View view) {
        EditText editText = findViewById(R.id.result);
        if (isNewOp) {
            editText.setText("");
        }
        isNewOp = false;
        editText.setText(editText.getText().append(((Button) view).getText().toString()));
    }

    public final void onEqual(@NotNull View view) {
        String newValue = ((EditText) findViewById(R.id.result)).getText().toString();
        String operationText = ((Button) view).getText().toString();
        Operation operationClass = getOperationClass(operationText);
        double finalResult = operationClass.performOperation(Double.parseDouble(newValue), Double.parseDouble(oldValue));
        ((EditText) findViewById(R.id.result)).setText(String.valueOf(finalResult));
        this.isNewOp = true;
    }
}
