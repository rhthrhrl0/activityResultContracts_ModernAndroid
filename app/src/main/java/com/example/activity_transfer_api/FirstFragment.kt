package com.example.activity_transfer_api

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.*
import com.example.activity_transfer_api.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding

    // 기본적으로 안드로이드에서 제공하는 클래스를 첫번쨰인자로 주면 됨.
    // 첫번째 인자에는 내가 수행할 작업에 대한 클래스 객체를 넘기면 된다.
    // 두번째 인자로는 람다식으로, 작업 수행을 위한 액티비티 화면에서 돌아올때에 대한 코드임.
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        //GetContent 클래스는 내가 수행한 작업의 결과물에 대한 uri를 리턴해준다.
        binding.imageView.setImageURI(it)
    }

    val getStartActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        activityResult.data?.let { intent ->   //널이 아닐때만 수행하겠다.
            intent.extras?.let { bundle ->
                Log.d("FirstFragment", "result: ${bundle.getString("data", "world")}")
            }
        }
    }

    //권한 요청에 대한 승인시
    val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
        }
    }

    //다중 권한 요청에 대한 승인시
    val requestMultiplePermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map -> //map은 권한의 이름 스트링과 그 권한의 부여 여부 불리언 값이 들어있음.
        if (map[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            Toast.makeText(requireContext(), "FINE_LOCATION: success", Toast.LENGTH_SHORT).show()
        }
        if (map[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            Toast.makeText(requireContext(), "COARSE_LOCATION: success", Toast.LENGTH_SHORT).show()
        }
    }

    // 프래그먼트가 생성될때 실행되는 생명주기 함수임.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment 한 것을 리턴해야함. 프래그먼트의 UI가 없다면 널리턴하면 됨.
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // onCreateView에서는 레이아웃의 뷰에 접근하면 안됨. onCreateView자체가 인플레이트하는 과정중이므로 불완전함.
        // 그래서 인플레이트한 레이아웃의 뷰에 대해서는 여기에서 접근해야 함.

        binding.button.setOnClickListener {
            // 내가 결과물로 가져올 데이터에 대한 MIME 타입을 인풋으로 지정해주면 된다.
            //getContent.launch("image/*")

//            Intent(context, ResultActivity::class.java).apply {
//                getStartActivityForResult.launch(this)
//            }

            //위치정보에 대한 권한 요청
            //requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)

            requestMultiplePermission.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}