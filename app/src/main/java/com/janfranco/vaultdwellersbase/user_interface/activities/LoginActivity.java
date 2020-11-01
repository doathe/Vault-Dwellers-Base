package com.janfranco.vaultdwellersbase.user_interface.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.janfranco.vaultdwellersbase.R;
import com.janfranco.vaultdwellersbase.entities.AccessToken;
import com.janfranco.vaultdwellersbase.entities.Dtos.UserLoginDto;
import com.janfranco.vaultdwellersbase.helpers.ResultCallback;
import com.janfranco.vaultdwellersbase.helpers.TokenHelper;
import com.janfranco.vaultdwellersbase.helpers.UnknownTypeException;
import com.janfranco.vaultdwellersbase.services.SharedPreferencesService;
import com.janfranco.vaultdwellersbase.services.api.MarinaService;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email_text_view)
    EditText mailEditText;

    @BindView(R.id.login_password_text_view)
    EditText passwordText;

    @BindString(R.string.shared_pref_file_key)
    String fileKey;

    @BindString(R.string.shared_pref_token_key)
    String tokenKey;

    @BindString(R.string.shared_pref_expiration_key)
    String expirationKey;

    private MarinaService mMarinaService;
    private SharedPreferencesService sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setup();
        checkTokenExpiration();
    }

    private void setup() {
        ButterKnife.bind(this);
        mMarinaService = MarinaService.getInstance();
        sharedPref = new SharedPreferencesService(this, fileKey);
    }

    private void checkTokenExpiration() {
        try {
            long expirationDate = sharedPref.readData(expirationKey, (long) 0);
            TokenHelper tokenHelper = new TokenHelper();
            if (!tokenHelper.checkTokenExpiration(expirationDate))
                navigateHomeActivity();
        } catch (UnknownTypeException ignored) { }
    }

    @OnClick(R.id.login_login_button) void loginButtonClicked() {
        String email = mailEditText.getText().toString();
        String password = passwordText.getText().toString();

        if (!checkInputs(email, password))
            return;

        clearInputs();
        login(new UserLoginDto(email, password));
    }

    private boolean checkInputs(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    private void clearInputs() {
        mailEditText.setText("");
        passwordText.setText("");
    }

    private void login(UserLoginDto userLoginDto) {
        mMarinaService.login(userLoginDto, new ResultCallback<AccessToken>() {
            @Override
            public void onSuccess(AccessToken data) {
                String token = data.getToken();
                int expiration = data.getExpiration();

                TokenHelper tokenHelper = new TokenHelper();
                long expirationDateInMilliseconds = tokenHelper.getExpirationDateInMilliseconds(expiration);

                try {
                    sharedPref.writeData(tokenKey, token);
                    sharedPref.writeData(expirationKey, expirationDateInMilliseconds);
                } catch (UnknownTypeException ignored) { }

                navigateHomeActivity();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void navigateHomeActivity() {
        Intent toHomeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(toHomeIntent);
        finish();
    }

}
