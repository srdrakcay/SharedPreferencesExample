package com.serdar.sharedprefencesexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.serdar.sharedprefencesexample.databinding.ScreenHomeBinding
import com.serdar.sharedprefencesexample.databinding.ScreenHomeBinding.inflate
import kotlinx.android.synthetic.main.screen_home.*

class HomeScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_home)

        // SheredPreferences tanımlanır
        val sp = getSharedPreferences("Entry Information", Context.MODE_PRIVATE)
        val user= sp.getString("userName", "Need To User Name")
        val pas= sp.getString("password", "Need To User Password")

        // tanımlanan kullanıcı adı ve şifre TextView üzerine yazılır
        textViewCikti.text="User Name : $user , Password : $pas"

        button2.setOnClickListener {

            /*Uygulamadan çıkış yapıldığı taktirde mevcut kullanıcı adı ve şifre silinir.
            Böylelikle uygulama kapatılıp açıldığında tekrar LoginScreen ekranı açılır.
            */
           val editor = sp.edit()
           editor.remove("userName")
           editor.remove("password")
           editor.commit()

           val intent = Intent (this, MainActivity::class.java)
           finish()
           startActivity(intent)

       }
    }

}