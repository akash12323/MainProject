package com.example.mainproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main8.*
import kotlinx.android.synthetic.main.activity_main8.cirImgiew
import kotlinx.android.synthetic.main.activity_main8.imgView
import kotlinx.android.synthetic.main.activity_main8.tv1
import kotlinx.android.synthetic.main.activity_main8.*

class Main8Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        val name = intent.getStringExtra("name")
        val title = intent.getStringExtra("title")
        val knownfor1 = intent.getStringExtra("knownforTitle")
        val knownForOverview = intent.getStringExtra("knownforoverview")

        tv3.setText("NAME: "+name)
        tv4.setText(title+"("+knownfor1+"):-\n"+knownForOverview)

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+intent.getStringExtra("person")).into(imgView)
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+intent.getStringExtra("person")).into(cirImgiew)

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+intent.getStringExtra("posterpath")).into(imgView1)
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+intent.getStringExtra("backdroppath")).into(imgView2)
    }
}
