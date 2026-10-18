package com.example.nadini_3tib.pertemuan6

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nadini_3tib.R

class SatuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_satu,
            container,
            false
        )

        // Mengambil username dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences(
            "user_pref",
            Context.MODE_PRIVATE
        )

        val username = sharedPref.getString(
            "username",
            "Pengguna"
        ) ?: "Pengguna"

        // Menampilkan username pada Fragment
        val txtUsername = view.findViewById<TextView>(
            R.id.txtUsernameFragment
        )

        txtUsername.text = "Halo, $username!"

        return view
    }
}
