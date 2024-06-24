package com.toscano.proyecto1.ui.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.toscano.proyecto1.databinding.ActivityBiometricBinding
import com.toscano.proyecto1.ui.entities.DataStoreEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor


class BiometricActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

    private lateinit var binding: ActivityBiometricBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var biometricManager: BiometricManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Creamos una variable para el inicio de la trnasicion
        val splash = installSplashScreen()
        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()


        //Duracion de la transicion
        Thread.sleep(2000)
        splash.setKeepOnScreenCondition{false}

    }

    private fun initListeners(){
        binding.imageFinger.setOnClickListener {
            initDataStoreSave(DataStoreEntity("Juan", true))
            Log.d("TAG", initDataStoreGet().toString())
            initBiometric()
            startActivity(Intent(this@BiometricActivity, RecyclerActivity::class.java))
        }
    }

    private fun initVariables(){
        //Ejecucion y Verificacion

        biometricManager = BiometricManager.from(this)
        executor = ContextCompat.getMainExecutor(this)

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Proyecto 1")
            .setSubtitle("Ingrese su huella digital")
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .setNegativeButtonText("Cancelar")
            .build()

        biometricPrompt = BiometricPrompt(this, executor , object : BiometricPrompt.AuthenticationCallback(){

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }
        } )
    }

    private fun initBiometric(){

        val execute = biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)

        when (execute) {
            BiometricManager.BIOMETRIC_SUCCESS ->{
                biometricPrompt.authenticate(promptInfo)
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->{Log.e("MY_APP_TAG", "No biometric features available on this device.")}
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->{Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")}
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
                startActivity(enrollIntent)
            }
        }
    }

    /*
    private fun initDataStoreGetString(): String{

        var get = ""
        lifecycleScope.launch(Dispatchers.Main){
            //Recuperar
            val set = withContext(Dispatchers.IO){
                dataStore.data.map { preference ->
                    preference[stringPreferencesKey("user")] ?: ""
                }
            }
            get = set.first()
            /*
            set.collect{
                get = it
            }

             */
        }
        return get
    }

    private fun initDataStoreGetBoolean(): Boolean{

        var get = false
        lifecycleScope.launch(Dispatchers.Main){
            //Recuperar
            val set = withContext(Dispatchers.IO){
                dataStore.data.map { preference ->
                    preference[booleanPreferencesKey("active")] ?: false
                }
            }
            get = set.first()
            /*
            set.collect{
                get = it
            }

             */
        }
        return get
    }
     */

    private fun initDataStoreGet(): DataStoreEntity{

        var get = DataStoreEntity()
        lifecycleScope.launch(Dispatchers.Main){
            //Recuperar
            val set = withContext(Dispatchers.IO){
                dataStore.data.map {preference ->
                    DataStoreEntity(
                        preference[stringPreferencesKey("user")] ?: "",
                        preference[booleanPreferencesKey("active")] ?: false
                    )
                }
            }
            set.first()
            /*
            set.collect{
                Log.d("TAG", it.toString())
            }
             */

        }
        return get!!
    }

    private fun initDataStoreSave(user: DataStoreEntity){

        lifecycleScope.launch(Dispatchers.IO){
            //Guardar
            dataStore.edit { preference ->
                preference[booleanPreferencesKey("active")] = user.active
                preference[stringPreferencesKey("user")] = user.name
            }
        }
    }

    /*
    private fun initDataStoreSave(user: String, active: Boolean){

        lifecycleScope.launch(Dispatchers.IO){
            //Guardar
            dataStore.edit { preference ->
                preference[booleanPreferencesKey("active")] = active
                preference[stringPreferencesKey("user")] = user
            }
        }
    }
     */
}