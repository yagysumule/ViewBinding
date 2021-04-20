package com.codewithyagy.mylistview

import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HeroAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var heroes = arrayListOf<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.lv_list)
        adapter = HeroAdapter(this) // Parameter this yang merupakan context dari activity
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@MainActivity, heroes[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    // Metode untuk inisialisasi array
    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    // Metode untuk memasukkan data ke arraylist supaya bisa diproses oleh adapter
    private fun addItem() {
        // Membuat perulangan dan menggunakan model untuk setter setiap data
        for (position in dataName.indices) {
            val hero = Hero(
                    dataPhoto.getResourceId(position, -1),
                    dataName[position],
                    dataDescription[position]
            )
            /*
            Untuk memasukkan ke arraylist,
             */
            heroes.add(hero)
        }

        // lalu memanggil setter yang berada di adapter dan memasukkan arraylist heroes sebagai argumen
        adapter.heroes = heroes
    }
}