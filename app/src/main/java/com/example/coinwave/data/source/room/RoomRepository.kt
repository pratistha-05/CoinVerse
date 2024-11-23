package com.example.coinwave.data.source.room

import com.example.coinwave.data.service.model.database.Coin
import com.example.coinwave.data.service.model.database.FavouriteDao

class RoomRepository(val dao: FavouriteDao){

  val subscribers = dao.getAllFavourites()

  suspend fun insertFavourite(coin: Coin){
    dao.insertFavourite(coin)
  }

  suspend fun deleteFavourite(coin: Coin){
    dao.deleteFavourite(coin)
  }

}