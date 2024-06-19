package com.toscano.proyecto1.ui.activities


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
import com.toscano.proyecto1.databinding.ActivityBiometricBinding
import java.util.concurrent.Executor


class BiometricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiometricBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var biometricManager: BiometricManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        initListeners()

    }

    private fun initListeners(){
        binding.imageFinger.setOnClickListener {
            initBiometric()
        }
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
}