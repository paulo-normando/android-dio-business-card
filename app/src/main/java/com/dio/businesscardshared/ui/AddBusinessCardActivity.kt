package com.elton.businesscardshared.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.elton.businesscardshared.App
import com.elton.businesscardshared.R
import com.elton.businesscardshared.data.BusinessCard
import com.elton.businesscardshared.databinding.ActivityAddBusinessCardBinding
import yuku.ambilwarna.AmbilWarnaDialog

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }
    private var mDefaultColor = -0x100

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertListeners()
    }

    private fun setColorPicker() {
        val dialog = AmbilWarnaDialog(this@AddBusinessCardActivity, mDefaultColor,
            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                    binding.btnCorCard.setColorFilter(color)
                    mDefaultColor = color
                }

                override fun onCancel(dialog: AmbilWarnaDialog) {
                    Toast.makeText(applicationContext, "cancel", Toast.LENGTH_SHORT).show()
                }
            })
        dialog.show()
    }

    private fun insertListeners() {
        //Color
        binding.btnCorCard.setOnClickListener { setColorPicker() }

        binding.btnConfirmar.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.txtInpLayNome.editText?.text.toString(),
                empresa = binding.txtInpLayEmpresa.editText?.text.toString(),
                telefone = binding.txtInpLayTelefone.editText?.text.toString(),
                email = binding.txtInpLayEmail.editText?.text.toString(),
                fundoPersonalizado = mDefaultColor
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.imgBtnClose.setOnClickListener { finish() }
    }
}
