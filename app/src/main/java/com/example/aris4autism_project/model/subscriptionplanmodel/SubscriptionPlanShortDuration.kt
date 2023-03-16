package com.example.aris4autism_project.model.subscriptionplanmodel


import com.google.gson.annotations.SerializedName

data class SubscriptionPlanShortDuration(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: Int,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("deleted_by")
    val deletedBy: Any,
    @SerializedName("description")
    val description: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("exchanged_price")
    val exchangedPrice: Int,
    @SerializedName("features")
    val features: List<SubscriptionPlanFeature>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("in_app_purchase_id")
    val inAppPurchaseId: Any,
    @SerializedName("is_fullpay_or_installment")
    val isFullpayOrInstallment: String,
    @SerializedName("is_trial_price")
    val isTrialPrice: String,
    @SerializedName("original_price")
    val originalPrice: Int,
    @SerializedName("plan_type")
    val planType: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("stripe_price_id")
    val stripePriceId: String,
    @SerializedName("stripe_product_id")
    val stripeProductId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("trial_period")
    val trialPeriod: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("updated_by")
    val updatedBy: Int,
    @SerializedName("uuid")
    val uuid: String
)
{

}