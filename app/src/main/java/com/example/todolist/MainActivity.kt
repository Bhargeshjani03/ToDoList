package com.example.todolist

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var item:EditText
    private lateinit var add:Button
    private lateinit var listView: ListView
    private var itemList=ArrayList<String>()
    private var fileHelper=FileHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        item=findViewById(R.id.editText)
        add=findViewById(R.id.button2)
       listView=findViewById(R.id.ListView)
        itemList=fileHelper.readData(this)
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList)
        listView.adapter=arrayAdapter
        add.setOnClickListener {
            val itemName:String=item.text.toString()
            itemList.add(itemName)
            item.setText("")
            fileHelper.writeData(itemList,applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }
     listView.setOnItemClickListener { parent, view, position, id ->
         val alert=MaterialAlertDialogBuilder(this)
         alert.setTitle("Delete")
         alert.setMessage("Are you sure you want to delete this item??")
         alert.setCancelable(false)
         alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
             dialog.cancel()
         })
         alert.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, which ->
             itemList.removeAt(position)
             arrayAdapter.notifyDataSetChanged()
             fileHelper.writeData(itemList,applicationContext)

         })
         alert.create().show()
     }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}