package com.example.superheroapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuperHeroResponse(
    val id: String,
    val name: String,
    @SerializedName("powerstats")
    val powerStats: PowerStats,
    val biography: Biography,
    val appearance: Appearance,
    val work: Work,
    val connections: Connections,
    val image: HeroImage
): Parcelable

@Parcelize
data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
): Parcelable

@Parcelize
data class Biography(
    @SerializedName("full-name")
    val fullName: String,
    @SerializedName("alter-egos")
    val alterEgos: String,
    val aliases: List<String>,
    @SerializedName("place-of-birth")
    val placeOfBirth: String,
    @SerializedName("first-appearance")
    val firstAppearance: String,
    val publisher: String,
    val alignment: String
): Parcelable

@Parcelize
data class Appearance(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    @SerializedName("eye-color")
    val eyeColor: String,
    @SerializedName("hair-color")
    val hairColor:String
): Parcelable

@Parcelize
data class Work(
    val occupation: String,
    val base: String
): Parcelable

@Parcelize
data class Connections(
    @SerializedName("group-affiliation")
    val groupAffiliation: String,
    val relatives: String
): Parcelable

@Parcelize
data class HeroImage(
    val url: String
): Parcelable