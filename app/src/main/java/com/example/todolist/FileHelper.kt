package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {
    private val fileName="listinfo.dat"
    fun writeData(item :ArrayList<String>,context: Context)
    {
        val fos:FileOutputStream=context.openFileOutput(fileName,Context.MODE_PRIVATE)
        val oas=ObjectOutputStream(fos)
        oas.writeObject(item)
        oas.close()
    }
    fun readData(context: Context):ArrayList<String>
    {

        var itemList:ArrayList<String>

        try {
            val fis:FileInputStream=context.openFileInput(fileName)
            val ois=ObjectInputStream(fis)
            itemList= ois.readObject() as ArrayList<String>

        }catch (e : FileNotFoundException){
            itemList=ArrayList()
        }
        return itemList
    }
}