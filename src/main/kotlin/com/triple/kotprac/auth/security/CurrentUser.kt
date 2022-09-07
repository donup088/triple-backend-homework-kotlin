package com.triple.kotprac.auth.security

import org.springframework.security.core.annotation.AuthenticationPrincipal

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@AuthenticationPrincipal
annotation class CurrentUser {
}