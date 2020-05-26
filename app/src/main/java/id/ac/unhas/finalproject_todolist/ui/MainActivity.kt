package id.ac.unhas.finalproject_todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import id.ac.unhas.finalproject_todolist.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ic_checkbox.startAnimation(AnimationUtils.loadAnimation(this,
            R.anim.splash_in
        ))
        Handler().postDelayed({
            ic_checkbox.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.splash_out
            ))
            Handler().postDelayed({
                ic_checkbox.visibility = View.GONE
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }, 500)
        }, 1500)

    }
}