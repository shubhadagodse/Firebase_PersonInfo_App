package com.firebase.firebasepersoninfoapp.Helper;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.widget.EditText;

public class InputValidation {

    Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    //method to check empty input
    public boolean isInputEmpty(EditText et, TextInputLayout textInputLayout, String message) {
        String value = et.getText().toString().trim();
        if(value.isEmpty()) {
            textInputLayout.setError(message);
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return false;
    }

    //email validation
    public boolean isValidEmail(EditText et,TextInputLayout textInputLayout,String message) {
        String value = et.getText().toString().trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return false;
    }
}
