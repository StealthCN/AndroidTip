package com.example.tip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipActivity extends Fragment implements TextWatcher, OnClickListener 
{
	private double bill = 0;
	private double rate = 15;
	private int split = 1;
	
	private double tip;
	private double total;
	private double each;
	
	EditText billTextView;
	EditText rateTextView;
	EditText splitTextView;
	
	TextView tipTextView;
	TextView totalTextView;
	TextView eachTextView;
	
	Button resetButton;

	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)  
	{
	    View view = inflater.inflate(R.layout.tip, container, false);  
		billTextView = (EditText)view.findViewById(R.id.bill);
		rateTextView = (EditText)view.findViewById(R.id.rate);
		splitTextView = (EditText)view.findViewById(R.id.split);
		tipTextView = (TextView)view.findViewById(R.id.tip);
		totalTextView = (TextView)view.findViewById(R.id.total);
		eachTextView = (TextView)view.findViewById(R.id.each);
		
		resetButton = (Button)view.findViewById(R.id.reset);

/*		if(billTextView.getText().length() > 0)
			bill = Double.parseDouble(billTextView.getText().toString());
		rate = Double.parseDouble(rateTextView.getText().toString()); 
		split = Integer.parseInt(splitTextView.getText().toString()); */

		billTextView.addTextChangedListener(this);
		rateTextView.addTextChangedListener(this);
		splitTextView.addTextChangedListener(this);	
		
		resetButton.setOnClickListener(this);
	    return view;
	}
	
/*    public void resetClickHandler(View target) 
    {
    	billText = (EditText)target.findViewById(R.id.bill);
		rateText = (EditText)target.findViewById(R.id.rate);
		splitText = (EditText)target.findViewById(R.id.split);
		tipText = (TextView)target.findViewById(R.id.tip);
		totalText = (TextView)target.findViewById(R.id.total);
		eachText = (TextView)target.findViewById(R.id.each);
    }*/


	@Override
	public void afterTextChanged(Editable s) {}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) 
	{	
		getInput();
		
    	calTip(bill, rate, split);
		
    	tipTextView.setText(String.format("%.2f", tip));
    	totalTextView.setText(String.format("%.2f", total));
    	eachTextView.setText(String.format("%.2f", each));
	}
	
	
	private void calTip(double bill, double rate, int split)
	{
		tip = bill*(rate/100);
		total = bill + tip;
		if(split > 0)
			each = total/split;
		else
			each = 0.00;
	}
	
	private void getInput()
	{
/*		billTextView = (EditText)getView().findViewById(R.id.bill);
		rateTextView = (EditText)getView().findViewById(R.id.rate);
		splitTextView = (EditText)getView().findViewById(R.id.split);*/
		
		if(billTextView.getText().length() > 0)
			bill = Double.parseDouble(billTextView.getText().toString());
		if(rateTextView.getText().length() > 0)
			rate = Integer.parseInt(rateTextView.getText().toString()); 
		if(splitTextView.getText().length() > 0)
			split = Integer.parseInt(splitTextView.getText().toString()); 
	}

	@Override
	public void onClick(View view) 
	{
		if(view.equals(resetButton))
		{
			billTextView.setText("");
			rateTextView.setText("");
			splitTextView.setText("");
			
			tipTextView.setText("0.00");
			totalTextView.setText("0.00");
			eachTextView.setText("0.00");
		}
	}
}
