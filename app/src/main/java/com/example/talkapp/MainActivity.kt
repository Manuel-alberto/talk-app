package com.example.talkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.example.talkapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener{

    private lateinit var binding:ActivityMainBinding

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tts= TextToSpeech(this, this)

        binding.btn.setOnClickListener{
            speak()
        }

    }

    private fun speak(){
        val message:String = binding.edtInput.text.toString().trim()
        if (message.isEmpty()) {
            binding.tvStatus.text=getString(R.string.s_ttsactive)

        }else{
            tts!!.speak(message, TextToSpeech.QUEUE_FLUSH,null, "")
        }
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            binding.tvStatus.text=getString(R.string.s_ttsactivet)
            tts!!.language = Locale("ES")
        }else{
            binding.tvStatus.text=getString(R.string.s_sttdisable)
        }
    }

    override fun onDestroy() {
        if (tts!=null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}