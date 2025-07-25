package com.example.matchmate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matchmate.ui.match.MatchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load MatchFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MatchFragment())
            .commit()
    }
}
