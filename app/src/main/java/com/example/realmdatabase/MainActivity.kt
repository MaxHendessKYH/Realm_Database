package com.example.realmdatabase

import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.realmdatabase.databinding.ActivityMainBinding
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

// About realm
// OOP DATABASE
//donde esta la biblioteka from mongoDB
//ACID Compliant ( Atomicity ,consisteny , isolation , durability)
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MyViewModel

    var selectedItemPosition = -1
    var currentItem: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        viewModel.getAllUsers().observe(this){ users ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
            binding.lvUsers.adapter = adapter
        }

        binding.lvUsers.onItemClickListener= AdapterView.OnItemClickListener { parent, view, position, id ->
            if(selectedItemPosition != -1)
            {
                val tempPos = parent.getChildAt(selectedItemPosition)
                tempPos.setBackgroundColor(Color.TRANSPARENT)
            }

            selectedItemPosition = position
            currentItem = parent.getItemAtPosition(position) as User
            view.setBackgroundColor(Color.GREEN)
        }
        binding.lvUsers.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as User
            deleteUser(selectedItem)
        }

        binding.btnAdd.setOnClickListener{
            addUser(null)
        }

        binding.btnUpdate.setOnClickListener {
            if(currentItem!= null) {
               addUser(currentItem)
            }
        }
        }
    fun deleteUser(user: User): Boolean
    {
         viewModel.deleteUser(user)
        return true
    }
    fun addUser(u: User?) {
            try {
                // Create & Add User Instance
                val user = User()

                if(u!= null)
                {
                    //Update
                    user._id = u._id
                }
                user.name = binding.etName.text.toString()
                user.score = binding.etScore.text.toString().toDouble()
                user.isHuman = binding.switchIshooman.isChecked
                println(user)
                viewModel.addUser(user)

                // Clear fields
                binding.etName.text.clear()
                binding.etScore.text.clear()

            } catch (e: Exception) {
                print(e.stackTrace)
            }

    }
}
