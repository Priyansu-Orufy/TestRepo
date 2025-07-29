package com.example.testapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.Model.Item
import com.example.testapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    init {
        fetchItems()
    }

    fun fetchItems() {
        _items.value = listOf(
            Item(
                R.drawable.banner1,
                "Welcome to Mega Shopping Bonanza",
                "Shop the latest fashion, electronics, and more.Get 50% off on your first purchase. Limited time offer only Hurry before it’s gone!"
            ),
            Item(
                R.drawable.banner2,
                "Summer Sale is Live Now",
                "Beat the heat with the hottest discounts of the season , From beachwear to gadgets, everything is on sale , Offer valid until stocks last!"
            ),
            Item(
                R.drawable.banner3,
                "Brand New Arrivals Just Dropped",
                "Discover a wide range of new arrivals every week, From trending outfits to smart accessories, explore now ,Fresh picks added daily"
            ),
            Item(
                R.drawable.banner4,
                "Refer and Earn Amazing Rewards",
                "Invite your friends and earn exclusive cashback on every referral.The more you refer, the more you earn.Track your rewards in your wallet section."
            ),

            Item(
                R.drawable.banner1,
                "Welcome to Mega Shopping Bonanza",
                "Shop the latest fashion, electronics, and more.Get 50% off on your first purchase. Limited time offer only Hurry before it’s gone!"
            ),
            Item(
                R.drawable.banner2,
                "Summer Sale is Live Now",
                "Beat the heat with the hottest discounts of the season , From beachwear to gadgets, everything is on sale , Offer valid until stocks last!"
            ),
            Item(
                R.drawable.banner3,
                "Brand New Arrivals Just Dropped",
                "Discover a wide range of new arrivals every week, From trending outfits to smart accessories, explore now ,Fresh picks added daily"
            ),
            Item(
                R.drawable.banner4,
                "Refer and Earn Amazing Rewards",
                "Invite your friends and earn exclusive cashback on every referral.The more you refer, the more you earn.Track your rewards in your wallet section."
            )


        )
    }
}