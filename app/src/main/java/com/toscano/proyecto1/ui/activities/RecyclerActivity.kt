package com.toscano.proyecto1.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.toscano.proyecto1.R
import com.toscano.proyecto1.databinding.ActivityRecyclerBinding

/*
class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initRecyclerView(items: List<Data>){
        binding.rvTopNews.adapter = NewsAdapter(items) {showTitle(it)}
        binding.rvTopNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initData(){

        //Progress Indicators
        binding.prgbarData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            val resultItem = GetAllNewsCase().invoke()

            withContext(Dispatchers.Main){

                binding.prgbarData.visibility = View.INVISIBLE

                resultItem.onSuccess { initRecyclerView(it!!.toList()) }
                resultItem.onFailure { initRecyclerView(emptyList()) }
            }

        }
    }

    //Funciones Lambda
    private fun showTitle(news: Data){

        /*
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", news.uuid)
        }
        startActivity(intent)
         */
        Snackbar.make(binding.rvTopNews, news.title.toString(), Snackbar.LENGTH_LONG).show()
    }
}
 */

/*
class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private var items: MutableList<NewsDataUI> = mutableListOf()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()
        initData()
    }

    private fun initListeners(){
        binding.rfRecycler.setOnRefreshListener {
            initData()
            binding.rfRecycler.isRefreshing = false
        }

        binding.btnInsertar.setOnClickListener {
            addItem()
        }
    }

    private fun initVariables(){
        newsAdapter = NewsAdapter({descriptionItem(it)}, {deleteItem(it)})
        binding.rvTopNews.adapter = newsAdapter
        //Vista de Contenido en forma de cuadros
        binding.rvTopNews.layoutManager = GridLayoutManager(this, 2)
        //Vista de Contenido en forma de Cascada
        //binding.rvTopNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initData(){

        //Progress Indicators
        binding.prgbarData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            val resultItem = GetAllNewsCase().invoke()

            withContext(Dispatchers.Main){

                binding.prgbarData.visibility = View.INVISIBLE

                resultItem.onSuccess {
                    items = it.toMutableList()
                    newsAdapter.listItem = items
                    newsAdapter.notifyDataSetChanged()
                }

                resultItem.onFailure { Snackbar.make(binding.rfRecycler, it.message.toString(), Snackbar.LENGTH_LONG).show() }
            }

        }
    }

    //Borrar noticia
    private fun deleteItem(position: Int){

        items.removeAt(position)
        newsAdapter.listItem = items
        newsAdapter.notifyItemRemoved(position)
        newsAdapter.notifyDataSetChanged()

    }

    //Agregar noticia
    private fun addItem(){

        items.add(NewsDataUI("1", "www.google.com","Noticia Fake", "123456", "123456"))
        newsAdapter.listItem = items
        newsAdapter.notifyItemInserted(items.size - 1)
        newsAdapter.notifyDataSetChanged()

    }


    //Funciones Lambda
    private fun descriptionItem(news: NewsDataUI){

        Log.d("UUID", news.id)
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("id", news.id)
            putExtra("image", news.image)
            putExtra("title", news.name)
            putExtra("url", news.url)
            putExtra("description", news.description)
        }
        startActivity(intent)
    }
}
 */
class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initVariables()


    }

    private fun initListeners(){

        binding.bottomNavigation.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.favoriteItem -> {
                    navController.navigate(R.id.action_favoritesFragment_to_modifyFragment)
                    true
                }
                R.id.presentItem -> {
                    navController.navigate(R.id.action_favoritesFragment_to_listNewsFragment)
                    true
                }
                R.id.recommendItem -> {
                    //Snackbar.make(binding.rfRecycler, "Listado de Recomendadas", Snackbar.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun initVariables(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.containerFragments) as NavHostFragment
        navController = navHostFragment.navController

    }
    override fun onDestroy() {
        super.onDestroy()

    }

}