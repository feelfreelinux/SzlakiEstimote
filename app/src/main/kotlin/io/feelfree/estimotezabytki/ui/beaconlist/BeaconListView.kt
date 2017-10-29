package io.feelfree.estimotezabytki.ui.beaconlist

import io.feelfree.estimotezabytki.base.BaseView
import io.feelfree.estimotezabytki.models.pojo.BeaconResponse

interface BeaconListView : BaseView {
    fun showBeacons(items : List<BeaconResponse>)
}