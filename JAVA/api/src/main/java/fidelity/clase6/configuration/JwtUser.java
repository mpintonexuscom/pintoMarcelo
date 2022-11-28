package fidelity.clase6.configuration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtUser implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return this.authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
