package jafari.alireza.contacts.utils


sealed class DirectionParamName {

    class DetailsParams(val id: Long) : DirectionParamName()

}