package co.ejjv.ccms_mobile.module.menu_list_pl.listpldet

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.ejjv.ccms_mobile.model.response.gson.ListPLImage
import com.bumptech.glide.Glide
import com.ejjv.ccms_mobile.R
import kotlinx.android.synthetic.main.item_pl_det.view.*
import kotlinx.android.synthetic.main.item_pl_det_image.view.*
import com.ceylonlabs.imageviewpopup.ImagePopup


class ListPLDetAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val VIEW_TYPE_TEXT = 0
    val VIEW_TYPE_IMAGE = 1

    private lateinit var mContext: Context
    private lateinit var mListPLDet: ArrayList<HashMap<String, String>>
    private lateinit var mListPLDetImage: ArrayList<ListPLImage>

    constructor(
        context: Context,
        mListPLDet: ArrayList<HashMap<String, String>>,
        mListPLDetImage: ArrayList<ListPLImage>
    ) : this() {
        this.mListPLDet = mListPLDet
        this.mListPLDetImage = mListPLDetImage
        this.mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE_TEXT) {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pl_det, parent, false)
            return TextHolder(view);
        }

        if (viewType == VIEW_TYPE_IMAGE) {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pl_det_image, parent, false)
            return ImageHolder(view);
        }

        return null!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextHolder) {
            val plDetHash = mListPLDet[position]
            plDetHash.forEach { (key, value) ->
                holder.tvLabel.text = key
                holder.tvData.text = value
            }
        }

        if (holder is ImageHolder) {
            val plDetImage = mListPLDetImage[position - mListPLDet.size]
            Glide.with(mContext).load(plDetImage.urlFile).into(holder.ivFoto)

            val imagePopup = ImagePopup(mContext)
            imagePopup.initiatePopupWithPicasso(plDetImage.urlFile);
            holder.ivFoto.setOnClickListener(){
                imagePopup.viewPopup();
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position < mListPLDet.size) {
            return VIEW_TYPE_TEXT
        }

        return if (position - mListPLDet.size < mListPLDetImage.size) {
            VIEW_TYPE_IMAGE
        } else -1
    }

    class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvLabel: TextView = itemView.tvLabel
        internal var tvData: TextView = itemView.tvData
    }

    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ivFoto: ImageView = itemView.ivFoto
    }

    override fun getItemCount() = mListPLDet.size + mListPLDetImage.size
}
