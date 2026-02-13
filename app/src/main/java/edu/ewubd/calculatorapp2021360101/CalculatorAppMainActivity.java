package edu.ewubd.calculatorapp2021360101;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;

public class CalculatorAppMainActivity extends AppCompatActivity {

    private TextView txtExpression;

    private String currentExpression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_app_main);
        txtExpression = findViewById(R.id.txtExpression);
        initializeButtons();
    }

    private void initializeButtons() {

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnPower = findViewById(R.id.btnPower);
        Button btnPercentage = findViewById(R.id.btnPercentage);
        Button btnDot = findViewById(R.id.btnDot);
        Button btnOpenBracket = findViewById(R.id.btnOpenBracket);
        Button btnCloseBracket = findViewById(R.id.btnCloseBracket);
        Button btnFactorial = findViewById(R.id.btnFactorial);
        Button btnSqrt = findViewById(R.id.btnSqrt);
        Button btnLog10 = findViewById(R.id.btnLog10);
        Button btnAC = findViewById(R.id.btnAC);
        Button btnEquals = findViewById(R.id.btnEquals);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("9");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("+");
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("-");
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("×");
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("÷");
            }
        });
        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("^");
            }
        });
        btnPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("%");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression(".");
            }
        });
        btnOpenBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("(");
            }
        });
        btnCloseBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression(")");
            }
        });
        btnFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("!");
            }
        });
        btnSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("√");
            }
        });
        btnLog10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToExpression("log10(");
            }
        });
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearExpression();
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateExpression();
            }
        });
    }

    private void appendToExpression(String value) {
        currentExpression += value;
        txtExpression.setText(currentExpression);
    }

    private void clearExpression() {
        currentExpression = "";
        txtExpression.setText("");
    }

    private void evaluateExpression() {
        try {

            String expressionToEvaluate = currentExpression.replace("√", "sqrt(");
            if (expressionToEvaluate.contains("sqrt(")) {
                expressionToEvaluate += ")";
            }

            Expression expression = new Expression(expressionToEvaluate);
            double result = expression.calculate();

            if (Double.isNaN(result)) {
                Toast.makeText(this, "Invalid Expression", Toast.LENGTH_SHORT).show();
            } else {
                currentExpression = String.valueOf(result);
                txtExpression.setText(currentExpression);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
