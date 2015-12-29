package com.example.tip;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CopyOfTipActivity extends Activity implements TextWatcher
{
	private double bill;
	private double rate;
	private int split;
	
	private double tip;
	private double total;
	private double each;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.tip);
		
		EditText billText = (EditText)this.findViewById(R.id.bill);
		EditText rateText = (EditText)this.findViewById(R.id.rate);
		EditText splitText = (EditText)this.findViewById(R.id.split);

		bill = Double.parseDouble(billText.getText().toString());
		rate = Double.parseDouble(rateText.getText().toString()); 
		split = Integer.parseInt(splitText.getText().toString()); 

		billText.addTextChangedListener(this);
		rateText.addTextChangedListener(this);
		splitText.addTextChangedListener(this);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public void resetClickHandler(View target) 
    {
    	EditText billText = (EditText)this.findViewById(R.id.bill);
		EditText rateText = (EditText)this.findViewById(R.id.rate);
		EditText splitText = (EditText)this.findViewById(R.id.split);
		TextView tipText = (TextView)this.findViewById(R.id.tip);
		TextView totalText = (TextView)this.findViewById(R.id.total);
		TextView eachText = (TextView)this.findViewById(R.id.each);
		
		billText.setText("0.00");
		rateText.setText("15");
		splitText.setText("1");
		
		tipText.setText("0.00");
		totalText.setText("0.00");
		eachText.setText("0.00");
    }


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
    	
    	TextView tipText = (TextView)CopyOfTipActivity.this.findViewById(R.id.tip);
		TextView totalText = (TextView)CopyOfTipActivity.this.findViewById(R.id.total);
		TextView eachText = (TextView)CopyOfTipActivity.this.findViewById(R.id.each);
		
    	tipText.setText(String.format("%.2f", tip));
    	totalText.setText(String.format("%.2f", total));
    	eachText.setText(String.format("%.2f", each));
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
		EditText billText = (EditText)this.findViewById(R.id.bill);
		EditText rateText = (EditText)this.findViewById(R.id.rate);
		EditText splitText = (EditText)this.findViewById(R.id.split);
		
		if(billText.getText().length() > 0)
			bill = Double.parseDouble(billText.getText().toString());
		if(rateText.getText().length() > 0)
			rate = Integer.parseInt(rateText.getText().toString()); 
		if(splitText.getText().length() > 0)
			split = Integer.parseInt(splitText.getText().toString()); 
	}
}
