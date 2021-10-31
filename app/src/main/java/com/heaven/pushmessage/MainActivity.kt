package com.heaven.pushmessage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val resultTextView: TextView by lazy {
        findViewById(R.id.resultText)
    }

    private val firebaseTokenTextView: TextView by lazy {
        findViewById(R.id.firebaseTokenTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
        updateResult()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setIntent(intent)
        updateResult(true)
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                firebaseTokenTextView.text = task.result

                Log.d("firebase token", task.result.toString())
            }
        }
    }

    private fun updateResult(isNewIntent: Boolean = false) {
        resultTextView.text = intent.getStringExtra("notificationType") ?: "앱 런처" +
                if (isNewIntent) {
                    "(으)로 갱싱했스니다"
                } else {
                    "(으)로 실행했습니다"
                }
    }
}