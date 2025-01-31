package com.example.subidaarchivos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
     static final int PICK_FILE_REQUEST = 1;
     TextView textViewFileName;
     Button buttonSelectFile;
     Button buttonUpload;
     Uri selectedFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        textViewFileName = findViewById(R.id.textViewFileName);
        buttonSelectFile = findViewById(R.id.buttonSelectFile);
        buttonUpload = findViewById(R.id.buttonUpload);

        // Configurar el botón de selección de archivo
        buttonSelectFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, PICK_FILE_REQUEST);
        });

        // Configurar el botón de subida
        buttonUpload.setOnClickListener(v -> {
            if (selectedFileUri != null) {
                // Aquí iría la lógica para subir el archivo
                Toast.makeText(this, "Archivo correctamente subido", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedFileUri = data.getData();
            String fileName = selectedFileUri.getLastPathSegment();
            textViewFileName.setText(fileName);
            buttonUpload.setEnabled(true);
        }
    }
}