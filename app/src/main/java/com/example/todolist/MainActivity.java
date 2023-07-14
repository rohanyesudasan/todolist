package com.example.todolist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edittext1;
    private Button addbtn;
    private ListView tasklistview;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext1 = findViewById(R.id.edittext1);
        addbtn = findViewById(R.id.addbtn);
        tasklistview = findViewById(R.id.tasklistview);
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, taskList);

        tasklistview.setAdapter(adapter);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = edittext1.getText().toString().trim();
                if (!task.isEmpty()) {
                    taskList.add(task);
                    adapter.notifyDataSetChanged();
                    edittext1.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tasklistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ListView listView = (ListView) adapterView;
                boolean isChecked = listView.isItemChecked(position);
                listView.setItemChecked(position, isChecked);
            }
        });

        tasklistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                taskList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }
}
