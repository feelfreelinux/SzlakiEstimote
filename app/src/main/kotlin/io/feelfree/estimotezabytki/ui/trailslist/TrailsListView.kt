package io.feelfree.estimotezabytki.ui.trailslist

import io.feelfree.estimotezabytki.base.BaseView
import io.feelfree.estimotezabytki.models.pojo.TrailResponse

interface TrailsListView : BaseView {
    fun showTrailsList(trails : List<TrailResponse>)
}