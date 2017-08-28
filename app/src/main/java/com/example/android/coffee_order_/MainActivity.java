package com.example.android.coffee_order_;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=0;
    //  EditText name;

    public void decrement(View view){
        if(quantity<=0)
        {
            Toast.makeText(this, "You cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
        }
        else
        {
            quantity = quantity - 1;
            display(quantity);
            return;
        }
    }

    public void increment(View view)
    {
        if(quantity==100)
        {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }else
        {
            quantity = quantity + 1;
            display(quantity);
            return;
        }
    }

    private void displayPrice(String number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(number);

    }

    public void submitOrder(View View) {

        EditText nameField =(EditText)findViewById(R.id.name_field);
        String name =nameField.getText().toString() ;


        CheckBox WhippedCreamCheckBox=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=WhippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox=(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate=chocolateCheckBox.isChecked();
        int price=calculatePrice(hasWhippedCream ,hasChocolate);


       displayPrice(createOrderSummary(name,price,hasWhippedCream,hasChocolate));
        createOrderSummary(name,price,hasWhippedCream,hasChocolate);


        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"just java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(name,price,hasWhippedCream,hasChocolate));
        if(intent.resolveActivity(getPackageManager())!=null);
        {
            startActivity(intent);
        }

    }




    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    private int calculatePrice(boolean addWhippedCream,boolean addChocolate){

        int basePrice=5;

        if(addWhippedCream){
            basePrice=basePrice+1;
        }
        if(addChocolate){

            basePrice=basePrice+2;
        }
        return quantity*basePrice;
    }



   private String createOrderSummary(String name,int price , boolean addWhippedCream ,boolean addChocolate){
       String PriceMessage = "name: " + name;
        PriceMessage +="\n Add Whipped cream?" + addWhippedCream;
        PriceMessage +="\n Add Whipped cream?" + addChocolate;
        PriceMessage += "\n Quantity:" + quantity;
     PriceMessage +="\n Total : $" + price;
        PriceMessage +="\nThank you";
        return  PriceMessage;
  }
//this method is called when the order button is clicked

}

