package com.example.mirea_app.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mirea_app.R;
import com.example.mirea_app.core_interaction.CoreAPI;
import com.example.mirea_app.data.MySharedPreferences;

public class LoginFragment extends Fragment {

    EditText email;
    Button sng_in;
    final static String URL = "https://virtserver.swaggerhub.com";
    final static String PASS = "Th1$_1S_P0ssw@rd";
    String userEmail;
    Retrofit retrofit;
    CoreAPI core;
    Dialog dialog;
    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = view.findViewById(R.id.username);
        sng_in = view.findViewById(R.id.login);
        userEmail = MySharedPreferences.getInstance(getContext()).getString(MySharedPreferences.EMAIL_KEY, "");
        if (!userEmail.isEmpty())
            email.setText(userEmail);

        dialog = new Dialog(getActivity());

        dialog.setTitle(R.string.email_code);
        dialog.setContentView(R.layout.dialog_fragment);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        core = retrofit.create(CoreAPI.class);

        sng_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                {
                    Call<Void> rCall = core.register(PASS, email.getText().toString());
                    Log.d("RRR", rCall.request().toString());
                    rCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                userEmail = email.getText().toString();
                                callAcceptCodeMenu();
                                Log.d("RRR","Register " + response.code() + " " + userEmail);
                            }
                            else {
                                Toast.makeText(getContext(), R.string.register_failed, Toast.LENGTH_SHORT).show();
                                Log.e("RRR", response.message() + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                            if (t.hashCode() == 409)
                            {
                                core.login(PASS, email.getText().toString())
                                        .enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if(response.isSuccessful()){
                                                    userEmail = email.getText().toString();
                                                    callAcceptCodeMenu();
                                                    Log.d("RRR","log-in " + response.code());
                                                }
                                                else {
                                                    Toast.makeText(getContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                                                    Log.e("RRR",response.message() + response.code());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                Toast.makeText(getContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                                                Log.e("RRR",t.getMessage() + t.hashCode());
                                            }
                                        });
                            }
                            else {
                                Toast.makeText(getContext(), R.string.register_failed, Toast.LENGTH_SHORT).show();
                                Log.e("RRR",t.getMessage() + t.hashCode());
                            }
                        }
                    });
                }
                else
                    Toast.makeText(getContext(), R.string.nod_valid_email, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void callAcceptCodeMenu(){
        dialog.show();
        dialog.findViewById(R.id.accept_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = dialog.findViewById(R.id.accept_code);
                core.accept(PASS, text.getText().toString(), userEmail)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getContext(), "Молодец ты вошел пока как-то так", Toast.LENGTH_SHORT).show();
                                    MySharedPreferences.getInstance(getContext()).putString(MySharedPreferences.EMAIL_KEY, userEmail);
                                }
                                else {
                                    Log.e("RRR",response.message() + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getContext(), R.string.nod_valid_code, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}