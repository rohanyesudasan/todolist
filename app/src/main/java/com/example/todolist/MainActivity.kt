package com.example.todolist

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edittext1: EditText
    private lateinit var addbtn: Button
    private lateinit var tasklistview: ListView
    private lateinit var taskList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittext1 = findViewById(R.id.edittext1)
        addbtn = findViewById(R.id.addbtn)
        tasklistview = findViewById(R.id.tasklistview)
        taskList = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, taskList)

        tasklistview.adapter = adapter

        addbtn.setOnClickListener {
            val task = edittext1.text.toString().trim()
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                edittext1.setText("")
            } else {
                Toast.makeText(this@MainActivity, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        tasklistview.setOnItemClickListener { adapterView, view, position, id ->
            val listView = adapterView as ListView
            val isChecked = listView.isItemChecked(position)
            listView.setItemChecked(position, isChecked)
        }

        tasklistview.setOnItemLongClickListener { adapterView, view, position, id ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }
    }
}
