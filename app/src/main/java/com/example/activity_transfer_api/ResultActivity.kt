package com.example.activity_transfer_api

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.activity_transfer_api.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            val intent= Intent()
            intent.putExtra("data","hello")
            //결과
            setResult(Activity.RESULT_OK,intent) // 이 인텐트와 결과상태 값을 시스템에게 넘겨주는 함수임.
            finish()
        }
    }
}