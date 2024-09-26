package com.example.individual_7;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button changeThemeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Cargar el tema almacenado en preferencias
        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false);
        setTheme(isDarkTheme ? R.style.AppTheme_Dark : R.style.AppTheme_Light);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar VideoView y botón
        videoView = findViewById(R.id.videoView);
        changeThemeButton = findViewById(R.id.button);

        // Establecer la URL del video
        Uri videoUri = Uri.parse("https://www.w3schools.com/html/mov_bbb.mp4");
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mp -> videoView.start());

        // Configurar el botón para cambiar el tema
        changeThemeButton.setOnClickListener(v -> {
            // Alternar el tema
            boolean newTheme = !isDarkTheme;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDarkTheme", newTheme);
            editor.apply();

            // Reiniciar la actividad para aplicar el nuevo tema
            recreate();
        });
    }
}
