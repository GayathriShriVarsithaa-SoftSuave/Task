import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.task_2.R


class RestApiDemoFragment : Fragment(R.layout.fragment_rest_api_demo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGet = view.findViewById<Button>(R.id.btnGet)
        val btnPost = view.findViewById<Button>(R.id.btnPost)
        val tvResult = view.findViewById<TextView>(R.id.tvResult)

        btnGet.setOnClickListener {
            tvResult.text = "GET API clicked"
        }

        btnPost.setOnClickListener {
            tvResult.text = "POST API clicked"
        }
    }
}
