package com.example.arara.utils

import com.google.firebase.Timestamp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

object TimestampSerializer : KSerializer<Timestamp> {
  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Timestamp") {
    element<Long>("seconds")
    element<Int>("nanoseconds")
  }
  
  override fun serialize(encoder: Encoder, value: Timestamp) {
    val composite = encoder.beginStructure(descriptor)
    composite.encodeLongElement(descriptor, 0, value.seconds)
    composite.encodeIntElement(descriptor, 1, value.nanoseconds)
    composite.endStructure(descriptor)
  }
  
  override fun deserialize(decoder: Decoder): Timestamp {
    val dec: CompositeDecoder = decoder.beginStructure(descriptor)
    var seconds: Long? = null
    var nanoseconds: Int? = null
    
    loop@ while (true) {
      when (val index = dec.decodeElementIndex(descriptor)) {
        CompositeDecoder.DECODE_DONE -> break@loop
        0 -> seconds = dec.decodeLongElement(descriptor, 0)
        1 -> nanoseconds = dec.decodeIntElement(descriptor, 1)
        else -> throw IllegalStateException("Unexpected index: $index")
      }
    }
    
    dec.endStructure(descriptor)
    return Timestamp(seconds ?: throw IllegalStateException("Seconds are missing"), nanoseconds ?: throw IllegalStateException("Nanoseconds are missing"))
  }
}