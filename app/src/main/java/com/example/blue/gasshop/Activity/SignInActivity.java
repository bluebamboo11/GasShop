/**
 * Copyright Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.blue.gasshop.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blue.gasshop.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class SignInActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private EditText mEmailField;
    private EditText mPasswordField;

    private DatabaseReference connectedRef;
    private ValueEventListener valueEventListenerConnect;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";
    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final int REQUEST_ACOUT = 991;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;
    // Firebase instance variables
    private static boolean connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        getWindow().setBackgroundDrawableResource(R.drawable.cool);
        if (mFirebaseUser != null) {

            finish();
            overridePendingTransition(0, 0);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            return;
        }
        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
        setContentView(R.layout.activity_sign_in);
        mEmailField = (EditText) findViewById(R.id.field_email);

        mPasswordField = (EditText) findViewById(R.id.field_password);




        // Set click listeners


        // Configure Google Sign In


        signInMail();
        resetPass();
        checkConnect();
    }

    @Override
    public void onClick(View v) {
        if (true) {
            switch (v.getId()) {

                case R.id.email_sign_in_button:
                    signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
                    break;

                case R.id.resetpass:
                    String email = mEmailField.getText().toString();
                    if (!email.equals("")) {
                        openDialogResetPass(email);
                    } else {
                        mEmailField.setError(getString(R.string.nhapmail));
                    }
                    break;

        }} else {
            Toast.makeText(SignInActivity.this, R.string.disconect, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (connectionResult.hasResolution()) {
            try {
                mResolvingError = true;
                connectionResult.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);


            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        } else {
            // Show dialog using GoogleApiAvailability.getErrorDialog()
            showErrorDialog(connectionResult.getErrorCode());
            mResolvingError = true;
        }
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }



    }






    private void signSucces() {
finish();
        startActivity(new Intent(this, MainActivity.class));
    }


    private void signIn(String email, String password) {

        if (!validateForm()) {
            return;
        }
        showProgressDialog();

        // [START sign_in_with_email]
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            setErros(task);

                            hideProgressDialog();
                        } else {
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            assert firebaseUser != null;

                                signSucces();


                        }


                    }
                });

    }


    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError(getString(R.string.Required));
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError(getString(R.string.Required));
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        return valid;
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void signInMail() {
        Button buttonSign = (Button) findViewById(R.id.email_sign_in_button);

        buttonSign.setOnClickListener(this);

    }


    private void openDialogResetPass(final String email) {
        hideProgressDialog();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle(R.string.doimatkhau_title);

        alertDialogBuilder
                .setMessage(getString(R.string.doiMatKhau) + " " + mEmailField.getText().toString())
                .setCancelable(true)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                mFirebaseAuth.sendPasswordResetEmail(email);

                            }
                        })

                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();

                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }

    private void resetPass() {
        TextView textpass = (TextView) findViewById(R.id.resetpass);
        textpass.setOnClickListener(this);
    }



    private void setErros(Task<AuthResult> task) {
        try {
            throw task.getException();
        } catch (FirebaseAuthWeakPasswordException e) {
            mPasswordField.setError(getString(R.string.loipass));
            mPasswordField.requestFocus();
        } catch (FirebaseAuthInvalidCredentialsException e) {
            Log.e("err", e.toString());
            if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted.")) {
                mEmailField.setError(getString(R.string.loiEmailSai));
                mEmailField.requestFocus();
            } else {
                mPasswordField.setError(getString(R.string.loipass));
                mPasswordField.requestFocus();
            }
        } catch (FirebaseAuthInvalidUserException e) {
            mEmailField.setError(getString(R.string.loikhongCoMail));
            mEmailField.requestFocus();
        } catch (FirebaseAuthUserCollisionException e) {
            mEmailField.setError(getString(R.string.loiDaCoEmail));
            mEmailField.requestFocus();
        } catch (Exception e) {
            if (e.toString().equals("com.google.firebase.FirebaseException: An internal error has occurred. [ WEAK_PASSWORD  ]")) {
                mPasswordField.setError(getString(R.string.minpass));
                mPasswordField.requestFocus();
            } else {
                Toast.makeText(SignInActivity.this, R.string.auth_failed,
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "errordialog");
    }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public void onDialogDismissed() {
        mResolvingError = false;
    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GoogleApiAvailability.getInstance().getErrorDialog(
                    this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            ((SignInActivity) getActivity()).onDialogDismissed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectedRef.addValueEventListener(valueEventListenerConnect);

    }
    @Override
    protected void onStop() {
        super.onStop();
        connectedRef.removeEventListener(valueEventListenerConnect);

    }

    private void checkConnect() {
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        connectedRef = mFirebaseDatabaseReference.child(".info/connected");
        valueEventListenerConnect = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snapshot) {
                connected = snapshot.getValue(Boolean.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        };

    }
}

