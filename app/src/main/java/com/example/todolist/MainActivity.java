package com.example.todolist; // Replace with your actual package name

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener((adapter, item, pos, id) -> {
            items.remove(pos);
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Item removed", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    public void onAddItem(View v) {
        EditText etNewItem = findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        if (!itemText.isEmpty()) {
            items.add(itemText);
            itemsAdapter.notifyDataSetChanged();
            etNewItem.setText("");
        } else {
            Toast.makeText(this, "Please enter an item", Toast.LENGTH_SHORT).show();
        }
    }
}
