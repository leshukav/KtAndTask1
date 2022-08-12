package ru.netology.mynmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.mynmedia.R
import ru.netology.mynmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    object Contract : ActivityResultContract<Unit, String?>() {
        override fun createIntent(context: Context, input: Unit): Intent =
            Intent(context, NewPostActivity::class.java)


        override fun parseResult(resultCode: Int, intent: Intent?): String? =
            if (resultCode == Activity.RESULT_OK) {
                intent?.getStringExtra(Intent.EXTRA_TEXT)
            } else {
                null
            }

    }
}