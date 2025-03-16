package org.yage.dict.star

/**
 * plain pojo, define the position of a word
 * @author beethoven99@126.com
 */
data class WordPosition(
    /**
     * 起始字节位置
     */
    var startPos: Int,
    /**
     * 字节长度
     */
    var length: Int
)
