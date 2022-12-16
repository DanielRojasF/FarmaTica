package com.farmatica

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Myadapter( private val productoList: ArrayList<Producto>): RecyclerView.Adapter<Myadapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.Nombre)
        val tvDescripcion: TextView = itemView.findViewById(R.id.Descripcion)
        val tvPrecio: TextView = itemView.findViewById(R.id.Precio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.lista_producto, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = productoList[position].producto
        holder.tvDescripcion.text = productoList[position].descripcion
        holder.tvPrecio.text = productoList[position].precio.toString()
    }

    override fun getItemCount(): Int {
       return productoList.size
    }
}