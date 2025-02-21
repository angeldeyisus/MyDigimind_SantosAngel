package santos.angel.mydigimind_205750.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import santos.angel.mydigimind_205750.R
import santos.angel.mydigimind_205750.Task
import santos.angel.mydigimind_205750.databinding.FragmentDashboardBinding
import santos.angel.mydigimind_205750.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Calendar

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(owner = this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container,false)

        root.findViewById<TextView>(R.id.btn_time).setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                root.findViewById<TextView>(R.id.btn_time).text = SimpleDateFormat("HH:mm").format(cal.time)
            }

            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                true).show()
        }

        root.findViewById<TextView>(R.id.btn_save).setOnClickListener {
            var title = root.findViewById<TextView>(R.id.et_task).text.toString()
            var time = root.findViewById<TextView>(R.id.btn_time).text.toString()
            var days = ArrayList<String>()

            if(root.findViewById<CheckBox>(R.id.monday).isChecked)
                days.add("Monday")
            if(root.findViewById<CheckBox>(R.id.tuesday).isChecked)
                days.add("Tuesday")
            if(root.findViewById<CheckBox>(R.id.wednesday).isChecked)
                days.add("Wednesday")
            if(root.findViewById<CheckBox>(R.id.thursday).isChecked)
                days.add("Thursday")
            if(root.findViewById<CheckBox>(R.id.friday).isChecked)
                days.add("Friday")
            if(root.findViewById<CheckBox>(R.id.saturday).isChecked)
                days.add("Saturday")
            if(root.findViewById<CheckBox>(R.id.sunday).isChecked)
                days.add("Sunday")

            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()

        }

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {


        })

        return root



    }
}