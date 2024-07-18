package vlu.android.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    View view;
    Button btnSignIn;
    EditText edtEmail, edtPassword;
    CheckBox ckcRemember;
    TextView txtForgotPassword, txtSignUpPrompt;
    FirebaseAuth auth;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        txtSignUpPrompt = view.findViewById(R.id.txtSignUpPrompt);
        txtForgotPassword = view.findViewById(R.id.txtForgotPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        ckcRemember = view.findViewById(R.id.ckcRemember);
        String text = "Don't have an account? Sign Up";

        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan greySpan = new ForegroundColorSpan(getResources().getColor(R.color.grey1));
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(getResources().getColor(R.color.blue));

        spannableString.setSpan(greySpan, 0, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(blueSpan, 23, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtSignUpPrompt.setText(spannableString);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    edtEmail.setError("Email is Required!");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    edtPassword.setError("Password is Required!");
                    return;
                }

                if (pass.length() < 6) {
                    edtPassword.setError("Password must be >= 6 characters!");
                    return;
                }

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Login in Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), HomeActivity.class));
                        } else {
                            Toast.makeText(getActivity(), "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        txtSignUpPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SplashActivity) getActivity()).loadFragment(new RegisterFragment());
            }
        });
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset password?");
                passwordResetDialog.setMessage("Enter your email to received reset link!");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetMail.getText().toString();
                        auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getActivity(), "Reset link sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Error! Reset link is not sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });

        return view;
    }
}