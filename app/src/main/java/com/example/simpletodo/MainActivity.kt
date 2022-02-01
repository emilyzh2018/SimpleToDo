package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils.readLines
import org.apache.commons.io.FileUtils.writeLines
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {


    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onlongClickListener = object : TaskItemAdapter.OnlongClickListener {
            override fun onItemLongClicked(position: Int) {
                // 1. remove the item from the list
                listOfTasks.removeAt(position)
                // 2. notify the adapter that the data set has changed
                adapter.notifyDataSetChanged()
                saveItems()

            }

        }
        loadItems()

        //look up recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(listOfTasks,onlongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val inputTextField=findViewById<EditText>(R.id.addTaskField)

        //set up button and input  field
        findViewById<Button>(R.id.button).setOnClickListener {
            // got a ref to the button and set on click listener
            //1. grab text user has inputted into @id/addtaskfield
            val userInputtedTask = inputTextField.text.toString()
            //2. add string to list of tasks: ListOfTasks
            listOfTasks.add(userInputtedTask)
            // notify the adapter that data has updated
            adapter.notifyItemInserted(listOfTasks.size-1)
            //3. reset text field
            inputTextField.setText("")

            saveItems() 

        }
   //     findViewById<Button>(R.id.button).setOnClickListener {
     //       Log.i("Caren","User clicked on button")
       // }
    }
    // save data user has inputted
    // save data by writting and reading from a file
    //get the file we need
    fun getDataFile(): File {
        return File(filesDir, "data.txt")
    }

    fun loadItems() {
        try {
            listOfTasks = org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException : IOException) {
            ioException.printStackTrace()
        }




    }
    //save items by writing them into our data file
    fun saveItems() {
        try {
            org.apache.commons.io.FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }


}