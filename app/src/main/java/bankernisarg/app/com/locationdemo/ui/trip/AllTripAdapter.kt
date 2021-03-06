package bankernisarg.app.com.locationdemo.ui.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bankernisarg.app.com.locationdemo.R
import bankernisarg.app.com.locationdemo.data.callback.RecyclerViewClickListener
import bankernisarg.app.com.locationdemo.data.db.entities.Trip
import bankernisarg.app.com.locationdemo.databinding.ItemTripBinding

class AllTripAdapter(
    private val trips: List<Trip>,
    private val listener: RecyclerViewClickListener,
    private val context: AllTripFragment
) : RecyclerView.Adapter<AllTripAdapter.MoviesViewHolder>() {

    override fun getItemCount() = trips.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_trip,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemTripBinding.trip = trips[position]
        holder.itemTripBinding.btnStartTrip.setOnClickListener {
            context.setTripOnOff(trips[position].id)
            this.notifyDataSetChanged()
            listener.onRecyclerViewItemClick(it, trips[position])
        }
        holder.itemTripBinding.btnEndTrip.setOnClickListener {
            context.setTripOnOff(-1)
            this.notifyDataSetChanged()
            listener.onRecyclerViewItemClick(it, trips[position])
        }
        holder.itemTripBinding.rootCard.setOnClickListener {
            listener.onRecyclerViewItemClick(it, trips[position])
        }

        if (context.getTripOnOff() == trips[position].id){
            holder.itemTripBinding.btnStartTrip.isEnabled = false
            holder.itemTripBinding.btnEndTrip.isEnabled = true
        }else if (context.getTripOnOff() == -1){
            holder.itemTripBinding.btnStartTrip.isEnabled = true
            holder.itemTripBinding.btnEndTrip.isEnabled = true
        }

    }


    inner class MoviesViewHolder(
        val itemTripBinding: ItemTripBinding
    ) : RecyclerView.ViewHolder(itemTripBinding.root)
}