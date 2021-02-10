package com.phoenixwings7.shoppinglisterassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewListActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 123;
    public static final String RESULT_TITLE = "title";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        // Set response for saving button
        Button savingBtn = findViewById(R.id.save_btn);
        savingBtn.setOnClickListener(this::submitNewList);
    }

    private void submitNewList(View view) {
        Bundle result = new Bundle();
        EditText titleEditText = findViewById(R.id.list_title);
        String listTitle = titleEditText.getText().toString();

        if (listTitle == null) {
            // provide default title
            listTitle = getString(R.string.label_list_title);
        }

        // provide results to parent Activity
        result.putString(RESULT_TITLE, listTitle);
        setResult(RESULT_OK, new Intent().putExtras(result));

        finish();
    }
}
