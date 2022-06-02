package com.serdar.sharedprefencesexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.serdar.sharedprefencesexample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        //SheredPrefences başlatmak için yazılan kod
        val sp = getSharedPreferences("Entry Information",Context.MODE_PRIVATE)


        //Daha önce uygulamaya girilmiş ise Kullanıcı adı ve şifre otamatik olarak tanımlanır
        val ouser= sp.getString("userName", "Need To User Name")
        val opas= sp.getString("password", "Need To User Password")

        /* eğer otamatik tanımlanan kullanıcı adı ve şifre data da mevcut ise direk uygulamanın
        HomeScreen ekranı açılacaktır.
        * */
        if (ouser=="Serdar" && opas =="123"){
            val intent = Intent (this, HomeScreen::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            //kullanıcı adı ve şifre editText'ten alınır
            val user = userName.text.toString()
            val pas = password.text.toString()

            //Eğer kullanıcı adı ve şifre belirtildiği gibi ise data ya kaydedilir ve HomeScreen ekranına giriş yapılır

            if (user== "Serdar" && pas == "123"){

                val editor = sp.edit()
                editor.putString("userName",user)
                editor.putString("password",pas)
                editor.commit()
                val intent = Intent (this, HomeScreen::class.java)
                startActivity(intent)

               //backpress yani eğer uygulama HomeScreen ekranına başarılı şekilde girilirse geri tuşundan LoginScreen silinir
                finish()

                //kullanıcı adı ve şifre yanlış ise toast mesaj yazar
            }else{
                Toast.makeText(this, "İncorrect User", Toast.LENGTH_SHORT).show()
            }
            


        }
    }

}