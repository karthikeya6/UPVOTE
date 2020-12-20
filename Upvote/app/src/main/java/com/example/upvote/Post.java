package com.example.upvote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Post extends AppCompatActivity {
    TextView t1,t2,t3;
    String st1,st2,st3;
    Button upvote;
    int u=0;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        imageView=findViewById(R.id.image);
        t1=findViewById(R.id.location12);
        t2=findViewById(R.id.date12);
        t3=findViewById(R.id.time12);
        upvote=findViewById(R.id.upvote);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            int res_image = bundle.getInt("my_image");
            imageView.setImageResource(res_image);
            st1 = bundle.getString("location");
            t1.setText(String.format("Location is %s", st1));
            st2=bundle.getString("date");
            t2.setText(String.format("Date is is %s", st2));
            st3=bundle.getString("time");
            t3.setText(String.format("Time is %s", st3));
        }
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u=u+1;
                Toast.makeText(Post.this, "Number of Upvotes:"+u, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
