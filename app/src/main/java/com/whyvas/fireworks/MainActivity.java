package com.whyvas.fireworks;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToSpeech = new TextToSpeech(this, this);
        View view = findViewById(R.id.main_layout);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    if (event.getPointerCount() == 1) {
                        // String text = "Calisse de tabarnak d'ostie de criss";
                        // String text = "Tu chie des caps de roues pis tu su de la rai";
                        textToSpeech.setLanguage(Locale.UK);
                        textToSpeech.speak("1", TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                }
                if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
                    if (event.getPointerCount() == 2) {
                        textToSpeech.speak("2", TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                    if (event.getPointerCount() == 3) {
                        textToSpeech.speak("3", TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                    if (event.getPointerCount() == 4) {
                        textToSpeech.speak("4", TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                    if (event.getPointerCount() == 5) {
                        textToSpeech.speak("5, please wait, scanning initiated", TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                }


                // Return true to prevent the button from processing the touch.
                return true;


            }
        });
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.UK);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error", "This Language is not supported");
            } else {
                texttoSpeak();
            }
        } else {
            Log.e("error", "Failed to Initialize");
        }
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void texttoSpeak() {
        String text = "Hello Alexa, Welcome to the mobile fireworks launching system. Please scan your fingertips for authetication";
        //textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);

    }

}