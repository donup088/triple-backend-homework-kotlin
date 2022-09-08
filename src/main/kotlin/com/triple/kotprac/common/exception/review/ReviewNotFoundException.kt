package com.triple.kotprac.common.exception.review

import com.triple.kotprac.common.exception.EntityNotFoundException

class ReviewNotFoundException : EntityNotFoundException("없는 리뷰입니다.")