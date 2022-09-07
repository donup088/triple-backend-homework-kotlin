package com.triple.kotprac.common.exception.user

import com.triple.kotprac.common.exception.BusinessException

class DuplicatedNickNameException : BusinessException("중복된 닉네임입니다.") {

}