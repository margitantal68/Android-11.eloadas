package ro.sapientia.android_11eloadas.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ro.sapientia.android_11eloadas.R
import ro.sapientia.android_11eloadas.model.LoginRequest
import ro.sapientia.android_11eloadas.repository.TrackerRepository
import ro.sapientia.android_11eloadas.util.Constants
import ro.sapientia.android_11eloadas.viewmodel.LoginViewModel
import ro.sapientia.android_11eloadas.viewmodel.LoginViewModelFactory


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), TrackerRepository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText1: EditText = view.findViewById(R.id.edittext_name_login_fragment)
        val editText2: EditText = view.findViewById(R.id.edittext_password_login_fragment)
        val button: Button = view.findViewById(R.id.button_login_fragment)

        val prefs = requireActivity().applicationContext.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
        Log.i("xxx-LoginFragment-oVC", "email: " + prefs.getString("email", ""))
        Log.i("xxx-LoginFragment-oVC", "password: " + prefs.getString("password", ""))

        editText1.setText(prefs.getString("email", ""))
        editText1.setText(prefs.getString("password", ""))

        button.setOnClickListener {
            val email = editText1.text.toString().trim()
            val password = editText2.text.toString().trim()
            if( email.isEmpty() || password.isEmpty()){
                Toast.makeText(this.requireContext(),"Please, enter your email and password", Toast.LENGTH_LONG).show()
            } else {
                try {
                    loginViewModel.login(LoginRequest(password, email))
                }catch(e: Exception){
                    Log.i("xxx", "LoginViewModel - exception: ${e.toString()}")
                    Toast.makeText(context, "Login failed: " + e.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
        loginViewModel.token.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_loginFragment_to_listFragment)
        }

    }
}