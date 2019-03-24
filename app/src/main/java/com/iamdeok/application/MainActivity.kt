package com.iamdeok.application

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        //버튼 클릭 이벤트
        resultButton.setOnClickListener {

            //anko라이브러리를 사용하지 않을 때 코드
            /*
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("weight", weightEditText.text.toString())
            intent.putExtra("height", heightEditText.text.toString())
            startActivity(intent)
            */

            //엑티비티 변환전 마지막 데이터를 저장
            saveData(heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())

            //anko라이브러리를 사용할 때 코드
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }

    //마지막 입력데이터를 저장
    private fun saveData(height : Int, weight : Int){
        //PreferenceManager를 통해 Preference 객체를 가져옴
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //preference editor 객체를 가져옴
        var editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    //데이터 로드
    private fun loadData(){
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }

}
