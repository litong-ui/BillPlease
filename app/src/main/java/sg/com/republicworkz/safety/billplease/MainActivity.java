package sg.com.republicworkz.safety.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmt, etPax, etDiscount;
    ToggleButton tbtnSvs,tbtnGst;
    Button btnSplit,btnReset;
    TextView tvTotal,tvEach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.editInputAmt);
        etPax = findViewById(R.id.editInoutNumPax);
        tbtnSvs = findViewById(R.id.tbSvs);
        tbtnGst = findViewById(R.id.tbGst);
        etDiscount = findViewById(R.id.editInputDiscount);
        btnSplit = findViewById(R.id.split);
        btnReset = findViewById(R.id.reset);
        tvTotal = findViewById(R.id.textTotal);
        tvEach = findViewById(R.id.textEach);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAmt.getText().toString().trim().length() == 0) {
                    return;
                }
                if (etPax.getText().toString().trim().length() == 0) {
                    return;
                }
                double amt = Double.parseDouble( etAmt.getText().toString().trim() );
                if(tbtnSvs.isChecked() && tbtnGst.isChecked()){
                    amt *= 1.17;
                }else if(tbtnSvs.isChecked() && !tbtnGst.isChecked()){
                    amt *= 1.1;
                }else if(!tbtnSvs.isChecked() && tbtnGst.isChecked()){
                    amt *= 1.07;
                }
                if(etDiscount.getText().toString().trim().length()>0){
                    double discount = Double.parseDouble( etDiscount.getText().toString().trim());
                    amt = amt * (1-discount/100.0);
                }

                int pax = Integer.parseInt(etPax.getText().toString().trim());
                tvTotal.setText("Total: $" + String.format("%.2f", amt));;
                tvEach.setText("Total: $" + String.format("%.2f", amt/pax));

            }

        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmt.setText("");
                etPax.setText("");
                etDiscount.setText("");
            }
        });
    }
}
