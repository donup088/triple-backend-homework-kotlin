package com.triple.kotprac.auth.security

import com.triple.kotprac.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class CustomUserDetails(
        val authorities: MutableList<SimpleGrantedAuthority>,
        val user: User
) : UserDetails {
    companion object {
        fun create(user: User): CustomUserDetails {
            val authorities = Collections.singletonList(SimpleGrantedAuthority("ROLE_USER"))
            return CustomUserDetails(authorities = authorities, user = user)
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.id.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}