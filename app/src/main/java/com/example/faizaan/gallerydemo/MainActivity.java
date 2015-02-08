package com.example.faizaan.gallerydemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class MainActivity extends Activity {

    ArrayList<Character> operators;

    EditText res;
    String expr;
    char check;
    int ln;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = (EditText) findViewById(R.id.result);
        expr = res.getText().toString();
        operators = new ArrayList<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
    }

    public void addText(View v) {
        Button b = (Button) v;
        Editable str = res.getText();
        ln = str.length();
        check = b.getContentDescription().charAt(0);
        if (!(ln > 0 && operators.contains(str.charAt(ln - 1)) && operators.contains(check))) {
            expr = expr + b.getContentDescription().toString() + " ";
            str.append(b.getContentDescription());
            res.setText(str);
        }
    }

    public void calc(View v) {
        String out = convert(expr);
       /* out = out.replaceAll("\\s+","");*/
        int output = evaluate(out);
        res.setText(Integer.toString(output));
        expr = Integer.toString(output);
        //expr = ;*/
    }
    public static boolean isNumeric(String n){
        if(n.equals(null)) return false;
        for (char c : n.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    public int evaluate(String expr){
        //String[] token = Pattern.compile("\\s+").split(expr);
        int a = -1, b = -1;
        for (int n = 1 ; n < expr.length(); n++) {
            if (!Character.isDigit(expr.charAt(n))) {
                switch (expr.charAt(n)) {
                    case '+':
                        a += b;
                        break;
                    case '-':
                        a -= b;
                        break;
                    case '*':
                        a *= b;
                        break;
                    case '/':
                        a /= b;
                        break;
                    default:
                        break;
                }
            } else if (a == -1) a = Character.digit(expr.charAt(n),10);
            else b = Character.digit(expr.charAt(n),10);
        }
        return a;
    }
    public String convert(final String infixExpression) {
        final String[] tokens = Pattern.compile("\\s+").split(infixExpression);
        final Stack<String> operatorStack = new Stack<>();
        final StringBuilder result = new StringBuilder();
        for (final String token : tokens) {
            if (token.equals("(")) operatorStack.push(token);
            else if (token.equals(")")) {
                String operator;
                while (!"(".equals(operator = operatorStack.pop())) {
                    append(result, operator);
                }
            } else if (Operator.isOperator(token)) {
                if (!operatorStack.isEmpty() && Operator.isPrecedent(token, operatorStack.peek())) {
                    append(result, operatorStack.pop());
                }
                operatorStack.push(token);
            } else append(result, token);
        }
        while (!operatorStack.isEmpty()) {
            append(result, operatorStack.pop());
        }
        return result.toString();
    }

    private static void append(final StringBuilder builder, String value) {
        builder.append(' ').append(value);
    }

    enum Operator {
        PLUS('+', 1, 2), MINUS('-', 1, 2), MULTIPLY('*', 3, 4), DIVIDE('/', 3, 4), EXPONENT('^', 6, 5);

        private final char value;
        private final int icpValue;
        private final int ispValue;

        Operator(char value, int icpValue, int ispValue) {
            this.value = value;
            this.icpValue = icpValue;
            this.ispValue = ispValue;
        }

        private final static Map<String, Operator> operatorMap = new HashMap<>();

        static {
            for (Operator operator : Operator.values()) {
                operatorMap.put(String.valueOf(operator.value), operator);
            }
        }

        private static Operator getOperator(final String operator) {
            return operatorMap.get(operator);
        }

        static boolean isOperator(final String operator) {
            return getOperator(operator) != null;
        }

        private static int getIcpValue(final String value) {
            final Operator operator = getOperator(value);
            return operator == null ? 0 : operator.icpValue;
        }

        private static int getIspValue(final String value) {
            final Operator operator = getOperator(value);
            return operator == null ? 0 : operator.ispValue;
        }

        static boolean isPrecedent(final String firstToken, final String secondToken) {
            return getIcpValue(firstToken) < getIspValue(secondToken);
        }

    }
}