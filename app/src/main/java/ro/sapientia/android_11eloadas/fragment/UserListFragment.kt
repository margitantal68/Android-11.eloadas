package ro.sapientia.android_11eloadas.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ro.sapientia.android_11eloadas.R
import ro.sapientia.android_11eloadas.repository.TrackerRepository
import ro.sapientia.android_11eloadas.viewmodel.UserListViewModel
import ro.sapientia.android_11eloadas.viewmodel.UserListViewModelFactory

class UserListFragment : Fragment() {
    private lateinit var userListViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UserListViewModelFactory(TrackerRepository())
        userListViewModel = ViewModelProvider(this, factory).get(UserListViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.button2)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_loginFragment)
        }
        val editText: EditText = view.findViewById(R.id.editTextTextMultiLine)
        userListViewModel.readUsers()
        userListViewModel.userList.observe(viewLifecycleOwner) {
            val userList = userListViewModel.userList.value
            editText.setText("User list size ${userList!!.size}")
            Log.i("xxx", userList.toString())
        }
    }
}