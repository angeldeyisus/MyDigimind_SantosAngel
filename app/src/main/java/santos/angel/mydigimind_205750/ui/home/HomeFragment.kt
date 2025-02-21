package santos.angel.mydigimind_205750.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import santos.angel.mydigimind_205750.R
import santos.angel.mydigimind_205750.Recordatorio
import santos.angel.mydigimind_205750.Task
import santos.angel.mydigimind_205750.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private var adaptador: AdaptadorTareas? = null

    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }
    private lateinit var homeViewModel: HomeViewModel


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if(first){
            fillTasks()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)
        root.findViewById<GridView>(R.id.gridView).adapter = adaptador

        return root
    }

    private fun fillTasks() {
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday", "Sunday"), "11:00"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"), "13:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"), "10:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"), "12:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"), "14:30"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"), "14:30"))


    }


    private class AdaptadorTareas: BaseAdapter{
        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Task>){
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view, null)

            var tv_title: TextView = vista.findViewById(R.id.tv_tittle)
            var tv_days: TextView = vista.findViewById(R.id.tv_days)
            var tv_time: TextView = vista.findViewById(R.id.tv_time)

            tv_title.setText(task.title)
            tv_days.setText(task.days.toString())
            tv_time.setText(task.time)

            return vista
        }


    }
}