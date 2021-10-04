package com.evangers.stockfield.domain.throwables


class UnknownException(val msg: String = "알수없는 에러가 발생했습니다.") : RuntimeException()
class ServerIsDownException(val noticeMessage: String) : RuntimeException()
class NoTokenException(override val message: String) : RuntimeException()