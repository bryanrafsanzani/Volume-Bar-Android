package org.bryanrafsanzani.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtLebar, edtPanjang, edtTinggi;
    Button btnCalculate;
    TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLebar = findViewById(R.id.edt_lebar);
        edtPanjang = findViewById(R.id.edt_panjang);
        edtTinggi = findViewById(R.id.edt_tinggi);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.txt_result);

        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btn_calculate){
            String inputLebar = edtLebar.getText().toString().trim();
            String inputPanjang = edtPanjang.getText().toString().trim();
            String inputTinggi = edtTinggi.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if(TextUtils.isEmpty(inputLebar)){
                isEmptyFields = true;
                edtLebar.setError("field lebar tidak boleh kosong");
            }

            if(TextUtils.isEmpty(inputPanjang)){
                isEmptyFields = true;
                edtPanjang.setError("field lebar tidak boleh kosong");
            }

            if(TextUtils.isEmpty(inputTinggi)){
                isEmptyFields = true;
                edtTinggi.setError("field lebar tidak boleh kosong");
            }

            Double lebar = toDouble(inputLebar);
            Double panjang = toDouble(inputPanjang);
            Double tinggi = toDouble(inputTinggi);

            if(lebar == null){
                isInvalidDouble = true;
                edtLebar.setError("Field ini harus berupa nomor yang valid");
            }

            if(panjang == null){
                isInvalidDouble = true;
                edtPanjang.setError("Field ini harus berupa nomor yang valid");
            }

            if(tinggi == null){
                isInvalidDouble = true;
                edtTinggi.setError("Field ini harus berupa nomor yang valid");
            }

            if(!isEmptyFields && !isInvalidDouble){
                double volume = panjang * lebar * tinggi;

                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    Double toDouble(String str){
        try{
            return Double.valueOf(str);
        }catch(NumberFormatException e){
            return null;

        }

    }
}
