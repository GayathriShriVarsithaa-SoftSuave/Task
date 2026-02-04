import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.task_2.R
import androidx.lifecycle.lifecycleScope
import com.example.task_2.model.PostRequest
import com.example.task_2.network.RetrofitInstance
import kotlinx.coroutines.launch
class RestApiDemoFragment : Fragment(R.layout.fragment_rest_api_demo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGet = view.findViewById<Button>(R.id.btnGet)
        val btnPost = view.findViewById<Button>(R.id.btnPost)
        val tvResult = view.findViewById<TextView>(R.id.tvResult)

        btnGet.setOnClickListener {
            //tvResult.text = "GET API clicked"
            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.api.getPost()
                    if (response.isSuccessful) {
                        val post = response.body()
                        tvResult.text = post?.title ?: "No data"
                    } else {
                        tvResult.text = "Error: ${response.code()}"
                    }
                } catch (e: Exception) {
                    tvResult.text = "Exception: ${e.localizedMessage}"
                }
            }
        }

        btnPost.setOnClickListener {
            //tvResult.text = "POST API clicked"
            val postRequest = PostRequest(
                userId = 90,
                title = "Android POST API",
                body = "Learning Retrofit with Coroutines"
            )

            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.api.createPost(postRequest)

                    if (response.isSuccessful) {
                        tvResult.text = "POST Success:\n${response.body()}"
                    } else {
                        tvResult.text = "POST Failed: ${response.code()}"
                    }

                } catch (e: Exception) {
                    tvResult.text = "Error: ${e.message}"
                }
            }
        }
    }
}
