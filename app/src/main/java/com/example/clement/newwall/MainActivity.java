package com.example.clement.newwall;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    String imgFrmServer = "http://192.168.1.4/one.jpg";
    TextView textView1;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        button= (Button)findViewById(R.id.btn);
        imageView=(ImageView)findViewById(R.id.imageView);
        textView1=(TextView)findViewById(R.id.name);
        textView2=(TextView)findViewById(R.id.age);
        button.setOnClickListener(new View.OnClickListener() {

           // RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
            @Override
            public void onClick(View v) {


                final ImageRequest imageRequest= new ImageRequest(imgFrmServer, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                                imageView.setImageBitmap(response);
                                // requestQueue.stop();
                    }
                }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"some error occured",Toast.LENGTH_SHORT).show();
                        //requestQueue.stop();

                    }
                });
                //requestQueue.add(imageRequest);
                Singleton.getmInstance(MainActivity.this).addtorequestQueue(imageRequest);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
