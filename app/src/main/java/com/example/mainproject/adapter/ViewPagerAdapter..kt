import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproject.R
import com.example.mainproject.model.trending.TrendingResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trending_viewpager_layout.view.*

class ViewPagerAdapter(var list: List<TrendingResultsItem>) : RecyclerView.Adapter<ViewPagerAdapter.TrendingViewHolder>() {

    var onItemClick: ((user: TrendingResultsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.trending_viewpager_layout, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) =
        holder.bind(list[position])


    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: TrendingResultsItem) {
            itemView.apply {
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath).into(imgView)
                setOnClickListener {
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}