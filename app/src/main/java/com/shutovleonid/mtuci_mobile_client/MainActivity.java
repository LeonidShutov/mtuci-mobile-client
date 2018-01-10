package com.shutovleonid.mtuci_mobile_client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shutovleonid.mtuci_mobile_client.operations.*;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public final class MainActivity extends AppCompatActivity {

    private String oldValue = "";
    private boolean isNewOp = true;
    private String currentOp;
    private APIService mAPIService = ApiUtils.getAPIService();
    private String android_id = UUID.randomUUID().toString();

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
        Toast.makeText(getApplicationContext(), "Sending data to server", Toast.LENGTH_SHORT).show();
        sendPost(((EditText) findViewById(R.id.result)).getText().toString());
    }

    private void sendPost(String s) {
        mAPIService.savePost(android_id, s).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.i("", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("", "Unable to submit post to API.");
            }
        });
    }


    public void getFromServer(View view) {
        Toast.makeText(getApplicationContext(), "Retrieving data from server", Toast.LENGTH_SHORT).show();
        mAPIService.getPost(android_id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    ((EditText) findViewById(R.id.result)).setText(response.body());
                    Log.i("", "post submitted to API." + response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("", "Unable to submit post to API.");
            }
        });
    }
}
